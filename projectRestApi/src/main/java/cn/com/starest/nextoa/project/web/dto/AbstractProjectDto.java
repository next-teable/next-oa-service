package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.model.dtr.AbstractBaseSummary;
import cn.com.starest.nextoa.project.domain.model.AbstractProject;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dz
 */
public class AbstractProjectDto extends AbstractPermissionAware {

	public static void convert(AbstractProject fromObj, AbstractProjectDto result) {
		AbstractBaseSummary.convert(fromObj, result);
		BeanUtils.copyProperties(fromObj, result);

		if (fromObj.getCompany() != null) {
			result.setCompanyId(fromObj.getCompany().getId());
			result.setCompanyName(fromObj.getCompany().getName());
		}
		if (fromObj.getProjectType() != null) {
			result.setProjectTypeId(fromObj.getProjectType().getId());
			result.setProjectTypeName(fromObj.getProjectType().getName());
		}
		if (fromObj.getFrameworkContract() != null) {
			result.setFrameworkContractId(fromObj.getFrameworkContract().getId());
			result.setFrameworkContractName(fromObj.getFrameworkContract().getName());
		}
		if (fromObj.getFirstParty() != null) {
			result.setFirstPartyId(fromObj.getFirstParty().getId());
			result.setFirstPartyName(fromObj.getFirstParty().getName());
		}
		if (fromObj.getDeliveryType() != null) {
			result.setDeliveryTypeId(fromObj.getDeliveryType().getId());
			result.setDeliveryTypeName(fromObj.getDeliveryType().getName());
		}

	}

	public static AbstractProjectDto from(AbstractProject fromObj) {
		if (fromObj == null) {
			return null;
		}
		AbstractProjectDto result = new AbstractProjectDto();
		convert(fromObj, result);
		return result;
	}

	private String province;

	private String city;

	//unique 项目名
	private String name;

	//unique 项目编码 格式要求：公司英文简称-年月日-序号（两位数）；如:XCJ-20170222-01/JD-20170222-01
	private String code;

	private String sort;

	//框架合同
	private String frameworkContractId;

	private String frameworkContractName;

	private String companyId;

	private String companyName;

	//项目类型* 枚举值：工程类；资源类；技术服务类
	private String projectTypeId;

	private String projectTypeName;

	//甲方单位
	private String firstPartyId;

	private String firstPartyName;

	//项目交付方式 内部承包；外部承包；外部挂靠；内部自营
	private String deliveryTypeId;

	private String deliveryTypeName;

	//项目周期
	private Date planStart;

	//项目周期
	private Date planEnd;

	//立项日期
	private Date setupDate;

	//	结项日期
	private Date closeDate;

	//产值利润率
	private BigDecimal outputProfitRate;

	//回款利润率
	private BigDecimal collectProfitRate;

	//	甲方项目负责人
	private String firstPartyManager;

	//	甲方联系电话
	private String firstPartyPhone;

	//项目描述,备注
	private String description;

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getFrameworkContractId() {
		return frameworkContractId;
	}

	public void setFrameworkContractId(String frameworkContractId) {
		this.frameworkContractId = frameworkContractId;
	}

	public String getFrameworkContractName() {
		return frameworkContractName;
	}

	public void setFrameworkContractName(String frameworkContractName) {
		this.frameworkContractName = frameworkContractName;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getProjectTypeId() {
		return projectTypeId;
	}

	public void setProjectTypeId(String projectTypeId) {
		this.projectTypeId = projectTypeId;
	}

	public String getProjectTypeName() {
		return projectTypeName;
	}

	public void setProjectTypeName(String projectTypeName) {
		this.projectTypeName = projectTypeName;
	}

	public String getFirstPartyId() {
		return firstPartyId;
	}

	public void setFirstPartyId(String firstPartyId) {
		this.firstPartyId = firstPartyId;
	}

	public String getFirstPartyName() {
		return firstPartyName;
	}

	public void setFirstPartyName(String firstPartyName) {
		this.firstPartyName = firstPartyName;
	}

	public String getDeliveryTypeId() {
		return deliveryTypeId;
	}

	public void setDeliveryTypeId(String deliveryTypeId) {
		this.deliveryTypeId = deliveryTypeId;
	}

	public String getDeliveryTypeName() {
		return deliveryTypeName;
	}

	public void setDeliveryTypeName(String deliveryTypeName) {
		this.deliveryTypeName = deliveryTypeName;
	}

	public Date getPlanStart() {
		return planStart;
	}

	public void setPlanStart(Date planStart) {
		this.planStart = planStart;
	}

	public Date getPlanEnd() {
		return planEnd;
	}

	public void setPlanEnd(Date planEnd) {
		this.planEnd = planEnd;
	}

	public Date getSetupDate() {
		return setupDate;
	}

	public void setSetupDate(Date setupDate) {
		this.setupDate = setupDate;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public BigDecimal getOutputProfitRate() {
		return outputProfitRate;
	}

	public void setOutputProfitRate(BigDecimal outputProfitRate) {
		this.outputProfitRate = outputProfitRate;
	}

	public BigDecimal getCollectProfitRate() {
		return collectProfitRate;
	}

	public void setCollectProfitRate(BigDecimal collectProfitRate) {
		this.collectProfitRate = collectProfitRate;
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
