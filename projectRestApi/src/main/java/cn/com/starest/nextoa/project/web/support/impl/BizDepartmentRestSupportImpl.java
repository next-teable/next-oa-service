package cn.com.starest.nextoa.project.web.support.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.dto.PageQueryParameter;
import cn.com.starest.nextoa.project.domain.model.BizDepartmentType;
import cn.com.starest.nextoa.project.service.BizDepartmentService;
import cn.com.starest.nextoa.project.web.dto.BizDepartmentSummary;
import cn.com.starest.nextoa.project.web.dto.SaveBizDepartmentParameter;
import cn.com.starest.nextoa.project.web.support.BizDepartmentRestSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dz
 */
@Component
public class BizDepartmentRestSupportImpl implements BizDepartmentRestSupport {

    @Autowired
    private BizDepartmentService accountSubjectService;

    @Override
    public BizDepartmentSummary createBizDepartment(SaveBizDepartmentParameter request, User byWho) {
        return BizDepartmentSummary.from(accountSubjectService.createBizDepartment(request, byWho));
    }

    @Override
    public BizDepartmentSummary updateBizDepartment(String id, SaveBizDepartmentParameter request, User byWho) {
        return BizDepartmentSummary.from(accountSubjectService.updateBizDepartment(id, request, byWho));
    }

    @Override
    public void deleteBizDepartmentById(String id, User byWho) {
        accountSubjectService.deleteBizDepartmentById(id, byWho);
    }

    @Override
    public BizDepartmentSummary findBizDepartmentById(String id) {
        return BizDepartmentSummary.from(accountSubjectService.findBizDepartmentById(id));
    }

    @Override
    public List<BizDepartmentSummary> listBizDepartments() {
        return accountSubjectService.listBizDepartments(new PageQueryParameter(0, 100))
                .getContent()
                .stream()
                .map(BizDepartmentSummary::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<BizDepartmentSummary> listBizDepartmentsByType(BizDepartmentType type) {
        return accountSubjectService.listBizDepartmentsByType(type, new PageQueryParameter(0, 100))
                .getContent()
                .stream()
                .map(BizDepartmentSummary::from)
                .collect(Collectors.toList());
    }

}
