package in.clouthink.nextoa.bl.openapi.support.impl;

import in.clouthink.nextoa.bl.model.User;
import in.clouthink.nextoa.bl.model.UserOpinionSummary;
import in.clouthink.nextoa.bl.openapi.dto.ChangeMyPasswordRequest;
import in.clouthink.nextoa.bl.openapi.dto.ChangeMyProfileParameter;
import in.clouthink.nextoa.bl.openapi.dto.MenuSummary;
import in.clouthink.nextoa.bl.openapi.dto.UserProfile;
import in.clouthink.nextoa.bl.openapi.support.UserProfileRestSupport;
import in.clouthink.nextoa.bl.service.UserProfileService;
import in.clouthink.nextoa.security.rbac.core.model.ResourceWithChildren;
import in.clouthink.nextoa.security.rbac.core.spi.RbacService;
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
