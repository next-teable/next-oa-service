package cn.com.starest.nextoa.project.repository;

import cn.com.starest.nextoa.project.domain.model.*;
import cn.com.starest.nextoa.project.repository.custom.ReceiveInvoiceRepositoryCustom;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;

import java.util.List;

/**
 * ReceiveInvoice repo
 */
public interface ReceiveInvoiceRepository extends AbstractRepository<ReceiveInvoice>, ReceiveInvoiceRepositoryCustom {

	List<ReceiveInvoice> findListByProjectOrderByCreatedAtDesc(Project project);

	ReceiveInvoice findFirstByCompany(Company target);

	ReceiveInvoice findFirstByProject(Project target);

	ReceiveInvoice findFirstBySubContract(SubContract target);

	ReceiveInvoice findFirstBySubOrder(SubOrder target);

	ReceiveInvoice findFirstBySubContractor(SubContractor target);
}