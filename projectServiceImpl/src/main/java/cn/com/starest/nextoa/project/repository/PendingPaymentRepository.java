package cn.com.starest.nextoa.project.repository;

import cn.com.starest.nextoa.project.domain.model.*;
import cn.com.starest.nextoa.project.repository.custom.PendingPaymentRepositoryCustom;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;

import java.util.List;

/**
 * PendingPayment repo
 */
public interface PendingPaymentRepository extends AbstractRepository<PendingPayment>, PendingPaymentRepositoryCustom {

	/**
	 * @param projectReceivedPayment
	 * @return
	 */
	List<PendingPayment> findListByProjectReceivedPaymentOrderByCreatedAtDesc(ProjectReceivedPayment projectReceivedPayment);

	/**
	 * @param companyReceivedPayment
	 * @return
	 */
	List<PendingPayment> findListByCompanyReceivedPaymentOrderByCreatedAtDesc(CompanyReceivedPayment companyReceivedPayment);

	PendingPayment findFirstByCompany(Company target);

	PendingPayment findFirstByProject(Project target);

	PendingPayment findFirstBySubContractor(SubContractor target);

	PendingPayment findFirstByCompanyReceivedPayment(CompanyReceivedPayment target);

	PendingPayment findFirstByProjectReceivedPayment(ProjectReceivedPayment target);

	PendingPayment findFirstByPayment(Payment target);
}