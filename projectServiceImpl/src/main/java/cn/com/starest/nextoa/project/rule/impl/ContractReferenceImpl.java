package cn.com.starest.nextoa.project.rule.impl;

import cn.com.starest.nextoa.project.domain.model.Contract;
import cn.com.starest.nextoa.project.domain.rule.ContractReference;
import cn.com.starest.nextoa.project.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author dz
 */
@Component
public class ContractReferenceImpl implements ContractReference {

	@Autowired
	private ContractHistoryRepository contractHistoryRepository;

	@Autowired
	private SubContractRepository subContractRepository;

	@Autowired
	private SubContractHistoryRepository subContractHistoryRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderHistoryRepository orderHistoryRepository;

	@Autowired
	private IssueInvoiceRepository issueInvoiceRepository;

	@Autowired
	private LicenseRepository licenseRepository;

	@Autowired
	private ProjectReceivedPaymentRepository projectReceivedPaymentRepository;

	@Autowired
	private ReimburseRepository reimburseRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private ProjectCompletionRepository projectCompletionRepository;

	@Override
	public boolean hasReference(Contract target) {
		return contractHistoryRepository.findFirstByContract(target) != null ||
			   orderRepository.findFirstByContract(target) != null ||
			   orderHistoryRepository.findFirstByContract(target) != null ||
			   subContractRepository.findFirstByContract(target) != null ||
			   subContractHistoryRepository.findFirstByContract(target) != null ||
			   licenseRepository.findFirstByContract(target) != null ||
			   projectReceivedPaymentRepository.findFirstByContract(target) != null ||
			   reimburseRepository.findFirstByContract(target) != null ||
			   paymentRepository.findFirstByContract(target) != null ||
			   issueInvoiceRepository.findFirstByContract(target) != null ||
			   projectCompletionRepository.findFirstByContract(target) != null;
	}
}
