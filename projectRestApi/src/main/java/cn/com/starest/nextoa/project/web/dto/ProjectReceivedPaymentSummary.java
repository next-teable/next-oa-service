package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.model.dtr.AbstractBaseSummary;
import cn.com.starest.nextoa.project.domain.model.Contract;
import cn.com.starest.nextoa.project.domain.model.Order;
import cn.com.starest.nextoa.project.domain.model.ProjectReceivedPayment;
import cn.com.starest.nextoa.project.domain.model.ReceivedPaymentType;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dz
 */
public class ProjectReceivedPaymentSummary extends AbstractPermissionAware {

	public static void convert(ProjectReceivedPayment fromObj, ProjectReceivedPaymentSummary result) {
		AbstractBaseSummary.convert(fromObj, result);
		BeanUtils.copyProperties(fromObj, result);

		if (fromObj.getAccountSubject() != null) {
			result.setAccountSubjectId(fromObj.getAccountSubject().getId());
			result.setAccountSubjectName(fromObj.getAccountSubject().getName());
		}
		if (fromObj.getSubAccountSubject() != null) {
			result.setSubAccountSubjectId(fromObj.getSubAccountSubject().getId());
			result.setSubAccountSubjectName(fromObj.getSubAccountSubject().getName());
		}

		if (fromObj.getCompany() != null) {
			result.setCompanyId(fromObj.getCompany().getId());
			result.setCompanyName(fromObj.getCompany().getName());
		}
		if (fromObj.getProject() != null) {
			result.setProjectId(fromObj.getProject().getId());
			result.setProjectName(fromObj.getProject().getName());
		}
		if (fromObj.getContract() != null) {
			result.setContractId(fromObj.getContract().getId());
			result.setContractName(Contract.getContractName(fromObj.getContract()));
		}
		if (fromObj.getOrder() != null) {
			result.setOrderId(fromObj.getOrder().getId());
			result.setOrderName(Order.getOrderName(fromObj.getOrder()));
		}
		if (fromObj.getIssueInvoice() != null) {
			result.setIssueInvoiceId(fromObj.getIssueInvoice().getId());
			result.setIssueInvoiceAmount(fromObj.getIssueInvoice().getAmount());
		}
	}

	public static ProjectReceivedPaymentSummary from(ProjectReceivedPayment fromObj) {
		if (fromObj == null) {
			return null;
		}
		ProjectReceivedPaymentSummary result = new ProjectReceivedPaymentSummary();
		convert(fromObj, result);
		return result;
	}

	private int year;

	private String code;

	private ReceivedPaymentType type;

	private String accountSubjectId;

	private String accountSubjectName;

	private String subAccountSubjectId;

	private String subAccountSubjectName;

	private String companyId;

	private String companyName;

	private String projectId;

	private String projectName;

	private String contractId;

	private String contractName;

	private String orderId;

	private String orderName;

	private String issueInvoiceId;

	private BigDecimal issueInvoiceAmount;

	private BigDecimal amount;

	private Date receivedAt;

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

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIssueInvoiceId() {
		return issueInvoiceId;
	}

	public void setIssueInvoiceId(String issueInvoiceId) {
		this.issueInvoiceId = issueInvoiceId;
	}

	public BigDecimal getIssueInvoiceAmount() {
		return issueInvoiceAmount;
	}

	public void setIssueInvoiceAmount(BigDecimal issueInvoiceAmount) {
		this.issueInvoiceAmount = issueInvoiceAmount;
	}
}
