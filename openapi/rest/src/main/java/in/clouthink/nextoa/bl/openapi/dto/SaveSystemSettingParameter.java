package in.clouthink.nextoa.bl.openapi.dto;

import in.clouthink.nextoa.bl.request.SaveSystemSettingRequest;

/**
 * @author dz
 */
public class SaveSystemSettingParameter implements SaveSystemSettingRequest {

	private String name;

	private String fileObjectId;

	private String contactEmail;

	private String contactPhone;

	private String[] companySupervisorIds = new String[0];

	private String[] bizDepartmentSupervisorIds = new String[0];

	private String[] projectSupervisorIds = new String[0];

	private String[] profitSupervisorIds = new String[0];

	private String profitAccountSubjectName;

	private String outsourceAccountSubjectName;

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getFileObjectId() {
		return fileObjectId;
	}

	public void setFileObjectId(String fileObjectId) {
		this.fileObjectId = fileObjectId;
	}

	@Override
	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	@Override
	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	@Override
	public String[] getCompanySupervisorIds() {
		return companySupervisorIds;
	}

	public void setCompanySupervisorIds(String[] companySupervisorIds) {
		this.companySupervisorIds = companySupervisorIds;
	}

	@Override
	public String[] getBizDepartmentSupervisorIds() {
		return bizDepartmentSupervisorIds;
	}

	public void setBizDepartmentSupervisorIds(String[] bizDepartmentSupervisorIds) {
		this.bizDepartmentSupervisorIds = bizDepartmentSupervisorIds;
	}

	@Override
	public String[] getProjectSupervisorIds() {
		return projectSupervisorIds;
	}

	public void setProjectSupervisorIds(String[] projectSupervisorIds) {
		this.projectSupervisorIds = projectSupervisorIds;
	}

	@Override
	public String[] getProfitSupervisorIds() {
		return profitSupervisorIds;
	}

	public void setProfitSupervisorIds(String[] profitSupervisorIds) {
		this.profitSupervisorIds = profitSupervisorIds;
	}

	@Override
	public String getProfitAccountSubjectName() {
		return profitAccountSubjectName;
	}

	public void setProfitAccountSubjectName(String profitAccountSubjectName) {
		this.profitAccountSubjectName = profitAccountSubjectName;
	}

	@Override
	public String getOutsourceAccountSubjectName() {
		return outsourceAccountSubjectName;
	}

	public void setOutsourceAccountSubjectName(String outsourceAccountSubjectName) {
		this.outsourceAccountSubjectName = outsourceAccountSubjectName;
	}
}
