package cn.com.starest.nextoa.repository;

import cn.com.starest.nextoa.model.Group;
import cn.com.starest.nextoa.model.Organization;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;

import java.util.List;

/**
 *
 */
public interface GroupRepository extends AbstractRepository<Group> {

	List<Group> findByOrganization(Organization organization);

	long countByOrganization(Organization organization);


}