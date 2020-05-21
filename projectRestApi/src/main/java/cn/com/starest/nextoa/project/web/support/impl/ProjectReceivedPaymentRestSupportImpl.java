package cn.com.starest.nextoa.project.web.support.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.ProjectReceivedPayment;
import cn.com.starest.nextoa.project.domain.request.SaveProjectReceivedPaymentRequest;
import cn.com.starest.nextoa.project.service.ProjectReceivedPaymentService;
import cn.com.starest.nextoa.project.web.dto.PermissionAwarePageImpl;
import cn.com.starest.nextoa.project.web.dto.ProjectReceivedPaymentDetail;
import cn.com.starest.nextoa.project.web.dto.ProjectReceivedPaymentQueryParameter;
import cn.com.starest.nextoa.project.web.dto.ProjectReceivedPaymentSummary;
import cn.com.starest.nextoa.project.web.support.ProjectReceivedPaymentRestSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * @author dz
 */
@Component
public class ProjectReceivedPaymentRestSupportImpl implements ProjectReceivedPaymentRestSupport {

	@Autowired
	private ProjectReceivedPaymentService paymentService;

	@Override
	public ProjectReceivedPaymentSummary createProjectReceivedPayment(SaveProjectReceivedPaymentRequest request,
																	  User byWho) {
		ProjectReceivedPayment payment = paymentService.createProjectReceivedPayment(request, byWho);
		ProjectReceivedPaymentSummary summary = ProjectReceivedPaymentSummary.from(payment);
		summary.setGrantedActions(paymentService.resolveGrantedActions(payment, byWho));
		return summary;
	}

	@Override
	public ProjectReceivedPaymentSummary updateProjectReceivedPayment(String id,
																	  SaveProjectReceivedPaymentRequest request,
																	  User byWho) {
		ProjectReceivedPayment payment = paymentService.updateProjectReceivedPayment(id, request, byWho);
		ProjectReceivedPaymentSummary summary = ProjectReceivedPaymentSummary.from(payment);
		summary.setGrantedActions(paymentService.resolveGrantedActions(payment, byWho));
		return summary;
	}

	@Override
	public ProjectReceivedPaymentDetail findProjectReceivedPaymentById(String id, User byWho) {
		ProjectReceivedPayment payment = paymentService.findProjectReceivedPaymentById(id, byWho);
		ProjectReceivedPaymentDetail summary = ProjectReceivedPaymentDetail.from(payment);
		summary.setGrantedActions(paymentService.resolveGrantedActions(payment, byWho));
		return summary;
	}

	@Override
	public Page<ProjectReceivedPaymentSummary> listProjectReceivedPayments(ProjectReceivedPaymentQueryParameter request,
																		   User byWho) {
		Page<ProjectReceivedPayment> paymentPage = paymentService.listProjectReceivedPayments(request, byWho);
		return new PermissionAwarePageImpl<>(paymentPage.getContent().stream().map(item -> {
			ProjectReceivedPaymentSummary summary = ProjectReceivedPaymentSummary.from(item);
			summary.setGrantedActions(paymentService.resolveGrantedActions(item, byWho));
			return summary;
		}).collect(Collectors.toList()),
											 new PageRequest(request.getStart(), request.getLimit()),
											 paymentPage.getTotalElements(),
											 paymentService.resolveGrantedActions(byWho));
	}

	@Override
	public void deleteProjectReceivedPaymentById(String id, User user) {
		paymentService.deleteProjectReceivedPaymentById(id, user);
	}

}
