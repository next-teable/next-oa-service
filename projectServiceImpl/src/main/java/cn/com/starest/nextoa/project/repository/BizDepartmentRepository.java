package cn.com.starest.nextoa.project.repository;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.BizDepartment;
import cn.com.starest.nextoa.project.domain.model.BizDepartmentType;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 */
public interface BizDepartmentRepository extends AbstractRepository<BizDepartment> {

    BizDepartment findFirstByName(String name);

    Page<BizDepartment> findPageByManagersIn(User manager, Pageable pagable);

    Page<BizDepartment> findPageByType(BizDepartmentType type, Pageable pagable);

}