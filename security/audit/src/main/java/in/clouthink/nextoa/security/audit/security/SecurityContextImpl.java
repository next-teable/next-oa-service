package in.clouthink.nextoa.security.audit.security;

import in.clouthink.daas.audit.security.SecurityContext;
import in.clouthink.nextoa.security.spring.UserDetails;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 */
public class SecurityContextImpl implements SecurityContext {

	private static final Log logger = LogFactory.getLog(SecurityContextImpl.class);

	@Override
	public String getPrincipal() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			Object principal = authentication.getPrincipal();
			if (principal instanceof UserDetails) {
				return ((UserDetails) principal).getUsername();
			}
		}

		logger.warn("Current User is not set, authentication: {}");
		return null;
	}

}
