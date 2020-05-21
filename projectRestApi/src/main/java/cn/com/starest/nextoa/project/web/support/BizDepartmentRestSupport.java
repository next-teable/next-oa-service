package cn.com.starest.nextoa.project.web.support;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.BizDepartmentType;
import cn.com.starest.nextoa.project.web.dto.BizDepartmentSummary;
import cn.com.starest.nextoa.project.web.dto.SaveBizDepartmentParameter;

import java.util.List;

/**
 * 会计科目
 *
 * @author dz
 */
public interface BizDepartmentRestSupport {

	BizDepartmentSummary createBizDepartment(SaveBizDepartmentParameter request, User user);

	BizDepartmentSummary updateBizDepartment(String id, SaveBizDepartmentParameter request, User user);

	void deleteBizDepartmentById(String id, User user);

	BizDepartmentSummary findBizDepartmentById(String id);

	List<BizDepartmentSummary> listBizDepartments();

	List<BizDepartmentSummary> listBizDepartmentsByType(BizDepartmentType type);

}
