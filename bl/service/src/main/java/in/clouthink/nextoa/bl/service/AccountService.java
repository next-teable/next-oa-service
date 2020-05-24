package in.clouthink.nextoa.bl.service;

import in.clouthink.nextoa.bl.model.Organization;
import in.clouthink.nextoa.bl.model.SysRole;
import in.clouthink.nextoa.bl.model.User;
import in.clouthink.nextoa.bl.model.UserType;
import in.clouthink.nextoa.bl.request.ChangeUserProfileRequest;
import in.clouthink.nextoa.bl.request.SaveUserRequest;
import in.clouthink.nextoa.bl.request.UserQueryRequest;
import org.springframework.data.domain.Page;

/**
 * The app user service
 */
public interface AccountService {

	/**
	 * @param username unique username
	 * @return
	 */
	User findAccountByUsername(String username);

	/**
	 * @param userType
	 * @param request
	 * @param sysRoles
	 * @return
	 */
	User createAccount(UserType userType, SaveUserRequest request, SysRole... sysRoles);

	/**
	 * @param userType
	 * @param request
	 * @param organization
	 * @param sysRoles
	 * @return
	 */
	User createAccount(UserType userType, SaveUserRequest request, Organization organization, SysRole... sysRoles);

	/**
	 * @param userId
	 * @param request
	 * @return
	 */
	User updateAccount(String userId, SaveUserRequest request);

	/**
	 * @param userId
	 * @param request
	 * @return
	 */
	User changeUserProfile(String userId, ChangeUserProfileRequest request);

	/**
	 * @param userId
	 * @param avatarId
	 * @return
	 */
	User changeUserAvatar(String userId, String avatarId);

	/**
	 * @param userId
	 * @return
	 */
	User enable(String userId);

	/**
	 * @param userId
	 * @return
	 */
	User disable(String userId);

	/**
	 * @param userId
	 * @return
	 */
	User lock(String userId);

	/**
	 * @param userId
	 * @return
	 */
	User unlock(String userId);

	/**
	 * @param userId
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	User changePassword(String userId, String oldPassword, String newPassword);

	/**
	 * @param userId
	 * @param newPassword
	 * @return
	 */
	User changePassword(String userId, String newPassword);

	/**
	 * @param userId
	 * @return
	 */
	User findById(String userId);

	/**
	 * @param username
	 * @return
	 */
	User findByUsername(String username);

	/**
	 * @param username
	 */
	void forgetPassword(String username);

	/**
	 * @param userId
	 */
	void deleteUser(String userId, User byWho);

	/**
	 * @param queryParameter
	 * @return
	 */
	Page<User> listUsers(UserQueryRequest queryParameter);

	/**
	 * @param role
	 * @param queryParameter
	 * @return
	 */
	Page<User> listUsersByRole(SysRole role, UserQueryRequest queryParameter);

	/**
	 * @param queryParameter
	 * @return
	 */
	Page<User> listArchivedUsers(UserQueryRequest queryParameter);

	/**
	 * 归档用户,只有管理员和超级管理员能够归档用户
	 *
	 * @param id
	 * @param byWho
	 */
	void archiveAccount(String id, User byWho);

}
