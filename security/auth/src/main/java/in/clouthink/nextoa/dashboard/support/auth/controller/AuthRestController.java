package in.clouthink.nextoa.dashboard.support.auth.controller;

import in.clouthink.nextoa.dashboard.support.auth.model.AuthEvent;
import in.clouthink.nextoa.dashboard.support.auth.support.AuthEventRestSupport;
import in.clouthink.nextoa.dashboard.support.auth.support.UserAuthQueryParameter;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping("/api")
public class AuthRestController {

	@Autowired
	private AuthEventRestSupport authEventRestSupport;

	@ApiOperation(value = "用户登录日志列表,支持分页,支持动态查询(按名称,状态查询)")
	@RequestMapping(value = "/authEvents", method = RequestMethod.GET)
	public Page<AuthEvent> listAuthEventPage(UserAuthQueryParameter queryRequest) {
		return authEventRestSupport.listAuthEventPage(queryRequest);
	}

	@ApiOperation(value = "用户登录日志列表")
	@RequestMapping(value = "/authEvents/{id}", method = RequestMethod.GET)
	public AuthEvent getAuthEventDetail(@PathVariable String id) {
		return authEventRestSupport.getAuthEventDetail(id);
	}

}
