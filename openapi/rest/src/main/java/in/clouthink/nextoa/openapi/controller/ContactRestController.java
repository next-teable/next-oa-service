package in.clouthink.nextoa.openapi.controller;

import in.clouthink.nextoa.model.User;
import in.clouthink.nextoa.openapi.dto.*;
import in.clouthink.nextoa.openapi.dto.*;
import in.clouthink.nextoa.openapi.security.SecurityContexts;
import in.clouthink.nextoa.openapi.support.ContactRestSupport;
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
@Api("我的通讯录")
@RestController
@RequestMapping("/api")
public class ContactRestController {

	@Autowired
	private ContactRestSupport contactRestSupport;

	@ApiOperation(value = "查看最近联系人")
	@RequestMapping(value = "/contact/recent", method = RequestMethod.GET)
	public List<UserSummary> listRecentUsers() {
		User user = SecurityContexts.getContext().requireUser();
		return contactRestSupport.listRecentUsers(user);
	}

	@ApiOperation(value = "查看当前用户所属部门")
	@RequestMapping(value = "/contact/myOrganization", method = RequestMethod.GET)
	public List<OrganizationSummary> listMyOrganizations() {
		User user = SecurityContexts.getContext().requireUser();
		return contactRestSupport.getOrganizationOfUser(user);
	}

	@ApiOperation(value = "查看所有联系人,支持分页,支持动态查询（用户名,联系电话等）,按联系人名称排序")
	@RequestMapping(value = "/contact/users", method = RequestMethod.GET)
	public Page<UserSummary> listAppUsers(UserQueryParameter queryRequest) {
		return contactRestSupport.listAppUsers(queryRequest);
	}

	@ApiOperation(value = "查看联系人详情")
	@RequestMapping(value = "/contact/users/{id}", method = RequestMethod.GET)
	public UserDetail getAppUserDetail(@PathVariable String id) {
		return contactRestSupport.getAppUserDetail(id);
	}

	@ApiOperation(value = "查看我的自定义分组信息,支持分页,支持动态查询（按分组的名称）")
	@RequestMapping(value = "/contact/groups", method = RequestMethod.GET)
	public Page<ContactGroupSummary> listContactGroups(ContactGroupQueryParameter queryRequest) {
		User user = SecurityContexts.getContext().requireUser();
		return contactRestSupport.listContactGroups(queryRequest, user);
	}

	@ApiOperation(value = "创建我的联系人分组")
	@RequestMapping(value = "/contact/groups", method = RequestMethod.POST)
	public void createContactGroup(@RequestBody SaveContactGroupParameter createRequest) {
		User user = SecurityContexts.getContext().requireUser();
		contactRestSupport.createContactGroup(createRequest, user);
	}

	@ApiOperation(value = "查看我的联系人分组详情（组信息,以及组下的联系人信息）")
	@RequestMapping(value = "/contact/groups/{id}", method = RequestMethod.GET)
	public ContactGroupDetail getContactGroupDetail(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		return contactRestSupport.getContactGroupDetail(id, user);
	}

	@ApiOperation(value = "修改我的联系人分组信息")
	@RequestMapping(value = "/contact/groups/{id}", method = RequestMethod.POST)
	public void updateContactGroup(@PathVariable String id, @RequestBody SaveContactGroupParameter updateRequest) {
		User user = SecurityContexts.getContext().requireUser();
		contactRestSupport.updateContactGroup(id, updateRequest, user);
	}

	@ApiOperation(value = "删除我的联系人分组信息,同时解除该分组下的联系人和该组的关系")
	@RequestMapping(value = "/contact/groups/{id}", method = RequestMethod.DELETE)
	public void deleteContactGroup(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		contactRestSupport.deleteContactGroup(id, user);
	}

	@ApiOperation(value = "添加用户到联系人分组,自动忽略已经重复添加的联系人")
	@RequestMapping(value = "/contact/groups/{id}/users", method = RequestMethod.POST)
	public void addUsersToContactGroup(@PathVariable String id, @RequestBody String[] userIds) {
		if (userIds == null || userIds.length == 0) {
			return;
		}
		User user = SecurityContexts.getContext().requireUser();
		contactRestSupport.addUsersToContactGroup(id, userIds, user);
	}

	@ApiOperation(value = "解除联系人和联系人分组的关系")
	@RequestMapping(value = "/contact/groups/{id}/users/{userIds}", method = RequestMethod.DELETE)
	public void removeUsersFromContactGroup(@PathVariable String id, @PathVariable String userIds) {
		if (StringUtils.isEmpty(userIds)) {
			return;
		}
		String[] userIdsArray = userIds.split(",");
		User user = SecurityContexts.getContext().requireUser();
		contactRestSupport.removeUsersFromContactGroup(id, userIdsArray, user);
	}

}
