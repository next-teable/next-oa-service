package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.model.dtr.AbstractBaseSummary;
import cn.com.starest.nextoa.project.domain.model.Contract;
import cn.com.starest.nextoa.project.domain.model.InvoiceType;
import cn.com.starest.nextoa.project.domain.model.IssueInvoice;
import cn.com.starest.nextoa.project.domain.model.Order;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dz
 */
public class IssueInvoiceSummary extends AbstractPermissionAware {

	public static void convert(IssueInvoice fromObj, IssueInvoiceSummary result) {
		AbstractBaseSummary.convert(fromObj, result);
		BeanUtils.copyProperties(fromObj, result);

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
		if (fromObj.getCompany() != null) {
			result.setCompanyId(fromObj.getCompany().getId());
			result.setCompanyName(fromObj.getCompany().getName());
		}
		if (fromObj.getFirstParty() != null) {
			result.setFirstPartyId(fromObj.getFirstParty().getId());
			result.setFirstPartyName(fromObj.getFirstParty().getName());
		}
	}

	public static IssueInvoiceSummary from(IssueInvoice fromObj) {
		if (fromObj == null) {
			return null;
		}
		IssueInvoiceSummary result = new IssueInvoiceSummary();
		convert(fromObj, result);
		return result;
	}

	//票据类型*
	private InvoiceType type;

	//项目名称（区域）*
	private String projectId;

	private String projectName;

	//公司
	private String companyId;

	private String companyName;

	//主合同/协议名称*
	private String contractId;

	private String contractName;

	//
	private String orderId;

	private String orderName;

	//开票单位
	private String firstPartyId;

	private String firstPartyName;

	//开票金额*
	private BigDecimal amount;

	//已回款金额
	private BigDecimal receivedAmount;

	//开票类型*
	private BigDecimal taxRate;

	//开票日期*
	private Date handleDate;

	private String description;

	public InvoiceType getType() {
		return type;
	}

	public void setType(InvoiceType type) {
		this.type = type;
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

	public String getFirstPartyId() {
		return firstPartyId;
	}

	public void setFirstPartyId(String firstPartyId) {
		this.firstPartyId = firstPartyId;
	}

	public String getFirstPartyName() {
		return firstPartyName;
	}

	public void setFirstPartyName(String firstPartyName) {
		this.firstPartyName = firstPartyName;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getReceivedAmount() {
		return receivedAmount;
	}

	public void setReceivedAmount(BigDecimal receivedAmount) {
		this.receivedAmount = receivedAmount;
	}

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	public Date getHandleDate() {
		return handleDate;
	}

	public void setHandleDate(Date handleDate) {
		this.handleDate = handleDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
