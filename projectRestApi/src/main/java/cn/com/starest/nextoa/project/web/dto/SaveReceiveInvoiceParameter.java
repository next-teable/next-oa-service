package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.project.domain.model.InvoiceType;
import cn.com.starest.nextoa.project.domain.request.SaveReceiveInvoiceRequest;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dz
 */
public class SaveReceiveInvoiceParameter implements SaveReceiveInvoiceRequest {

	//票据类型*
	@NotNull(message = "票据类型不能为空")
	private InvoiceType type;

	@NotNull(message = "项目不能为空")
	private String projectId;

	@NotNull(message = "分包合同不能为空")
	private String subContractId;

	private String subOrderId;

	//收票单位
	@NotNull(message = "收票单位不能为空")
	private String subContractorId;

	//收票金额*
	@NotNull(message = "金额不能为空")
	@DecimalMin(value = "0", inclusive = true, message = "金额不能小于零")
	private BigDecimal amount;

	//收票类型*
	private BigDecimal taxRate;

	//收票日期*
	@NotNull(message = "收票日期不能为空")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date receivedAt;

	private String description;

	@Override
	public InvoiceType getType() {
		return type;
	}

	public void setType(InvoiceType type) {
		this.type = type;
	}

	@Override
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	@Override
	public String getSubContractId() {
		return subContractId;
	}

	public void setSubContractId(String subContractId) {
		this.subContractId = subContractId;
	}

	@Override
	public String getSubOrderId() {
		return subOrderId;
	}

	public void setSubOrderId(String subOrderId) {
		this.subOrderId = subOrderId;
	}

	@Override
	public String getSubContractorId() {
		return subContractorId;
	}

	public void setSubContractorId(String subContractorId) {
		this.subContractorId = subContractorId;
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
	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	@Override
	public Date getReceivedAt() {
		return receivedAt;
	}

	public void setReceivedAt(Date receivedAt) {
		this.receivedAt = receivedAt;
	}

	@Override
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
