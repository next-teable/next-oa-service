package cn.com.starest.nextoa.project.rule.impl;

import cn.com.starest.nextoa.project.domain.model.ProjectReceivedPayment;
import cn.com.starest.nextoa.project.domain.rule.ProjectReceivedPaymentReference;
import cn.com.starest.nextoa.project.repository.PendingPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author dz
 */
@Component
public class ProjectReceivedPaymentReferenceImpl implements ProjectReceivedPaymentReference {

	@Autowired
	private PendingPaymentRepository pendingPaymentRepository;

	@Override
	public boolean hasReference(ProjectReceivedPayment target) {
		return pendingPaymentRepository.findFirstByProjectReceivedPayment(target) != null;
	}

}
