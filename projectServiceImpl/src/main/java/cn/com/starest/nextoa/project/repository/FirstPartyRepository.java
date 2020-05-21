package cn.com.starest.nextoa.project.repository;

import cn.com.starest.nextoa.project.domain.model.FirstParty;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * FirstParty repo
 */
public interface FirstPartyRepository extends AbstractRepository<FirstParty> {

	Page<FirstParty> findPageByNameLike(String name, Pageable pageable);

	Page<FirstParty> findPageByProvince(String province, Pageable pageable);

	FirstParty findFirstByName(String name);
}