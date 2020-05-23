package in.clouthink.nextoa.repository;

import in.clouthink.nextoa.model.Group;
import in.clouthink.nextoa.model.Organization;
import in.clouthink.nextoa.repository.shared.AbstractRepository;

import java.util.List;

/**
 *
 */
public interface GroupRepository extends AbstractRepository<Group> {

	List<Group> findByOrganization(Organization organization);

	long countByOrganization(Organization organization);


}
