package cn.com.starest.nextoa.openapi.controller;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.dto.*;
import cn.com.starest.nextoa.openapi.security.SecurityContexts;
import cn.com.starest.nextoa.openapi.support.OrganizationRestSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 */
@Api("组织机构及用户管理")
@RestController
@RequestMapping("/api")
public class OrganizationRestController {

	@Autowired
	private OrganizationRestSupport organizationRestSupport;

	@ApiOperation(value = "列出根节点的所有组织机构节点")
	@RequestMapping(value = {"/organizations", "/contact/organizations"}, method = RequestMethod.GET)
	public List<OrganizationSummary> listRootOrgainizations() {
		return organizationRestSupport.listRootOrganizations();
	}

	@ApiOperation(value = "列出指定父节点下的所有子组织机构节点")
	@RequestMapping(value = {"/organizations/{id}/children",
							 "/contact/organizations/{id}/children"}, method = RequestMethod.GET)
	public List<OrganizationSummary> listOrgainizationChildren(@PathVariable String id) {
		return organizationRestSupport.listOrganizationChildren(id);
	}

	@ApiOperation(value = "列出指定组织机构下的用户,支持分页")
	@RequestMapping(value = {"/organizations/{id}/users",
							 "/contact/organizations/{id}/users"}, method = RequestMethod.GET)
	public Page<UserSummary> listAppUsersOfOrganization(@PathVariable String id,
														UsernamePageQueryParameter queryRequest) {
		return organizationRestSupport.listUsersOfOrganization(id, queryRequest);
	}

	@ApiOperation(value = "新增根节点的组织机构")
	@RequestMapping(value = "/organizations", method = RequestMethod.POST)
	public String createOrganization(@RequestBody SaveOrganizationParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return organizationRestSupport.createOrganization(request, user);
	}

	@ApiOperation(value = "修改指定的组织结构基本信息")
	@RequestMapping(value = "/organizations/{id}", method = RequestMethod.POST)
	public void updateOrganization(@PathVariable String id, @RequestBody SaveOrganizationParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		organizationRestSupport.updateOrganization(id, request, user);
	}

	@ApiOperation(value = "删除指定的组织机构（如果子节点不为空不能删除）")
	@RequestMapping(value = "/organizations/{id}", method = RequestMethod.DELETE)
	public void deleteOrganization(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		organizationRestSupport.deleteOrganization(id, user);
	}

	@ApiOperation(value = "在指定的组织机构节点下增加子节点")
	@RequestMapping(value = "/organizations/{id}/children", method = RequestMethod.POST)
	public String createOrganizationChild(@PathVariable String id, @RequestBody SaveOrganizationParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return organizationRestSupport.createOrganizationChild(id, request, user);
	}

	@ApiOperation(value = "在指定的组织机构节点下增加用户")
	@RequestMapping(value = "/organizations/{id}/users", method = RequestMethod.POST)
	public String createAppUser(@PathVariable String id, @RequestBody SaveAppUserParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return organizationRestSupport.createAppUser(id, request, user);
	}

	@ApiOperation(value = "获取用户基本信息")
	@RequestMapping(value = "/appusers/{id}", method = RequestMethod.GET)
	public UserDetail getAppUser(@PathVariable String id) {
		return organizationRestSupport.getAppUser(id);
	}

	@ApiOperation(value = "修改用户基本信息")
	@RequestMapping(value = "/appusers/{id}", method = RequestMethod.POST)
	public void updateAppUser(@PathVariable String id, @RequestBody SaveAppUserParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		organizationRestSupport.updateAppUser(id, request, user);
	}

	@ApiOperation(value = "删除用户头像")
	@RequestMapping(value = "/appusers/{id}/avatar", method = RequestMethod.DELETE)
	public void deleteAppUserAvatar(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		organizationRestSupport.updateAppUserAvatar(id, null, user);
	}

	@ApiOperation(value = "修改用户头像")
	@RequestMapping(value = "/appusers/{id}/avatar/{avatarId}", method = RequestMethod.POST)
	public void updateAppUserAvatar(@PathVariable String id, @PathVariable String avatarId) {
		User user = SecurityContexts.getContext().requireUser();
		organizationRestSupport.updateAppUserAvatar(id, avatarId, user);
	}

	@ApiOperation(value = "修改用户密码")
	@RequestMapping(value = "/appusers/{id}/password", method = RequestMethod.POST)
	public void changePassword(@PathVariable String id, @RequestBody ChangePasswordRequest request) {
		organizationRestSupport.changeAppUserPassword(id, request);
	}

	@ApiOperation(value = "删除指定用户（该用户被归档,并非真的删除,归档后,用户的用户会加上已删除的后缀）")
	@RequestMapping(value = "/appusers/{id}", method = RequestMethod.DELETE)
	public void deleteAppUser(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		organizationRestSupport.deleteAppUser(id, user);
	}

	@ApiOperation(value = "启用系统用户")
	@RequestMapping(value = "/appusers/{id}/enable", method = RequestMethod.POST)
	public void enableAppUser(@PathVariable String id) {
		organizationRestSupport.enableAppUser(id);
	}

	@ApiOperation(value = "禁用系统用户,用户被禁用后不能使用系统")
	@RequestMapping(value = "/appusers/{id}/disable", method = RequestMethod.POST)
	public void disableAppUser(@PathVariable String id) {
		organizationRestSupport.disableAppUser(id);
	}

	@ApiOperation(value = "锁定系统用户,用户被锁定后不能使用系统")
	@RequestMapping(value = "/appusers/{id}/lock", method = RequestMethod.POST)
	public void lockAppUser(@PathVariable String id) {
		organizationRestSupport.lockAppUser(id);
	}

	@ApiOperation(value = "取消锁定系统用户")
	@RequestMapping(value = "/appusers/{id}/unlock", method = RequestMethod.POST)
	public void unlockAppUser(@PathVariable String id) {
		organizationRestSupport.unlockAppUser(id);
	}

	@ApiOperation(value = "查看用户所属组织机构")
	@RequestMapping(value = "/appusers/{userId}/organizations", method = RequestMethod.GET)
	public List<OrganizationOfAppUser> getAppUserOrganizationRelationship(@PathVariable String userId) {
		return organizationRestSupport.getAppUserOrganizationRelationship(userId);
	}

	@ApiOperation(value = "设置用户所属组织机构")
	@RequestMapping(value = "/appusers/{userId}/organizations/{organizationIds}", method = RequestMethod.POST)
	public void updateAppUserOrganizationRelationship(@PathVariable String userId,
													  @PathVariable String organizationIds) {
		if (StringUtils.isEmpty(organizationIds)) {
			return;
		}
		organizationRestSupport.updateAppUserOrganizationRelationship(userId, organizationIds.split(","));
	}

	@ApiOperation(value = "查看组织机构下定义的用户组列表")
	@RequestMapping(value = "/organizations/{id}/groups", method = RequestMethod.GET)
	public List<OrganizationGroupSummary> listOrganizationGroups(@PathVariable String id) {
		return organizationRestSupport.listOrganizationGroups(id);
	}

	@ApiOperation(value = "在指定的组织机构下增加新的用户组")
	@RequestMapping(value = "/organizations/{id}/groups", method = RequestMethod.POST)
	public String addGroupToOrganization(@PathVariable String id, @RequestBody SaveGroupParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return organizationRestSupport.addOrganizationGroup(id, request, user);
	}

	@ApiOperation(value = "修改用户组基本信息")
	@RequestMapping(value = "/groups/{id}", method = RequestMethod.POST)
	public void updateOrganizationGroup(@PathVariable String id, @RequestBody SaveGroupParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		organizationRestSupport.updateOrganizationGroup(id, request, user);
	}

	@ApiOperation(value = "删除用户组,用户组删除后,和该用户组绑定的用户自动解绑")
	@RequestMapping(value = "/groups/{id}", method = RequestMethod.DELETE)
	public void deleteOrganizationGroup(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		organizationRestSupport.deleteOrganizationGroup(id, user);
	}

	@ApiOperation(value = "查看用户组下的用户列表,支持分页")
	@RequestMapping(value = "/groups/{id}/users", method = RequestMethod.GET)
	public Page<UserSummary> listUsersUnderOrganizationGroup(@PathVariable String id, PageQueryParameter queryRequest) {
		return organizationRestSupport.listUsersUnderOrganizationGroup(id, queryRequest);
	}

	@ApiOperation(value = "绑定用户（多个）和用户组的关系")
	@RequestMapping(value = "/groups/{id}/users", method = RequestMethod.POST)
	public void addUsersToOrganizationGroup(@PathVariable String id, @RequestBody String[] userIds) {
		if (userIds == null || userIds.length == 0) {
			return;
		}
		User user = SecurityContexts.getContext().requireUser();
		organizationRestSupport.addUsersToOrganizationGroup(id, userIds, user);
	}

	@ApiOperation(value = "接绑用户（多个）和用户组的关系")
	@RequestMapping(value = "/groups/{id}/users/{userIds}", method = RequestMethod.DELETE)
	public void removeUsersFromOrganizationGroup(@PathVariable String id, @PathVariable String userIds) {
		if (StringUtils.isEmpty(userIds)) {
			return;
		}
		User user = SecurityContexts.getContext().requireUser();
		organizationRestSupport.removeUsersFromOrganizationGroup(id, userIds.split(","), user);
	}

}
