package in.clouthink.nextoa.openapi.support;

import in.clouthink.nextoa.model.User;
import in.clouthink.nextoa.model.UserOpinionSummary;
import in.clouthink.nextoa.openapi.dto.*;
import in.clouthink.nextoa.openapi.dto.ChangeMyPasswordRequest;
import in.clouthink.nextoa.openapi.dto.ChangeMyProfileParameter;
import in.clouthink.nextoa.openapi.dto.MenuSummary;
import in.clouthink.nextoa.openapi.dto.UserProfile;

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
