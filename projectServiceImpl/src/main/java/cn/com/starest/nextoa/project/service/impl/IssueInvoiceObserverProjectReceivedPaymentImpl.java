package cn.com.starest.nextoa.project.service.impl;

import cn.com.starest.nextoa.project.domain.model.IssueInvoice;
import cn.com.starest.nextoa.project.domain.model.ProjectReceivedPayment;
import cn.com.starest.nextoa.project.domain.rule.IssueInvoiceObserver;
import cn.com.starest.nextoa.project.service.ProjectReceivedPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dz
 */
@Service
public class IssueInvoiceObserverProjectReceivedPaymentImpl implements IssueInvoiceObserver {

	@Autowired
	private ProjectReceivedPaymentService projectReceivedPaymentService;

	@Override
	public void onChange(IssueInvoice issueInvoice) {
		List<ProjectReceivedPayment> projectReceivedPaymentList = projectReceivedPaymentService.findListByIssueInvoice(
				issueInvoice);

		projectReceivedPaymentList.stream().forEach(item -> {
			projectReceivedPaymentService.updateProjectReceivedPayment(item,
																	   issueInvoice.getCompany(),
																	   issueInvoice.getProject());
		});
	}

}
