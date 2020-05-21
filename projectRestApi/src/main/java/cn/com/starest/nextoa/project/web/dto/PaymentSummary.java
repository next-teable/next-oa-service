package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.model.dtr.AbstractBaseSummary;
import cn.com.starest.nextoa.project.domain.model.*;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dz
 */
public class PaymentSummary extends AbstractPermissionAware {

	public static void convert(Payment fromObj, PaymentSummary result) {
		AbstractBaseSummary.convert(fromObj, result);
		BeanUtils.copyProperties(fromObj, result);

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
		if (fromObj.getHandleBy() != null) {
			result.setHandleById(fromObj.getHandleBy().getId());
			result.setHandleByName(fromObj.getHandleBy().getUsername());
		}
	}

	public static PaymentSummary from(Payment fromObj) {
		if (fromObj == null) {
			return null;
		}
		PaymentSummary result = new PaymentSummary();
		convert(fromObj, result);
		return result;
	}

	private int year;

	private String code;

	private String accountSubjectId;

	private String accountSubjectName;

	private String subAccountSubjectId;

	private String subAccountSubjectName;

	private String companyId;

	private String companyName;

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

	private BigDecimal amount;

	private PaymentObject paymentObject;

	//付款单位（分包单位）
	private String subContractorId;

	private String subContractorName;

	//付款人员（用户）
	private String payToId;

	private String payToName;

	private String other;

	//付款日期
	private Date payAt;

	private String receiveInvoiceId;

	private BigDecimal receiveInvoiceAmount;

	//操作人
	private String handleById;

	private String handleByName;

	//备注
	private String description;

	//数据来源
	private PaymentSource source;

	//数据源 id
	private String sourceId;

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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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

	public Date getPayAt() {
		return payAt;
	}

	public void setPayAt(Date payAt) {
		this.payAt = payAt;
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

	public String getHandleById() {
		return handleById;
	}

	public void setHandleById(String handleById) {
		this.handleById = handleById;
	}

	public String getHandleByName() {
		return handleByName;
	}

	public void setHandleByName(String handleByName) {
		this.handleByName = handleByName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public PaymentSource getSource() {
		return source;
	}

	public void setSource(PaymentSource source) {
		this.source = source;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
}
