package cn.com.starest.nextoa.project.domain.request;

import cn.com.starest.nextoa.project.domain.model.AbstractSalary;

/**
 * @author dz
 */
public class SaveSalaryRequest extends AbstractSalary {

    String companyId;

    String companyName;

    String projectId;
    
    String projectName;
    
    String bizDepartmentId;
    
    String bizDepartmentName;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        if (companyName != null) {
            companyName = companyName.trim();
        }
        this.companyName = companyName;
    }

    public String getProjectId() {
        return projectId;
    }
    
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
    
    public String getProjectName() {
        return projectName;
    }
    
    public void setProjectName(String projectName) {
        if (projectName != null) {
            projectName = projectName.trim();
        }
        this.projectName = projectName;
    }
    
    public String getBizDepartmentId() {
        return bizDepartmentId;
    }
    
    public void setBizDepartmentId(String bizDepartmentId) {
        this.bizDepartmentId = bizDepartmentId;
    }
    
    public String getBizDepartmentName() {
        return bizDepartmentName;
    }
    
    public void setBizDepartmentName(String bizDepartmentName) {
        if (bizDepartmentName != null) {
            bizDepartmentName = bizDepartmentName.trim();
        }
        this.bizDepartmentName = bizDepartmentName;
    }
}
