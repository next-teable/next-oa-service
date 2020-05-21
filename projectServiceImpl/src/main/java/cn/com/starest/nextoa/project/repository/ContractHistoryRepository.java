package cn.com.starest.nextoa.project.repository;

import cn.com.starest.nextoa.project.domain.model.Contract;
import cn.com.starest.nextoa.project.domain.model.ContractHistory;
import cn.com.starest.nextoa.project.domain.model.FirstParty;
import cn.com.starest.nextoa.project.domain.model.Project;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 */
public interface ContractHistoryRepository extends AbstractRepository<ContractHistory> {

	void deleteByContract(Contract contract);

	ContractHistory findFirstByProjectsIn(Project target);

	ContractHistory findFirstByFirstParty(FirstParty firstParty);

	Page<ContractHistory> findPageByContract(Contract contract, Pageable pageable);

	ContractHistory findFirstByContract(Contract target);
}