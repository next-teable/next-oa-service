package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.project.domain.request.SaveProjectManageRelationshipRequest;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by cengruilin on 2017/12/28.
 */
public class SaveProjectManageRelationshipParameter implements SaveProjectManageRelationshipRequest {

    @NotNull(message = "项目经理不能为空")
    @NotEmpty(message = "项目经理不能为空")
    private String[] managerIds;
    @NotNull(message = "年份不能为空")
    private Integer year;
    @NotNull(message = "所属事业部不能为空")
    private String bizDepartmentId;
    @NotNull(message = "项目不能为空")
    private String projectId;

    @Override
    public String[] getManagerIds() {
        return managerIds;
    }

    public void setManagerIds(String[] managerIds) {
        this.managerIds = managerIds;
    }

    @Override
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public String getBizDepartmentId() {
        return bizDepartmentId;
    }

    public void setBizDepartmentId(String bizDepartmentId) {
        this.bizDepartmentId = bizDepartmentId;
    }

    @Override
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
