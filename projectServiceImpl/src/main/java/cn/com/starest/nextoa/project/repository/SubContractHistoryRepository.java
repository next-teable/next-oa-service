package cn.com.starest.nextoa.project.repository;

import cn.com.starest.nextoa.project.domain.model.*;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 */
public interface SubContractHistoryRepository extends AbstractRepository<SubContractHistory> {

	Page<SubContractHistory> findPageBySubContract(SubContract order, Pageable pageable);

	void deleteBySubContract(SubContract order);

	SubContractHistory findFirstByProject(Project project);

	SubContractHistory findFirstByContract(Contract contract);

	SubContractHistory findFirstBySubContractor(SubContractor subContractor);

	SubContractHistory findFirstBySubContract(SubContract subContract);

}
