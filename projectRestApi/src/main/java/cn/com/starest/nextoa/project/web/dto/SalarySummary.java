package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.project.domain.model.AbstractSalary;
import cn.com.starest.nextoa.project.domain.model.ModuleActions;
import cn.com.starest.nextoa.project.domain.model.Salary;
import org.springframework.beans.BeanUtils;

/**
 * @author dz
 */
public class SalarySummary extends AbstractSalary {

	public static void convert(Salary fromObj, SalarySummary result) {
		BeanUtils.copyProperties(fromObj, result);

		if (fromObj.getCompany() != null) {
			result.setCompanyId(fromObj.getCompany().getId());
			result.setCompanyName(fromObj.getCompany().getName());
		}
		if (fromObj.getProject() != null) {
			result.setProjectId(fromObj.getProject().getId());
			result.setProjectName(fromObj.getProject().getName());
		}
		if (fromObj.getBizDepartment() != null) {
			result.setBizDepartmentId(fromObj.getBizDepartment().getId());
			result.setBizDepartmentName(fromObj.getBizDepartment().getName());
		}
	}

	public static SalarySummary from(Salary fromObj) {
		if (fromObj == null) {
			return null;
		}
		SalarySummary result = new SalarySummary();
		convert(fromObj, result);
		return result;
	}

	private ModuleActions.ModuleAction[] grantedActions;

	private String id;

	private String companyId;

	private String companyName;

	private String projectId;

	private String projectName;

	private String bizDepartmentId;

	private String bizDepartmentName;

	public ModuleActions.ModuleAction[] getGrantedActions() {
		return grantedActions;
	}

	public void setGrantedActions(ModuleActions.ModuleAction[] grantedActions) {
		this.grantedActions = grantedActions;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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
		this.bizDepartmentName = bizDepartmentName;
	}
}
