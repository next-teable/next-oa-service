package in.clouthink.nextoa.openapi.support;

import in.clouthink.nextoa.model.User;
import in.clouthink.nextoa.openapi.dto.SaveSystemSettingParameter;
import in.clouthink.nextoa.openapi.dto.SystemSettingSummary;

/**
 * @author dz
 */
public interface SystemSettingRestSupport {

	SystemSettingSummary getSystemSetting();

	void updateSystemSetting(SaveSystemSettingParameter updateSystemSetting, User byWho);

}
