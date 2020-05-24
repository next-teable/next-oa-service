package in.clouthink.nextoa.bl.openapi.controller;

import in.clouthink.nextoa.bl.model.User;
import in.clouthink.nextoa.bl.model.UserOpinionSummary;
import in.clouthink.nextoa.bl.openapi.dto.ChangeMyPasswordRequest;
import in.clouthink.nextoa.bl.openapi.dto.ChangeMyProfileParameter;
import in.clouthink.nextoa.bl.openapi.dto.MenuSummary;
import in.clouthink.nextoa.bl.openapi.dto.UserProfile;
import in.clouthink.nextoa.bl.openapi.support.UserProfileRestSupport;
import in.clouthink.nextoa.security.SecurityContexts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 */
@Api("我的个人资料")
@RestController
@RequestMapping("/api")
public class UserProfileRestController {

	@Autowired
	private UserProfileRestSupport userProfileRestSupport;

	@ApiOperation(value = "查看我的个人资料")
	@RequestMapping(value = "/my/profile", method = RequestMethod.GET)
	public UserProfile getUserProfile() {
		User user = (User)SecurityContexts.getContext().requireUser();
		return userProfileRestSupport.getUserProfile(user);
	}

	@ApiOperation(value = "修改我的个人资料")
	@RequestMapping(value = "/my/profile", method = RequestMethod.POST)
	public void updateUserProfile(@RequestBody ChangeMyProfileParameter request) {
		User user = (User)SecurityContexts.getContext().requireUser();
		userProfileRestSupport.updateUserProfile(request, user);
	}

	@ApiOperation(value = "修改我的头像信息")
	@RequestMapping(value = "/my/avatar/{avatarId}", method = RequestMethod.POST)
	public void updateUserAvatar(@PathVariable String avatarId) {
		User user = (User)SecurityContexts.getContext().requireUser();
		userProfileRestSupport.updateUserAvatar(avatarId, user);
	}

	@ApiOperation(value = "删除我的头像信息")
	@RequestMapping(value = "/my/avatar", method = RequestMethod.DELETE)
	public void deleteUserAvatar() {
		User user = (User)SecurityContexts.getContext().requireUser();
		userProfileRestSupport.updateUserAvatar(null, user);
	}

	@ApiOperation(value = "修改我的密码")
	@RequestMapping(value = "/my/password", method = RequestMethod.POST)
	public void changeMyPassword(@RequestBody ChangeMyPasswordRequest request) {
		User user = (User)SecurityContexts.getContext().requireUser();
		userProfileRestSupport.changeMyPassword(request, user);
	}

	@ApiOperation(value = "查看我的菜单(已授权的)")
	@RequestMapping(value = "/my/menus", method = RequestMethod.GET)
	public List<MenuSummary> getUserGrantedMenus() {
		User user = (User)SecurityContexts.getContext().requireUser();
		return userProfileRestSupport.getUserGrantedMenus(user);
	}

	@ApiOperation(value = "查看我的常用意见(所有)")
	@RequestMapping(value = "/my/opinions", method = RequestMethod.GET)
	public List<UserOpinionSummary> getUserOpinions() {
		User user = (User)SecurityContexts.getContext().requireUser();
		return userProfileRestSupport.getUserOpinions(user);
	}

	@ApiOperation(value = "查看我的常用意见(单条)")
	@RequestMapping(value = "/my/opinions/{id}", method = RequestMethod.GET)
	public UserOpinionSummary getUserOpinionDetail(@PathVariable String id) {
		User user = (User)SecurityContexts.getContext().requireUser();
		return userProfileRestSupport.findUserOpinionById(id, user);
	}

	@ApiOperation(value = "新增我的常用意见")
	@RequestMapping(value = "/my/opinions", method = RequestMethod.POST)
	public UserOpinionSummary createUserOpinion(@RequestBody String content) {
		User user = (User)SecurityContexts.getContext().requireUser();
		return userProfileRestSupport.createUserOpinion(content, user);
	}

	@ApiOperation(value = "修改我的常用意见")
	@RequestMapping(value = "/my/opinions/{id}", method = RequestMethod.POST)
	public UserOpinionSummary updateUserOpinion(@PathVariable String id, @RequestBody String content) {
		User user = (User)SecurityContexts.getContext().requireUser();
		return userProfileRestSupport.updateUserOpinion(id, content, user);
	}

	@ApiOperation(value = "删除我的常用意见")
	@RequestMapping(value = "/my/opinions/{id}", method = RequestMethod.DELETE)
	public void deleteUserOpinion(@PathVariable String id) {
		User user = (User)SecurityContexts.getContext().requireUser();
		userProfileRestSupport.deleteUserOpinion(id, user);
	}

}
