package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.model.dtr.AbstractBaseSummary;
import cn.com.starest.nextoa.project.domain.model.CompanyReceivedPayment;
import cn.com.starest.nextoa.project.domain.model.ReceivedPaymentType;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dz
 */
public class CompanyReceivedPaymentSummary extends AbstractPermissionAware {

	public static void convert(CompanyReceivedPayment fromObj, CompanyReceivedPaymentSummary result) {
		AbstractBaseSummary.convert(fromObj, result);
		BeanUtils.copyProperties(fromObj, result);

		if (fromObj.getCompany() != null) {
			result.setCompanyId(fromObj.getCompany().getId());
			result.setCompanyName(fromObj.getCompany().getName());
		}

		if (fromObj.getAccountSubject() != null) {
			result.setAccountSubjectId(fromObj.getAccountSubject().getId());
			result.setAccountSubjectName(fromObj.getAccountSubject().getName());
		}

		if (fromObj.getSubAccountSubject() != null) {
			result.setSubAccountSubjectId(fromObj.getSubAccountSubject().getId());
			result.setSubAccountSubjectName(fromObj.getSubAccountSubject().getName());
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

	public static CompanyReceivedPaymentSummary from(CompanyReceivedPayment fromObj) {
		if (fromObj == null) {
			return null;
		}
		CompanyReceivedPaymentSummary result = new CompanyReceivedPaymentSummary();
		convert(fromObj, result);
		return result;
	}

	private int year;

	private String code;

	private String companyId;

	private String companyName;

	private ReceivedPaymentType type;

	private String accountSubjectId;

	private String accountSubjectName;

	private String subAccountSubjectId;

	private String subAccountSubjectName;

	//项目
	private String projectId;

	private String projectName;

	//部门
	private String bizDepartmentId;

	private String bizDepartmentName;

	private BigDecimal amount;

	private Date receivedAt;

	// 回款单位
	private String receivedFrom;

	private String description;

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public ReceivedPaymentType getType() {
		return type;
	}

	public void setType(ReceivedPaymentType type) {
		this.type = type;
	}

	public String getAccountSubjectId() {
		return accountSubjectId;
	}

	public void setAccountSubjectId(String accountSubjectId) {
		this.accountSubjectId = accountSubjectId;
	}

	public String getAccountSubjectName() {
		return accountSubjectName;
	}

	public void setAccountSubjectName(String accountSubjectName) {
		this.accountSubjectName = accountSubjectName;
	}

	public String getSubAccountSubjectId() {
		return subAccountSubjectId;
	}

	public void setSubAccountSubjectId(String subAccountSubjectId) {
		this.subAccountSubjectId = subAccountSubjectId;
	}

	public String getSubAccountSubjectName() {
		return subAccountSubjectName;
	}

	public void setSubAccountSubjectName(String subAccountSubjectName) {
		this.subAccountSubjectName = subAccountSubjectName;
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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getReceivedAt() {
		return receivedAt;
	}

	public void setReceivedAt(Date receivedAt) {
		this.receivedAt = receivedAt;
	}

	public String getReceivedFrom() {
		return receivedFrom;
	}

	public void setReceivedFrom(String receivedFrom) {
		this.receivedFrom = receivedFrom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
