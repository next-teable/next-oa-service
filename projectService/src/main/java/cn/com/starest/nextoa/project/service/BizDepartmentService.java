package cn.com.starest.nextoa.project.service;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.model.dtr.PageQueryRequest;
import cn.com.starest.nextoa.project.domain.model.BizDepartment;
import cn.com.starest.nextoa.project.domain.model.BizDepartmentType;
import cn.com.starest.nextoa.project.domain.request.SaveBizDepartmentRequest;
import org.springframework.data.domain.Page;

/**
 * @author dz
 */
public interface BizDepartmentService {

	BizDepartment createBizDepartment(SaveBizDepartmentRequest request, User byWho);

	BizDepartment updateBizDepartment(String id, SaveBizDepartmentRequest request, User byWho);

	BizDepartment deleteBizDepartmentById(String id, User byWho);

	BizDepartment findBizDepartmentById(String id);

	Page<BizDepartment> listBizDepartments(PageQueryRequest request);

	Page<BizDepartment> listBizDepartmentsByType(BizDepartmentType type, PageQueryRequest request);

	Page<BizDepartment> listSupervisedBizDepartments(PageQueryRequest request, User byWho);

}
