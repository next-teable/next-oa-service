package cn.com.starest.nextoa.project.web.support.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.PendingPayment;
import cn.com.starest.nextoa.project.domain.model.PendingPaymentAggregation;
import cn.com.starest.nextoa.project.domain.request.SavePendingPaymentRequest;
import cn.com.starest.nextoa.project.service.PendingPaymentService;
import cn.com.starest.nextoa.project.web.dto.*;
import cn.com.starest.nextoa.project.web.support.PendingPaymentRestSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * @author dz
 */
@Component
public class PendingPaymentRestSupportImpl implements PendingPaymentRestSupport {

	@Autowired
	private PendingPaymentService paymentService;

	@Override
	public PendingPaymentSummary createPendingPayment(SavePendingPaymentRequest request, User byWho) {
		PendingPayment payment = paymentService.createPendingPayment(request, byWho);
		PendingPaymentSummary summary = PendingPaymentSummary.from(payment);
		summary.setGrantedActions(paymentService.resolveGrantedActions(payment, byWho));
		return summary;
	}

	@Override
	public PendingPaymentSummary updatePendingPayment(String id, SavePendingPaymentRequest request, User byWho) {
		PendingPayment payment = paymentService.updatePendingPayment(id, request, byWho);
		PendingPaymentSummary summary = PendingPaymentSummary.from(payment);
		summary.setGrantedActions(paymentService.resolveGrantedActions(payment, byWho));
		return summary;
	}

	@Override
	public PendingPaymentDetail findPendingPaymentById(String id, User byWho) {
		PendingPayment pendingPayment = paymentService.findPendingPaymentById(id, byWho);
		PendingPaymentDetail result = PendingPaymentDetail.from(pendingPayment);
		if (pendingPayment.getPayment() != null) {
			result.setPayment(PaymentSummary.from(pendingPayment.getPayment()));
		}
		result.setGrantedActions(paymentService.resolveGrantedActions(pendingPayment, byWho));
		return result;
	}

	@Override
	public Page<PendingPaymentSummary> listPendingPayments(PendingPaymentQueryParameter request, User byWho) {
		Page<PendingPayment> paymentPage = paymentService.listPendingPayments(request, byWho);
		PendingPaymentAggregation pendingPaymentAggregation = paymentService.aggregatePendingPayments(request, byWho);

		return new PermissionAwarePageImpl<>(paymentPage.getContent().stream().map(item -> {
			PendingPaymentSummary summary = PendingPaymentSummary.from(item);
			summary.setGrantedActions(paymentService.resolveGrantedActions(item, byWho));
			return summary;
		}).collect(Collectors.toList()),
											 new PageRequest(request.getStart(), request.getLimit()),
											 paymentPage.getTotalElements(),
											 pendingPaymentAggregation,
											 paymentService.resolveGrantedActions(byWho));
	}

	@Override
	public void deletePendingPaymentById(String id, User user) {
		paymentService.deletePendingPaymentById(id, user);
	}

}
