package in.clouthink.nextoa.security.rbac.impl.repository;

import in.clouthink.nextoa.security.rbac.impl.model.ResourceRoleRelationship;
import in.clouthink.nextoa.shared.repository.AbstractRepository;

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
