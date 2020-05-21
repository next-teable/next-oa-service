package cn.com.starest.nextoa.project.repository;

import cn.com.starest.nextoa.project.domain.model.ContractType;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;

/**
 *
 */
public interface ContractTypeRepository extends AbstractRepository<ContractType> {

	ContractType findFirstByName(String name);
}