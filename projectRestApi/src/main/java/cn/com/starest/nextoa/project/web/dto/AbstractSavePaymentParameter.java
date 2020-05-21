package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.project.domain.model.PaymentObject;
import cn.com.starest.nextoa.project.domain.request.AbstractSavePaymentRequest;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author dz
 */
public class AbstractSavePaymentParameter implements AbstractSavePaymentRequest {

	@Min(value = 2012, message = "年份不能小于2012年")
	private int year;

	@NotNull(message = "公司不能为空")
	String companyId;

	//费用类型（会计科目）
	@NotNull(message = "费用类型不能为空")
	String accountSubjectId;

	String subAccountSubjectId;

	//项目名称（区域）*
	String projectId;

	String bizDepartmentId;

	String contractId;

	String subContractId;

	String orderId;

	String subOrderId;

	@NotNull(message = "付款对象不能为空")
	PaymentObject paymentObject;

	//付款单位（分包单位）
	String subContractorId;

	//付款人员（用户）
	String payToId;

	String other;

	String receiveInvoiceId;

	//报销金额* 允许金额为负数
	@NotNull(message = "金额不能为空")
	BigDecimal amount;

	//备注
	String description;

	@Override
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	@Override
	public String getAccountSubjectId() {
		return accountSubjectId;
	}

	public void setAccountSubjectId(String accountSubjectId) {
		this.accountSubjectId = accountSubjectId;
	}

	@Override
	public String getSubAccountSubjectId() {
		return subAccountSubjectId;
	}

	public void setSubAccountSubjectId(String subAccountSubjectId) {
		this.subAccountSubjectId = subAccountSubjectId;
	}

	@Override
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	@Override
	public String getBizDepartmentId() {
		return bizDepartmentId;
	}

	public void setBizDepartmentId(String bizDepartmentId) {
		this.bizDepartmentId = bizDepartmentId;
	}

	@Override
	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	@Override
	public String getSubContractId() {
		return subContractId;
	}

	public void setSubContractId(String subContractId) {
		this.subContractId = subContractId;
	}

	@Override
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Override
	public String getSubOrderId() {
		return subOrderId;
	}

	public void setSubOrderId(String subOrderId) {
		this.subOrderId = subOrderId;
	}

	@Override
	public PaymentObject getPaymentObject() {
		return paymentObject;
	}

	public void setPaymentObject(PaymentObject paymentObject) {
		this.paymentObject = paymentObject;
	}

	@Override
	public String getSubContractorId() {
		return subContractorId;
	}

	public void setSubContractorId(String subContractorId) {
		this.subContractorId = subContractorId;
	}

	@Override
	public String getPayToId() {
		return payToId;
	}

	public void setPayToId(String payToId) {
		this.payToId = payToId;
	}

	@Override
	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	@Override
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		if (amount != null) {
			amount = amount.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		this.amount = amount;
	}

	@Override
	public String getReceiveInvoiceId() {
		return receiveInvoiceId;
	}

	public void setReceiveInvoiceId(String receiveInvoiceId) {
		this.receiveInvoiceId = receiveInvoiceId;
	}

	@Override
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
