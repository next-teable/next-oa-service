package cn.com.starest.nextoa.project.rule.impl;

import cn.com.starest.nextoa.project.domain.model.AccountSubject;
import cn.com.starest.nextoa.project.domain.rule.AccountSubjectReference;
import cn.com.starest.nextoa.project.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author dz
 */
@Component
public class AccountSubjectReferenceImpl implements AccountSubjectReference {

	@Autowired
	CompanyReceivedPaymentRepository companyReceivedPaymentRepository;

	@Autowired
	PaymentRepository paymentRepository;

	@Autowired
	ProjectReceivedPaymentRepository projectReceivedPaymentRepository;

	@Autowired
	ReimburseRepository reimburseRepository;

	@Autowired
	AccountSubjectRepository accountSubjectRepository;

	@Override
	public boolean hasReference(AccountSubject target) {
		return (companyReceivedPaymentRepository.findFirstByAccountSubject(target) != null ||
				paymentRepository.findFirstByAccountSubject(target) != null ||
				projectReceivedPaymentRepository.findFirstByAccountSubject(target) != null ||
				reimburseRepository.findFirstByAccountSubject(target) != null ||
				companyReceivedPaymentRepository.findFirstBySubAccountSubject(target) != null ||
				paymentRepository.findFirstBySubAccountSubject(target) != null ||
				projectReceivedPaymentRepository.findFirstBySubAccountSubject(target) != null ||
				reimburseRepository.findFirstBySubAccountSubject(target) != null ||
				accountSubjectRepository.findFirstByParent(target) != null);
	}

}
