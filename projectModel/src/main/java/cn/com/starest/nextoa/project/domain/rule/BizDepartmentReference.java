package cn.com.starest.nextoa.project.domain.rule;

import cn.com.starest.nextoa.project.domain.model.BizDepartment;

/**
 * @author dz
 */
public interface BizDepartmentReference {

	boolean hasReference(BizDepartment target);

}
