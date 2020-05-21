package cn.com.starest.nextoa.project.rule.impl;

import cn.com.starest.nextoa.project.domain.model.PendingPayment;
import cn.com.starest.nextoa.project.domain.rule.PendingPaymentReference;
import cn.com.starest.nextoa.project.repository.PaymentRepository;
import cn.com.starest.nextoa.project.repository.ReimburseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author dz
 */
@Component
public class PendingPaymentReferenceImpl implements PendingPaymentReference {

	@Autowired
	private ReimburseRepository reimburseRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public boolean hasReference(PendingPayment target) {
		return reimburseRepository.findFirstByPendingPayment(target) != null ||
			   paymentRepository.findFirstByPendingPayment(target) != null;
	}

}
