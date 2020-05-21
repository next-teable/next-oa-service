package cn.com.starest.nextoa.project.rule.impl;

import cn.com.starest.nextoa.project.domain.model.SubContract;
import cn.com.starest.nextoa.project.domain.rule.SubContractReference;
import cn.com.starest.nextoa.project.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author dz
 */
@Component
public class SubContractReferenceImpl implements SubContractReference {

	@Autowired
	private SubOrderRepository subOrderRepository;

	@Autowired
	private SubOrderHistoryRepository subOrderHistoryRepository;

	@Autowired
	private SubContractHistoryRepository subContractHistoryRepository;

	@Autowired
	private ReceiveInvoiceRepository receiveInvoiceRepository;

	@Autowired
	private ReimburseRepository reimburseRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public boolean hasReference(SubContract target) {
		return subOrderRepository.findFirstBySubContract(target) != null ||
			   subOrderHistoryRepository.findFirstBySubContract(target) != null ||
			   subContractHistoryRepository.findFirstBySubContract(target) != null ||
			   reimburseRepository.findFirstBySubContract(target) != null ||
			   paymentRepository.findFirstBySubContract(target) != null ||
			   receiveInvoiceRepository.findFirstBySubContract(target) != null;
	}

}
