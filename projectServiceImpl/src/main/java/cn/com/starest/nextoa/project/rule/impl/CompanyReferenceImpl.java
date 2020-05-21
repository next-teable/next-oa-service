package cn.com.starest.nextoa.project.rule.impl;

import cn.com.starest.nextoa.project.domain.model.Company;
import cn.com.starest.nextoa.project.domain.rule.CompanyReference;
import cn.com.starest.nextoa.project.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author dz
 */
@Component
public class CompanyReferenceImpl implements CompanyReference {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private ProjectHistoryRepository projectHistoryRepository;

	@Autowired
	CompanyReceivedPaymentRepository companyReceivedPaymentRepository;

	@Autowired
	PaymentRepository paymentRepository;

	@Autowired
	ProjectReceivedPaymentRepository projectReceivedPaymentRepository;

	@Autowired
	ReimburseRepository reimburseRepository;

	@Autowired
	LendingRepository lendingRepository;

	@Autowired
	PendingPaymentRepository pendingPaymentRepository;

	@Autowired
	ReceiveInvoiceRepository receiveInvoiceRepository;

	@Autowired
	IssueInvoiceRepository issueInvoiceRepository;

	@Autowired
	DepositRepository depositRepository;

	@Override
	public boolean hasReference(Company target) {
		return (projectRepository.findFirstByCompany(target) != null ||
				projectHistoryRepository.findFirstByCompany(target) != null ||
				companyReceivedPaymentRepository.findFirstByCompany(target) != null ||
				paymentRepository.findFirstByCompany(target) != null ||
				projectReceivedPaymentRepository.findFirstByCompany(target) != null ||
				reimburseRepository.findFirstByCompany(target) != null ||
				lendingRepository.findFirstByCompany(target) != null ||
				pendingPaymentRepository.findFirstByCompany(target) != null ||
				lendingRepository.findFirstByLendingTo(target) != null ||
				receiveInvoiceRepository.findFirstByCompany(target) != null ||
				issueInvoiceRepository.findFirstByCompany(target) != null ||
				depositRepository.findFirstByCompany(target) != null);
	}

}
