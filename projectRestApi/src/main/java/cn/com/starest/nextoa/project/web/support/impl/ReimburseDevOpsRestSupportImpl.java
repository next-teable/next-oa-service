package cn.com.starest.nextoa.project.web.support.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.Payment;
import cn.com.starest.nextoa.project.domain.model.PaymentSource;
import cn.com.starest.nextoa.project.domain.request.SavePaymentRequest;
import cn.com.starest.nextoa.project.service.ReimburseDevOpsService;
import cn.com.starest.nextoa.project.web.dto.PaymentDetail;
import cn.com.starest.nextoa.project.web.dto.PaymentQueryParameter;
import cn.com.starest.nextoa.project.web.dto.PaymentSummary;
import cn.com.starest.nextoa.project.web.dto.PermissionAwarePageImpl;
import cn.com.starest.nextoa.project.web.support.ReimburseDevOpsRestSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * @author dz
 */
@Component
public class ReimburseDevOpsRestSupportImpl implements ReimburseDevOpsRestSupport {

	@Autowired
	private ReimburseDevOpsService paymentService;

	@Override
	public PaymentSummary updatePayment(String id, SavePaymentRequest request, User byWho) {
		Payment payment = paymentService.updatePayment(id, request, byWho);
		PaymentSummary summary = PaymentSummary.from(payment);
		summary.setGrantedActions(paymentService.resolveGrantedActions(payment, byWho));
		return summary;
	}

	@Override
	public PaymentDetail findPaymentById(String id, User byWho) {
		Payment payment = paymentService.findPaymentById(id, byWho);
		PaymentDetail summary = PaymentDetail.from(payment);
		summary.setGrantedActions(paymentService.resolveGrantedActions(payment, byWho));
		return summary;
	}

	@Override
	public Page<PaymentSummary> listPayments(PaymentQueryParameter request, User byWho) {
		request.setSource(PaymentSource.REIMBURSE);
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

}
