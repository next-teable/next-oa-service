package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.project.domain.request.SaveOrderRequest;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author dz
 */
public class SaveOrderParameter implements SaveOrderRequest {

	@Min(value = 2012, message = "年份不能小于2012年")
	private int year;

	//unique 订单编号
	@NotNull(message = "编码不能为空")
	private String code;

	//unique 订单名称
	@NotNull(message = "名称不能为空")
	private String name;

	//项目名称（区域）*
	@NotNull(message = "项目不能为空")
	private String projectId;

	//主合同/协议名称*
	@NotNull(message = "合同不能为空")
	private String contractId;

	@NotNull(message = "金额不能为空")
	@DecimalMin(value = "0", inclusive = true, message = "金额不能小于零")
	private BigDecimal amount;

	private String description;

	@Override
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		if (amount != null) {
			amount = amount.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
