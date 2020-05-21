package cn.com.starest.nextoa.project.rule.impl;

import cn.com.starest.nextoa.project.domain.model.CompanyReceivedPayment;
import cn.com.starest.nextoa.project.domain.rule.CompanyReceivedPaymentReference;
import cn.com.starest.nextoa.project.repository.PendingPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author dz
 */
@Component
public class CompanyReceivedPaymentReferenceImpl implements CompanyReceivedPaymentReference {

	@Autowired
	private PendingPaymentRepository pendingPaymentRepository;

	@Override
	public boolean hasReference(CompanyReceivedPayment target) {
		return pendingPaymentRepository.findFirstByCompanyReceivedPayment(target) != null;
	}

}
