package cn.com.starest.nextoa.openapi.support;

import cn.com.starest.nextoa.openapi.dto.ResourceSummary;
import cn.com.starest.nextoa.openapi.dto.TypedRoleSummary;

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
