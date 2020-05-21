package cn.com.starest.nextoa.project.repository;

import cn.com.starest.nextoa.model.Paper;
import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.*;
import cn.com.starest.nextoa.project.repository.custom.ReimburseRepositoryCustom;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;

import java.util.List;

/**
 * Reimburse repo
 */
public interface ReimburseRepository extends AbstractRepository<Reimburse>, ReimburseRepositoryCustom {

	List<Reimburse> findListByBizRefIdAndPayToOrderByCreatedAtAsc(String bizRefId, User payTo);

	List<Reimburse> findListByPaperOrderByCreatedAtAsc(Paper paper);

	Reimburse findFirstByAccountSubject(AccountSubject target);

	Reimburse findFirstBySubAccountSubject(AccountSubject target);

	Reimburse findFirstByBizDepartment(BizDepartment target);

	Reimburse findFirstByCompany(Company target);

	Reimburse findFirstByProject(Project target);

	Reimburse findFirstByContract(Contract target);

	Reimburse findFirstBySubContract(SubContract target);

	Reimburse findFirstBySubContractor(SubContractor target);

	Reimburse findFirstByOrder(Order target);

	Reimburse findFirstBySubOrder(SubOrder target);

	Reimburse findFirstByBizRefId(String bizRefId);

	Reimburse findFirstByPendingPayment(PendingPayment target);

}