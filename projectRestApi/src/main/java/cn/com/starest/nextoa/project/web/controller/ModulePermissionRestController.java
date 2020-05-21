package cn.com.starest.nextoa.project.web.controller;

import cn.com.starest.nextoa.project.web.dto.GrantModuleParameter;
import cn.com.starest.nextoa.project.web.dto.ModulePermissionSummary;
import cn.com.starest.nextoa.project.web.support.ModulePermissionRestSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("模块权限管理")
@RestController
@RequestMapping("/api")
public class ModulePermissionRestController {

	@Autowired
	private ModulePermissionRestSupport modulePermissionRestSupport;

	@ApiOperation(value = "查询用户的模块权限")
	@RequestMapping(value = "/permissions/modules/byUser/{id}", method = RequestMethod.GET)
	public List<ModulePermissionSummary> listModulePermissions(@PathVariable String id) {
		return modulePermissionRestSupport.listModulePermissions(id);
	}

	@ApiOperation(value = "保存用户的模块权限")
	@RequestMapping(value = "/permissions/modules/byUser/{id}", method = RequestMethod.POST)
	public void saveModulePermission(@PathVariable String id, @RequestBody GrantModuleParameter request) {
		//		User user = SecurityContexts.getContext().requireUser();
		modulePermissionRestSupport.saveModulePermissions(id, request);
	}

}
