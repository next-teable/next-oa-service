package in.clouthink.nextoa.security.rbac.impl.spring.security;

import in.clouthink.nextoa.bl.model.SysRole;
import in.clouthink.nextoa.security.rbac.core.model.Resource;
import in.clouthink.nextoa.security.rbac.core.spi.RbacService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.WebSecurityExpressionRoot;

import java.util.Collection;

/**
 */
public class RbacWebSecurityExpressionRoot extends WebSecurityExpressionRoot {

	private RbacService rbacService;

	private FilterInvocation filterInvocation;

	public RbacWebSecurityExpressionRoot(Authentication a, FilterInvocation fi, RbacService rbacService) {
		super(a, fi);
		this.filterInvocation = fi;
		this.rbacService = rbacService;
	}

	public boolean isPassRbacCheck() {
		Collection<? extends GrantedAuthority> authorities = extractAuthorities(authentication);
		//no permission if the request is not from system role user
		if (!authorities.contains(SysRole.ROLE_USER)) {
			return false;
		}
		//the admin role will get the permission automatically
		if (authorities.contains(SysRole.ROLE_ADMIN)) {
			return true;
		}

		// Attempt to find a matching granted authority
		String requestUrl = filterInvocation.getRequestUrl();
		Resource resource = rbacService.getMatchedResource(requestUrl);
		if (resource != null) {
			for (GrantedAuthority authority : authorities) {
				if (rbacService.isAllowed(resource.getCode(), authority)) {
					return true;
				}
			}
		}

		return false;
	}

	Collection<? extends GrantedAuthority> extractAuthorities(Authentication authentication) {
		return authentication.getAuthorities();
	}

}
