package cn.com.starest.nextoa.openapi.support;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.model.UserOpinionSummary;
import cn.com.starest.nextoa.openapi.dto.*;

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
