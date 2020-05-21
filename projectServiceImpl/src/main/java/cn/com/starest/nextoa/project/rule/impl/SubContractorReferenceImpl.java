package cn.com.starest.nextoa.project.rule.impl;

import cn.com.starest.nextoa.project.domain.model.SubContractor;
import cn.com.starest.nextoa.project.domain.rule.SubContractorReference;
import cn.com.starest.nextoa.project.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author dz
 */
@Component
public class SubContractorReferenceImpl implements SubContractorReference {

	@Autowired
	private SubContractRepository subContractRepository;

	@Autowired
	private SubContractHistoryRepository subContractHistoryRepository;

	@Autowired
	PaymentRepository paymentRepository;

	@Autowired
	PendingPaymentRepository pendingPaymentRepository;

	@Autowired
	ReceiveInvoiceRepository receiveInvoiceRepository;

	@Autowired
	ReimburseRepository reimburseRepository;

	@Override
	public boolean hasReference(SubContractor target) {
		return subContractRepository.findFirstBySubContractor(target) != null ||
			   subContractHistoryRepository.findFirstBySubContractor(target) != null ||
			   paymentRepository.findFirstBySubContractor(target) != null ||
			   pendingPaymentRepository.findFirstBySubContractor(target) != null ||
			   receiveInvoiceRepository.findFirstBySubContractor(target) != null ||
			   reimburseRepository.findFirstBySubContractor(target) != null;
	}
}
