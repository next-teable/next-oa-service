package cn.com.starest.nextoa.project.rule.impl;

import cn.com.starest.nextoa.project.domain.model.Order;
import cn.com.starest.nextoa.project.domain.rule.OrderReference;
import cn.com.starest.nextoa.project.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author dz
 */
@Component
public class OrderReferenceImpl implements OrderReference {

	@Autowired
	private OrderHistoryRepository orderHistoryRepository;

	@Autowired
	private SubOrderRepository subOrderRepository;

	@Autowired
	private SubOrderHistoryRepository subOrderHistoryRepository;

	@Autowired
	private IssueInvoiceRepository issueInvoiceRepository;

	@Autowired
	private LicenseRepository licenseRepository;

	@Autowired
	private ReimburseRepository reimburseRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private ProjectReceivedPaymentRepository projectReceivedPaymentRepository;

	@Autowired
	private ProjectCompletionRepository projectCompletionRepository;

	@Override
	public boolean hasReference(Order target) {
		return orderHistoryRepository.findFirstByOrder(target) != null ||
			   subOrderRepository.findFirstByOrder(target) != null ||
			   subOrderHistoryRepository.findFirstByOrder(target) != null ||
			   licenseRepository.findFirstByOrder(target) != null ||
			   reimburseRepository.findFirstByOrder(target) != null ||
			   paymentRepository.findFirstByOrder(target) != null ||
			   issueInvoiceRepository.findFirstByOrder(target) != null ||
			   projectReceivedPaymentRepository.findFirstByOrder(target) != null ||
			   projectCompletionRepository.findFirstByOrder(target) != null;
	}

}
