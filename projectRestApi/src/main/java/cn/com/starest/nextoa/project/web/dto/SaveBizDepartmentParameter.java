package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.project.domain.model.BizDepartmentType;
import cn.com.starest.nextoa.project.domain.request.SaveBizDepartmentRequest;

import javax.validation.constraints.NotNull;

/**
 * @author dz
 */
public class SaveBizDepartmentParameter implements SaveBizDepartmentRequest {

    @NotNull(message = "名称不能为空")
    private String name;

    private String[] managerIds;

    private String sort;

    private String description;

    @NotNull(message = "业务部门类型不能为空")
    private BizDepartmentType type;

    @Override
    public BizDepartmentType getType() {
        return type;
    }

    public void setType(BizDepartmentType type) {
        this.type = type;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String[] getManagerIds() {
        return managerIds;
    }

    public void setManagerIds(String[] managerIds) {
        this.managerIds = managerIds;
    }

    @Override
    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
