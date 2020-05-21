package cn.com.starest.nextoa.project.service.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.*;
import cn.com.starest.nextoa.project.domain.request.ProjectReceivedPaymentQueryRequest;
import cn.com.starest.nextoa.project.domain.request.SaveProjectReceivedPaymentRequest;
import cn.com.starest.nextoa.project.domain.rule.ProjectReceivedPaymentObserver;
import cn.com.starest.nextoa.project.domain.rule.ProjectReceivedPaymentReference;
import cn.com.starest.nextoa.project.exception.*;
import cn.com.starest.nextoa.project.repository.*;
import cn.com.starest.nextoa.project.service.ModulePermissionService;
import cn.com.starest.nextoa.project.service.ModuleSerialNumberService;
import cn.com.starest.nextoa.project.service.ProjectReceivedPaymentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author dz
 */
@Service
public class ProjectReceivedPaymentServiceImpl implements ProjectReceivedPaymentService {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private ContractRepository contractRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ModuleSerialNumberService moduleSerialNumberService;

	@Autowired
	private AccountSubjectRepository accountSubjectRepository;

	@Autowired
	private IssueInvoiceRepository issueInvoiceRepository;

	@Autowired
	private ProjectReceivedPaymentRepository projectReceivedPaymentRepository;

	@Autowired(required = false)
	private List<ProjectReceivedPaymentReference> projectReceivedPaymentReferenceList;

	@Autowired(required = false)
	private List<ProjectReceivedPaymentObserver> projectReceivedPaymentObserverList;

	@Autowired
	private ModulePermissionService modulePermissionService;

	@Override
	public ModuleActions.ModuleAction[] resolveGrantedActions(User byWho) {
		return modulePermissionService.listGrantedActions(Module.PROJECT_RECEIVED_PAYMENT, byWho)
									  .toArray(ModuleActions.EMPTY_ACTIONS);
	}

	@Override
	public ModuleActions.ModuleAction[] resolveGrantedActions(ProjectReceivedPayment item, User byWho) {
		ModuleActions.ModuleAction[] grantedActions = modulePermissionService.listGrantedActions(Module.PROJECT_RECEIVED_PAYMENT,
																								 byWho)
																			 .toArray(ModuleActions.EMPTY_ACTIONS);


		return grantedActions;
	}

	@Override
	public ProjectReceivedPayment createProjectReceivedPayment(SaveProjectReceivedPaymentRequest request, User byWho) {
		ProjectReceivedPayment payment = new ProjectReceivedPayment();

		handleProjectReceivedPaymentReference(request, payment);
		BeanUtils.copyProperties(request, payment);
		ProjectReceivedPayment.onCreate(payment, byWho);

		payment.setCode(moduleSerialNumberService.generate(Module.PROJECT_RECEIVED_PAYMENT));

		return projectReceivedPaymentRepository.save(payment);
	}

	@Override
	public ProjectReceivedPayment updateProjectReceivedPayment(String paymentId,
															   SaveProjectReceivedPaymentRequest request,
															   User byWho) {
		ProjectReceivedPayment payment = projectReceivedPaymentRepository.findById(paymentId);
		if (payment == null) {
			throw new ProjectReceivedPaymentNotFoundException("没有找到对应的项目回款.");
		}

		handleProjectReceivedPaymentReference(request, payment);
		BeanUtils.copyProperties(request, payment);
		ProjectReceivedPayment.onModify(payment, byWho);

		ProjectReceivedPayment result = projectReceivedPaymentRepository.save(payment);

		if (projectReceivedPaymentObserverList != null) {
			projectReceivedPaymentObserverList.forEach(observer -> {
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
	public void updateProjectReceivedPayment(ProjectReceivedPayment projectReceivedPayment,
											 Company company,
											 Project project) {
		projectReceivedPayment.setCompany(company);
		projectReceivedPayment.setProject(project);
		projectReceivedPaymentRepository.save(projectReceivedPayment);

		if (projectReceivedPaymentObserverList != null) {
			projectReceivedPaymentObserverList.forEach(observer -> {
				try {
					observer.onChange(projectReceivedPayment);
				}
				catch (Throwable e) {
				}
			});
		}
	}

	@Override
	public ProjectReceivedPayment findProjectReceivedPaymentById(String paymentId, User byWho) {
		return projectReceivedPaymentRepository.findById(paymentId);
	}

	@Override
	public List<ProjectReceivedPayment> findListByIssueInvoice(IssueInvoice issueInvoice) {
		return projectReceivedPaymentRepository.findListByIssueInvoiceOrderByCreatedAtDesc(issueInvoice);
	}

	@Override
	public void deleteProjectReceivedPaymentById(String paymentId, User byWho) {
		ProjectReceivedPayment payment = projectReceivedPaymentRepository.findById(paymentId);
		if (payment == null) {
			throw new ProjectReceivedPaymentNotFoundException("没有找到对应的项目回款.");
		}

		if (projectReceivedPaymentReferenceList != null) {
			projectReceivedPaymentReferenceList.forEach(ref -> {
				if (ref.hasReference(payment)) {
					throw new ProjectReceivedPaymentException("该项目回款已被其他业务数据引用,不能删除.");
				}
			});
		}

		projectReceivedPaymentRepository.delete(payment);
	}

	@Override
	public Page<ProjectReceivedPayment> listProjectReceivedPayments(ProjectReceivedPaymentQueryRequest request,
																	User byWho) {
		return projectReceivedPaymentRepository.queryPage(request);
	}

	private void handleProjectReceivedPaymentReference(SaveProjectReceivedPaymentRequest request,
													   ProjectReceivedPayment payment) {
		//公司账户回款 必须 有开票
		if (ReceivedPaymentType.ACCOUNT == request.getType() && StringUtils.isEmpty(request.getIssueInvoiceId())) {
			throw new ProjectReceivedPaymentException("项目回款类型为公司账户的时候, 请提供开票（发票）");
		}

		if (!StringUtils.isEmpty(request.getIssueInvoiceId())) {
			IssueInvoice issueInvoice = issueInvoiceRepository.findById(request.getIssueInvoiceId());
			if (issueInvoice == null) {
				throw new ProjectReceivedPaymentException("无效的开票id");
			}

			BigDecimal issueAmount = issueInvoice.getAmount().setScale(2, BigDecimal.ROUND_HALF_UP);
			BigDecimal receivedAmount = projectReceivedPaymentRepository.calculateTotalAmount(issueInvoice);

			if (!StringUtils.isEmpty(payment.getId())) {
				BigDecimal previousAmount = payment.getAmount();
				if (previousAmount != null) {
					receivedAmount = receivedAmount.subtract(previousAmount.setScale(2, BigDecimal.ROUND_HALF_UP));
				}
				if (issueAmount.subtract(receivedAmount).compareTo(request.getAmount()) < 0) {
					throw new ProjectReceivedPaymentException("已超出开票剩余可回款金额");
				}
			}
			else {
				if (issueAmount.subtract(receivedAmount).compareTo(request.getAmount()) < 0) {
					throw new ProjectReceivedPaymentException("已超出开票剩余可回款金额");
				}
			}

			payment.setIssueInvoice(issueInvoice);
		}
		else {
			payment.setIssueInvoice(null);
		}


		AccountSubject accountSubject = accountSubjectRepository.findById(request.getAccountSubjectId());
		if (accountSubject == null) {
			throw new EntityNotFoundException("无效的费用类型id");
		}
		payment.setAccountSubject(accountSubject);

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

		Project project = projectRepository.findById(request.getProjectId());
		if (project == null) {
			throw new EntityNotFoundException("无效的项目id");
		}
		payment.setProject(project);
		payment.setCompany(project.getCompany() != null ? project.getCompany() : null);

		if (payment.getContract() != null &&
			payment.getContract().isSettled() &&
			!payment.getContract().getId().equals(request.getContractId())) {
			throw new ContractException("项目回款对应的合同已经结算,不能修改为其他合同");
		}

		if (!StringUtils.isEmpty(request.getContractId())) {
			Contract contract = contractRepository.findById(request.getContractId());
			if (contract == null) {
				throw new EntityNotFoundException("无效的合同id");
			}

			if (payment.getContract() != null &&
				payment.getContract().isSettled() &&
				!payment.getContract().getId().equals(request.getContractId())) {
				throw new ContractException("项目回款对应的合同已经结算,不能修改为其他合同");
			}
			else if (contract.isSettled()) {
				throw new ContractException("不能选择已经结算的合同");
			}

			payment.setContract(contract);
		}
		else {
			payment.setContract(null);
		}

		if (payment.getOrder() != null &&
			payment.getOrder().isSettled() &&
			!payment.getOrder().getId().equals(request.getOrderId())) {
			throw new OrderException("项目回款对应的订单已经结算,不能修改为其他订单");
		}

		if (!StringUtils.isEmpty(request.getOrderId())) {
			Order order = orderRepository.findById(request.getOrderId());
			if (order == null) {
				throw new EntityNotFoundException("无效的订单id");
			}


			if (payment.getOrder() != null &&
				payment.getOrder().isSettled() &&
				!payment.getOrder().getId().equals(request.getOrderId())) {
				throw new OrderException("项目回款对应的订单已经结算,不能修改为其他订单");
			}
			else if (order.isSettled()) {
				throw new OrderException("不能选择已经结算的订单");
			}

			payment.setOrder(order);
		}
		else {
			payment.setOrder(null);
		}

	}

}
