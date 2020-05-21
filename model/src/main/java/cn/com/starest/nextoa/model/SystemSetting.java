package cn.com.starest.nextoa.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 */
@Document(collection = "SystemSettings")
public class SystemSetting extends BaseModel {

	private String name;

	private String fileObjectId;

	private String contactEmail;

	private String contactPhone;

	@DBRef
	private List<User> companySupervisors = new ArrayList<>();

	@DBRef
	private List<User> bizDepartmentSupervisors = new ArrayList<>();

	@DBRef
	private List<User> projectSupervisors = new ArrayList<>();

	@DBRef
	private List<User> profitSupervisors = new ArrayList<>();

	//项目利润提成 会计科目
	private String profitAccountSubjectName;

	//外协单位报销 会计科目
	private String outsourceAccountSubjectName;

	public List<User> getCompanySupervisors() {
		return companySupervisors;
	}

	public void setCompanySupervisors(List<User> companySupervisors) {
		this.companySupervisors = companySupervisors;
	}

	public List<User> getBizDepartmentSupervisors() {
		return bizDepartmentSupervisors;
	}

	public void setBizDepartmentSupervisors(List<User> bizDepartmentSupervisors) {
		this.bizDepartmentSupervisors = bizDepartmentSupervisors;
	}

	public List<User> getProjectSupervisors() {
		return projectSupervisors;
	}

	public void setProjectSupervisors(List<User> projectSupervisors) {
		this.projectSupervisors = projectSupervisors;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFileObjectId() {
		return fileObjectId;
	}

	public void setFileObjectId(String fileObjectId) {
		this.fileObjectId = fileObjectId;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public List<User> getProfitSupervisors() {
		return profitSupervisors;
	}

	public void setProfitSupervisors(List<User> profitSupervisors) {
		this.profitSupervisors = profitSupervisors;
	}


	public String getProfitAccountSubjectName() {
		return profitAccountSubjectName;
	}

	public void setProfitAccountSubjectName(String profitAccountSubjectName) {
		this.profitAccountSubjectName = trim(profitAccountSubjectName);
	}

	public String getOutsourceAccountSubjectName() {
		return outsourceAccountSubjectName;
	}

	public void setOutsourceAccountSubjectName(String outsourceAccountSubjectName) {
		this.outsourceAccountSubjectName = outsourceAccountSubjectName;
	}
}
