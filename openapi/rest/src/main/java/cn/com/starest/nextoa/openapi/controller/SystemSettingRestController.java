package cn.com.starest.nextoa.openapi.controller;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.dto.SaveSystemSettingParameter;
import cn.com.starest.nextoa.openapi.dto.SystemSettingSummary;
import cn.com.starest.nextoa.openapi.security.SecurityContexts;
import cn.com.starest.nextoa.openapi.support.SystemSettingRestSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api("系统设置")
@RestController
@RequestMapping
public class SystemSettingRestController {

	@Autowired
	private SystemSettingRestSupport systemSettingRestSupport;

	@ApiOperation(value = "查询系统设置")
	@RequestMapping(value = {"/api/system/setting", "/guest/application/branding"}, method = RequestMethod.GET)
	public SystemSettingSummary getSystemSetting() {
		return systemSettingRestSupport.getSystemSetting();
	}

	@ApiOperation(value = "修改系统设置")
	@RequestMapping(value = "/api/system/setting", method = RequestMethod.POST)
	public void updateSystemSetting(@RequestBody SaveSystemSettingParameter updateSystemSetting) {
		User user = SecurityContexts.getContext().requireUser();
		systemSettingRestSupport.updateSystemSetting(updateSystemSetting, user);
	}

}
