package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.project.domain.model.ReceivedPaymentType;
import cn.com.starest.nextoa.project.domain.request.SaveCompanyReceivedPaymentRequest;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dz
 */
public class SaveCompanyReceivedPaymentParameter implements SaveCompanyReceivedPaymentRequest {

	@Min(value = 2012, message = "年份不能小于2012年")
	private int year;

	@NotNull(message = "公司不能为空")
	String companyId;

	@NotNull(message = "类型不能为空")
	ReceivedPaymentType type;

	@NotNull(message = "回款单位不能为空")
	String receivedFrom;

	// 费用类型（会计科目）
	@NotNull(message = "费用类型不能为空")
	String accountSubjectId;

	String subAccountSubjectId;

	//项目名称（区域）*
	String projectId;

	String bizDepartmentId;

	// 付款金额*
	@NotNull(message = "回款金额不能为空")
	@DecimalMin(value = "0", inclusive = true, message = "回款金额不能小于零")
	BigDecimal amount;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "回款时间不能为空")
	Date receivedAt;

	// 回款单位
	String paymentCompany;

	// 备注
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
	public ReceivedPaymentType getType() {
		return type;
	}

	public void setType(ReceivedPaymentType type) {
		this.type = type;
	}

	@Override
	public String getReceivedFrom() {
		return receivedFrom;
	}

	public void setReceivedFrom(String receivedFrom) {
		this.receivedFrom = receivedFrom;
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

	public String getPaymentCompany() {
		return paymentCompany;
	}

	public void setPaymentCompany(String paymentCompany) {
		this.paymentCompany = paymentCompany;
	}
}
