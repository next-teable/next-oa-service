package in.clouthink.nextoa.service.impl;

import in.clouthink.nextoa.exception.*;
import in.clouthink.nextoa.model.*;
import in.clouthink.nextoa.model.dtr.*;
import in.clouthink.nextoa.exception.*;
import in.clouthink.nextoa.model.*;
import in.clouthink.nextoa.model.dtr.*;
import in.clouthink.nextoa.repository.GroupRepository;
import in.clouthink.nextoa.repository.OrganizationRepository;
import in.clouthink.nextoa.repository.UserGroupRelationshipRepository;
import in.clouthink.nextoa.repository.UserRepository;
import in.clouthink.nextoa.service.AccountService;
import in.clouthink.nextoa.service.OrganizationService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

	private static final Log logger = LogFactory.getLog(OrganizationServiceImpl.class);

	@Autowired
	private OrganizationRepository organizationRepository;

	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserGroupRelationshipRepository userGroupRelationshipRepository;

	@Autowired
	private AccountService accountService;

	@Override
	public List<Organization> listRootOrgainizations() {
		return organizationRepository.findByParentOrderByCodeAscNameAsc(null);
	}

	@Override
	public List<Organization> listOrgainizationChildren(String id) {
		Organization organization = organizationRepository.findById(id);
		if (organization == null) {
			throw new OrganizationNotFoundException(id);
		}
		return organizationRepository.findByParentOrderByCodeAscNameAsc(organization);
	}

	@Override
	public Page<User> listUsersOfOrganization(String id, UsernameQueryRequest queryParameter) {
		Organization organization = organizationRepository.findById(id);
		if (organization == null) {
			throw new OrganizationNotFoundException(id);
		}

		return userRepository.queryPage(organization, queryParameter);
	}

	@Override
	public Organization findOrganizationById(String id) {
		return organizationRepository.findById(id);
	}

	@Override
	public Organization createOrganization(SaveOrganizationRequest request, User byWho) {
		if (StringUtils.isEmpty(request.getName())) {
			throw new OrganizationException("组织机构名称不能为空");
		}

		Organization organization = organizationRepository.findByParentAndName(null, request.getName());
		if (organization != null) {
			throw new OrganizationException("组织机构名称不能重复");
		}

		organization = new Organization();
		organization.setCode(request.getCode());
		organization.setName(request.getName());
		organization.setLeaf(true);
		organization.setCreatedAt(new Date());
		organization.setCreatedBy(byWho);
		return organizationRepository.save(organization);
	}

	@Override
	public void updateOrganization(String id, SaveOrganizationRequest request, User byWho) {
		if (StringUtils.isEmpty(request.getName())) {
			throw new OrganizationException("组织机构名称不能为空");
		}
		Organization organization = organizationRepository.findById(id);
		if (organization == null) {
			throw new OrganizationNotFoundException(id);
		}

		Organization orgByName = organizationRepository.findByParentAndName(organization.getParent(),
																			request.getName());
		if (orgByName != null && !orgByName.getId().equals(id)) {
			throw new OrganizationException("组织机构名称不能重复");
		}

		organization.setCode(request.getCode());
		organization.setName(request.getName());
		organization.setModifiedAt(new Date());
		organization.setModifiedBy(byWho);
		organizationRepository.save(organization);
	}

	@Override
	public void deleteOrganization(String id, User byWho) {
		Organization organization = organizationRepository.findById(id);
		if (organization == null) {
			throw new OrganizationNotFoundException(id);
		}
		long userCountUnderOrg = userRepository.countByOrganization(organization);
		if (userCountUnderOrg > 0) {
			throw new OrganizationException("该组织机构下用户不为空,不能删除.");
		}
		long childCountUnderOrg = organizationRepository.countByParent(organization);
		if (childCountUnderOrg > 0) {
			throw new OrganizationException("该组织机构下子部门不为空,不能删除.");
		}
		long groupCountUnderOrg = groupRepository.countByOrganization(organization);
		if (groupCountUnderOrg > 0) {
			throw new OrganizationException("该组织机构下用户组不为空,不能删除.");
		}

		Organization parent = organization.getParent();
		organizationRepository.delete(organization);
		if (parent != null && organizationRepository.countByParent(parent) == 0) {
			parent.setLeaf(true);
			organizationRepository.save(parent);
		}
	}

	@Override
	public Organization createOrganizationChild(String id, SaveOrganizationRequest request, User byWho) {
		if (StringUtils.isEmpty(request.getName())) {
			throw new OrganizationException("组织机构名称不能为空");
		}

		Organization parent = organizationRepository.findById(id);
		if (parent == null) {
			throw new OrganizationNotFoundException(id);
		}

		Organization orgByName = organizationRepository.findByParentAndName(parent, request.getName());
		if (orgByName != null) {
			throw new OrganizationException("组织机构名称不能重复");
		}

		Organization organization = new Organization();
		organization.setCode(request.getCode());
		organization.setName(request.getName());
		organization.setCreatedAt(new Date());
		organization.setCreatedBy(byWho);
		organization.setLeaf(true);
		organization.setParent(parent);
		Organization target = organizationRepository.save(organization);
		parent.setLeaf(false);
		organizationRepository.save(parent);
		return target;
	}

	@Override
	public User createAppUser(String id, SaveUserRequest request, User byWho) {
		Organization parent = organizationRepository.findById(id);
		if (parent == null) {
			throw new OrganizationNotFoundException(id);
		}

		return accountService.createAccount(UserType.APPUSER, request, parent, SysRole.ROLE_USER);
	}

	@Override
	public void updateAppUser(String id, SaveUserRequest request, User byWho) {
		accountService.updateAccount(id, request);
	}

	@Override
	public void deleteAppUser(String id, User byWho) {
		accountService.archiveAccount(id, byWho);
	}

	@Override
	public void updateAppUserOrganizationRelationship(String userId, String[] organizationIds) {
		User appUser = userRepository.findById(userId);
		if (appUser == null) {
			throw new UserNotFoundException(userId);
		}

		if (UserType.SYSUSER == appUser.getUserType()) {
			throw new UserException("系统管理用户不需要设置所属部门");
		}

		if (organizationIds == null || organizationIds.length == 0) {
			throw new UserException("应用用户至少应该归属于一个部门");
		}

		List<Organization> organizations = new ArrayList<>();
		for (String orgId : organizationIds) {
			Organization organization = organizationRepository.findById(orgId);
			if (organization == null) {
				logger.warn(String.format(
						"The backend is try to update the user and organization relationship, but the organization[id=%s] is not found",
						orgId));
				continue;
			}
			organizations.add(organization);
		}

		if (organizations.isEmpty()) {
			throw new UserException("应用用户至少应该归属于一个部门");
		}

		appUser.setOrganizations(organizations);
		userRepository.save(appUser);
	}

	@Override
	public List<Group> listOrganizationGroups(String id) {
		Organization organization = organizationRepository.findById(id);
		if (organization == null) {
			throw new OrganizationNotFoundException(id);
		}
		return groupRepository.findByOrganization(organization);
	}

	@Override
	public Group findGroupById(String id) {
		return groupRepository.findById(id);
	}

	@Override
	public Group addOrganizationGroup(String id, SaveGroupRequest saveGroupRequest, User byWho) {
		Organization organization = organizationRepository.findById(id);
		if (organization == null) {
			throw new OrganizationNotFoundException(id);
		}

		Group group = new Group();
		group.setName(saveGroupRequest.getName());
		group.setCreatedBy(byWho);
		group.setCreatedAt(new Date());
		return groupRepository.save(group);
	}

	@Override
	public void updateOrganizationGroup(String id, SaveGroupRequest saveGroupRequest, User byWho) {
		Group group = groupRepository.findById(id);
		if (group == null) {
			throw new GroupNotFoundException(id);
		}
		group.setName(saveGroupRequest.getName());
		group.setModifiedBy(byWho);
		group.setModifiedAt(new Date());
		groupRepository.save(group);
	}

	@Override
	public void deleteOrganizationGroup(String id, User byWho) {
		Group group = groupRepository.findById(id);
		if (group == null) {
			return;
		}

		long userGroupRelationshipCount = userGroupRelationshipRepository.countByGroup(group);
		if (userGroupRelationshipCount > 0) {
			throw new OrganizationException("该用户组下关联的用户不为空,不能删除.");
		}

		groupRepository.delete(group);
	}

	@Override
	public Page<User> listUsersUnderOrganizationGroup(String id, PageQueryRequest queryParameter) {
		return null;
	}

	@Override
	public void addUsersToOrganizationGroup(String id, String[] userIds, User byWho) {

	}

	@Override
	public void removeUsersFromOrganizationGroup(String id, String[] userIdsArray, User byWho) {

	}

	@Override
	public Page<User> listAppUsers(UserQueryRequest queryParameter) {
		return userRepository.queryPage(queryParameter);
	}

	@Override
	public User findAppUserById(String id) {
		return userRepository.findById(id);
	}

}
