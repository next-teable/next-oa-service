package cn.com.starest.nextoa.project.repository;

import cn.com.starest.nextoa.project.domain.model.BiddingAgent;
import cn.com.starest.nextoa.project.domain.model.Company;
import cn.com.starest.nextoa.project.domain.model.Deposit;
import cn.com.starest.nextoa.project.domain.model.Project;
import cn.com.starest.nextoa.project.repository.custom.DepositRepositoryCustom;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;

import java.util.List;

/**
 * Deposit repo
 */
public interface DepositRepository extends AbstractRepository<Deposit>, DepositRepositoryCustom {

	List<Deposit> findListByProjectOrderByCreatedAtAsc(Project project);

	Deposit findFirstByCompany(Company target);

	Deposit findFirstByProject(Project target);

	Deposit findFirstByBiddingAgent(BiddingAgent target);
}