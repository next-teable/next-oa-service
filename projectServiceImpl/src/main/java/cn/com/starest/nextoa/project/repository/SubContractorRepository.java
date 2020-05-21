package cn.com.starest.nextoa.project.repository;

import cn.com.starest.nextoa.project.domain.model.SubContractor;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 */
public interface SubContractorRepository extends AbstractRepository<SubContractor> {

	Page<SubContractor> findPageByNameLike(String name, Pageable pageable);

	SubContractor findFirstByName(String name);
}