package in.clouthink.nextoa.rbac.impl.repository;

import in.clouthink.nextoa.rbac.impl.model.ResourceRoleRelationship;
import in.clouthink.nextoa.repository.shared.AbstractRepository;

import java.util.List;

/**
 *
 */
public interface ResourceRoleRelationshipRepository extends AbstractRepository<ResourceRoleRelationship> {

	ResourceRoleRelationship findByResourceCodeAndRoleCode(String resourceCode, String roleCode);

	List<ResourceRoleRelationship> findByResourceCode(String resourceCode);

	List<ResourceRoleRelationship> findByRoleCode(String roleCode);

	ResourceRoleRelationship findFirstByRoleCode(String roleCode);

}
