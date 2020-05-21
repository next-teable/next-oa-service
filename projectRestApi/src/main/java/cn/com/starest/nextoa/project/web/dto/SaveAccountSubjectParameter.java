package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.project.domain.model.Module;
import cn.com.starest.nextoa.project.domain.request.SaveAccountSubjectRequest;

import javax.validation.constraints.NotNull;

/**
 * @author dz
 */
public class SaveAccountSubjectParameter implements SaveAccountSubjectRequest {

	@NotNull(message = "名称不能为空")
	private String name;

	private String sort;

	//选分包单位
	private boolean projectEnabled;

	//选业务部门
	private boolean bizDepartmentEnabled;

	private String description;

	private Module[] modules;

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	@Override
	public boolean isProjectEnabled() {
		return projectEnabled;
	}

	public void setProjectEnabled(boolean projectEnabled) {
		this.projectEnabled = projectEnabled;
	}

	@Override
	public boolean isBizDepartmentEnabled() {
		return bizDepartmentEnabled;
	}

	public void setBizDepartmentEnabled(boolean bizDepartmentEnabled) {
		this.bizDepartmentEnabled = bizDepartmentEnabled;
	}

	@Override
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public Module[] getModules() {
		return modules;
	}

	public void setModules(Module[] modules) {
		this.modules = modules;
	}
}
