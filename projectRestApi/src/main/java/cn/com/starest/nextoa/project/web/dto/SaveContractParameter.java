package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.project.domain.request.SaveContractRequest;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author dz
 */
public class SaveContractParameter implements SaveContractRequest {

	//unique 主合同编号* 格式：分包单位简称-年月日-编号
	@NotNull(message = "编码不能为空")
	private String code;

	//unique 主合同/协议名称*
	@NotNull(message = "名称不能为空")
	private String name;

	//项目名称（区域）*
	@NotNull(message = "项目不能为空")
	private String[] projectIds;

	//主合同签订甲方名称*
	@NotNull(message = "甲方不能为空")
	private String firstPartyId;

	@NotNull(message = "金额不能为空")
	@DecimalMin(value = "0", inclusive = true, message = "金额不能小于零")
	private BigDecimal amount;

	//	甲方项目负责人
	private String firstPartyManager;

	//	甲方联系电话
	private String firstPartyPhone;

	private String description;

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

	public String[] getProjectIds() {
		return projectIds;
	}

	public void setProjectIds(String[] projectIds) {
		this.projectIds = projectIds;
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

	public String getFirstPartyManager() {
		return firstPartyManager;
	}

	public void setFirstPartyManager(String firstPartyManager) {
		this.firstPartyManager = firstPartyManager;
	}

	public String getFirstPartyPhone() {
		return firstPartyPhone;
	}

	public void setFirstPartyPhone(String firstPartyPhone) {
		this.firstPartyPhone = firstPartyPhone;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
