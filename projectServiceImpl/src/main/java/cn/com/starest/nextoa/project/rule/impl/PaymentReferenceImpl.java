package cn.com.starest.nextoa.project.rule.impl;

import cn.com.starest.nextoa.project.domain.model.Order;
import cn.com.starest.nextoa.project.domain.model.Payment;
import cn.com.starest.nextoa.project.domain.rule.OrderReference;
import cn.com.starest.nextoa.project.domain.rule.PaymentReference;
import cn.com.starest.nextoa.project.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author dz
 */
@Component
public class PaymentReferenceImpl implements PaymentReference {

	@Autowired
	private PendingPaymentRepository pendingPaymentRepository;

	@Override
	public boolean hasReference(Payment target) {
		return pendingPaymentRepository.findFirstByPayment(target) != null;
	}

}
