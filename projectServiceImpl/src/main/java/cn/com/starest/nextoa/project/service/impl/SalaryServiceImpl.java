package cn.com.starest.nextoa.project.service.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.dto.PageQueryParameter;
import cn.com.starest.nextoa.project.domain.model.*;
import cn.com.starest.nextoa.project.domain.request.SalaryAggregationQueryRequest;
import cn.com.starest.nextoa.project.domain.request.SalaryQueryRequest;
import cn.com.starest.nextoa.project.domain.request.SaveSalaryRequest;
import cn.com.starest.nextoa.project.exception.SalaryException;
import cn.com.starest.nextoa.project.exception.SalaryNotFoundException;
import cn.com.starest.nextoa.project.repository.*;
import cn.com.starest.nextoa.project.service.ModulePermissionService;
import cn.com.starest.nextoa.project.service.SalaryService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author dz
 */
@Service
public class SalaryServiceImpl implements SalaryService {

	private static final Log logger = LogFactory.getLog(SalaryServiceImpl.class);

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private BizDepartmentRepository bizDepartmentRepository;

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private ModulePermissionService modulePermissionService;

	@Autowired
	private SalaryRepository salaryRepository;

	@Autowired
	private SalaryAggregationRepository salaryAggregationRepository;

	@Autowired
	private SalaryImportHistoryRepository salaryImportHistoryRepository;

	@Override
	public ModuleActions.ModuleAction[] resolveGrantedActions(User byWho) {
		return modulePermissionService.listGrantedActions(Module.SALARY, byWho).toArray(ModuleActions.EMPTY_ACTIONS);
	}

	@Override
	public ModuleActions.ModuleAction[] resolveGrantedActions(Salary salary, User byWho) {
		ModuleActions.ModuleAction[] grantedActions = modulePermissionService.listGrantedActions(Module.SALARY, byWho)
																			 .toArray(ModuleActions.EMPTY_ACTIONS);

		return grantedActions;
	}

	@Override
	public Salary createSalary(SaveSalaryRequest request, User byWho) {
		Salary salary = new Salary();

		BeanUtils.copyProperties(request, salary);
		handleSalaryReference(request, salary);

		salary = salaryRepository.save(salary);

		updateSalaryAggregation(salary.getCompany(), salary.getYear(), salary.getMonth());

		return salary;
	}

	@Override
	public Salary updateSalary(String id, SaveSalaryRequest request, User byWho) {
		Salary salary = salaryRepository.findById(id);
		if (salary == null) {
			throw new SalaryNotFoundException("没有找到对应的数据.");
		}

		BeanUtils.copyProperties(request, salary);
		handleSalaryReference(request, salary);

		salary = salaryRepository.save(salary);

		updateSalaryAggregation(salary.getCompany(), salary.getYear(), salary.getMonth());

		return salary;
	}

	@Override
	public void deleteSalaryById(String id, User byWho) {
		Salary salary = salaryRepository.findById(id);
		if (salary == null) {
			throw new SalaryNotFoundException("没有找到对应的数据.");
		}

		salaryRepository.delete(salary);

		updateSalaryAggregation(salary.getCompany(), salary.getYear(), salary.getMonth());
	}

	@Override
	public Salary findSalaryById(String id, User byWho) {
		return salaryRepository.findById(id);
	}

	@Override
	public Page<Salary> listSalaries(SalaryQueryRequest request, User byWho) {
		return salaryRepository.queryPage(request);
	}

	@Override
	public BigDecimal calculateBizDepartmentSalary(String id, int year, int month) {
		BizDepartment bizDepartment = bizDepartmentRepository.findById(id);
		if (bizDepartment == null) {
			return BigDecimal.ZERO;
		}
		return salaryRepository.calculateTotalPay(bizDepartment, year, month);
	}

	@Override
	public BigDecimal calculateProjectSalary(String id, int year, int month) {
		Project project = projectRepository.findById(id);
		if (project == null) {
			return BigDecimal.ZERO;
		}
		return salaryRepository.calculateTotalPay(project, year, month);
	}

	@Override
	public Page<SalaryImportHistory> listSalaryImportHistories(PageQueryParameter request, User byWho) {
		return salaryImportHistoryRepository.findAll(new PageRequest(request.getStart(),
																	 request.getLimit(),
																	 new Sort(Sort.Direction.DESC, "createdAt")));
	}

	@Override
	public Page<SalaryAggregation> listSalaryByAggregation(SalaryAggregationQueryRequest request,
														   SalaryAggregation.AggregationType aggregationType,
														   User byWho) {
		return salaryAggregationRepository.queryPage(request, aggregationType);
	}


	@Override
	public SalaryImportHistory importSalaries(List<SaveSalaryRequest> requestList, User byWho) {
		//validate the import
		for (int i = 0; i < requestList.size(); i++) {
			validateImportSalary(requestList.get(i), i + 1);
		}

		//check the project & bizDepartment
		Map<String,Company> companyMap = new HashMap<>();
		Map<String,Project> projectMap = new HashMap<>();
		Map<String,BizDepartment> bizDepartmentMap = new HashMap<>();
		requestList.forEach(request -> {
			Company company = companyMap.get(request.getCompanyName());
			if (company == null) {
				company = companyRepository.findFirstByName(request.getCompanyName());
				if (company == null) {
					throw new SalaryException(String.format("无效的公司名「%s」", request.getCompanyName()));
				}
				companyMap.put(request.getCompanyName(), company);
			}

			if (!StringUtils.isEmpty(request.getProjectName())) {
				Project project = projectMap.get(request.getProjectName());
				if (project == null) {
					project = projectRepository.findFirstByName(request.getProjectName());
					if (project == null) {
						throw new SalaryException(String.format("无效的项目名「%s」", request.getProjectName()));
					}
					projectMap.put(request.getProjectName(), project);
				}
			}
			else if (!StringUtils.isEmpty(request.getBizDepartmentName())) {
				BizDepartment bizDepartment = bizDepartmentMap.get(request.getBizDepartmentName());
				if (bizDepartment == null) {
					bizDepartment = bizDepartmentRepository.findFirstByName(request.getBizDepartmentName());
					if (bizDepartment == null) {
						throw new SalaryException(String.format("无效的部门名「%s」", request.getBizDepartmentName()));
					}
					bizDepartmentMap.put(request.getBizDepartmentName(), bizDepartment);
				}
			}

		});


		List<Salary> salaryList = requestList.stream().map(request -> {
			int year = request.getYear();
			int month = request.getMonth();

			Company company = companyMap.get(request.getCompanyName());

			String projectName = request.getProjectName();
			String bizDepartmentName = request.getBizDepartmentName();
			String employee = request.getEmployee();

			Salary salary = null;


			if (!StringUtils.isEmpty(projectName)) {
				Project project = projectMap.get(projectName);
				salary = salaryRepository.findFirstByYearAndMonthAndCompanyAndProjectAndEmployee(year,
																								 month,
																								 company,
																								 project,
																								 employee);
			}
			else if (!StringUtils.isEmpty(bizDepartmentName)) {
				BizDepartment bizDepartment = bizDepartmentMap.get(bizDepartmentName);
				salary = salaryRepository.findFirstByYearAndMonthAndCompanyAndBizDepartmentAndEmployee(year,
																									   month,
																									   company,
																									   bizDepartment,
																									   employee);
			}

			if (salary == null) {
				salary = new Salary();
				salary.setYear(year);
				salary.setMonth(month);
				salary.setCompany(company);
				salary.setProject(projectMap.get(request.getProjectName()));
				salary.setBizDepartment(bizDepartmentMap.get(request.getBizDepartmentName()));
				salary.setEmployee(employee);
			}

			BeanUtils.copyProperties(request, salary);


			return salary;
		}).collect(Collectors.toList());

		salaryRepository.save(salaryList);

		SalaryImportHistory salaryImportHistory = new SalaryImportHistory();
		SalaryImportHistory.onCreate(salaryImportHistory, byWho);
		SalaryImportHistory result = salaryImportHistoryRepository.save(salaryImportHistory);

		Set<SalaryAggregation.SalaryAggregationCriteria> criteriaSet = new HashSet<>();

		salaryList.forEach(salary -> {
			salary.setSalaryImportHistory(result);
			salaryRepository.save(salary);
			criteriaSet.add(SalaryAggregation.SalaryAggregationCriteria.from(salary));
		});

		criteriaSet.stream().forEach(criteria -> {
			updateSalaryAggregation(criteria.getCompany(), criteria.getYear(), criteria.getMonth());
		});

		return result;
	}

	private void validateImportSalary(SaveSalaryRequest request, int index) {
		if (StringUtils.isEmpty(request.getCompanyName())) {
			throw new SalaryException(String.format("导入的工资数据第[%d]行: 公司不能为空", index));
		}
		if (request.getYear() < 2012) {
			throw new SalaryException(String.format("导入的工资数据第[%d]行:年份不能小于2012年", index));
		}
		if (request.getMonth() < 1 || request.getMonth() > 12) {
			throw new SalaryException(String.format("导入的工资数据第[%d]行:无效的月份", index));
		}
		if (StringUtils.isEmpty(request.getProjectName()) && StringUtils.isEmpty(request.getBizDepartmentName())) {
			throw new SalaryException(String.format("导入的工资数据第[%d]行: 项目和部门均为空", index));
		}

		if (StringUtils.isEmpty(request.getEmployee())) {
			throw new SalaryException(String.format("导入的工资数据第[%d]行: 姓名不能为空", index));
		}
	}

	private void handleSalaryReference(SaveSalaryRequest request, Salary salary) {
		if (StringUtils.isEmpty(request.getCompanyId())) {
			throw new SalaryException("公司不能同时为空");
		}

		if (StringUtils.isEmpty(request.getProjectId()) && StringUtils.isEmpty(request.getBizDepartmentId())) {
			throw new SalaryException("项目和部门不能同时为空");
		}

		if (!StringUtils.isEmpty(request.getProjectId()) && !StringUtils.isEmpty(request.getBizDepartmentId())) {
			throw new SalaryException("项目和部门只需要设置其中一个有值");
		}

		Company company = companyRepository.findById(request.getCompanyId());
		if (company == null) {
			throw new SalaryException("无效的公司id");
		}
		salary.setCompany(company);

		if (!StringUtils.isEmpty(request.getProjectId())) {
			Project project = projectRepository.findById(request.getProjectId());
			if (project == null) {
				throw new SalaryException("无效的项目id");
			}
			salary.setProject(project);
		}
		else {
			salary.setProject(null);
		}

		if (!StringUtils.isEmpty(request.getBizDepartmentId())) {
			BizDepartment bizDepartment = bizDepartmentRepository.findById(request.getBizDepartmentId());
			if (bizDepartment == null) {
				throw new SalaryException("无效的部门id");
			}
			salary.setBizDepartment(bizDepartment);
		}
		else {
			salary.setBizDepartment(null);
		}

	}

	private void updateSalaryAggregation(Company company, int year, int month) {
		if (company == null ||
			year < 2016 ||
			month < 1 ||
			month > 12) {
			return;
		}

		//By month
		SalaryAggregation salaryAggregation = salaryAggregationRepository.findFirstByCompanyAndYearAndMonth(company,
																											year,
																											month);

		if (salaryAggregation == null) {
			salaryAggregation = createIfPossible(company, year, month);
		}

		salaryAggregation.setTotalPay(salaryRepository.calculateTotalPay(company, year, month));
		salaryAggregation.setModifiedAt(new Date());
		salaryAggregationRepository.save(salaryAggregation);

		//By year
		salaryAggregation = salaryAggregationRepository.findFirstByCompanyAndYearAndMonth(company, year, -1);

		if (salaryAggregation == null) {
			salaryAggregation = createIfPossible(company, year, -1);
		}

		salaryAggregation.setTotalPay(salaryRepository.calculateTotalPay(company, year));
		salaryAggregation.setModifiedAt(new Date());
		salaryAggregationRepository.save(salaryAggregation);
	}

	private synchronized SalaryAggregation createIfPossible(Company company, int year, int month) {
		//re-query for concurrent request
		SalaryAggregation salaryAggregation = salaryAggregationRepository.findFirstByCompanyAndYearAndMonth(company,
																											year,
																											month);
		if (salaryAggregation == null) {
			salaryAggregation = new SalaryAggregation();
			salaryAggregation.setCompany(company);
			salaryAggregation.setYear(year);
			salaryAggregation.setMonth(month);
			salaryAggregation = salaryAggregationRepository.save(salaryAggregation);
		}
		return salaryAggregation;
	}

}
