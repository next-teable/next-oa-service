package in.clouthink.nextoa.openapi.controller;

import in.clouthink.nextoa.openapi.dto.UserDetail;
import in.clouthink.nextoa.openapi.dto.UserQueryParameter;
import in.clouthink.nextoa.openapi.dto.UserSummary;
import in.clouthink.nextoa.openapi.support.ArchivedUserRestSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api("已归档的用户管理")
@RestController
@RequestMapping("/api")
public class ArchivedUserRestController {

	@Autowired
	private ArchivedUserRestSupport archivedUserRestSupport;

	@ApiOperation(value = "查看归档用户列表,支持分页,支持动态查询（用户名等）")
	@RequestMapping(value = "/archivedusers", method = RequestMethod.GET)
	public Page<UserSummary> listArchivedUsers(UserQueryParameter queryRequest) {
		return archivedUserRestSupport.listArchivedUsers(queryRequest);
	}

	@ApiOperation(value = "查看归档用户基本信息")
	@RequestMapping(value = "/archivedusers/{id}", method = RequestMethod.GET)
	public UserDetail getArchivedUser(@PathVariable String id) {
		return archivedUserRestSupport.getArchivedUser(id);
	}

}
