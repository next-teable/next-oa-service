package cn.com.starest.nextoa.openapi.support.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.model.UserOpinionSummary;
import cn.com.starest.nextoa.openapi.dto.ChangeMyPasswordRequest;
import cn.com.starest.nextoa.openapi.dto.ChangeMyProfileParameter;
import cn.com.starest.nextoa.openapi.dto.MenuSummary;
import cn.com.starest.nextoa.openapi.dto.UserProfile;
import cn.com.starest.nextoa.openapi.support.UserProfileRestSupport;
import cn.com.starest.nextoa.rbac.core.model.ResourceWithChildren;
import cn.com.starest.nextoa.rbac.core.spi.RbacService;
import cn.com.starest.nextoa.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@Service
public class UserProfileRestSupportImpl implements UserProfileRestSupport {

	@Autowired
	private UserProfileService userProfileService;

	@Autowired
	private RbacService rbacService;

	@Override
	public UserProfile getUserProfile(User user) {
		User result = userProfileService.findUserById(user.getId());
		return UserProfile.from(result);
	}

	@Override
	public void updateUserProfile(ChangeMyProfileParameter request, User byWho) {
		userProfileService.changeUserProfile(byWho.getId(), request);
	}

	@Override
	public void updateUserAvatar(String avatarId, User byWho) {
		userProfileService.updateUserAvatar(avatarId, byWho);
	}

	@Override
	public void changeMyPassword(ChangeMyPasswordRequest request, User byWho) {
		userProfileService.changeUserPassword(byWho.getId(), request.getOldPassword(), request.getNewPassword());
	}

	@Override
	public List<MenuSummary> getUserGrantedMenus(User user) {
		List<ResourceWithChildren> resourceWithChildren = rbacService.getAllowedResources((List) user.getAuthorities());
		return resourceWithChildren.stream().map(MenuSummary::from).collect(Collectors.toList());
	}

	@Override
	public List<UserOpinionSummary> getUserOpinions(User user) {
		return userProfileService.getUserOpinions(user)
								 .stream()
								 .map(UserOpinionSummary::from)
								 .collect(Collectors.toList());
	}

	@Override
	public UserOpinionSummary findUserOpinionById(String id, User user) {
		return UserOpinionSummary.from(userProfileService.findUserOpinionById(id, user));
	}

	@Override
	public UserOpinionSummary createUserOpinion(String content, User user) {
		return UserOpinionSummary.from(userProfileService.createUserOpinion(content, user));
	}

	@Override
	public UserOpinionSummary updateUserOpinion(String id, String content, User user) {
		return UserOpinionSummary.from(userProfileService.updateUserOpinion(id, content, user));
	}

	@Override
	public void deleteUserOpinion(String id, User user) {
		userProfileService.deleteUserOpinion(id, user);
	}
}
