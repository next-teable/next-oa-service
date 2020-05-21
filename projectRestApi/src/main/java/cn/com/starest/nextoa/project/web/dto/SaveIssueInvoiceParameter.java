package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.project.domain.model.InvoiceType;
import cn.com.starest.nextoa.project.domain.request.SaveIssueInvoiceRequest;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * SaveIssueInvoiceParameter
 *
 * @author dz
 */
public class SaveIssueInvoiceParameter implements SaveIssueInvoiceRequest {

	//票据类型*
	@NotNull(message = "票据类型不能为空")
	private InvoiceType type;

	//公司
	@NotNull(message = "公司不能为空")
	private String companyId;

	//项目名称（区域）*
	@NotNull(message = "项目不能为空")
	private String projectId;

	//主合同/协议名称*
	@NotNull(message = "合同不能为空")
	private String contractId;

	//主订单
	private String orderId;

	//开票单位
	@NotNull(message = "开票单位不能为空")
	private String firstPartyId;

	//开票金额*
	@NotNull(message = "金额不能为空")
	@DecimalMin(value = "0", inclusive = true, message = "金额不能小于零")
	private BigDecimal amount;

	//税率
	private BigDecimal taxRate;

	//开票日期*
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "开票日期不能为空")
	private Date handleDate;

	private String description;

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

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

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getFirstPartyId() {
		return firstPartyId;
	}

	public void setFirstPartyId(String firstPartyId) {
		this.firstPartyId = firstPartyId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		if (amount != null) {
			amount = amount.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		this.amount = amount;
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
