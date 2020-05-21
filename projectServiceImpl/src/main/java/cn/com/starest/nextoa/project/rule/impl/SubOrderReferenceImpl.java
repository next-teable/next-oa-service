package cn.com.starest.nextoa.project.rule.impl;

import cn.com.starest.nextoa.project.domain.model.SubOrder;
import cn.com.starest.nextoa.project.domain.rule.SubOrderReference;
import cn.com.starest.nextoa.project.repository.PaymentRepository;
import cn.com.starest.nextoa.project.repository.ReceiveInvoiceRepository;
import cn.com.starest.nextoa.project.repository.ReimburseRepository;
import cn.com.starest.nextoa.project.repository.SubOrderHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author dz
 */
@Component
public class SubOrderReferenceImpl implements SubOrderReference {

	@Autowired
	private SubOrderHistoryRepository subOrderHistoryRepository;

	@Autowired
	private ReceiveInvoiceRepository receiveInvoiceRepository;

	@Autowired
	private ReimburseRepository reimburseRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public boolean hasReference(SubOrder target) {
		return subOrderHistoryRepository.findFirstBySubOrder(target) != null ||
			   reimburseRepository.findFirstBySubOrder(target) != null ||
			   paymentRepository.findFirstBySubOrder(target) != null ||
			   receiveInvoiceRepository.findFirstBySubOrder(target) != null;
	}

}
