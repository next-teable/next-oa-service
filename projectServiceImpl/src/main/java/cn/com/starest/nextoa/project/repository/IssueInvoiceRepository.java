package cn.com.starest.nextoa.project.repository;

import cn.com.starest.nextoa.project.domain.model.*;
import cn.com.starest.nextoa.project.repository.custom.IssueInvoiceRepositoryCustom;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;

import java.util.List;

/**
 * IssueInvoice repo
 */
public interface IssueInvoiceRepository extends AbstractRepository<IssueInvoice>, IssueInvoiceRepositoryCustom {

	List<IssueInvoice> findListByProjectOrderByCreatedAtDesc(Project project);

	IssueInvoice findFirstByCompany(Company target);

	IssueInvoice findFirstByProject(Project target);

	IssueInvoice findFirstByContract(Contract target);

	IssueInvoice findFirstByOrder(Order target);

	IssueInvoice findFirstByFirstParty(FirstParty target);

	List<IssueInvoice> findByOrderYearIsNull();
}