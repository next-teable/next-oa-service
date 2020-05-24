package in.clouthink.nextoa.security.rbac.core.support;

/**
 *
 */
public class SimpleRoleParser implements RoleParser<String> {

	@Override
	public String parse(String roleCode) {
		return roleCode;
	}

}
