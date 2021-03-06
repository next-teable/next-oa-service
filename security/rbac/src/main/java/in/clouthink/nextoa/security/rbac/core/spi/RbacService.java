package in.clouthink.nextoa.security.rbac.core.spi;


import in.clouthink.nextoa.security.rbac.core.model.Permission;
import in.clouthink.nextoa.security.rbac.core.model.Resource;
import in.clouthink.nextoa.security.rbac.core.model.ResourceWithChildren;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 */
public interface RbacService {

	Resource getMatchedResource(String resourceUri);

	List<Permission> getPermissions(String resourceCode);

	List<Permission> getPermissions(Resource resource);

	List<ResourceWithChildren> getAllowedResources(GrantedAuthority role);

	List<ResourceWithChildren> getAllowedResources(List<GrantedAuthority> roles);

	boolean isAllowed(String resourceCode, GrantedAuthority role);

	boolean isAllowed(Resource resource, GrantedAuthority role);

}
