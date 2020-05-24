package in.clouthink.nextoa.security.rbac.impl.support;


import in.clouthink.nextoa.security.rbac.core.model.Resource;

/**
 *
 */
public interface ResourceMatcher {

	boolean matched(Resource resource, String expression);

}
