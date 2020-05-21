package cn.com.starest.nextoa.project.repository;

import cn.com.starest.nextoa.project.domain.model.*;
import cn.com.starest.nextoa.project.repository.custom.SubContractRepositoryCustom;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;

import java.util.List;

/**
 *
 */
public interface SubContractRepository extends AbstractRepository<SubContract>, SubContractRepositoryCustom {

	SubContract findFirstByCode(String code);

	SubContract findFirstByName(String name);

	List<SubContract> findListByProject(Project project);

	List<SubContract> findListByContract(Contract contract);

	SubContract findFirstByProject(Project project);

	SubContract findFirstByContract(Contract contract);

	SubContract findFirstBySubContractor(SubContractor target);

}