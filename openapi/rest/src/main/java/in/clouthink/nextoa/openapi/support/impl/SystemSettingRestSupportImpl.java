package in.clouthink.nextoa.openapi.support.impl;

import in.clouthink.nextoa.model.User;
import in.clouthink.nextoa.openapi.dto.SaveSystemSettingParameter;
import in.clouthink.nextoa.openapi.dto.SystemSettingSummary;
import in.clouthink.nextoa.openapi.support.SystemSettingRestSupport;
import in.clouthink.nextoa.service.SystemSettingService;
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
