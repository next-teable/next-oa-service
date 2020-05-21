package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.model.shared.StringIdentifier;
import cn.com.starest.nextoa.project.domain.model.AccountSubject;
import cn.com.starest.nextoa.project.domain.model.Module;
import org.springframework.beans.BeanUtils;

/**
 * @author dz
 */
public class AccountSubjectSummary extends StringIdentifier {

	public static void convert(AccountSubject fromObj, AccountSubjectSummary result) {
		BeanUtils.copyProperties(fromObj, result);

		if (result.getModules() == null) {
			result.setModules(Module.EMPTY_MODULES);
		}
	}

	public static AccountSubjectSummary from(AccountSubject fromObj) {
		if (fromObj == null) {
			return null;
		}
		AccountSubjectSummary result = new AccountSubjectSummary();
		convert(fromObj, result);
		return result;
	}

	private String name;

	private Module[] modules;

	private boolean projectEnabled;

	private boolean bizDepartmentEnabled;

	private String sort;

	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Module[] getModules() {
		return modules;
	}

	public void setModules(Module[] modules) {
		this.modules = modules;
	}

	public boolean isProjectEnabled() {
		return projectEnabled;
	}

	public void setProjectEnabled(boolean projectEnabled) {
		this.projectEnabled = projectEnabled;
	}

	public boolean isBizDepartmentEnabled() {
		return bizDepartmentEnabled;
	}

	public void setBizDepartmentEnabled(boolean bizDepartmentEnabled) {
		this.bizDepartmentEnabled = bizDepartmentEnabled;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
