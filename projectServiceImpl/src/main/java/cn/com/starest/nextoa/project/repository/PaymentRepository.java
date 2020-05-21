package cn.com.starest.nextoa.project.repository;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.*;
import cn.com.starest.nextoa.project.repository.custom.PaymentRepositoryCustom;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Payment repo
 */
public interface PaymentRepository extends AbstractRepository<Payment>, PaymentRepositoryCustom {

	List<Payment> findListByBizRefIdAndPayToOrderByCreatedAtAsc(String bizRefId, User payTo);

	Page<Payment> findPageBySource(PaymentSource source, Pageable pageable);

	Payment findFirstBySourceId(String sourceId);

	Payment findFirstByAccountSubject(AccountSubject target);

	Payment findFirstBySubAccountSubject(AccountSubject target);

	Payment findFirstByBizDepartment(BizDepartment target);

	Payment findFirstByCompany(Company target);

	Payment findFirstByProject(Project target);

	Payment findFirstByContract(Contract target);

	Payment findFirstBySubContract(SubContract target);

	Payment findFirstBySubContractor(SubContractor target);

	Payment findFirstByOrder(Order target);

	Payment findFirstBySubOrder(SubOrder target);

	Payment findFirstByReceiveInvoice(ReceiveInvoice target);

	Payment findFirstByPendingPayment(PendingPayment target);


}