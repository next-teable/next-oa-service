package in.clouthink.nextoa.rbac.impl.support;


import in.clouthink.nextoa.rbac.core.model.Resource;

/**
 *
 */
public interface ResourceMatcher {

	boolean matched(Resource resource, String expression);

}
