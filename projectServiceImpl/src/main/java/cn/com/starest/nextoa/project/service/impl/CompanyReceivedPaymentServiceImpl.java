package cn.com.starest.nextoa.project.service.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.*;
import cn.com.starest.nextoa.project.domain.request.CompanyReceivedPaymentQueryRequest;
import cn.com.starest.nextoa.project.domain.request.SaveCompanyReceivedPaymentRequest;
import cn.com.starest.nextoa.project.domain.rule.CompanyReceivedPaymentObserver;
import cn.com.starest.nextoa.project.domain.rule.CompanyReceivedPaymentReference;
import cn.com.starest.nextoa.project.exception.CompanyReceivedPaymentException;
import cn.com.starest.nextoa.project.exception.CompanyReceivedPaymentNotFoundException;
import cn.com.starest.nextoa.project.exception.EntityNotFoundException;
import cn.com.starest.nextoa.project.repository.*;
import cn.com.starest.nextoa.project.service.CompanyReceivedPaymentService;
import cn.com.starest.nextoa.project.service.ModulePermissionService;
import cn.com.starest.nextoa.project.service.ModuleSerialNumberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author dz
 */
@Service
public class CompanyReceivedPaymentServiceImpl implements CompanyReceivedPaymentService {

	@Autowired
	private ModuleSerialNumberService moduleSerialNumberService;

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private AccountSubjectRepository accountSubjectRepository;

	@Autowired
	private BizDepartmentRepository bizDepartmentRepository;

	@Autowired
	private CompanyReceivedPaymentRepository paymentRepository;

	@Autowired(required = false)
	private List<CompanyReceivedPaymentReference> companyReceivedPaymentReferenceList;

	@Autowired(required = false)
	private List<CompanyReceivedPaymentObserver> companyReceivedPaymentObserverList;

	@Autowired
	private ModulePermissionService modulePermissionService;

	@Override
	public ModuleActions.ModuleAction[] resolveGrantedActions(User byWho) {
		return modulePermissionService.listGrantedActions(Module.COMPANY_RECEIVED_PAYMENT, byWho)
									  .toArray(ModuleActions.EMPTY_ACTIONS);
	}

	@Override
	public ModuleActions.ModuleAction[] resolveGrantedActions(CompanyReceivedPayment item, User byWho) {
		ModuleActions.ModuleAction[] grantedActions = modulePermissionService.listGrantedActions(Module.COMPANY_RECEIVED_PAYMENT,
																								 byWho)
																			 .toArray(ModuleActions.EMPTY_ACTIONS);


		return grantedActions;
	}

	@Override
	public CompanyReceivedPayment createCompanyReceivedPayment(SaveCompanyReceivedPaymentRequest request, User byWho) {
		CompanyReceivedPayment payment = new CompanyReceivedPayment();

		BeanUtils.copyProperties(request, payment);
		handleCompanyReceivedPaymentReference(request, payment);
		CompanyReceivedPayment.onCreate(payment, byWho);

		payment.setCode(moduleSerialNumberService.generate(Module.COMPANY_RECEIVED_PAYMENT));

		return paymentRepository.save(payment);
	}

	@Override
	public CompanyReceivedPayment updateCompanyReceivedPayment(String paymentId,
															   SaveCompanyReceivedPaymentRequest request,
															   User byWho) {
		CompanyReceivedPayment payment = paymentRepository.findById(paymentId);
		if (payment == null) {
			throw new CompanyReceivedPaymentNotFoundException("没有找到对应的公司回款.");
		}

		BeanUtils.copyProperties(request, payment);
		handleCompanyReceivedPaymentReference(request, payment);
		CompanyReceivedPayment.onModify(payment, byWho);

		CompanyReceivedPayment result = paymentRepository.save(payment);

		if (companyReceivedPaymentObserverList != null) {
			companyReceivedPaymentObserverList.forEach(observer -> {
				try {
					observer.onChange(result);
				}
				catch (Throwable e) {
				}
			});
		}

		return result;
	}

	@Override
	public CompanyReceivedPayment findCompanyReceivedPaymentById(String paymentId, User byWho) {
		return paymentRepository.findById(paymentId);
	}

	@Override
	public void deleteCompanyReceivedPaymentById(String paymentId, User byWho) {
		CompanyReceivedPayment payment = paymentRepository.findById(paymentId);
		if (payment == null) {
			throw new CompanyReceivedPaymentNotFoundException("没有找到对应的公司回款.");
		}

		if (companyReceivedPaymentReferenceList != null) {
			companyReceivedPaymentReferenceList.forEach(ref -> {
				if (ref.hasReference(payment)) {
					throw new CompanyReceivedPaymentException("该公司回款已被其他业务数据引用,不能删除.");
				}
			});
		}

		paymentRepository.delete(payment);
	}

	@Override
	public Page<CompanyReceivedPayment> listCompanyReceivedPayments(CompanyReceivedPaymentQueryRequest request,
																	User byWho) {
		return paymentRepository.queryPage(request);
	}

	private void handleCompanyReceivedPaymentReference(SaveCompanyReceivedPaymentRequest request,
													   CompanyReceivedPayment payment) {
		Company company = companyRepository.findById(request.getCompanyId());
		if (company == null) {
			throw new EntityNotFoundException("无效的公司id");
		}
		payment.setCompany(company);

		AccountSubject accountSubject = accountSubjectRepository.findById(request.getAccountSubjectId());
		if (accountSubject == null) {
			throw new EntityNotFoundException("无效的费用类型id");
		}
		payment.setAccountSubject(accountSubject);

		if (accountSubject.isProjectEnabled() && StringUtils.isEmpty(request.getProjectId())) {
			throw new CompanyReceivedPaymentException("项目不能为空");
		}

		if (accountSubject.isBizDepartmentEnabled() && StringUtils.isEmpty(request.getBizDepartmentId())) {
			throw new CompanyReceivedPaymentException("部门不能为空");
		}

		if (!StringUtils.isEmpty(request.getSubAccountSubjectId())) {
			AccountSubject subAccountSubject = accountSubjectRepository.findById(request.getSubAccountSubjectId());
			if (subAccountSubject == null) {
				throw new EntityNotFoundException("无效的二级费用类型id");
			}
			payment.setSubAccountSubject(subAccountSubject);
		}
		else {
			payment.setSubAccountSubject(null);
		}


		if (!StringUtils.isEmpty(request.getProjectId())) {
			Project project = projectRepository.findById(request.getProjectId());
			if (project == null) {
				throw new EntityNotFoundException("无效的项目id");
			}
			payment.setProject(project);
		}
		else {
			payment.setProject(null);
		}

		if (!StringUtils.isEmpty(request.getBizDepartmentId())) {
			BizDepartment bizDepartment = bizDepartmentRepository.findById(request.getBizDepartmentId());
			if (bizDepartment == null) {
				throw new EntityNotFoundException("无效的部门id");
			}
			payment.setBizDepartment(bizDepartment);
		}
		else {
			payment.setBizDepartment(null);
		}
	}

}
