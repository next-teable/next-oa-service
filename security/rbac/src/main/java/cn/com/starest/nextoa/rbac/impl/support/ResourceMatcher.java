package cn.com.starest.nextoa.rbac.impl.support;


import cn.com.starest.nextoa.rbac.core.model.Resource;

/**
 *
 */
public interface ResourceMatcher {

	boolean matched(Resource resource, String expression);

}
