package in.clouthink.nextoa.bl.openapi.support;

import in.clouthink.nextoa.bl.model.User;
import in.clouthink.nextoa.bl.openapi.dto.*;
import in.clouthink.nextoa.shared.domain.params.PageQueryParameter;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 *
 */
public interface OrganizationRestSupport {

	/**
	 * @return
	 */
	List<OrganizationSummary> listRootOrganizations();

	/**
	 * @param id
	 * @return
	 */
	List<OrganizationSummary> listOrganizationChildren(String id);

	/**
	 * @param id
	 * @param queryRequest
	 * @return
	 */
	Page<UserSummary> listUsersOfOrganization(String id, UsernamePageQueryParameter queryRequest);

	/**
	 * @param request
	 * @param byWho
	 * @return
	 */
	String createOrganization(SaveOrganizationParameter request, User byWho);

	/**
	 * @param id
	 * @param request
	 * @param byWho
	 */
	void updateOrganization(String id, SaveOrganizationParameter request, User byWho);

	/**
	 * @param id
	 * @param byWho
	 */
	void deleteOrganization(String id, User byWho);

	/**
	 * @param id
	 * @param request
	 * @param byWho
	 * @return
	 */
	String createOrganizationChild(String id, SaveOrganizationParameter request, User byWho);

	/**
	 * @param id
	 * @param request
	 * @param byWho
	 * @return
	 */
	String createAppUser(String id, SaveAppUserParameter request, User byWho);

	/**
	 * @param id
	 * @return
	 */
	UserDetail getAppUser(String id);

	/**
	 * @param id
	 * @param request
	 * @param byWho
	 */
	void updateAppUser(String id, SaveAppUserParameter request, User byWho);

	/**
	 *
	 * @param userId
	 * @param avatarId
	 * @param user
	 */
	void updateAppUserAvatar(String userId, String avatarId, User user);

	/**
	 * @param id
	 * @param byWho
	 */
	void deleteAppUser(String id, User byWho);

	/**
	 * @param id
	 * @param request
	 */
	void changeAppUserPassword(String id, ChangePasswordRequest request);

	/**
	 * @param id
	 * @return
	 */
	List<OrganizationGroupSummary> listOrganizationGroups(String id);

	/**
	 * @param id
	 * @param request
	 * @param byWho
	 * @return
	 */
	String addOrganizationGroup(String id, SaveGroupParameter request, User byWho);

	/**
	 * @param id
	 * @param request
	 * @param byWho
	 */
	void updateOrganizationGroup(String id, SaveGroupParameter request, User byWho);

	/**
	 * @param id
	 * @param byWho
	 */
	void deleteOrganizationGroup(String id, User byWho);

	/**
	 * @param id
	 * @param queryRequest
	 * @return
	 */
	Page<UserSummary> listUsersUnderOrganizationGroup(String id, PageQueryParameter queryRequest);

	/**
	 * @param id
	 * @param userIds
	 * @param byWho
	 */
	void addUsersToOrganizationGroup(String id, String[] userIds, User byWho);

	/**
	 * @param id
	 * @param userIdsArray
	 * @param byWho
	 */
	void removeUsersFromOrganizationGroup(String id, String[] userIdsArray, User byWho);

	/**
	 * @param id
	 */
	void enableAppUser(String id);

	/**
	 * @param id
	 */
	void disableAppUser(String id);

	/**
	 * @param id
	 */
	void lockAppUser(String id);

	/**
	 * @param id
	 */
	void unlockAppUser(String id);

	/**
	 * @param userId
	 * @param organizationIdsArray
	 */
	void updateAppUserOrganizationRelationship(String userId, String[] organizationIdsArray);

	/**
	 *
	 * @param userId
	 * @return
	 */
	List<OrganizationOfAppUser> getAppUserOrganizationRelationship(String userId);

}
