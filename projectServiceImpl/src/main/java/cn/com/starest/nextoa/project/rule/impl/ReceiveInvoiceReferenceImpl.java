package cn.com.starest.nextoa.project.rule.impl;

import cn.com.starest.nextoa.project.domain.model.ReceiveInvoice;
import cn.com.starest.nextoa.project.domain.rule.ReceiveInvoiceReference;
import cn.com.starest.nextoa.project.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author dz
 */
@Component
public class ReceiveInvoiceReferenceImpl implements ReceiveInvoiceReference {

	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public boolean hasReference(ReceiveInvoice target) {
		return paymentRepository.findFirstByReceiveInvoice(target) != null;
	}
}
