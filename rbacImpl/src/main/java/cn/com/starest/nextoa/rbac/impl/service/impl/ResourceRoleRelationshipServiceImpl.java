package cn.com.starest.nextoa.rbac.impl.service.impl;

import cn.com.starest.nextoa.model.AppRole;
import cn.com.starest.nextoa.model.SysRole;
import cn.com.starest.nextoa.rbac.core.model.Resource;
import cn.com.starest.nextoa.rbac.core.spi.ResourceService;
import cn.com.starest.nextoa.rbac.core.support.RoleCodeParser;
import cn.com.starest.nextoa.rbac.core.support.RoleParser;
import cn.com.starest.nextoa.rbac.core.support.TypedCode;
import cn.com.starest.nextoa.rbac.impl.model.ResourceRoleRelationship;
import cn.com.starest.nextoa.rbac.impl.model.TypedRole;
import cn.com.starest.nextoa.rbac.impl.repository.ResourceRoleRelationshipRepository;
import cn.com.starest.nextoa.rbac.impl.service.ResourceRoleRelationshipService;
import cn.com.starest.nextoa.rbac.impl.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 */
@Service
public class ResourceRoleRelationshipServiceImpl implements ResourceRoleRelationshipService {

	private RoleParser<TypedCode> roleParser = new RoleCodeParser();

	@Autowired
	private ResourceService resourceService;

	@Autowired
	private ResourceRoleRelationshipRepository resourceRoleRelationshipRepository;

	@Autowired
	private RoleService roleService;

	public void setRoleParser(RoleParser<TypedCode> roleParser) {
		this.roleParser = roleParser;
	}

	@Override
	public Resource findResourceByCode(String code) {
		return resourceService.findByCode(code);
	}

	@Override
	public List<? extends Resource> listRootResources() {
		return resourceService.getRootResources();
	}

	@Override
	public List<? extends Resource> listResourceChildren(String resourceCode) {
		return listResourceChildren(resourceService.findByCode(resourceCode));
	}

	@Override
	public List<? extends Resource> listResourceChildren(Resource parent) {
		return resourceService.getResourceChildren(parent);
	}

	@Override
	public List<? extends Resource> listAllowedResource(GrantedAuthority role) {
		String roleCode = RbacUtils.buildRoleCode(role);
		return listAllowedResource(roleCode);
	}

	@Override
	public List<? extends Resource> listAllowedResource(String roleCode) {
		return resourceRoleRelationshipRepository.findByRoleCode(roleCode)
												 .stream()
												 .map(relationship -> resourceService.findByCode(relationship.getResourceCode()))
												 .collect(Collectors.toList());
	}

	@Override
	public List<String> listAllowedRoleCodes(String resourceCode) {
		return resourceRoleRelationshipRepository.findByResourceCode(resourceCode)
												 .stream()
												 .map(relationship -> relationship.getRoleCode())
												 .collect(Collectors.toList());
	}

	@Override
	public List<String> listAllowedRoleCodes(Resource resource) {
		return listAllowedRoleCodes(resource.getCode());
	}

	@Override
	public List<GrantedAuthority> listAllowedRoles(String resourceCode) {
		return resourceRoleRelationshipRepository.findByResourceCode(resourceCode).stream().map(relationship -> {
			String role = relationship.getRoleCode();
			TypedCode typedCode = roleParser.parse(role.toUpperCase());
			if (TypedRole.isAppRole(typedCode.getType())) {
				AppRole appRole = roleService.findByCode(typedCode.getCode());
				if (appRole != null) {
					return appRole;
				}
			}
			else if (TypedRole.isSysRole(typedCode.getType())) {
				SysRole sysRole = SysRole.valueOf(typedCode.getCode());
				if (sysRole != null) {
					return sysRole;
				}
			}
			return null;
		}).collect(Collectors.toList());
	}

	@Override
	public List<GrantedAuthority> listAllowedRoles(Resource resource) {
		return listAllowedRoles(resource.getCode());
	}

	@Override
	public void bindResourceAndRole(String resourceCode, GrantedAuthority role) {
		String roleCode = RbacUtils.buildRoleCode(role);
		Resource resource = resourceService.findByCode(resourceCode);
		if (resource == null) {
			return;
		}
		ResourceRoleRelationship resourceRoleRelationship = resourceRoleRelationshipRepository.findByResourceCodeAndRoleCode(
				resourceCode,
				roleCode);
		if (resourceRoleRelationship == null) {
			resourceRoleRelationship = new ResourceRoleRelationship();
			resourceRoleRelationship.setResourceCode(resourceCode);
			resourceRoleRelationship.setRoleCode(roleCode);
			resourceRoleRelationshipRepository.save(resourceRoleRelationship);
		}

		Resource parentResource = resource.getParent();
		if (parentResource == null) {
			return;
		}

		//The parent will be granted automatically if the child is granted
		ResourceRoleRelationship parentResourceRoleRelationship = resourceRoleRelationshipRepository.findByResourceCodeAndRoleCode(
				parentResource.getCode(),
				roleCode);
		if (parentResourceRoleRelationship == null) {
			parentResourceRoleRelationship = new ResourceRoleRelationship();
			parentResourceRoleRelationship.setResourceCode(parentResource.getCode());
			parentResourceRoleRelationship.setRoleCode(roleCode);
			resourceRoleRelationshipRepository.save(parentResourceRoleRelationship);
		}
	}

	@Override
	public void unbindResourceAndRole(String resourceCode, GrantedAuthority role) {
		String roleCode = RbacUtils.buildRoleCode(role);
		ResourceRoleRelationship resourceRoleRelationship = resourceRoleRelationshipRepository.findByResourceCodeAndRoleCode(
				resourceCode,
				roleCode);
		if (resourceRoleRelationship == null) {
			return;
		}
		resourceRoleRelationshipRepository.delete(resourceRoleRelationship);

		Resource resource = resourceService.findByCode(resourceCode);
		if (resource == null) {
			return;
		}

		Resource parentResource = resource.getParent();
		if (parentResource == null) {
			return;
		}

		//The parent will be revoked automatically if the child is revoked
		ResourceRoleRelationship parentResourceRoleRelationship = resourceRoleRelationshipRepository.findByResourceCodeAndRoleCode(
				parentResource.getCode(),
				roleCode);
		if (parentResourceRoleRelationship != null) {
			//if no child is granted , the parent can be revoke
			List<? extends Resource> children = resourceService.getResourceChildren(parentResource);

			boolean hasChildGranted = false;
			for (Resource child : children) {
				ResourceRoleRelationship childResourceRoleRelationship = resourceRoleRelationshipRepository.findByResourceCodeAndRoleCode(
						child.getCode(),
						roleCode);
				if (childResourceRoleRelationship != null) {
					hasChildGranted = true;
					break;
				}
			}

			if (!hasChildGranted) {
				resourceRoleRelationshipRepository.delete(parentResourceRoleRelationship);
			}
		}
	}

}
