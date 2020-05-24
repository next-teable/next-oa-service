package in.clouthink.nextoa.bl.openapi.support;

import in.clouthink.nextoa.bl.model.User;
import in.clouthink.nextoa.bl.openapi.dto.SaveSystemSettingParameter;
import in.clouthink.nextoa.bl.openapi.dto.SystemSettingSummary;

/**
 * @author dz
 */
public interface SystemSettingRestSupport {

	SystemSettingSummary getSystemSetting();

	void updateSystemSetting(SaveSystemSettingParameter updateSystemSetting, User byWho);

}
