package cn.com.starest.nextoa.project.service.impl;

import cn.com.starest.nextoa.model.SystemSetting;
import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.*;
import cn.com.starest.nextoa.project.domain.request.AbstractSavePaymentRequest;
import cn.com.starest.nextoa.project.exception.ContractException;
import cn.com.starest.nextoa.project.exception.EntityNotFoundException;
import cn.com.starest.nextoa.project.exception.OrderException;
import cn.com.starest.nextoa.project.exception.PaymentException;
import cn.com.starest.nextoa.project.repository.*;
import cn.com.starest.nextoa.project.service.FinancialReportService;
import cn.com.starest.nextoa.service.AccountService;
import cn.com.starest.nextoa.service.SystemSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * @author dz
 */
public abstract class AbstractPaymentServiceImpl {

	@Autowired
	AccountService accountService;

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	BizDepartmentRepository bizDepartmentRepository;

	@Autowired
	ContractRepository contractRepository;

	@Autowired
	SubContractRepository subContractRepository;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	SubOrderRepository subOrderRepository;

	@Autowired
	AccountSubjectRepository accountSubjectRepository;

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	SubContractorRepository subContractorRepository;

	@Autowired
	ReceiveInvoiceRepository receiveInvoiceRepository;

	@Autowired
	PaymentRepository paymentRepository;

	@Autowired
	PendingPaymentRepository pendingPaymentRepository;

	@Autowired
	SystemSettingService systemSettingService;

	@Autowired
	ProjectSettlementRepository projectSettlementRepository;

	@Autowired
	FinancialReportService financialReportService;


	void handlePaymentReference(AbstractSavePaymentRequest request, AbstractPayment payment, User byWho) {
		Company company = companyRepository.findById(request.getCompanyId());
		if (company == null) {
			throw new PaymentException("无效的公司id");
		}
		payment.setCompany(company);

		AccountSubject accountSubject = accountSubjectRepository.findById(request.getAccountSubjectId());
		if (accountSubject == null) {
			throw new PaymentException("无效的费用类型id");
		}
		payment.setAccountSubject(accountSubject);

		if (!StringUtils.isEmpty(request.getSubAccountSubjectId())) {
			AccountSubject subAccountSubject = accountSubjectRepository.findById(request.getSubAccountSubjectId());
			if (subAccountSubject == null) {
				throw new PaymentException("无效的二级费用类型id");
			}
			payment.setSubAccountSubject(subAccountSubject);
		}
		else {
			payment.setSubAccountSubject(null);
		}

		if (accountSubject.isProjectEnabled() && StringUtils.isEmpty(request.getProjectId())) {
			throw new PaymentException("项目不能为空");
		}
		if (accountSubject.isBizDepartmentEnabled() && StringUtils.isEmpty(request.getBizDepartmentId())) {
			throw new PaymentException("部门不能为空");
		}

		if (!StringUtils.isEmpty(request.getReceiveInvoiceId())) {
			payment.setReceiveInvoice(receiveInvoiceRepository.findById(request.getReceiveInvoiceId()));
		}
		else {
			payment.setReceiveInvoice(null);
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

		if (!accountSubject.isProjectEnabled()) {
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

		if (!accountSubject.isBizDepartmentEnabled()) {
			payment.setBizDepartment(null);
		}

		if (payment.getContract() != null &&
			payment.getContract().isSettled() &&
			!payment.getContract().getId().equals(request.getContractId())) {
			throw new ContractException("付款对应的合同已经结算,不能修改为其他合同");
		}

		if (!StringUtils.isEmpty(request.getContractId())) {
			Contract contract = contractRepository.findById(request.getContractId());
			if (contract == null) {
				throw new EntityNotFoundException("无效的主合同id");
			}

			if (payment.getContract() != null &&
				payment.getContract().isSettled() &&
				!payment.getContract().getId().equals(request.getContractId())) {
				throw new ContractException("付款对应的合同已经结算,不能修改为其他合同");
			}
			else if (contract.isSettled()) {
				throw new ContractException("不能选择已经结算的合同");
			}

			payment.setContract(contract);
		}
		else {
			payment.setContract(null);
		}

		if (!StringUtils.isEmpty(request.getSubContractId())) {
			SubContract subContract = subContractRepository.findById(request.getSubContractId());
			if (subContract == null) {
				throw new EntityNotFoundException("无效的分包合同id");
			}
			payment.setSubContract(subContract);
		}
		else {
			payment.setSubContract(null);
		}

		if (payment.getOrder() != null &&
			payment.getOrder().isSettled() &&
			!payment.getOrder().getId().equals(request.getOrderId())) {
			throw new OrderException("付款对应的订单已经结算,不能修改为其他订单");
		}

		if (!StringUtils.isEmpty(request.getOrderId())) {
			Order order = orderRepository.findById(request.getOrderId());
			if (order == null) {
				throw new EntityNotFoundException("无效的主订单id");
			}

			if (payment.getOrder() != null &&
				payment.getOrder().isSettled() &&
				!payment.getOrder().getId().equals(request.getOrderId())) {
				throw new OrderException("付款对应的订单已经结算,不能修改为其他订单");
			}
			else if (order.isSettled()) {
				throw new OrderException("不能选择已经结算的订单");
			}

			payment.setOrder(order);
		}
		else {
			payment.setOrder(null);
		}

		if (!StringUtils.isEmpty(request.getSubOrderId())) {
			SubOrder subOrder = subOrderRepository.findById(request.getSubOrderId());
			if (subOrder == null) {
				throw new EntityNotFoundException("无效的分包订单id");
			}
			payment.setSubOrder(subOrder);
		}
		else {
			payment.setSubOrder(null);
		}

		if (!StringUtils.isEmpty(request.getSubContractorId())) {
			SubContractor subContractor = subContractorRepository.findById(request.getSubContractorId());
			if (subContractor == null) {
				throw new EntityNotFoundException("无效的分包公司id");
			}
			payment.setSubContractor(subContractor);
		}
		else {
			payment.setSubContractor(null);
		}

		if (!StringUtils.isEmpty(request.getPayToId())) {
			User user = accountService.findById(request.getPayToId());
			if (user == null) {
				throw new EntityNotFoundException("无效的付款用户id");
			}
			payment.setPayTo(user);
		}
		else {
			payment.setPayTo(null);
		}

		if (request.getPaymentObject() == PaymentObject.CORP) {
			payment.setPayTo(null);
			payment.setOther(null);
		}
		else if (request.getPaymentObject() == PaymentObject.PERSON) {
			payment.setSubContractor(null);
			payment.setOther(null);
		}
		else if (request.getPaymentObject() == PaymentObject.OTHER) {
			payment.setSubContractor(null);
			payment.setPayTo(null);
		}

		SystemSetting systemSetting = systemSettingService.getSystemSetting();
		if (payment.getSubAccountSubject() != null &&
			payment.getSubAccountSubject().getName().equals(systemSetting.getProfitAccountSubjectName())) {
			ProjectSettlement projectSettlement = null;
			if (!StringUtils.isEmpty(request.getOrderId())) {
				Order order = orderRepository.findById(request.getOrderId());
				projectSettlement = projectSettlementRepository.findFirstByOrder(order);
			}
			else if (!StringUtils.isEmpty(request.getContractId())) {
				Contract contract = contractRepository.findById(request.getContractId());
				projectSettlement = projectSettlementRepository.findFirstByContract(contract);
			}

			if (projectSettlement != null) {
				payment.setBizRefId(projectSettlement.getId());
			}
		}
		else {
			payment.setBizRefId(null);
		}

	}

	/**
	 * Create or update reimburse(payment) : check the left amount of receive invoice
	 *
	 * @param request
	 * @param payment
	 */
	protected void checkReceiveInvoiceAmount(AbstractSavePaymentRequest request, AbstractPayment payment) {
		if (StringUtils.isEmpty(request.getReceiveInvoiceId())) {
			return;
		}

		ReceiveInvoice receiveInvoice = receiveInvoiceRepository.findById(request.getReceiveInvoiceId());
		if (receiveInvoice == null) {
			throw new EntityNotFoundException("无效的收票id");
		}

		BigDecimal receiveAmount = receiveInvoice.getAmount().setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal paymentAmount = paymentRepository.calculateTotalAmount(receiveInvoice);

		if (payment != null) {
			BigDecimal previousAmount = payment.getAmount();
			if (previousAmount != null) {
				paymentAmount = paymentAmount.subtract(previousAmount.setScale(2, BigDecimal.ROUND_HALF_UP));
			}
			if (receiveAmount.subtract(paymentAmount).compareTo(request.getAmount()) < 0) {
				throw new PaymentException(String.format("付款金额[%s]已超出收票剩余可付款金额[%s]",
														 payment.getAmount(),
														 receiveAmount.subtract(paymentAmount)));
			}
		}
		else {
			if (receiveAmount.subtract(paymentAmount).compareTo(request.getAmount()) < 0) {
				throw new PaymentException(String.format("付款金额[%s]已超出收票剩余可付款金额[%s]",
														 payment.getAmount(),
														 receiveAmount.subtract(paymentAmount)));
			}
		}
	}

	/**
	 * Create payment from reimburse : check the left amount of receive invoice
	 *
	 * @param payment
	 */
	protected void checkReceiveInvoiceAmount(AbstractPayment payment) {
		ReceiveInvoice receiveInvoice = payment.getReceiveInvoice();
		if (receiveInvoice != null) {
			BigDecimal receiveAmount = receiveInvoice.getAmount().setScale(2, BigDecimal.ROUND_HALF_UP);
			BigDecimal paymentAmount = paymentRepository.calculateTotalAmount(receiveInvoice);

			if (receiveAmount.subtract(paymentAmount).compareTo(payment.getAmount()) < 0) {
				throw new PaymentException(String.format("付款金额[%s]已超出收票剩余可付款金额[%s]",
														 payment.getAmount(),
														 receiveAmount.subtract(paymentAmount)));
			}
		}
	}

	protected void checkPendingPaymentForOutsource(AbstractSavePaymentRequest request) {
		if (StringUtils.isEmpty(request.getSubAccountSubjectId())) {
			return;
		}


		AccountSubject subAccountSubject = accountSubjectRepository.findById(request.getSubAccountSubjectId());
		if (subAccountSubject == null) {
			throw new PaymentException("无效的二级费用类型id");
		}


	}

	/**
	 * Create or update reimburse(payment) : check the left amount for project profit
	 *
	 * @param request
	 */
	protected void checkProjectProfitAmount(AbstractSavePaymentRequest request) {
		if (StringUtils.isEmpty(request.getSubAccountSubjectId())) {
			return;
		}

		AccountSubject subAccountSubject = accountSubjectRepository.findById(request.getSubAccountSubjectId());
		if (subAccountSubject == null) {
			throw new PaymentException("无效的二级费用类型id");
		}

		SystemSetting systemSetting = systemSettingService.getSystemSetting();
		if (subAccountSubject.getName().equals(systemSetting.getProfitAccountSubjectName())) {
			//走提成报销流程
			if (StringUtils.isEmpty(request.getOrderId()) && StringUtils.isEmpty(request.getContractId())) {
				throw new PaymentException("提成报销的时候必须提供主订单或者主合同");
			}

			if (StringUtils.isEmpty(request.getPayToId())) {
				throw new PaymentException("请选择付款用户");
			}

			User payToUser = accountService.findById(request.getPayToId());
			if (payToUser == null) {
				throw new EntityNotFoundException("无效的付款用户id");
			}

			ProjectSettlement projectSettlement = null;
			if (!StringUtils.isEmpty(request.getOrderId())) {
				Order order = orderRepository.findById(request.getOrderId());
				projectSettlement = projectSettlementRepository.findFirstByOrder(order);

				if (projectSettlement == null) {
					throw new PaymentException(String.format("未设置该订单[%s]的提成比例", order.getName()));
				}

				if (projectSettlement.getStatus() == ProjectSettlement.ProjectSettlementStatus.COMPLETE) {
					throw new PaymentException(String.format("该订单[%s]已结算,不能报销", order.getName()));
				}
			}
			else if (!StringUtils.isEmpty(request.getContractId())) {
				Contract contract = contractRepository.findById(request.getContractId());
				projectSettlement = projectSettlementRepository.findFirstByContract(contract);

				if (projectSettlement == null) {
					throw new PaymentException(String.format("未设置该合同[%s]的提成比例", contract.getName()));
				}

				if (projectSettlement.getStatus() == ProjectSettlement.ProjectSettlementStatus.COMPLETE) {
					throw new PaymentException(String.format("该合同[%s]已结算,不能报销", contract.getName()));
				}
			}

			if (projectSettlement == null) {
				throw new PaymentException("未设置该项目的提成比例");
			}

			Set<String> userIdSet = new HashSet<>();
			if (null != projectSettlement.getUser1()) {
				userIdSet.add(projectSettlement.getUser1().getId());
			}
			if (null != projectSettlement.getUser2()) {
				userIdSet.add(projectSettlement.getUser2().getId());
			}
			if (null != projectSettlement.getUser3()) {
				userIdSet.add(projectSettlement.getUser3().getId());
			}
			if (null != projectSettlement.getUser4()) {
				userIdSet.add(projectSettlement.getUser4().getId());
			}
			if (null != projectSettlement.getUser5()) {
				userIdSet.add(projectSettlement.getUser5().getId());
			}

			if (!userIdSet.contains(payToUser.getId())) {
				throw new PaymentException(String.format("付款用户[%s]未设置提成比例", payToUser.getUsername()));
			}

			ProjectSettlementAggregation projectSettlementAggregation = financialReportService.calculateProjectSettlementAggregationByOneUser(
					projectSettlement,
					payToUser);

			BigDecimal leftAmount = projectSettlementAggregation.getLeftAmount1();
			if (leftAmount.compareTo(BigDecimal.ZERO) <= 0) {
				throw new PaymentException("该用户的提成比例已经全部结算");
			}

			if (request.getAmount().compareTo(leftAmount) > 0) {
				throw new PaymentException(String.format("申请报销金额[%s]超过该项目剩余可提取金额[%s]",
														 request.getAmount(),
														 leftAmount));
			}
		}
	}

	protected void checkPendingPaymentForOutsource(AbstractPayment payment) {

		SystemSetting systemSetting = systemSettingService.getSystemSetting();
		if (payment.getSubAccountSubject() != null &&
			payment.getSubAccountSubject().getName().equals(systemSetting.getOutsourceAccountSubjectName())) {
			if (payment.getPendingPayment() == null) {
				throw new PaymentException(String.format("当二级费用类型为「%s」的时候,必须提供待付款",
														 systemSetting.getOutsourceAccountSubjectName()));
			}
		}

	}

	/**
	 * Create payment from reimburse : check the left amount for project profit
	 *
	 * @param payment
	 */
	protected void checkProjectProfitAmount(AbstractPayment payment) {
		SystemSetting systemSetting = systemSettingService.getSystemSetting();
		if (payment.getSubAccountSubject() != null &&
			payment.getSubAccountSubject().getName().equals(systemSetting.getProfitAccountSubjectName())) {
			//走提成报销流程
			if (null == payment.getOrder() && null == payment.getContract()) {
				throw new PaymentException("提成报销的时候必须提供主订单或者主合同");
			}

			if (StringUtils.isEmpty(payment.getPayTo())) {
				throw new PaymentException("请选择付款用户");
			}

			User payToUser = payment.getPayTo();

			ProjectSettlement projectSettlement = null;
			if (null != payment.getOrder()) {
				projectSettlement = projectSettlementRepository.findFirstByOrder(payment.getOrder());

				if (projectSettlement == null) {
					throw new PaymentException("未设置该订单的提成比例");
				}
			}
			else if (null != payment.getContract()) {
				projectSettlement = projectSettlementRepository.findFirstByContract(payment.getContract());

				if (projectSettlement == null) {
					throw new PaymentException("未设置该合同的提成比例");
				}
			}

			if (projectSettlement == null) {
				throw new PaymentException("未设置该项目的提成比例");
			}

			if (projectSettlement.getStatus() == ProjectSettlement.ProjectSettlementStatus.COMPLETE) {
				throw new PaymentException("该项目已结算,不能报销");
			}

			Set<String> userIdSet = new HashSet<>();
			if (null != projectSettlement.getUser1()) {
				userIdSet.add(projectSettlement.getUser1().getId());
			}
			if (null != projectSettlement.getUser2()) {
				userIdSet.add(projectSettlement.getUser2().getId());
			}
			if (null != projectSettlement.getUser3()) {
				userIdSet.add(projectSettlement.getUser3().getId());
			}
			if (null != projectSettlement.getUser4()) {
				userIdSet.add(projectSettlement.getUser4().getId());
			}
			if (null != projectSettlement.getUser5()) {
				userIdSet.add(projectSettlement.getUser5().getId());
			}

			if (!userIdSet.contains(payToUser.getId())) {
				throw new PaymentException(String.format("付款用户[%s]未设置无提成比例", payToUser.getUsername()));
			}

			ProjectSettlementAggregation projectSettlementAggregation = financialReportService.calculateProjectSettlementAggregationByOneUser(
					projectSettlement,
					payToUser);

			BigDecimal leftAmount = projectSettlementAggregation.getLeftAmount1();
			if (leftAmount.compareTo(BigDecimal.ZERO) <= 0) {
				throw new PaymentException("该用户的提成比例已经全部结算");
			}

			if (payment.getAmount().compareTo(leftAmount) > 0) {
				throw new PaymentException(String.format("申请报销金额[%s]超过该项目剩余可提取金额[%s]",
														 payment.getAmount(),
														 leftAmount));
			}
		}
	}

}
