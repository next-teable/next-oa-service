package in.clouthink.nextoa.security.rbac.impl.service.impl;

import in.clouthink.nextoa.bl.model.AppRole;
import in.clouthink.nextoa.bl.model.RoleType;
import in.clouthink.nextoa.bl.model.SysRole;
import in.clouthink.nextoa.security.rbac.impl.model.TypedRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.StringUtils;

/**
 */
public class RbacUtils {

	public static String buildRoleCode(GrantedAuthority role) {
		String result = role.getAuthority();
		if (role instanceof SysRole) {
			result = RoleType.SYS_ROLE.name() + ":" + result;
		}
		else {
			result = RoleType.APP_ROLE.name() + ":" + result;
		}
		return result;
	}

	public static TypedRole convertToTypedRole(GrantedAuthority authority) {
		if (authority instanceof SysRole) {
			TypedRole typedRole = TypedRole.newSysRole();
			typedRole.setCode(authority.getAuthority());
			return typedRole;
		}
		if (authority instanceof AppRole) {
			TypedRole typedRole = TypedRole.newAppRole();
			typedRole.setCode(authority.getAuthority());
			return typedRole;
		}
		return null;
	}

	public static boolean checkTypedRoleCodeFormat(String roleCode) {
		String[] splitRoleCode = roleCode.split(":");
		if (splitRoleCode.length != 2) {
			return false;
		}
		if (!RoleType.APP_ROLE.name().equalsIgnoreCase(splitRoleCode[0].toUpperCase()) &&
			!RoleType.SYS_ROLE.name().equalsIgnoreCase(splitRoleCode[0].toUpperCase())) {
			return false;
		}
		if (StringUtils.isEmpty(splitRoleCode[1])) {
			return false;
		}
		return true;
	}

}
