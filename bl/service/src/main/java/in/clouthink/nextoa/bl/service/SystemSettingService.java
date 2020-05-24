package in.clouthink.nextoa.bl.service;

import in.clouthink.nextoa.bl.model.SystemSetting;
import in.clouthink.nextoa.bl.model.User;
import in.clouthink.nextoa.bl.request.SaveSystemSettingRequest;

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
