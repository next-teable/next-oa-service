package in.clouthink.nextoa.bl.openapi.support.impl;

import in.clouthink.nextoa.bl.model.SysRole;
import in.clouthink.nextoa.bl.model.User;
import in.clouthink.nextoa.bl.model.UserType;
import in.clouthink.nextoa.bl.openapi.dto.*;
import in.clouthink.nextoa.bl.openapi.support.SysUserRestSupport;
import in.clouthink.nextoa.bl.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 *
 */
@Service
public class SysUserRestSupportImpl implements SysUserRestSupport {

	@Autowired
	private AccountService accountService;

	@Override
	public Page<UserSummary> listSysUsers(UserQueryParameter queryRequest) {
		queryRequest.setUserType(UserType.SYSUSER);
		Page<User> userPage = accountService.listUsers(queryRequest);
		return new PageImpl<>(userPage.getContent().stream().map(UserSummary::from).collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  userPage.getTotalElements());
	}

	@Override
	public UserDetail getSysUserDetail(String id) {
		User user = accountService.findById(id);
		return UserDetail.from(user);
	}

	@Override
	public User createSysUser(SaveSysUserParameter request) {
		return accountService.createAccount(UserType.SYSUSER, request, SysRole.ROLE_USER);
	}

	@Override
	public void updateSysUser(String id, SaveSysUserParameter request) {
		accountService.updateAccount(id, request);
	}

	@Override
	public void deleteSysUser(String id, User byWho) {
		accountService.archiveAccount(id, byWho);
	}

	@Override
	public void changePassword(String id, ChangePasswordRequest request) {
		accountService.changePassword(id, request.getNewPassword());
	}

	@Override
	public void enableSysUser(String id) {
		accountService.enable(id);
	}

	@Override
	public void disableSysUser(String id) {
		accountService.disable(id);
	}

	@Override
	public void lockSysUser(String id) {
		accountService.lock(id);
	}

	@Override
	public void unlockSysUser(String id) {
		accountService.unlock(id);
	}
}
