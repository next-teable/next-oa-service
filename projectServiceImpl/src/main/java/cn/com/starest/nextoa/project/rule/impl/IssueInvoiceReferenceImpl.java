package cn.com.starest.nextoa.project.rule.impl;

import cn.com.starest.nextoa.project.domain.model.IssueInvoice;
import cn.com.starest.nextoa.project.domain.rule.IssueInvoiceReference;
import cn.com.starest.nextoa.project.repository.ProjectReceivedPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author dz
 */
@Component
public class IssueInvoiceReferenceImpl implements IssueInvoiceReference {

	@Autowired
	private ProjectReceivedPaymentRepository projectReceivedPaymentRepository;

	@Override
	public boolean hasReference(IssueInvoice target) {
		return projectReceivedPaymentRepository.findFirstByIssueInvoice(target) != null;
	}
}
