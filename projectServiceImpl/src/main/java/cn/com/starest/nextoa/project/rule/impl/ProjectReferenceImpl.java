package cn.com.starest.nextoa.project.rule.impl;

import cn.com.starest.nextoa.project.domain.model.Project;
import cn.com.starest.nextoa.project.domain.rule.ProjectReference;
import cn.com.starest.nextoa.project.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author dz
 */
@Component
public class ProjectReferenceImpl implements ProjectReference {

	@Autowired
	private ContractRepository contractRepository;

	@Autowired
	private ContractHistoryRepository contractHistoryRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderHistoryRepository orderHistoryRepository;

	@Autowired
	private SubContractRepository subContractRepository;

	@Autowired
	private SubContractHistoryRepository subContractHistoryRepository;

	@Autowired
	private SubOrderRepository subOrderRepository;

	@Autowired
	private SubOrderHistoryRepository subOrderHistoryRepository;

	@Autowired
	private LicenseRepository licenseRepository;

	@Autowired
	private CompanyReceivedPaymentRepository companyReceivedPaymentRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private ProjectReceivedPaymentRepository projectReceivedPaymentRepository;

	@Autowired
	private ReimburseRepository reimburseRepository;

	@Autowired
	private PendingPaymentRepository pendingPaymentRepository;

	@Autowired
	private ReceiveInvoiceRepository receiveInvoiceRepository;

	@Autowired
	private IssueInvoiceRepository issueInvoiceRepository;

	@Autowired
	private DepositRepository depositRepository;

	@Autowired
	private ProjectCompletionRepository projectCompletionRepository;

	@Override
	public boolean hasReference(Project target) {
		return contractRepository.findFirstByProjectsIn(target) != null ||
			   contractHistoryRepository.findFirstByProjectsIn(target) != null ||
			   orderRepository.findFirstByProject(target) != null ||
			   orderHistoryRepository.findFirstByProject(target) != null ||
			   subContractRepository.findFirstByProject(target) != null ||
			   subContractHistoryRepository.findFirstByProject(target) != null ||
			   subOrderRepository.findFirstByProject(target) != null ||
			   subOrderHistoryRepository.findFirstByProject(target) != null ||
			   licenseRepository.findFirstByProject(target) != null ||
			   companyReceivedPaymentRepository.findFirstByProject(target) != null ||
			   paymentRepository.findFirstByProject(target) != null ||
			   projectReceivedPaymentRepository.findFirstByProject(target) != null ||
			   reimburseRepository.findFirstByProject(target) != null ||
			   pendingPaymentRepository.findFirstByProject(target) != null ||
			   receiveInvoiceRepository.findFirstByProject(target) != null ||
			   issueInvoiceRepository.findFirstByProject(target) != null ||
			   depositRepository.findFirstByProject(target) != null ||
			   projectCompletionRepository.findFirstByProject(target) != null;
	}

}
