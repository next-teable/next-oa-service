package cn.com.starest.nextoa.project.service.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.*;
import cn.com.starest.nextoa.project.domain.request.CompanyCapitalQueryRequest;
import cn.com.starest.nextoa.project.domain.request.SaveCompanyCapitalRequest;
import cn.com.starest.nextoa.project.exception.CompanyCapitalException;
import cn.com.starest.nextoa.project.exception.CompanyCapitalNotFoundException;
import cn.com.starest.nextoa.project.exception.EntityNotFoundException;
import cn.com.starest.nextoa.project.repository.CompanyCapitalRepository;
import cn.com.starest.nextoa.project.repository.CompanyRepository;
import cn.com.starest.nextoa.project.service.CompanyCapitalService;
import cn.com.starest.nextoa.project.service.FinancialReportService;
import cn.com.starest.nextoa.project.service.FinancialYear;
import cn.com.starest.nextoa.project.service.ModulePermissionService;
import cn.com.starest.nextoa.project.service.utils.FinancialDateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * @author dz
 */
@Service
public class CompanyCapitalServiceImpl implements CompanyCapitalService {

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private ModulePermissionService modulePermissionService;

	@Autowired
	private CompanyCapitalRepository companyCapitalRepository;

	@Autowired
	private FinancialReportService financialReportService;

	@Override
	public ModuleActions.ModuleAction[] resolveGrantedActions(User byWho) {
		return modulePermissionService.listGrantedActions(Module.COMPANY_CAPITAL, byWho)
									  .toArray(ModuleActions.EMPTY_ACTIONS);
	}

	@Override
	public ModuleActions.ModuleAction[] resolveGrantedActions(CompanyCapital companyCapital, User byWho) {
		ModuleActions.ModuleAction[] grantedActions = modulePermissionService.listGrantedActions(Module.COMPANY_CAPITAL,
																								 byWho)
																			 .toArray(ModuleActions.EMPTY_ACTIONS);

		return grantedActions;
	}

	@Override
	public CompanyCapital createCompanyCapital(SaveCompanyCapitalRequest request, User byWho) {
		CompanyCapital companyCapital = new CompanyCapital();

		checkDuplicateCompanyCapital(request, null);

		BeanUtils.copyProperties(request, companyCapital);
		handleCompanyCapitalReference(request, companyCapital);
		CompanyCapital.onCreate(companyCapital, byWho);

		return companyCapitalRepository.save(companyCapital);
	}

	@Override
	public CompanyCapital updateCompanyCapital(String id, SaveCompanyCapitalRequest request, User byWho) {
		CompanyCapital companyCapital = companyCapitalRepository.findById(id);
		if (companyCapital == null) {
			throw new CompanyCapitalNotFoundException("没有找到对应的公司资金.");
		}

		checkDuplicateCompanyCapital(request, companyCapital);

		BeanUtils.copyProperties(request, companyCapital);
		handleCompanyCapitalReference(request, companyCapital);
		CompanyCapital.onModify(companyCapital, byWho);

		return companyCapitalRepository.save(companyCapital);
	}

	@Override
	public void deleteCompanyCapitalById(String id, User byWho) {
		CompanyCapital companyCapital = companyCapitalRepository.findById(id);
		if (companyCapital == null) {
			throw new CompanyCapitalNotFoundException("没有找到对应的公司资金.");
		}

		companyCapitalRepository.delete(companyCapital);
	}

	@Override
	public CompanyCapital findCompanyCapitalById(String id, User byWho) {
		return companyCapitalRepository.findById(id);
	}

	@Override
	public Page<CompanyCapital> listCompanyCapitals(CompanyCapitalQueryRequest request, User byWho) {
		return companyCapitalRepository.queryPage(request);
	}

	@Override
	public void autoCalculateInitialCapital(Company company) {
		FinancialYear[] financialYears = FinancialDateUtils.getYearsForReport();

		if (financialYears.length > 0) {
			int startYear = financialYears[0].getYear();
			CompanyCapital companyCapital = companyCapitalRepository.findFirstByCompanyAndYearAndMonth(company,
																									   startYear,
																									   1);

			if (companyCapital == null) {
				companyCapital = new CompanyCapital();
				companyCapital.setCompany(company);
				companyCapital.setYear(startYear);
				companyCapital.setMonth(1);
				companyCapital.setAmount(BigDecimal.ZERO);
				CompanyCapital.onCreate(companyCapital, null);
				CompanyCapital.onModify(companyCapital, null);
				companyCapitalRepository.save(companyCapital);
			}
			else {
				if (companyCapital.getAmount() == null) {
					companyCapital.setAmount(BigDecimal.ZERO);
					CompanyCapital.onModify(companyCapital, null);
					companyCapitalRepository.save(companyCapital);
				}
			}

		}

		Arrays.asList(financialYears).stream().map(item -> item.getYear()).forEach(year -> {
			Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12).stream().forEach(month -> {
				if (!FinancialDateUtils.isFuture(year, month)) {
					CompanyCapitalAggregation companyCapitalAggregation = financialReportService.calculateCompanyCapitalReport(
							company,
							year,
							month);

					BigDecimal reservedCashAmount = companyCapitalAggregation.getReservedCashAmount();

					if (month == 12) {
						CompanyCapital companyCapital = companyCapitalRepository.findFirstByCompanyAndYearAndMonth(
								company,
								year + 1,
								1);
						if (companyCapital == null) {
							companyCapital = new CompanyCapital();
							companyCapital.setCompany(company);
							companyCapital.setYear(year + 1);
							companyCapital.setMonth(1);
							companyCapital.setAmount(reservedCashAmount);
							CompanyCapital.onCreate(companyCapital, null);
							CompanyCapital.onModify(companyCapital, null);
							companyCapitalRepository.save(companyCapital);
						}
						else {
							companyCapital.setAmount(reservedCashAmount);
							CompanyCapital.onModify(companyCapital, null);
							companyCapitalRepository.save(companyCapital);
						}
					}
					else {
						CompanyCapital companyCapital = companyCapitalRepository.findFirstByCompanyAndYearAndMonth(
								company,
								year,
								month + 1);
						if (companyCapital == null) {
							companyCapital = new CompanyCapital();
							companyCapital.setCompany(company);
							companyCapital.setYear(year);
							companyCapital.setMonth(month + 1);
							companyCapital.setAmount(reservedCashAmount);
							CompanyCapital.onCreate(companyCapital, null);
							CompanyCapital.onModify(companyCapital, null);
							companyCapitalRepository.save(companyCapital);
						}
						else {
							companyCapital.setAmount(reservedCashAmount);
							CompanyCapital.onModify(companyCapital, null);
							companyCapitalRepository.save(companyCapital);
						}
					}
				}
			});
		});


	}

	private void checkDuplicateCompanyCapital(SaveCompanyCapitalRequest request, CompanyCapital existedCompanyCapital) {
		Company company = companyRepository.findById(request.getCompanyId());
		if (company == null) {
			throw new EntityNotFoundException("无效的公司id");
		}

		if (existedCompanyCapital == null) {
			if (null !=
				companyCapitalRepository.findFirstByCompanyAndYearAndMonth(company,
																		   request.getYear(),
																		   request.getMonth())) {
				throw new CompanyCapitalException("发现（公司,年,月）重复的数据");
			}
		}
		else {
			CompanyCapital matchedCompanyCapital = companyCapitalRepository.findFirstByCompanyAndYearAndMonth(company,
																											  request.getYear(),
																											  request.getMonth());
			if (null != matchedCompanyCapital && !matchedCompanyCapital.getId().equals(existedCompanyCapital.getId())) {
				throw new CompanyCapitalException("发现（公司,年,月）重复的数据");
			}
		}
	}

	private void handleCompanyCapitalReference(SaveCompanyCapitalRequest request, CompanyCapital companyCapital) {
		Company company = companyRepository.findById(request.getCompanyId());
		if (company == null) {
			throw new EntityNotFoundException("无效的公司id");
		}
		companyCapital.setCompany(company);
	}

}
