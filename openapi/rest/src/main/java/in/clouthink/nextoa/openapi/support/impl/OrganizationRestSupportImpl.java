package in.clouthink.nextoa.openapi.support.impl;

import in.clouthink.nextoa.exception.UserNotFoundException;
import in.clouthink.nextoa.model.Organization;
import in.clouthink.nextoa.model.User;
import cn.com.starest.nextoa.openapi.dto.*;
import in.clouthink.nextoa.openapi.dto.*;
import in.clouthink.nextoa.openapi.support.OrganizationRestSupport;
import in.clouthink.nextoa.service.AccountService;
import in.clouthink.nextoa.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@Service
public class OrganizationRestSupportImpl implements OrganizationRestSupport {

	@Autowired
	private OrganizationService organizationService;

	@Autowired
	private AccountService accountService;

	@Override
	public List<OrganizationSummary> listRootOrganizations() {
		return organizationService.listRootOrgainizations()
								  .stream()
								  .map(OrganizationSummary::from)
								  .collect(Collectors.toList());
	}

	@Override
	public List<OrganizationSummary> listOrganizationChildren(String id) {
		return organizationService.listOrgainizationChildren(id)
								  .stream()
								  .map(OrganizationSummary::from)
								  .collect(Collectors.toList());
	}

	@Override
	public Page<UserSummary> listUsersOfOrganization(String id, UsernamePageQueryParameter queryRequest) {
		Page<User> userPage = organizationService.listUsersOfOrganization(id, queryRequest);
		return new PageImpl<>(userPage.getContent().stream().map(UserSummary::from).collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  userPage.getTotalElements());
	}

	@Override
	public String createOrganization(SaveOrganizationParameter request, User user) {
		return organizationService.createOrganization(request, user).getId();
	}

	@Override
	public void updateOrganization(String id, SaveOrganizationParameter request, User user) {
		organizationService.updateOrganization(id, request, user);
	}

	@Override
	public void deleteOrganization(String id, User user) {
		organizationService.deleteOrganization(id, user);
	}

	@Override
	public String createOrganizationChild(String id, SaveOrganizationParameter request, User user) {
		return organizationService.createOrganizationChild(id, request, user).getId();
	}

	@Override
	public String createAppUser(String id, SaveAppUserParameter request, User user) {
		return organizationService.createAppUser(id, request, user).getId();
	}

	@Override
	public UserDetail getAppUser(String id) {
		User user = organizationService.findAppUserById(id);
		return UserDetail.from(user);
	}

	@Override
	public void updateAppUser(String id, SaveAppUserParameter request, User user) {
		organizationService.updateAppUser(id, request, user);
	}

	@Override
	public void updateAppUserAvatar(String userId, String avatarId, User user) {
		accountService.changeUserAvatar(userId, avatarId);
	}

	@Override
	public void deleteAppUser(String id, User user) {
		organizationService.deleteAppUser(id, user);
	}

	@Override
	public void changeAppUserPassword(String id, ChangePasswordRequest request) {
		accountService.changePassword(id, request.getNewPassword());
	}

	@Override
	public List<OrganizationGroupSummary> listOrganizationGroups(String id) {
		return organizationService.listOrganizationGroups(id)
								  .stream()
								  .map(OrganizationGroupSummary::from)
								  .collect(Collectors.toList());
	}

	@Override
	public String addOrganizationGroup(String id, SaveGroupParameter request, User user) {
		return organizationService.addOrganizationGroup(id, request, user).getId();
	}

	@Override
	public void updateOrganizationGroup(String id, SaveGroupParameter request, User user) {
		organizationService.updateOrganizationGroup(id, request, user);
	}

	@Override
	public void deleteOrganizationGroup(String id, User user) {
		organizationService.deleteOrganizationGroup(id, user);
	}

	@Override
	public Page<UserSummary> listUsersUnderOrganizationGroup(String id, PageQueryParameter queryRequest) {
		Page<User> userPage = organizationService.listUsersUnderOrganizationGroup(id, queryRequest);
		return new PageImpl<>(userPage.getContent().stream().map(UserSummary::from).collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  userPage.getTotalElements());
	}

	@Override
	public void addUsersToOrganizationGroup(String id, String[] userIds, User user) {
		organizationService.addUsersToOrganizationGroup(id, userIds, user);
	}

	@Override
	public void removeUsersFromOrganizationGroup(String id, String[] userIds, User user) {
		organizationService.removeUsersFromOrganizationGroup(id, userIds, user);
	}

	@Override
	public void enableAppUser(String id) {
		accountService.enable(id);
	}

	@Override
	public void disableAppUser(String id) {
		accountService.disable(id);
	}

	@Override
	public void lockAppUser(String id) {
		accountService.lock(id);
	}

	@Override
	public void unlockAppUser(String id) {
		accountService.unlock(id);
	}

	@Override
	public void updateAppUserOrganizationRelationship(String userId, String[] organizationIds) {
		organizationService.updateAppUserOrganizationRelationship(userId, organizationIds);
	}

	@Override
	public List<OrganizationOfAppUser> getAppUserOrganizationRelationship(String userId) {
		User user = accountService.findById(userId);
		if (user == null) {
			throw new UserNotFoundException(userId);
		}
		List<Organization> organizations = user.getOrganizations();
		if (organizations == null) {
			return null;
		}
		return organizations.stream().map(OrganizationOfAppUser::from).collect(Collectors.toList());
	}

}
