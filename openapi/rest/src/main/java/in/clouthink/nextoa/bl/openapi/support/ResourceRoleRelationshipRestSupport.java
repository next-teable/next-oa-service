package in.clouthink.nextoa.bl.openapi.support;

import in.clouthink.nextoa.bl.openapi.dto.ResourceSummary;
import in.clouthink.nextoa.bl.openapi.dto.TypedRoleSummary;

import java.util.List;

/**
 */
public interface ResourceRoleRelationshipRestSupport {

	/**
	 * @return
	 */
	List<ResourceSummary> listResourceSummaries();

	/**
	 * @param roleCode
	 * @return
	 */
	List<ResourceSummary> listResourceSummariesByRole(String roleCode);

	/**
	 * @param code
	 * @return
	 */
	List<TypedRoleSummary> listGrantedRoles(String code);

	/**
	 * @param code
	 * @param typedRoleCodes
	 */
	void grantRolesToResource(String code, String[] typedRoleCodes);

	/**
	 * @param code
	 * @param typedRoleCodes
	 */
	void revokeRolesFromResource(String code, String[] typedRoleCodes);

}
