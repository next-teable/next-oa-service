package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.project.domain.request.SaveSubOrderRequest;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author dz
 */
public class SaveSubOrderParameter implements SaveSubOrderRequest {

	//unique 订单编号
	@NotNull(message = "编码不能为空")
	private String code;

	//unique 订单名称
	@NotNull(message = "名称不能为空")
	private String name;

	//项目名称（区域）*
	@NotNull(message = "项目不能为空")
	private String projectId;

	//订单*
	@NotNull(message = "主订单不能为空")
	private String orderId;

	//分包合同/协议名称*
	@NotNull(message = "分包合同不能为空")
	private String subContractId;

	@NotNull(message = "金额不能为空")
	@DecimalMin(value = "0", inclusive = true, message = "金额不能小于零")
	private BigDecimal amount;

	private String description;

	@Override
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	@Override
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Override
	public String getSubContractId() {
		return subContractId;
	}

	public void setSubContractId(String subContractId) {
		this.subContractId = subContractId;
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
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
