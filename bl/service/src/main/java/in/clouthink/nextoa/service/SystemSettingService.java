package in.clouthink.nextoa.service;

import in.clouthink.nextoa.model.SystemSetting;
import in.clouthink.nextoa.model.User;
import in.clouthink.nextoa.model.dtr.SaveSystemSettingRequest;

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
