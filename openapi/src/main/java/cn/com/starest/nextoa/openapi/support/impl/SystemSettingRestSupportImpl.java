package cn.com.starest.nextoa.openapi.support.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.dto.SaveSystemSettingParameter;
import cn.com.starest.nextoa.openapi.dto.SystemSettingSummary;
import cn.com.starest.nextoa.openapi.support.SystemSettingRestSupport;
import cn.com.starest.nextoa.service.SystemSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dz
 */
@Service
public class SystemSettingRestSupportImpl implements SystemSettingRestSupport {

	@Autowired
	private SystemSettingService systemSettingService;

	@Override
	public SystemSettingSummary getSystemSetting() {
		return SystemSettingSummary.from(systemSettingService.getSystemSetting());
	}

	@Override
	public void updateSystemSetting(SaveSystemSettingParameter systemSetting, User byWho) {
		systemSettingService.updateSystemSetting(systemSetting, byWho);
	}

}
