package cn.com.starest.nextoa.project.domain.model;

import cn.com.starest.nextoa.model.shared.StringIdentifier;

import java.math.BigDecimal;

/**
 * @author dz
 */
public class ProjectAggregation extends StringIdentifier {

	private BigDecimal contractTotalAmount;

	private BigDecimal orderTotalAmount;

	private BigDecimal issueInvoiceTotalAmount;

	private BigDecimal projectReceivedPaymentTotalAmount;

	private BigDecimal subOrderTotalAmount;

	private BigDecimal receiveInvoiceTotalAmount;

	private BigDecimal paymentTotalAmount;

	//new @since 20170628
	private BigDecimal depositTotalAmount;

	private BigDecimal pendingPaymentTotalAmount;

	private BigDecimal salaryTotalAmount;

	public BigDecimal getContractTotalAmount() {
		return contractTotalAmount;
	}

	public void setContractTotalAmount(BigDecimal contractTotalAmount) {
		this.contractTotalAmount = contractTotalAmount;
	}

	public BigDecimal getOrderTotalAmount() {
		return orderTotalAmount;
	}

	public void setOrderTotalAmount(BigDecimal orderTotalAmount) {
		this.orderTotalAmount = orderTotalAmount;
	}

	public BigDecimal getIssueInvoiceTotalAmount() {
		return issueInvoiceTotalAmount;
	}

	public void setIssueInvoiceTotalAmount(BigDecimal issueInvoiceTotalAmount) {
		this.issueInvoiceTotalAmount = issueInvoiceTotalAmount;
	}

	public BigDecimal getProjectReceivedPaymentTotalAmount() {
		return projectReceivedPaymentTotalAmount;
	}

	public void setProjectReceivedPaymentTotalAmount(BigDecimal projectReceivedPaymentTotalAmount) {
		this.projectReceivedPaymentTotalAmount = projectReceivedPaymentTotalAmount;
	}

	public BigDecimal getSubOrderTotalAmount() {
		return subOrderTotalAmount;
	}

	public void setSubOrderTotalAmount(BigDecimal subOrderTotalAmount) {
		this.subOrderTotalAmount = subOrderTotalAmount;
	}

	public BigDecimal getReceiveInvoiceTotalAmount() {
		return receiveInvoiceTotalAmount;
	}

	public void setReceiveInvoiceTotalAmount(BigDecimal receiveInvoiceTotalAmount) {
		this.receiveInvoiceTotalAmount = receiveInvoiceTotalAmount;
	}

	public BigDecimal getPaymentTotalAmount() {
		return paymentTotalAmount;
	}

	public void setPaymentTotalAmount(BigDecimal paymentTotalAmount) {
		this.paymentTotalAmount = paymentTotalAmount;
	}

	public BigDecimal getDepositTotalAmount() {
		return depositTotalAmount;
	}

	public void setDepositTotalAmount(BigDecimal depositTotalAmount) {
		this.depositTotalAmount = depositTotalAmount;
	}

	public BigDecimal getPendingPaymentTotalAmount() {
		return pendingPaymentTotalAmount;
	}

	public void setPendingPaymentTotalAmount(BigDecimal pendingPaymentTotalAmount) {
		this.pendingPaymentTotalAmount = pendingPaymentTotalAmount;
	}

	public BigDecimal getSalaryTotalAmount() {
		return salaryTotalAmount;
	}

	public void setSalaryTotalAmount(BigDecimal salaryTotalAmount) {
		this.salaryTotalAmount = salaryTotalAmount;
	}
}
