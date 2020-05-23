package in.clouthink.nextoa.rbac.impl.service.impl;

import in.clouthink.nextoa.model.SysRole;
import cn.com.starest.nextoa.rbac.core.model.*;
import in.clouthink.nextoa.rbac.core.model.*;
import in.clouthink.nextoa.rbac.core.spi.RbacService;
import in.clouthink.nextoa.rbac.core.spi.ResourceService;
import in.clouthink.nextoa.rbac.impl.model.ResourceRoleRelationship;
import in.clouthink.nextoa.rbac.impl.repository.ResourceRoleRelationshipRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@Service
public class RbacServiceImpl implements RbacService {

	@Autowired
	private ResourceService resourceService;

	@Autowired
	private ResourceRoleRelationshipRepository resourceRoleRelationshipRepository;

	@Override
	public Resource getMatchedResource(String resourceUri) {
		return resourceService.getFirstMatchedResource(resourceUri);
	}

	@Override
	public List<Permission> getPermissions(String resourceCode) {
		List<ResourceRoleRelationship> relationshipList = resourceRoleRelationshipRepository.findByResourceCode(
				resourceCode);
		if (relationshipList == null) {
			return Collections.unmodifiableList(Collections.emptyList());
		}

		return relationshipList.stream().map(relationship -> {
			DefaultPermission permission = new DefaultPermission();
			permission.setResource(resourceService.findByCode(relationship.getResourceCode()));
			permission.setRole(relationship.getRoleCode());
			return permission;
		}).collect(Collectors.toList());
	}

	@Override
	public List<Permission> getPermissions(Resource resource) {
		List<ResourceRoleRelationship> relationshipList = resourceRoleRelationshipRepository.findByResourceCode(resource.getCode());
		if (relationshipList == null) {
			return Collections.unmodifiableList(Collections.emptyList());
		}

		return relationshipList.stream().map(relationship -> {
			DefaultPermission permission = new DefaultPermission();
			permission.setResource(resource);
			permission.setRole(relationship.getRoleCode());
			return permission;
		}).collect(Collectors.toList());
	}

	@Override
	public List<ResourceWithChildren> getAllowedResources(GrantedAuthority role) {
		if (role == null) {
			return null;
		}

		return getAllowedResources(Arrays.asList(role));
	}

	@Override
	public List<ResourceWithChildren> getAllowedResources(List<GrantedAuthority> roles) {
		if (roles == null || roles.isEmpty()) {
			return null;
		}

		for (GrantedAuthority role : roles) {
			if (SysRole.ROLE_ADMIN == role) {
				List<ResourceWithChildren> result = new ArrayList<>();
				resourceService.getRootResources()
							   .stream()
							   .forEach(resource -> result.add((ResourceWithChildren) resource));
				return result;
			}
		}

		return doGetAllowedResources(resourceService.getRootResources(), roles);
	}


	private List<ResourceWithChildren> doGetAllowedResources(List<? extends Resource> existedResources,
															 List<GrantedAuthority> roles) {
		List<ResourceWithChildren> result = new ArrayList<>();
		existedResources.stream().forEach(resource -> {
			if (resource.isOpen()) {
				result.add((ResourceWithChildren) resource);
				return;
			}

			boolean granted = false;
			String resourceCode = resource.getCode();
			for (GrantedAuthority role : roles) {
				ResourceRoleRelationship resourceRoleRelationship = resourceRoleRelationshipRepository.findByResourceCodeAndRoleCode(
						resourceCode,
						RbacUtils.buildRoleCode(role));
				if (resourceRoleRelationship != null) {
					granted = true;
					break;
				}
			}

			if (granted) {
				DefaultResourceWithChildren resourceWithChildren = new DefaultResourceWithChildren();
				BeanUtils.copyProperties(resource, resourceWithChildren, "children");
				if (((ResourceWithChildren) resource).hasChildren()) {
					resourceWithChildren.setChildren(doGetAllowedResources(((ResourceWithChildren) resource).getChildren(),
																		   roles));
				}
				//虚父节点有权限,但是子节点无权限,虚父节点不需要返回
				if (resourceWithChildren.isVirtual() && !resourceWithChildren.hasChildren()) {
					return;
				}
				result.add(resourceWithChildren);
			}
		});

		return result;
	}


	@Override
	public boolean isAllowed(String resourceCode, GrantedAuthority role) {
		if (SysRole.ROLE_ADMIN.getCode().equalsIgnoreCase(role.getAuthority())) {
			return true;
		}
		ResourceRoleRelationship result = resourceRoleRelationshipRepository.findByResourceCodeAndRoleCode(resourceCode,
																										   RbacUtils.buildRoleCode(
																												   role));
		return result != null;
	}

	@Override
	public boolean isAllowed(Resource resource, GrantedAuthority role) {
		if (SysRole.ROLE_ADMIN.getCode().equalsIgnoreCase(role.getAuthority())) {
			return true;
		}
		ResourceRoleRelationship result = resourceRoleRelationshipRepository.findByResourceCodeAndRoleCode(resource.getCode(),
																										   RbacUtils.buildRoleCode(
																												   role));
		return (result != null);
	}

}
