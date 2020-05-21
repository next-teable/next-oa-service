package cn.com.starest.nextoa.project.rule.impl;

import cn.com.starest.nextoa.project.domain.model.BizDepartment;
import cn.com.starest.nextoa.project.domain.rule.BizDepartmentReference;
import cn.com.starest.nextoa.project.repository.CompanyReceivedPaymentRepository;
import cn.com.starest.nextoa.project.repository.PaymentRepository;
import cn.com.starest.nextoa.project.repository.ProjectReceivedPaymentRepository;
import cn.com.starest.nextoa.project.repository.ReimburseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author dz
 */
@Component
public class BizDepartmentReferenceImpl implements BizDepartmentReference {

	@Autowired
	CompanyReceivedPaymentRepository companyReceivedPaymentRepository;

	@Autowired
	PaymentRepository paymentRepository;

	@Autowired
	ReimburseRepository reimburseRepository;

	@Override
	public boolean hasReference(BizDepartment target) {
		return (companyReceivedPaymentRepository.findFirstByBizDepartment(target) != null ||
				paymentRepository.findFirstByBizDepartment(target) != null ||
				reimburseRepository.findFirstByBizDepartment(target) != null);
	}

}
