package cn.com.starest.nextoa.service;

import cn.com.starest.nextoa.model.SystemSetting;
import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.model.dtr.SaveSystemSettingRequest;

import java.util.List;

/**
 * @author dz
 */
public interface SystemSettingService {

	SystemSetting getSystemSetting();

	void updateSystemSetting(SaveSystemSettingRequest systemSetting, User byWho);

	String getFullUrl(String shortUrlKey);

	List<User> listProjectSupervisors();

	boolean isProjectSupervisor(User user);

	List<User> listCompanySupervisors();

	boolean isCompanySupervisor(User user);

	List<User> listBizDepartmentSupervisors();

	boolean isBizDepartmentSupervisor(User user);
}
