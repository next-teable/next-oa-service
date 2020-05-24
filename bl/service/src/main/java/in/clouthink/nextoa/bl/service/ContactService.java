package in.clouthink.nextoa.bl.service;

import in.clouthink.nextoa.bl.model.ContactGroup;
import in.clouthink.nextoa.bl.model.User;
import in.clouthink.nextoa.bl.request.ContactGroupQueryRequest;
import in.clouthink.nextoa.shared.domain.request.PageQueryRequest;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 *
 */
public interface ContactService {

	Page<ContactGroup> listContactGroups(ContactGroupQueryRequest queryParameter, User byWho);

	ContactGroup createContactGroup(String name, User byWho);

	ContactGroup findContactGroupById(String id, User byWho);

	void updateContactGroup(String id, String name, User byWho);

	void deleteContactGroup(String id, User byWho);

	void addUsersToGroup(String id, String[] userIds, User byWho);

	void removeUsersFromGroup(String id, String[] userIdsArray, User byWho);

	Page<User> listUsersOfContactGroups(String id, PageQueryRequest pageParameter);

	List<User> listRecentUsers(User user);

}
