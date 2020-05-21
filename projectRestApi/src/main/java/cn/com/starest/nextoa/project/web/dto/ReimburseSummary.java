package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.model.dtr.AbstractBaseSummary;
import cn.com.starest.nextoa.project.domain.model.Contract;
import cn.com.starest.nextoa.project.domain.model.Order;
import cn.com.starest.nextoa.project.domain.model.PaymentObject;
import cn.com.starest.nextoa.project.domain.model.Reimburse;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dz
 */
public class ReimburseSummary extends AbstractPermissionAware {

	public static void convert(Reimburse fromObj, ReimburseSummary result) {
		AbstractBaseSummary.convert(fromObj, result);
		BeanUtils.copyProperties(fromObj, result);

		if (fromObj.getPaper() != null) {
			result.setPaperId(fromObj.getPaper().getId());
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
		if (fromObj.getContract() != null) {
			result.setContractId(fromObj.getContract().getId());
			result.setContractName(Contract.getContractName(fromObj.getContract()));
		}
		if (fromObj.getSubContract() != null) {
			result.setSubContractId(fromObj.getSubContract().getId());
			result.setSubContractName(fromObj.getSubContract().getName());
		}
		if (fromObj.getOrder() != null) {
			result.setOrderId(fromObj.getOrder().getId());
			result.setOrderName(Order.getOrderName(fromObj.getOrder()));
		}
		if (fromObj.getSubOrder() != null) {
			result.setSubOrderId(fromObj.getSubOrder().getId());
			result.setSubOrderName(fromObj.getSubOrder().getName());
		}
		if (fromObj.getAccountSubject() != null) {
			result.setAccountSubjectId(fromObj.getAccountSubject().getId());
			result.setAccountSubjectName(fromObj.getAccountSubject().getName());
		}
		if (fromObj.getSubAccountSubject() != null) {
			result.setSubAccountSubjectId(fromObj.getSubAccountSubject().getId());
			result.setSubAccountSubjectName(fromObj.getSubAccountSubject().getName());
		}
		if (fromObj.getSubContractor() != null) {
			result.setSubContractorId(fromObj.getSubContractor().getId());
			result.setSubContractorName(fromObj.getSubContractor().getName());
		}
		if (fromObj.getPayTo() != null) {
			result.setPayToId(fromObj.getPayTo().getId());
			result.setPayToName(fromObj.getPayTo().getUsername());
		}
		if (fromObj.getReceiveInvoice() != null) {
			result.setReceiveInvoiceId(fromObj.getReceiveInvoice().getId());
			result.setReceiveInvoiceAmount(fromObj.getReceiveInvoice().getAmount());
		}
		if (fromObj.getSettleBy() != null) {
			result.setSettleById(fromObj.getSettleBy().getId());
			result.setSettleByName(fromObj.getSettleBy().getUsername());
		}
	}

	public static ReimburseSummary from(Reimburse fromObj) {
		if (fromObj == null) {
			return null;
		}
		ReimburseSummary result = new ReimburseSummary();
		convert(fromObj, result);
		return result;
	}

	private int year;

	private String paperId;

	private String companyId;

	private String companyName;

	private String accountSubjectId;

	private String accountSubjectName;

	private String subAccountSubjectId;

	private String subAccountSubjectName;

	//报销项目
	private String projectId;

	private String projectName;

	//报销部门
	private String bizDepartmentId;

	private String bizDepartmentName;

	private String contractId;

	private String contractName;

	private String subContractId;

	private String subContractName;

	private String orderId;

	private String orderName;

	private String subOrderId;

	private String subOrderName;

	private PaymentObject paymentObject;

	//付款单位（分包单位）
	private String subContractorId;

	private String subContractorName;

	//付款人员（用户）
	private String payToId;

	private String payToName;

	private String other;

	private String receiveInvoiceId;

	private BigDecimal receiveInvoiceAmount;

	private BigDecimal amount;

	//是否结算
	private boolean settled;

	private Date settleAt;

	private String settleById;

	private String settleByName;

	private String description;

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getPaperId() {
		return paperId;
	}

	public void setPaperId(String paperId) {
		this.paperId = paperId;
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

	public String getSubContractId() {
		return subContractId;
	}

	public void setSubContractId(String subContractId) {
		this.subContractId = subContractId;
	}

	public String getSubContractName() {
		return subContractName;
	}

	public void setSubContractName(String subContractName) {
		this.subContractName = subContractName;
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

	public String getSubOrderId() {
		return subOrderId;
	}

	public void setSubOrderId(String subOrderId) {
		this.subOrderId = subOrderId;
	}

	public String getSubOrderName() {
		return subOrderName;
	}

	public void setSubOrderName(String subOrderName) {
		this.subOrderName = subOrderName;
	}

	public PaymentObject getPaymentObject() {
		return paymentObject;
	}

	public void setPaymentObject(PaymentObject paymentObject) {
		this.paymentObject = paymentObject;
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

	public String getPayToId() {
		return payToId;
	}

	public void setPayToId(String payToId) {
		this.payToId = payToId;
	}

	public String getPayToName() {
		return payToName;
	}

	public void setPayToName(String payToName) {
		this.payToName = payToName;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getReceiveInvoiceId() {
		return receiveInvoiceId;
	}

	public void setReceiveInvoiceId(String receiveInvoiceId) {
		this.receiveInvoiceId = receiveInvoiceId;
	}

	public BigDecimal getReceiveInvoiceAmount() {
		return receiveInvoiceAmount;
	}

	public void setReceiveInvoiceAmount(BigDecimal receiveInvoiceAmount) {
		this.receiveInvoiceAmount = receiveInvoiceAmount;
	}

	public boolean isSettled() {
		return settled;
	}

	public void setSettled(boolean settled) {
		this.settled = settled;
	}

	public Date getSettleAt() {
		return settleAt;
	}

	public void setSettleAt(Date settleAt) {
		this.settleAt = settleAt;
	}

	public String getSettleById() {
		return settleById;
	}

	public void setSettleById(String settleById) {
		this.settleById = settleById;
	}

	public String getSettleByName() {
		return settleByName;
	}

	public void setSettleByName(String settleByName) {
		this.settleByName = settleByName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
