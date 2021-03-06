package in.clouthink.nextoa.bl.openapi.support;

import in.clouthink.nextoa.bl.model.User;
import in.clouthink.nextoa.bl.openapi.dto.*;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 *
 */
public interface ContactRestSupport {

	List<UserSummary> listRecentUsers(User user );

	List<OrganizationSummary> getOrganizationOfUser(User user );

	Page<UserSummary> listAppUsers(UserQueryParameter queryRequest);

	UserDetail getAppUserDetail(String id);

	Page<ContactGroupSummary> listContactGroups(ContactGroupQueryParameter queryRequest, User user);

	ContactGroupDetail getContactGroupDetail(String id, User user);

	ContactGroupSummary createContactGroup(SaveContactGroupParameter createRequest, User user);

	void updateContactGroup(String id, SaveContactGroupParameter updateRequest, User user);

	void deleteContactGroup(String id, User user);

	void addUsersToContactGroup(String id, String[] userIds, User user);

	void removeUsersFromContactGroup(String id, String[] userIdsArray, User user);

}
