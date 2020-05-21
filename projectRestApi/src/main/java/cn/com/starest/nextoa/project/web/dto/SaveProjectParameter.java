package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.project.domain.request.SaveProjectRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel("保存项目信息")
public class SaveProjectParameter implements SaveProjectRequest {

	@NotNull(message = "省不能为空")
	private String province;

	@NotNull(message = "市不能为空")
	private String city;

	@NotNull(message = "公司不能为空")
	private String companyId;

	@NotNull(message = "名称不能为空")
	private String name;

	@NotNull(message = "编码不能为空")
	private String code;

	private String frameworkContractId;

	@NotNull(message = "类型不能为空")
	private String projectTypeId;

	@NotNull(message = "甲方不能为空")
	private String firstPartyId;

	@NotNull(message = "项目交付方式不能为空")
	private String deliveryTypeId;

	@NotNull(message = "项目经理不能为空")
	@NotEmpty(message = "项目经理不能为空")
	private String[] managerIds;

	private String[] supervisorIds;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date planStart;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date planEnd;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date setupDate;

	@NotNull(message = "产值利润率不能为空")
	private BigDecimal outputProfitRate;

	@NotNull(message = "回款利润率不能为空")
	private BigDecimal collectProfitRate;

	private String firstPartyManager;

	private String firstPartyPhone;

	private String description;

	@Override
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Override
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String getFrameworkContractId() {
		return frameworkContractId;
	}

	public void setFrameworkContractId(String frameworkContractId) {
		this.frameworkContractId = frameworkContractId;
	}

	@Override
	public String getProjectTypeId() {
		return projectTypeId;
	}

	public void setProjectTypeId(String projectTypeId) {
		this.projectTypeId = projectTypeId;
	}

	@Override
	public String getFirstPartyId() {
		return firstPartyId;
	}

	public void setFirstPartyId(String firstPartyId) {
		this.firstPartyId = firstPartyId;
	}

	@Override
	public String getDeliveryTypeId() {
		return deliveryTypeId;
	}

	public void setDeliveryTypeId(String deliveryTypeId) {
		this.deliveryTypeId = deliveryTypeId;
	}

	@Override
	public String[] getManagerIds() {
		return managerIds;
	}

	public void setManagerIds(String[] managerIds) {
		this.managerIds = managerIds;
	}

	@Override
	public String[] getSupervisorIds() {
		return supervisorIds;
	}

	public void setSupervisorIds(String[] supervisorIds) {
		this.supervisorIds = supervisorIds;
	}

	@Override
	public Date getPlanStart() {
		return planStart;
	}

	public void setPlanStart(Date planStart) {
		this.planStart = planStart;
	}

	@Override
	public Date getPlanEnd() {
		return planEnd;
	}

	public void setPlanEnd(Date planEnd) {
		this.planEnd = planEnd;
	}

	@Override
	public Date getSetupDate() {
		return setupDate;
	}

	public void setSetupDate(Date setupDate) {
		this.setupDate = setupDate;
	}

	@Override
	public BigDecimal getOutputProfitRate() {
		return outputProfitRate;
	}

	public void setOutputProfitRate(BigDecimal outputProfitRate) {
		this.outputProfitRate = outputProfitRate;
	}

	@Override
	public BigDecimal getCollectProfitRate() {
		return collectProfitRate;
	}

	public void setCollectProfitRate(BigDecimal collectProfitRate) {
		this.collectProfitRate = collectProfitRate;
	}

	@Override
	public String getFirstPartyManager() {
		return firstPartyManager;
	}

	public void setFirstPartyManager(String firstPartyManager) {
		this.firstPartyManager = firstPartyManager;
	}

	@Override
	public String getFirstPartyPhone() {
		return firstPartyPhone;
	}

	public void setFirstPartyPhone(String firstPartyPhone) {
		this.firstPartyPhone = firstPartyPhone;
	}

	@Override
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
