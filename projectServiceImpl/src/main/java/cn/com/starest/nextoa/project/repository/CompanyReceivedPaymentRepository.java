package cn.com.starest.nextoa.project.repository;

import cn.com.starest.nextoa.project.domain.model.*;
import cn.com.starest.nextoa.project.repository.custom.CompanyReceivedPaymentRepositoryCustom;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;

/**
 * Reimburse repo
 */
public interface CompanyReceivedPaymentRepository extends AbstractRepository<CompanyReceivedPayment>,
														  CompanyReceivedPaymentRepositoryCustom {


	CompanyReceivedPayment findFirstByAccountSubject(AccountSubject target);

	CompanyReceivedPayment findFirstBySubAccountSubject(AccountSubject target);

	CompanyReceivedPayment findFirstByBizDepartment(BizDepartment target);

	CompanyReceivedPayment findFirstByCompany(Company target);

	CompanyReceivedPayment findFirstByProject(Project target);

	CompanyReceivedPayment findFirstByCode(String receivedPaymentCode);
}