package cn.com.starest.nextoa.project.service.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.*;
import cn.com.starest.nextoa.project.domain.request.PaymentQueryRequest;
import cn.com.starest.nextoa.project.domain.request.SavePaymentRequest;
import cn.com.starest.nextoa.project.domain.rule.PaymentReference;
import cn.com.starest.nextoa.project.exception.EntityNotFoundException;
import cn.com.starest.nextoa.project.exception.PaymentException;
import cn.com.starest.nextoa.project.exception.PaymentNotFoundException;
import cn.com.starest.nextoa.project.repository.PaymentHistoryRepository;
import cn.com.starest.nextoa.project.repository.PendingPaymentRepository;
import cn.com.starest.nextoa.project.service.ModulePermissionService;
import cn.com.starest.nextoa.project.service.ModuleSerialNumberService;
import cn.com.starest.nextoa.project.service.PaymentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dz
 */
@Service
public class PaymentServiceImpl extends AbstractPaymentServiceImpl implements PaymentService {

	@Autowired
	private ModuleSerialNumberService moduleSerialNumberService;

	@Autowired(required = false)
	private List<PaymentReference> paymentReferenceList;

	@Autowired
	private ModulePermissionService modulePermissionService;

	@Autowired
	private PaymentHistoryRepository paymentHistoryRepository;

	@Autowired
	private PendingPaymentRepository pendingPaymentRepository;

	@Override
	public ModuleActions.ModuleAction[] resolveGrantedActions(User byWho) {
		return modulePermissionService.listGrantedActions(Module.PAYMENT, byWho).toArray(ModuleActions.EMPTY_ACTIONS);
	}

	@Override
	public ModuleActions.ModuleAction[] resolveGrantedActions(Payment item, User byWho) {
		List<ModuleActions.ModuleAction> grantedActions = modulePermissionService.listGrantedActions(Module.PAYMENT,
																									 byWho);

		if (item.getSource() == PaymentSource.REIMBURSE) {
			grantedActions.remove(ModuleActions.EDIT_ACTION);
			grantedActions.remove(ModuleActions.DELETE_ACTION);
		}
		return grantedActions.toArray(ModuleActions.EMPTY_ACTIONS);
	}

	@Override
	public Payment createPayment(SavePaymentRequest request, User byWho) {
		checkReceiveInvoiceAmount(request, null);
		checkProjectProfitAmount(request);
//		checkPendingPaymentForOutsource(request);

		Payment payment = new Payment();

		BeanUtils.copyProperties(request, payment);
		handlePaymentReference(request, payment, byWho);
		payment.setSource(PaymentSource.INPUT);
		Payment.onCreate(payment, byWho);

		payment.setCode(moduleSerialNumberService.generate(Module.PAYMENT));

		return paymentRepository.save(payment);
	}

	@Override
	public Payment createPayment(Reimburse reimburse, User byWho) {
		if (!reimburse.isSettled()) {
			throw new PaymentException("未结算的报销不能转付款");
		}

		Payment payment = new Payment();

		BeanUtils.copyProperties(reimburse, payment);

		payment.setPayAt(reimburse.getSettleAt());
		payment.setSource(PaymentSource.REIMBURSE);
		payment.setSourceId(reimburse.getId());
		Payment.onCreate(payment, byWho);
		payment = paymentRepository.save(payment);

		//通过报销产生的付款,需要处理待付款状态
		if (payment.getPendingPayment() != null) {
			PendingPayment pendingPayment = payment.getPendingPayment();
			pendingPayment.setPayed(true);
			pendingPayment.setPayment(payment);
			pendingPaymentRepository.save(pendingPayment);
		}

		PaymentHistory paymentHistory = new PaymentHistory();
		BeanUtils.copyProperties(payment, paymentHistory, "id");
		paymentHistory.setPayment(payment);
		PaymentHistory.onModify(paymentHistory, byWho);
		paymentHistoryRepository.save(paymentHistory);

		return payment;
	}

	@Override
	public Payment updatePayment(String paymentId, SavePaymentRequest request, User byWho) {
		Payment payment = paymentRepository.findById(paymentId);
		if (payment == null) {
			throw new PaymentNotFoundException("没有找到对应的付款.");
		}

		if (payment.getSource() == PaymentSource.REIMBURSE) {
			throw new PaymentException("该付款来自快文报销,不能修改.");
		}

		checkReceiveInvoiceAmount(request, payment);
		checkProjectProfitAmount(request);
//		checkPendingPaymentForOutsource(request);

		BeanUtils.copyProperties(request, payment);
		handlePaymentReference(request, payment, byWho);
		Payment.onModify(payment, byWho);

		return paymentRepository.save(payment);
	}

	@Override
	public Payment findPaymentById(String paymentId, User byWho) {
		return paymentRepository.findById(paymentId);
	}

	@Override
	public void deletePaymentById(String paymentId, User byWho) {
		Payment payment = paymentRepository.findById(paymentId);
		if (payment == null) {
			throw new PaymentNotFoundException("没有找到对应的付款.");
		}

		if (payment.getSource() == PaymentSource.REIMBURSE) {
			throw new PaymentException("该付款来自快文报销,不能删除.");
		}

		if (paymentReferenceList != null) {
			paymentReferenceList.forEach(ref -> {
				if (ref.hasReference(payment)) {
					throw new PaymentException("该付款已被其他业务数据引用,不能删除.");
				}
			});
		}

		paymentRepository.delete(payment);
	}

	@Override
	public Page<Payment> listPayments(PaymentQueryRequest request, User byWho) {
		return paymentRepository.queryPage(request);
	}

	@Override
	public List<Payment> listPayments(String bizRefId, String payToId) {
		User payTo = accountService.findById(payToId);
		if (payTo == null) {
			throw new EntityNotFoundException("无效的付款用户id");
		}
		return paymentRepository.findListByBizRefIdAndPayToOrderByCreatedAtAsc(bizRefId, payTo);
	}
}
