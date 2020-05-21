package cn.com.starest.nextoa.rbac.impl.support;


import cn.com.starest.nextoa.rbac.core.model.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;

/**
 * The resource matcher based on spring AntPathMatcher.
 */
public class ResourceAntMatcher implements ResourceMatcher {

	private static final Log logger = LogFactory.getLog(ResourceAntMatcher.class);

	private AntPathMatcher matcher = new AntPathMatcher();

	public boolean matched(Resource resource, String requestUrl) {
		if (resource.getPatterns() == null) {
			return false;
		}
		if (StringUtils.isEmpty(requestUrl)) {
			logger.warn("The incoming expression is empty");
			return false;
		}
		for (String pattern : resource.getPatterns()) {
			if (StringUtils.isEmpty(pattern)) {
				continue;
			}
			if (matcher.match(pattern, requestUrl)) {
				logger.info(String.format("The resource pattern '%s' is matched on the incoming expression '%s'",
										  pattern,
										  requestUrl));
				return true;
			}
		}
		return false;
	}

}
