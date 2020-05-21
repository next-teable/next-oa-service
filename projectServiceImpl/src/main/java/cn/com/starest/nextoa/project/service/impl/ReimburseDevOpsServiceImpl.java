package cn.com.starest.nextoa.project.service.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.*;
import cn.com.starest.nextoa.project.domain.request.PaymentQueryRequest;
import cn.com.starest.nextoa.project.domain.request.SavePaymentRequest;
import cn.com.starest.nextoa.project.exception.PaymentException;
import cn.com.starest.nextoa.project.exception.PaymentNotFoundException;
import cn.com.starest.nextoa.project.repository.PaymentHistoryRepository;
import cn.com.starest.nextoa.project.repository.PaymentRepository;
import cn.com.starest.nextoa.project.service.ModulePermissionService;
import cn.com.starest.nextoa.project.service.ModuleSerialNumberService;
import cn.com.starest.nextoa.project.service.ReimburseDevOpsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dz
 */
@Service
public class ReimburseDevOpsServiceImpl extends AbstractPaymentServiceImpl implements ReimburseDevOpsService {

	@Autowired
	private ModuleSerialNumberService moduleSerialNumberService;

	@Autowired
	private ModulePermissionService modulePermissionService;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private PaymentHistoryRepository paymentHistoryRepository;

	@Override
	public ModuleActions.ModuleAction[] resolveGrantedActions(User byWho) {
		return modulePermissionService.listGrantedActions(Module.REIMBURSE_DEVOPS, byWho)
									  .toArray(ModuleActions.EMPTY_ACTIONS);
	}

	@Override
	public ModuleActions.ModuleAction[] resolveGrantedActions(Payment item, User byWho) {
		List<ModuleActions.ModuleAction> grantedActions = modulePermissionService.listGrantedActions(Module.REIMBURSE_DEVOPS,
																									 byWho);

		return grantedActions.toArray(ModuleActions.EMPTY_ACTIONS);
	}

	@Override
	public Payment updatePayment(String paymentId, SavePaymentRequest request, User byWho) {
		Payment payment = paymentRepository.findById(paymentId);
		if (payment == null) {
			throw new PaymentNotFoundException("没有找到对应的报销付款.");
		}

		if (payment.getSource() != PaymentSource.REIMBURSE) {
			throw new PaymentException("只能修改报销付款.");
		}

		BeanUtils.copyProperties(request, payment);
		handlePaymentReference(request, payment, byWho);
		Payment.onModify(payment, byWho);

		PaymentHistory paymentHistory = new PaymentHistory();
		BeanUtils.copyProperties(payment, paymentHistory, "id");
		paymentHistory.setPayment(payment);
		PaymentHistory.onModify(paymentHistory, byWho);
		paymentHistoryRepository.save(paymentHistory);

		return paymentRepository.save(payment);
	}

	@Override
	public Payment findPaymentById(String paymentId, User byWho) {
		return paymentRepository.findById(paymentId);
	}

	@Override
	public Page<Payment> listPayments(PaymentQueryRequest request, User byWho) {
		return paymentRepository.queryPage(request);
	}

}
