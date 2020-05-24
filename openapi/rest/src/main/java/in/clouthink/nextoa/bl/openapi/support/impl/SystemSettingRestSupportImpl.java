package in.clouthink.nextoa.bl.openapi.support.impl;

import in.clouthink.nextoa.bl.model.User;
import in.clouthink.nextoa.bl.openapi.dto.SaveSystemSettingParameter;
import in.clouthink.nextoa.bl.openapi.dto.SystemSettingSummary;
import in.clouthink.nextoa.bl.openapi.support.SystemSettingRestSupport;
import in.clouthink.nextoa.bl.service.SystemSettingService;
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
