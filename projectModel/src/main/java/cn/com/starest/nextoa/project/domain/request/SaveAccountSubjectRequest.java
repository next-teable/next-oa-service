package cn.com.starest.nextoa.project.domain.request;

import cn.com.starest.nextoa.project.domain.model.Module;

/**
 * @author dz
 */
public interface SaveAccountSubjectRequest {

	String getName();

	String getSort();

	boolean isProjectEnabled();

	boolean isBizDepartmentEnabled();

	String getDescription();

	Module[] getModules();

}
