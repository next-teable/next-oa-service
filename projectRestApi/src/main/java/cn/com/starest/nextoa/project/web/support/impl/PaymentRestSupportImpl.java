package cn.com.starest.nextoa.project.web.support.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.Payment;
import cn.com.starest.nextoa.project.domain.request.SavePaymentRequest;
import cn.com.starest.nextoa.project.service.PaymentService;
import cn.com.starest.nextoa.project.web.dto.*;
import cn.com.starest.nextoa.project.web.support.PaymentRestSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * @author dz
 */
@Component
public class PaymentRestSupportImpl implements PaymentRestSupport {

	@Autowired
	private PaymentService paymentService;

	@Override
	public PaymentSummary createPayment(SavePaymentRequest request, User byWho) {
		Payment payment = paymentService.createPayment(request, byWho);
		PaymentSummary result = PaymentSummary.from(payment);
		result.setGrantedActions(paymentService.resolveGrantedActions(payment, byWho));
		return result;
	}

	@Override
	public PaymentSummary updatePayment(String id, SavePaymentRequest request, User byWho) {
		Payment payment = paymentService.updatePayment(id, request, byWho);
		PaymentSummary result = PaymentSummary.from(payment);
		result.setGrantedActions(paymentService.resolveGrantedActions(payment, byWho));
		return result;
	}

	@Override
	public PaymentDetail findPaymentById(String id, User byWho) {
		Payment payment = paymentService.findPaymentById(id, byWho);
		PaymentDetail result = PaymentDetail.from(payment);
		if (payment.getPendingPayment() != null) {
			result.setPendingPayment(PendingPaymentSummary.from(payment.getPendingPayment()));
		}
		result.setGrantedActions(paymentService.resolveGrantedActions(payment, byWho));
		return result;
	}

	@Override
	public Page<PaymentSummary> listPayments(PaymentQueryParameter request, User byWho) {
		Page<Payment> paymentPage = paymentService.listPayments(request, byWho);
		return new PermissionAwarePageImpl<>(paymentPage.getContent().stream().map(item -> {
			PaymentSummary summary = PaymentSummary.from(item);
			summary.setGrantedActions(paymentService.resolveGrantedActions(item, byWho));
			return summary;
		}).collect(Collectors.toList()),
											 new PageRequest(request.getStart(), request.getLimit()),
											 paymentPage.getTotalElements(),
											 paymentService.resolveGrantedActions(byWho));
	}

	@Override
	public void deletePaymentById(String id, User user) {
		paymentService.deletePaymentById(id, user);
	}

}
