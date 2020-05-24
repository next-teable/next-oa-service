package in.clouthink.nextoa.bl.repository;

import in.clouthink.nextoa.bl.model.Group;
import in.clouthink.nextoa.bl.model.Organization;
import in.clouthink.nextoa.shared.repository.AbstractRepository;

import java.util.List;

/**
 *
 */
public interface GroupRepository extends AbstractRepository<Group> {

	List<Group> findByOrganization(Organization organization);

	long countByOrganization(Organization organization);


}
