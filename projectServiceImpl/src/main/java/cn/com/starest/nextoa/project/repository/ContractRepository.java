package cn.com.starest.nextoa.project.repository;

import cn.com.starest.nextoa.project.domain.model.Contract;
import cn.com.starest.nextoa.project.domain.model.FirstParty;
import cn.com.starest.nextoa.project.domain.model.Project;
import cn.com.starest.nextoa.project.repository.custom.ContractRepositoryCustom;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;

import java.util.List;

/**
 *
 */
public interface ContractRepository extends AbstractRepository<Contract>, ContractRepositoryCustom {

	Contract findFirstByCode(String code);

	Contract findFirstByName(String name);

	List<Contract> findListByProjectsIn(Project project);

	Contract findFirstByProjectsIn(Project target);

	Contract findFirstByFirstParty(FirstParty firstParty);

}