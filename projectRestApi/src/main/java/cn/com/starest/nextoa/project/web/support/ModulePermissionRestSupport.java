package cn.com.starest.nextoa.project.web.support;

import cn.com.starest.nextoa.project.web.dto.GrantModuleParameter;
import cn.com.starest.nextoa.project.web.dto.ModulePermissionSummary;

import java.util.List;

/**
 * @author dz
 */
public interface ModulePermissionRestSupport {

	List<ModulePermissionSummary> listModulePermissions(String userId);

	void saveModulePermissions(String userId, GrantModuleParameter request);

}
