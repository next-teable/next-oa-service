package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.project.domain.model.ReceivedPaymentType;
import cn.com.starest.nextoa.project.domain.request.SaveProjectReceivedPaymentRequest;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * SaveProjectReceivedPaymentParameter
 *
 * @author dz
 */
public class SaveProjectReceivedPaymentParameter implements SaveProjectReceivedPaymentRequest {

	@Min(value = 2012, message = "年份不能小于2012年")
	private int year;

	@NotNull(message = "类型不能为空")
	ReceivedPaymentType type;

	//费用类型（会计科目）
	@NotNull(message = "费用类型不能为空")
	String accountSubjectId;

	String subAccountSubjectId;

	//项目名称（区域）*
	@NotNull(message = "项目不能为空")
	private String projectId;

	//主合同/协议名称*
//	@NotNull(message = "合同不能为空")
	private String contractId;

	//主订单
	private String orderId;

	//开票
	String issueInvoiceId;

	//付款金额*
	@NotNull(message = "回款金额不能为空")
	@DecimalMin(value = "0", inclusive = true, message = "回款金额不能小于零")
	BigDecimal amount;

	@NotNull(message = "回款时间不能为空")
	@JsonFormat(pattern = "yyyy-MM-dd")
	Date receivedAt;

	String description;

	@Override
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public ReceivedPaymentType getType() {
		return type;
	}

	public void setType(ReceivedPaymentType type) {
		this.type = type;
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
	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	@Override
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Override
	public String getIssueInvoiceId() {
		return issueInvoiceId;
	}

	public void setIssueInvoiceId(String issueInvoiceId) {
		this.issueInvoiceId = issueInvoiceId;
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
