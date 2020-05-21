package cn.com.starest.nextoa.project.repository;

import cn.com.starest.nextoa.project.domain.model.FrameworkContract;
import cn.com.starest.nextoa.project.domain.model.FrameworkContractType;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * FrameworkContractRepository
 */
public interface FrameworkContractRepository extends AbstractRepository<FrameworkContract> {

	FrameworkContract findFirstByName(String name);

	Page<FrameworkContract> findPageByType(FrameworkContractType type, Pageable pageable);

	Page<FrameworkContract> findPageByNameLike(String name, Pageable pageable);

	Page<FrameworkContract> findPageByTypeAndNameLike(FrameworkContractType type, String name, Pageable pageable);

}