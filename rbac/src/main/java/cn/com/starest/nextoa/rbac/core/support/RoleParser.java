package cn.com.starest.nextoa.rbac.core.support;

/**
 *
 */
public interface RoleParser<T> {

	T parse(String roleCode);

}
