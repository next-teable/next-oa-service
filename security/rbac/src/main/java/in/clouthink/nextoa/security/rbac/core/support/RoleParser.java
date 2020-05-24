package in.clouthink.nextoa.security.rbac.core.support;

/**
 *
 */
public interface RoleParser<T> {

	T parse(String roleCode);

}
