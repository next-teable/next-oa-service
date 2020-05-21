package cn.com.starest.nextoa.service;

import cn.com.starest.nextoa.model.Group;
import cn.com.starest.nextoa.model.Organization;
import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.model.dtr.*;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 */
public interface OrganizationService {

	List<Organization> listRootOrgainizations();

	List<Organization> listOrgainizationChildren(String id);

	Page<User> listUsersOfOrganization(String id, UsernameQueryRequest queryRequest);

	Organization findOrganizationById(String id);

	Organization createOrganization(SaveOrganizationRequest organization, User byWho);

	void updateOrganization(String id, SaveOrganizationRequest organization, User byWho);

	void deleteOrganization(String id, User byWho);

	Organization createOrganizationChild(String id, SaveOrganizationRequest request, User byWho);

	User createAppUser(String id, SaveUserRequest request, User byWho);

	void updateAppUser(String id, SaveUserRequest request, User byWho);

	void deleteAppUser(String id, User byWho);

	void updateAppUserOrganizationRelationship(String userId, String[] organizationIds);

	List<Group> listOrganizationGroups(String id);

	Group findGroupById(String id);

	Group addOrganizationGroup(String id, SaveGroupRequest saveGroupRequest, User byWho);

	void updateOrganizationGroup(String id, SaveGroupRequest saveGroupRequest, User byWho);

	void deleteOrganizationGroup(String id, User byWho);

	Page<User> listUsersUnderOrganizationGroup(String id, PageQueryRequest queryParameter);

	void addUsersToOrganizationGroup(String id, String[] userIds, User byWho);

	void removeUsersFromOrganizationGroup(String id, String[] userIdsArray, User byWho);

	Page<User> listAppUsers(UserQueryRequest queryParameter);

	User findAppUserById(String id);

}
