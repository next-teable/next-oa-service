package in.clouthink.nextoa.bl.openapi.dto;

import in.clouthink.nextoa.bl.model.RoleType;
import in.clouthink.nextoa.security.rbac.impl.model.TypedRole;

/**
 */
public class TypedRoleSummary {

	public static TypedRoleSummary from(TypedRole role) {
		TypedRoleSummary result = new TypedRoleSummary();
		result.setRoleType(role.getRoleType());
		result.setCode(role.getCode());
		result.setName(role.getName());
		return result;
	}

	private RoleType roleType;

	private String code;

	private String name;

	public RoleType getRoleType() {
		return roleType;
	}

	public void setRoleType(RoleType roleType) {
		this.roleType = roleType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
