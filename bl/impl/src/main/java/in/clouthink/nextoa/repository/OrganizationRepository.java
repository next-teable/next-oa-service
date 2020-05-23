package in.clouthink.nextoa.repository;

import in.clouthink.nextoa.model.Organization;
import in.clouthink.nextoa.repository.shared.AbstractRepository;

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
