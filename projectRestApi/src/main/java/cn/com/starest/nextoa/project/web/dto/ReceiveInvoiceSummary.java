package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.model.dtr.AbstractBaseSummary;
import cn.com.starest.nextoa.project.domain.model.InvoiceType;
import cn.com.starest.nextoa.project.domain.model.ReceiveInvoice;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dz
 */
public class ReceiveInvoiceSummary extends AbstractPermissionAware {

	public static void convert(ReceiveInvoice fromObj, ReceiveInvoiceSummary result) {
		AbstractBaseSummary.convert(fromObj, result);
		BeanUtils.copyProperties(fromObj, result);

		if (fromObj.getProject() != null) {
			result.setProjectId(fromObj.getProject().getId());
			result.setProjectName(fromObj.getProject().getName());
		}
		if (fromObj.getCompany() != null) {
			result.setCompanyId(fromObj.getCompany().getId());
			result.setCompanyName(fromObj.getCompany().getName());
		}
		if (fromObj.getSubContract() != null) {
			result.setSubContractId(fromObj.getSubContract().getId());
			result.setSubContractName(fromObj.getSubContract().getName());
		}
		if (fromObj.getSubOrder() != null) {
			result.setSubOrderId(fromObj.getSubOrder().getId());
			result.setSubOrderName(fromObj.getSubOrder().getName());
		}
		if (fromObj.getSubContractor() != null) {
			result.setSubContractorId(fromObj.getSubContractor().getId());
			result.setSubContractorName(fromObj.getSubContractor().getName());
		}
	}

	public static ReceiveInvoiceSummary from(ReceiveInvoice fromObj) {
		if (fromObj == null) {
			return null;
		}
		ReceiveInvoiceSummary result = new ReceiveInvoiceSummary();
		convert(fromObj, result);
		return result;
	}

	//票据类型*
	private InvoiceType type;

	//项目名称（区域）*
	private String projectId;
	private String projectName;

	//分包合同
	private String subContractId;
	private String subContractName;

	//分包订单
	private String subOrderId;
	private String subOrderName;

	//公司
	private String companyId;
	private String companyName;

	//收票单位
	private String subContractorId;
	private String subContractorName;

	//收票金额*
	private BigDecimal amount;

	//已付款金额
	private BigDecimal paymentAmount;

	//收票类型*
	private BigDecimal taxRate;

	//收票日期*
	private Date receivedAt;

	private String description;

	public InvoiceType getType() {
		return type;
	}

	public void setType(InvoiceType type) {
		this.type = type;
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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
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
}
