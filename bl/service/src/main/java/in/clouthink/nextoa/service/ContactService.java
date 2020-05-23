package in.clouthink.nextoa.service;

import in.clouthink.nextoa.model.ContactGroup;
import in.clouthink.nextoa.model.User;
import in.clouthink.nextoa.model.dtr.ContactGroupQueryRequest;
import in.clouthink.nextoa.model.dtr.PageQueryRequest;
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
