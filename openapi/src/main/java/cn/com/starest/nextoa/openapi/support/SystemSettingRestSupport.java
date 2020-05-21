package cn.com.starest.nextoa.openapi.support;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.dto.SaveSystemSettingParameter;
import cn.com.starest.nextoa.openapi.dto.SystemSettingSummary;

/**
 * @author dz
 */
public interface SystemSettingRestSupport {

	SystemSettingSummary getSystemSetting();

	void updateSystemSetting(SaveSystemSettingParameter updateSystemSetting, User byWho);

}
