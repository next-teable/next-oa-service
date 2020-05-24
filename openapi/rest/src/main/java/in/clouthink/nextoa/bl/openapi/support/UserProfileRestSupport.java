package in.clouthink.nextoa.bl.openapi.support;

import in.clouthink.nextoa.bl.model.User;
import in.clouthink.nextoa.bl.model.UserOpinionSummary;
import in.clouthink.nextoa.bl.openapi.dto.ChangeMyPasswordRequest;
import in.clouthink.nextoa.bl.openapi.dto.ChangeMyProfileParameter;
import in.clouthink.nextoa.bl.openapi.dto.MenuSummary;
import in.clouthink.nextoa.bl.openapi.dto.UserProfile;

import java.util.List;

/**
 *
 */
public interface UserProfileRestSupport {

	UserProfile getUserProfile(User byWho);

	void updateUserProfile(ChangeMyProfileParameter request, User byWho);

	void updateUserAvatar(String avatarId, User byWho);

	void changeMyPassword(ChangeMyPasswordRequest request, User byWho);

	List<MenuSummary> getUserGrantedMenus(User user);

	List<UserOpinionSummary> getUserOpinions(User user);

	UserOpinionSummary findUserOpinionById(String id, User user);

	UserOpinionSummary createUserOpinion(String content, User user);

	UserOpinionSummary updateUserOpinion(String id, String content, User user);

	void deleteUserOpinion(String id, User user);

}
