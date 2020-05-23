package in.clouthink.nextoa.rbac.core.support;

import in.clouthink.nextoa.rbac.core.exception.RbacException;

/**
 *
 */
public class RoleCodeParser implements RoleParser<TypedCode> {

	@Override
	public TypedCode parse(String roleCode) {
		String[] splitdRoleCode = roleCode.split(":");
		if (splitdRoleCode.length != 2) {
			throw new RbacException("无效的角色编码表达式");
		}

		return new TypedCode(splitdRoleCode[0], splitdRoleCode[1]);
	}

}

