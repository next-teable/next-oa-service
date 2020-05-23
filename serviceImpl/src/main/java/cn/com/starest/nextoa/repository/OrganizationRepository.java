package cn.com.starest.nextoa.repository;

import cn.com.starest.nextoa.model.Organization;
import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;

import java.util.List;

/**
 *
 */
public interface OrganizationRepository extends AbstractRepository<Organization> {

	List<Organization> findByParentOrderByCodeAscNameAsc(Organization parent);

	long countByParent(Organization parent);

	Organization findByName(String name);

	Organization findByParentAndName(Organization parent, String name);

}