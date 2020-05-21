package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.project.domain.request.SaveLicenseRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dz
 */
public class SaveLicenseParameter implements SaveLicenseRequest {

	@NotNull(message = "外管站编号不能为空")
	private String code;

	//项目名称（区域）*
	@NotNull(message = "项目不能为空")
	private String projectId;

	//主合同/协议名称*
	@NotNull(message = "主合同不能为空")
	private String contractId;

	//主合同/协议名称*
	private String orderId;

	@NotNull(message = "金额不能为空")
	@DecimalMin(value = "0", inclusive = true, message = "金额不能小于零")
	BigDecimal amount;

	//外管证办理日期*
	@NotNull(message = "办理日期不能为空")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date handleDate;

	//	外管证有效期*
	@NotNull(message = "有效期不能为空")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date expireDate;

	//注销
	private boolean cancelled;

	//注销时间
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date cancelledAt;

	private String description;

	@Override
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
	public Date getHandleDate() {
		return handleDate;
	}

	public void setHandleDate(Date handleDate) {
		this.handleDate = handleDate;
	}

	@Override
	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	@Override
	public Date getCancelledAt() {
		return cancelledAt;
	}

	public void setCancelledAt(Date cancelledAt) {
		this.cancelledAt = cancelledAt;
	}

	@Override
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
