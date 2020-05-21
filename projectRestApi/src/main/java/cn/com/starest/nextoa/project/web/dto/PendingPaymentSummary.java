package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.model.dtr.AbstractBaseSummary;
import cn.com.starest.nextoa.project.domain.model.CompanyReceivedPayment;
import cn.com.starest.nextoa.project.domain.model.PendingPayment;
import cn.com.starest.nextoa.project.domain.model.ProjectReceivedPayment;
import cn.com.starest.nextoa.project.domain.model.ReceivedPaymentSource;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dz
 */
public class PendingPaymentSummary extends AbstractPermissionAware {

	public static void convert(PendingPayment fromObj, PendingPaymentSummary result) {
		AbstractBaseSummary.convert(fromObj, result);
		BeanUtils.copyProperties(fromObj, result);

		if (fromObj.getProjectReceivedPayment() != null) {
			ProjectReceivedPayment prp = fromObj.getProjectReceivedPayment();
			result.setReceivedPaymentCode(prp.getCode());
			result.setReceivedPaymentAmount(prp.getAmount());
		}

		if (fromObj.getCompanyReceivedPayment() != null) {
			CompanyReceivedPayment prp = fromObj.getCompanyReceivedPayment();
			result.setReceivedPaymentCode(prp.getCode());
			result.setReceivedPaymentAmount(prp.getAmount());
		}

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

		if (fromObj.getSubContractor() != null) {
			result.setSubContractorId(fromObj.getSubContractor().getId());
			result.setSubContractorName(fromObj.getSubContractor().getName());
		}
	}

	public static PendingPaymentSummary from(PendingPayment fromObj) {
		if (fromObj == null) {
			return null;
		}
		PendingPaymentSummary result = new PendingPaymentSummary();
		convert(fromObj, result);
		return result;
	}

	private int year;

	//回款来源（公司回款,项目回款）
	private ReceivedPaymentSource receivedPaymentSource;

	//回款流程ID
	private String receivedPaymentCode;

	//回款金额
	private BigDecimal receivedPaymentAmount;

	//回款所属公司
	private String companyId;

	private String companyName;

	//回款所属项目
	private String projectId;

	private String projectName;

	//回款所属部门
	private String bizDepartmentId;

	private String bizDepartmentName;

	private Date pendingAt;

	//待付款金额
	private BigDecimal amount;

	//待付款单位（分包单位）
	private String subContractorId;

	private String subContractorName;

	private boolean payed;

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public ReceivedPaymentSource getReceivedPaymentSource() {
		return receivedPaymentSource;
	}

	public void setReceivedPaymentSource(ReceivedPaymentSource receivedPaymentSource) {
		this.receivedPaymentSource = receivedPaymentSource;
	}

	public String getReceivedPaymentCode() {
		return receivedPaymentCode;
	}

	public void setReceivedPaymentCode(String receivedPaymentCode) {
		this.receivedPaymentCode = receivedPaymentCode;
	}

	public BigDecimal getReceivedPaymentAmount() {
		return receivedPaymentAmount;
	}

	public void setReceivedPaymentAmount(BigDecimal receivedPaymentAmount) {
		this.receivedPaymentAmount = receivedPaymentAmount;
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

	public Date getPendingAt() {
		return pendingAt;
	}

	public void setPendingAt(Date pendingAt) {
		this.pendingAt = pendingAt;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getSubContractorId() {
		return subContractorId;
	}

	public void setSubContractorId(String subContractorId) {
		this.subContractorId = subContractorId;
	}

	public String getSubContractorName() {
		return subContractorName;
	}

	public void setSubContractorName(String subContractorName) {
		this.subContractorName = subContractorName;
	}

	public boolean isPayed() {
		return payed;
	}

	public void setPayed(boolean payed) {
		this.payed = payed;
	}

}
