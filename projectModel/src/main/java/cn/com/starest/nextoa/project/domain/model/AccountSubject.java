package cn.com.starest.nextoa.project.domain.model;

import cn.com.starest.nextoa.model.BaseModel;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 会计科目
 *
 * @author dz
 */
@Document(collection = "AccountSubjects")
public class AccountSubject extends BaseModel {

	@Indexed
	private String name;

	private String description;

	@Indexed
	private Module[] modules;

	private boolean projectEnabled;

	private boolean bizDepartmentEnabled;

	private String sort;

	@Indexed
	@DBRef(lazy = true)
	private AccountSubject parent;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public AccountSubject getParent() {
		return parent;
	}

	public void setParent(AccountSubject parent) {
		this.parent = parent;
	}

}
