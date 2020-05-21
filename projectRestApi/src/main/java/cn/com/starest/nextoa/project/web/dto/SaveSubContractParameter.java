package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.project.domain.request.SaveSubContractRequest;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author dz
 */
public class SaveSubContractParameter implements SaveSubContractRequest {

	// unique 主合同编号* 格式：分包单位简称-年月日-编号
	@NotNull(message = "编码不能为空")
	private String code;

	// unique 主合同/协议名称*
	@NotNull(message = "名称不能为空")
	private String name;

	// 项目名称（区域）*
	@NotNull(message = "项目不能为空")
	private String projectId;

	// 主合同/协议名称*
	@NotNull(message = "合同不能为空")
	private String contractId;

	// 分包单位名称*
	@NotNull(message = "分包单位不能为空")
	private String subContractorId;

	// 分包比例
	@NotNull(message = "分包比例不能为空")
	@DecimalMin(value = "0", inclusive = true, message = "分包比例不能小于零")
	private BigDecimal percent;

	// 分包单位联系人
	private String contactName;

	// 分包单位联系电话
	private String contactPhone;

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

	public String getSubContractorId() {
		return subContractorId;
	}

	public void setSubContractorId(String subContractorId) {
		this.subContractorId = subContractorId;
	}

	public BigDecimal getPercent() {
		return percent;
	}

	public void setPercent(BigDecimal percent) {
		this.percent = percent;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
}
