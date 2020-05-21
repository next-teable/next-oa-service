package cn.com.starest.nextoa.project.web.support.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.CompanyReceivedPayment;
import cn.com.starest.nextoa.project.domain.request.SaveCompanyReceivedPaymentRequest;
import cn.com.starest.nextoa.project.service.CompanyReceivedPaymentService;
import cn.com.starest.nextoa.project.web.dto.CompanyReceivedPaymentDetail;
import cn.com.starest.nextoa.project.web.dto.CompanyReceivedPaymentQueryParameter;
import cn.com.starest.nextoa.project.web.dto.CompanyReceivedPaymentSummary;
import cn.com.starest.nextoa.project.web.dto.PermissionAwarePageImpl;
import cn.com.starest.nextoa.project.web.support.CompanyReceivedPaymentRestSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * @author dz
 */
@Component
public class CompanyReceivedPaymentRestSupportImpl implements CompanyReceivedPaymentRestSupport {

	@Autowired
	private CompanyReceivedPaymentService paymentService;

	@Override
	public CompanyReceivedPaymentSummary createCompanyReceivedPayment(SaveCompanyReceivedPaymentRequest request,
																	  User byWho) {
		CompanyReceivedPayment payment = paymentService.createCompanyReceivedPayment(request, byWho);
		CompanyReceivedPaymentSummary summary = CompanyReceivedPaymentSummary.from(payment);
		summary.setGrantedActions(paymentService.resolveGrantedActions(payment, byWho));
		return summary;
	}

	@Override
	public CompanyReceivedPaymentSummary updateCompanyReceivedPayment(String id,
																	  SaveCompanyReceivedPaymentRequest request,
																	  User byWho) {
		CompanyReceivedPayment payment = paymentService.updateCompanyReceivedPayment(id, request, byWho);
		CompanyReceivedPaymentSummary summary = CompanyReceivedPaymentSummary.from(payment);
		summary.setGrantedActions(paymentService.resolveGrantedActions(payment, byWho));
		return summary;
	}

	@Override
	public CompanyReceivedPaymentDetail findCompanyReceivedPaymentById(String id, User byWho) {
		CompanyReceivedPayment payment = paymentService.findCompanyReceivedPaymentById(id, byWho);
		CompanyReceivedPaymentDetail summary = CompanyReceivedPaymentDetail.from(payment);
		summary.setGrantedActions(paymentService.resolveGrantedActions(payment, byWho));
		return summary;
	}

	@Override
	public Page<CompanyReceivedPaymentSummary> listCompanyReceivedPayments(CompanyReceivedPaymentQueryParameter request,
																		   User byWho) {
		Page<CompanyReceivedPayment> paymentPage = paymentService.listCompanyReceivedPayments(request, byWho);
		return new PermissionAwarePageImpl<>(paymentPage.getContent().stream().map(item -> {
			CompanyReceivedPaymentSummary summary = CompanyReceivedPaymentSummary.from(item);
			summary.setGrantedActions(paymentService.resolveGrantedActions(item, byWho));
			return summary;
		}).collect(Collectors.toList()),
											 new PageRequest(request.getStart(), request.getLimit()),
											 paymentPage.getTotalElements(),
											 paymentService.resolveGrantedActions(byWho));
	}

	@Override
	public void deleteCompanyReceivedPaymentById(String id, User user) {
		paymentService.deleteCompanyReceivedPaymentById(id, user);
	}

}
