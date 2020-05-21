package cn.com.starest.nextoa.openapi.dto;

import cn.com.starest.nextoa.model.AppRole;
import cn.com.starest.nextoa.model.RoleType;
import cn.com.starest.nextoa.model.SysRole;
import cn.com.starest.nextoa.model.shared.StringIdentifier;

public class RoleSummary extends StringIdentifier {

	public static RoleSummary from(AppRole role) {
		if (role == null) {
			return null;
		}
		RoleSummary result = new RoleSummary();
		result.setRoleType(RoleType.APP_ROLE);
		result.setId(role.getId());
		result.setCode(role.getCode());
		result.setName(role.getName());
		result.setDescription(role.getDescription());
		return result;
	}

	public static RoleSummary from(SysRole role) {
		if (role == null) {
			return null;
		}
		RoleSummary result = new RoleSummary();
		result.setRoleType(RoleType.SYS_ROLE);
		result.setCode(role.getCode());
		result.setName(role.getDisplayName());
		return result;
	}

	private String code;

	private String name;

	private String description;

	private RoleType roleType;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public RoleType getRoleType() {
		return roleType;
	}

	public void setRoleType(RoleType roleType) {
		this.roleType = roleType;
	}
}
