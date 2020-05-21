package cn.com.starest.nextoa.project.domain.request;

import cn.com.starest.nextoa.project.domain.model.BizDepartmentType;

/**
 * @author dz
 */
public interface SaveBizDepartmentRequest {

	String getName();

	String[] getManagerIds();

	String getSort();

	String getDescription();

	BizDepartmentType getType();

}
