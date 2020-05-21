package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.project.domain.model.LendingObject;
import cn.com.starest.nextoa.project.domain.model.LendingType;
import cn.com.starest.nextoa.project.domain.request.SaveLendingRequest;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dz
 */
public class SaveLendingParameter implements SaveLendingRequest {

	@NotNull(message = "公司不能为空")
	String companyId;

	// 借款对象
	@NotNull(message = "借款对象不能为空")
	LendingObject lendingObject;

	// 借款类型
	@NotNull(message = "借款类型不能为空")
	LendingType lendingType;

	// 借款(还款)金额*
	@NotNull(message = "借款(还款)金额不能为空")
	@DecimalMin(value = "0", inclusive = true, message = "借款(还款)金额不能小于零")
	BigDecimal amount;

	// 借款日期
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "借款(还款)时间不能为空")
	Date handleAt;

	// 借款人
	String lendingById;

	// 借款公司（分包公司）
	String lendingToId;

	// 借款原因
	String reason;

	// 备注
	String description;

	@Override
	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public LendingObject getLendingObject() {
		return lendingObject;
	}

	public void setLendingObject(LendingObject lendingObject) {
		this.lendingObject = lendingObject;
	}

	public LendingType getLendingType() {
		return lendingType;
	}

	public void setLendingType(LendingType lendingType) {
		this.lendingType = lendingType;
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
	public Date getHandleAt() {
		return handleAt;
	}

	public void setHandleAt(Date handleAt) {
		this.handleAt = handleAt;
	}

	@Override
	public String getLendingById() {
		return lendingById;
	}

	public void setLendingById(String lendingById) {
		this.lendingById = lendingById;
	}

	@Override
	public String getLendingToId() {
		return lendingToId;
	}

	public void setLendingToId(String lendingToId) {
		this.lendingToId = lendingToId;
	}

	@Override
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
