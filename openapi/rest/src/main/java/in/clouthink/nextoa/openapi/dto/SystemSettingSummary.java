package in.clouthink.nextoa.openapi.dto;

import in.clouthink.nextoa.model.SystemSetting;
import in.clouthink.nextoa.model.dtr.AbstractBaseSummary;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dz
 */
public class SystemSettingSummary extends AbstractBaseSummary {

	public static void convert(SystemSetting fromObj, SystemSettingSummary result) {
		BeanUtils.copyProperties(fromObj,
								 result,
								 "companySupervisors",
								 "bizDepartmentSupervisors",
								 "projectSupervisors");
		AbstractBaseSummary.convert(fromObj, result);
		if (fromObj.getCompanySupervisors() != null) {
			result.setCompanySupervisors(fromObj.getCompanySupervisors()
												.stream()
												.map(mgr -> SimpleUser.from(mgr))
												.collect(Collectors.toList()));
		}
		if (fromObj.getBizDepartmentSupervisors() != null) {
			result.setBizDepartmentSupervisors(fromObj.getBizDepartmentSupervisors()
													  .stream()
													  .map(mgr -> SimpleUser.from(mgr))
													  .collect(Collectors.toList()));
		}
		if (fromObj.getProjectSupervisors() != null) {
			result.setProjectSupervisors(fromObj.getProjectSupervisors()
												.stream()
												.map(mgr -> SimpleUser.from(mgr))
												.collect(Collectors.toList()));
		}
		if (fromObj.getProfitSupervisors() != null) {
			result.setProfitSupervisors(fromObj.getProfitSupervisors()
											   .stream()
											   .map(mgr -> SimpleUser.from(mgr))
											   .collect(Collectors.toList()));
		}
	}

	public static SystemSettingSummary from(SystemSetting fromObj) {
		if (fromObj == null) {
			return null;
		}
		SystemSettingSummary result = new SystemSettingSummary();
		convert(fromObj, result);
		return result;
	}

	private String name;

	private String fileObjectId;

	private String contactEmail;

	private String contactPhone;

	private List<SimpleUser> companySupervisors = new ArrayList();

	private List<SimpleUser> bizDepartmentSupervisors = new ArrayList();

	private List<SimpleUser> projectSupervisors = new ArrayList();

	private List<SimpleUser> profitSupervisors = new ArrayList();

	private String profitAccountSubjectName;

	private String outsourceAccountSubjectName;

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

	public List<SimpleUser> getCompanySupervisors() {
		return companySupervisors;
	}

	public void setCompanySupervisors(List<SimpleUser> companySupervisors) {
		this.companySupervisors = companySupervisors;
	}

	public List<SimpleUser> getBizDepartmentSupervisors() {
		return bizDepartmentSupervisors;
	}

	public void setBizDepartmentSupervisors(List<SimpleUser> bizDepartmentSupervisors) {
		this.bizDepartmentSupervisors = bizDepartmentSupervisors;
	}

	public List<SimpleUser> getProjectSupervisors() {
		return projectSupervisors;
	}

	public void setProjectSupervisors(List<SimpleUser> projectSupervisors) {
		this.projectSupervisors = projectSupervisors;
	}

	public List<SimpleUser> getProfitSupervisors() {
		return profitSupervisors;
	}

	public void setProfitSupervisors(List<SimpleUser> profitSupervisors) {
		this.profitSupervisors = profitSupervisors;
	}

	public String getProfitAccountSubjectName() {
		return profitAccountSubjectName;
	}

	public void setProfitAccountSubjectName(String profitAccountSubjectName) {
		this.profitAccountSubjectName = profitAccountSubjectName;
	}

	public String getOutsourceAccountSubjectName() {
		return outsourceAccountSubjectName;
	}

	public void setOutsourceAccountSubjectName(String outsourceAccountSubjectName) {
		this.outsourceAccountSubjectName = outsourceAccountSubjectName;
	}
}
