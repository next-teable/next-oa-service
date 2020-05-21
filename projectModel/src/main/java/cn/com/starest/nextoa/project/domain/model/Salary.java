package cn.com.starest.nextoa.project.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Salary
 *
 * @author dz
 */
@Document(collection = "Salaries")
public class Salary extends AbstractSalary {

	@Id
	String id;

	@Indexed
	@DBRef(lazy = true)
	Company company;

	@Indexed
	@DBRef(lazy = true)
	Project project;

	@Indexed
	@DBRef(lazy = true)
	BizDepartment bizDepartment;

	//关联的数据导入
	@Indexed
	@DBRef(lazy = true)
	SalaryImportHistory salaryImportHistory;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public BizDepartment getBizDepartment() {
		return bizDepartment;
	}

	public void setBizDepartment(BizDepartment bizDepartment) {
		this.bizDepartment = bizDepartment;
	}

	public SalaryImportHistory getSalaryImportHistory() {
		return salaryImportHistory;
	}

	public void setSalaryImportHistory(SalaryImportHistory salaryImportHistory) {
		this.salaryImportHistory = salaryImportHistory;
	}
}
