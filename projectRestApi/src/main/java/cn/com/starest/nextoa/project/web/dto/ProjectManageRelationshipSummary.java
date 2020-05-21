package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.model.dtr.AbstractBaseSummary;
import cn.com.starest.nextoa.openapi.dto.SimpleUser;
import cn.com.starest.nextoa.project.domain.model.ProjectManageRelationship;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cengruilin on 2017/12/28.
 */
public class ProjectManageRelationshipSummary extends AbstractBaseSummary {

    private String projectId;
    private int year;
    private String bizDepartmentId;
    private List<SimpleUser> projectManagers = new ArrayList();

    public static ProjectManageRelationshipSummary from(ProjectManageRelationship fromObj) {
        if (fromObj == null) {
            return null;
        }
        ProjectManageRelationshipSummary result = new ProjectManageRelationshipSummary();
        convert(fromObj, result);
        return result;
    }

    public static void convert(ProjectManageRelationship fromObj, ProjectManageRelationshipSummary result) {
        AbstractBaseSummary.convert(fromObj, result);
        if (fromObj.getBelongTo() != null) {
            result.setBizDepartmentId(fromObj.getBelongTo().getId());
        }


        if (fromObj.getProject() != null) {
            result.setProjectId(fromObj.getProject().getId());
        }

        result.setYear(fromObj.getYear());
        result.setId(fromObj.getId());

        if (fromObj.getManagers() != null) {
            result.setProjectManagers(fromObj.getManagers()
                    .stream()
                    .map(mgr -> SimpleUser.from(mgr))
                    .collect(Collectors.toList()));
        }
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public List<SimpleUser> getProjectManagers() {
        return projectManagers;
    }

    public void setProjectManagers(List<SimpleUser> projectManagers) {
        this.projectManagers = projectManagers;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getBizDepartmentId() {
        return bizDepartmentId;
    }

    public void setBizDepartmentId(String bizDepartmentId) {
        this.bizDepartmentId = bizDepartmentId;
    }
}
