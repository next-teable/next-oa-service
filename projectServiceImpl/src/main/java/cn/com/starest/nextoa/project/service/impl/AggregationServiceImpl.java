package cn.com.starest.nextoa.project.service.impl;

import cn.com.starest.nextoa.project.domain.model.Project;
import cn.com.starest.nextoa.project.repository.*;
import cn.com.starest.nextoa.project.service.AggregationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author dz
 */
@Service
public class AggregationServiceImpl implements AggregationService {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private ContractRepository contractRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private SubOrderRepository subOrderRepository;

	@Autowired
	private ProjectReceivedPaymentRepository projectReceivedPaymentRepository;

	@Autowired
	private IssueInvoiceRepository issueInvoiceRepository;

	@Autowired
	private ReceiveInvoiceRepository receiveInvoiceRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private DepositRepository depositRepository;

	@Autowired
	private PendingPaymentRepository pendingPaymentRepository;

	@Autowired
	private SalaryRepository salaryRepository;

	@Override
	public BigDecimal getContractTotalAmount(Project project) {
		return contractRepository.calculateTotalAmount(project);
	}

	@Override
	public BigDecimal getOrderTotalAmount(Project project) {
		return orderRepository.calculateTotalAmount(project);
	}

	@Override
	public BigDecimal getIssueInvoiceTotalAmount(Project project) {
		return issueInvoiceRepository.calculateTotalAmount(project);
	}

	@Override
	public BigDecimal getProjectReceivedPaymentTotalAmount(Project project) {
		return projectReceivedPaymentRepository.calculateTotalAmount(project);
	}

	@Override
	public BigDecimal getSubOrderTotalAmount(Project project) {
		return subOrderRepository.calculateTotalAmount(project);
	}

	@Override
	public BigDecimal getReceiveInvoiceTotalAmount(Project project) {
		return receiveInvoiceRepository.calculateTotalAmount(project);
	}

	@Override
	public BigDecimal getPaymentTotalAmount(Project project) {
		return paymentRepository.calculateTotalAmount(project);
	}

	@Override
	public BigDecimal getDepositTotalAmount(Project project) {
		return depositRepository.calculateTotalTransferAmount(project)
								.subtract(depositRepository.calculateTotalReturnedAmount(project));
	}

	@Override
	public BigDecimal getPendingPaymentTotalAmount(Project project) {
		return pendingPaymentRepository.calculateTotalAmount(project);
	}

	@Override
	public BigDecimal getSalaryTotalAmount(Project project) {
		return salaryRepository.calculateProjectTotalPay(project);
	}
}
