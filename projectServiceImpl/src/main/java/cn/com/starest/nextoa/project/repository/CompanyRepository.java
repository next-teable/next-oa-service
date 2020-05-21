package cn.com.starest.nextoa.project.repository;

import cn.com.starest.nextoa.project.domain.model.Company;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * CompanyRepository
 */
public interface CompanyRepository extends AbstractRepository<Company> {

	Company findFirstByName(String name);

	Page<Company> findPageByNameLike(String name, Pageable pageable);
}