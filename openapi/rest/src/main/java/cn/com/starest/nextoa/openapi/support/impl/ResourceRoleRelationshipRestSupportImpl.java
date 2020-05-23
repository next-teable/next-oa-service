package cn.com.starest.nextoa.openapi.support.impl;

import cn.com.starest.nextoa.model.AppRole;
import cn.com.starest.nextoa.model.RoleType;
import cn.com.starest.nextoa.model.SysRole;
import cn.com.starest.nextoa.openapi.dto.ResourceSummary;
import cn.com.starest.nextoa.openapi.dto.TypedRoleSummary;
import cn.com.starest.nextoa.openapi.support.ResourceRoleRelationshipRestSupport;
import cn.com.starest.nextoa.rbac.core.model.Resource;
import cn.com.starest.nextoa.rbac.core.model.ResourceWithChildren;
import cn.com.starest.nextoa.rbac.core.support.RoleCodeParser;
import cn.com.starest.nextoa.rbac.core.support.TypedCode;
import cn.com.starest.nextoa.rbac.impl.model.TypedRole;
import cn.com.starest.nextoa.rbac.impl.service.ResourceRoleRelationshipService;
import cn.com.starest.nextoa.rbac.impl.service.RoleService;
import cn.com.starest.nextoa.rbac.impl.service.impl.RbacUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 */
@Service
public class ResourceRoleRelationshipRestSupportImpl implements ResourceRoleRelationshipRestSupport {

	private RoleCodeParser roleCodeParser = new RoleCodeParser();

	@Autowired
	private RoleService roleService;

	@Autowired
	private ResourceRoleRelationshipService resourceRoleRelationshipService;

	@Override
	public List<ResourceSummary> listResourceSummaries() {
		return resourceRoleRelationshipService.listRootResources()
											  .stream()
											  .filter(resource -> !resource.isOpen())
											  .map(resource -> ResourceSummary.from((ResourceWithChildren) resource))
											  .collect(Collectors.toList());
	}

	@Override
	public List<ResourceSummary> listResourceSummariesByRole(String roleCode) {
		Set<String> resourceCodes = new HashSet<>();
        resourceRoleRelationshipService.listAllowedResource(roleCode)
                                       .stream()
                                       .filter(resource -> resource != null)
                                       .forEach(resource -> resourceCodes.add(resource.getCode()));
		//The allowed resources list by role is not ordered , so we have to filter the resource definition tree
		return resourceRoleRelationshipService.listRootResources()
											  .stream()
											  .filter(resource -> !resource.isOpen())
											  .filter(resource -> resourceCodes.contains(resource.getCode()))
											  .map(resource -> ResourceSummary.from((ResourceWithChildren) resource,
																					(Resource r) -> resourceCodes.contains(
																							r.getCode())))
											  .collect(Collectors.toList());
	}

	@Override
	public List<TypedRoleSummary> listGrantedRoles(String code) {
		return resourceRoleRelationshipService.listAllowedRoles(code)
											  .stream()
											  .map(authority -> RbacUtils.convertToTypedRole(authority))
											  .map(role -> TypedRoleSummary.from((TypedRole) role))
											  .collect(Collectors.toList());
	}

	@Override
	public void grantRolesToResource(String code, String[] typedRoleCodes) {
		if (typedRoleCodes == null || typedRoleCodes.length == 0) {
			return;
		}
		for (String typedRoleCode : typedRoleCodes) {
			TypedCode typedCode = roleCodeParser.parse(typedRoleCode);
			if (RoleType.APP_ROLE.name().equals(typedCode.getType())) {
				AppRole appRole = roleService.findByCode(typedCode.getCode());
				if (appRole != null) {
					resourceRoleRelationshipService.bindResourceAndRole(code, appRole);
				}
			}
			else if (RoleType.SYS_ROLE.name().equals(typedCode.getType())) {
				SysRole sysRole = SysRole.valueOf(typedCode.getCode());
				if (sysRole != null) {
					resourceRoleRelationshipService.bindResourceAndRole(code, sysRole);
				}
			}
		}
	}

	@Override
	public void revokeRolesFromResource(String code, String[] typedRoleCodes) {
		if (typedRoleCodes == null || typedRoleCodes.length == 0) {
			return;
		}
		for (String typedRoleCode : typedRoleCodes) {
			TypedCode typedCode = roleCodeParser.parse(typedRoleCode);
			if (RoleType.APP_ROLE.name().equals(typedCode.getType())) {
				AppRole appRole = roleService.findByCode(typedCode.getCode());
				if (appRole != null) {
					resourceRoleRelationshipService.unbindResourceAndRole(code, appRole);
				}
			}
			else if (RoleType.SYS_ROLE.name().equals(typedCode.getType())) {
				SysRole sysRole = SysRole.valueOf(typedCode.getCode());
				if (sysRole != null) {
					resourceRoleRelationshipService.unbindResourceAndRole(code, sysRole);
				}
			}
		}
	}
}
