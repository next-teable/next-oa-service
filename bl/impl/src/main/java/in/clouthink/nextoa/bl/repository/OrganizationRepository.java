package in.clouthink.nextoa.bl.repository;

import in.clouthink.nextoa.bl.model.Organization;
import in.clouthink.nextoa.shared.repository.AbstractRepository;

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
