package cn.com.starest.nextoa.project.repository;

import cn.com.starest.nextoa.project.domain.model.*;
import cn.com.starest.nextoa.project.repository.custom.ProjectReceivedPaymentRepositoryCustom;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;

import java.util.List;

/**
 * ProjectReceivedPayment repo
 */
public interface ProjectReceivedPaymentRepository extends AbstractRepository<ProjectReceivedPayment>,
														  ProjectReceivedPaymentRepositoryCustom {

	/**
	 * @param receivedPaymentCode
	 * @return
	 */
	ProjectReceivedPayment findFirstByCode(String receivedPaymentCode);

	/**
	 * @param issueInvoice
	 * @return
	 */
	List<ProjectReceivedPayment> findListByIssueInvoiceOrderByCreatedAtDesc(IssueInvoice issueInvoice);

	ProjectReceivedPayment findFirstByAccountSubject(AccountSubject target);

	ProjectReceivedPayment findFirstBySubAccountSubject(AccountSubject target);

	ProjectReceivedPayment findFirstByCompany(Company target);

	ProjectReceivedPayment findFirstByProject(Project target);

	ProjectReceivedPayment findFirstByIssueInvoice(IssueInvoice target);

	ProjectReceivedPayment findFirstByOrder(Order order);

	ProjectReceivedPayment findFirstByContract(Contract contract);

}