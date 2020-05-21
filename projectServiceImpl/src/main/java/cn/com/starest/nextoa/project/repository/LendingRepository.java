package cn.com.starest.nextoa.project.repository;

import cn.com.starest.nextoa.project.domain.model.Company;
import cn.com.starest.nextoa.project.domain.model.Lending;
import cn.com.starest.nextoa.project.domain.model.Project;
import cn.com.starest.nextoa.project.repository.custom.LendingRepositoryCustom;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;

/**
 * Lending repo
 */
public interface LendingRepository extends AbstractRepository<Lending>, LendingRepositoryCustom {

	Lending findFirstByCompany(Company target);

	Lending findFirstByLendingTo(Company target);
}