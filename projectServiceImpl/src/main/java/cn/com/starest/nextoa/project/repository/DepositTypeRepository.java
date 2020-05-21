package cn.com.starest.nextoa.project.repository;

import cn.com.starest.nextoa.project.domain.model.DepositType;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;

/**
 *
 */
public interface DepositTypeRepository extends AbstractRepository<DepositType> {

	DepositType findFirstByName(String name);

}