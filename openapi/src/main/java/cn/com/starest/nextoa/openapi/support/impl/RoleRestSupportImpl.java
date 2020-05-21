package cn.com.starest.nextoa.openapi.support.impl;

import cn.com.starest.nextoa.exception.RoleException;
import cn.com.starest.nextoa.model.AppRole;
import cn.com.starest.nextoa.model.SysRole;
import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.model.dtr.RoleQueryRequest;
import cn.com.starest.nextoa.openapi.dto.*;
import cn.com.starest.nextoa.openapi.support.RoleRestSupport;
import cn.com.starest.nextoa.rbac.impl.service.RoleService;
import cn.com.starest.nextoa.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleRestSupportImpl implements RoleRestSupport {

	@Autowired
	private RoleService appRoleService;

	@Autowired
	private AccountService accountService;

	@Override
	public List<RoleSummary> getSysRoles() {
		return Arrays.asList(SysRole.values())
					 .stream()
					 .filter(role -> role != SysRole.ROLE_USER)
					 .map(RoleSummary::from)
					 .collect(Collectors.toList());
	}

	@Override
	public List<RoleSummary> getSysRoles4Privilege() {
		return Arrays.asList(SysRole.values())
					 .stream()
					 .filter(role -> role != SysRole.ROLE_ADMIN)
					 .map(RoleSummary::from)
					 .collect(Collectors.toList());
	}

	@Override
	public Page<RoleSummary> getAppRoles(RoleQueryRequest request) {
		Page<AppRole> appRoles = appRoleService.listAppRoles(request);
		return new PageImpl<>(appRoles.getContent().stream().map(RoleSummary::from).collect(Collectors.toList()),
							  new PageRequest(request.getStart(), request.getLimit()),
							  appRoles.getTotalElements());
	}

	@Override
	public List<RoleSummary> getAppRolesList() {
		return appRoleService.listAppRoles().stream().map(RoleSummary::from).collect(Collectors.toList());
	}

	@Override
	public Page<UserSummary> getUsersBySysRoleId(String roleCode, UserQueryParameter request) {
		SysRole role = null;

		try {
			role = SysRole.valueOf(roleCode);
		}
		catch (RuntimeException e) {
			throw new RoleException(String.format("无效的系统角色名'%s'", roleCode));
		}
		Page<User> users = accountService.listUsersByRole(role, request);
		return new PageImpl<>(users.getContent().stream().map(UserSummary::from).collect(Collectors.toList()),
							  new PageRequest(request.getStart(), request.getLimit()),
							  users.getTotalElements());
	}

	@Override
	public Page<UserSummary> getUsersByAppRoleId(String roleId, UserQueryParameter request) {
		Page<User> users = appRoleService.listBindUsers(roleId, request);
		return new PageImpl<>(users.getContent().stream().map(UserSummary::from).collect(Collectors.toList()),
							  new PageRequest(request.getStart(), request.getLimit()),
							  users.getTotalElements());
	}

	@Override
	public AppRole createAppRole(SaveRoleParameter request) {
		return appRoleService.createAppRole(request);
	}

	@Override
	public void updateAppRole(String id, SaveRoleParameter request) {
		appRoleService.updateAppRole(id, request);
	}

	@Override
	public void deleteAppRole(String id) {
		appRoleService.deleteAppRole(id);
	}

	@Override
	public void bindUsers4AppRole(String id, UsersForRoleParameter request) {
		appRoleService.bindUsers4AppRole(id, request.getUserIds());
	}

	@Override
	public void unBindUsers4AppRole(String id, UsersForRoleParameter request) {
		appRoleService.unBindUsers4AppRole(id, request.getUserIds());
	}

	@Override
	public void bindUsers4SysRole(String id, UsersForRoleParameter request) {
		appRoleService.bindUsers4SysRole(id, request.getUserIds());
	}

	@Override
	public void unBindUsers4SysRole(String id, UsersForRoleParameter request) {
		appRoleService.unBindUsers4SysRole(id, request.getUserIds());
	}
}
