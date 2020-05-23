package in.clouthink.nextoa.service;

import in.clouthink.nextoa.model.Organization;
import in.clouthink.nextoa.model.User;
import in.clouthink.nextoa.model.UserOpinion;
import in.clouthink.nextoa.model.dtr.ChangeUserProfileRequest;

import java.util.List;

/**
 */
public interface UserProfileService {

	/**
	 * @param userId
	 * @return
	 */
	User findUserById(String userId);


	/**
	 * @param userId
	 * @param request
	 * @return
	 */
	User changeUserProfile(String userId, ChangeUserProfileRequest request);

	/**
	 * @param userId
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	User changeUserPassword(String userId, String oldPassword, String newPassword);

	/**
	 * @param user
	 * @return
	 */
	List<Organization> getOrganizationsOfUser(User user);

	/**
	 * @param user
	 * @return
	 */
	List<UserOpinion> getUserOpinions(User user);

	/**
	 * @param id
	 * @param user
	 * @return
	 */
	UserOpinion findUserOpinionById(String id, User user);

	/**
	 * @param content
	 * @param user
	 * @return
	 */
	UserOpinion createUserOpinion(String content, User user);

	/**
	 * @param id
	 * @param content
	 * @param user
	 * @return
	 */
	UserOpinion updateUserOpinion(String id, String content, User user);

	/**
	 * @param id
	 * @param user
	 */
	void deleteUserOpinion(String id, User user);

	/**
	 * @param fileObjectId
	 * @param byWho
	 */
	void updateUserAvatar(String fileObjectId, User byWho);
}
