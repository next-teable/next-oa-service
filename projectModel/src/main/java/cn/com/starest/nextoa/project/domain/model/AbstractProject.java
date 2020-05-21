package cn.com.starest.nextoa.project.domain.model;

import cn.com.starest.nextoa.model.BaseModel;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dz
 */
public abstract class AbstractProject extends BaseModel {

	private String province;

	@Indexed
	private String city;

	//项目所属公司
	@Indexed
	@DBRef(lazy = true)
	private Company company;

	//unique 项目名
	@Indexed
	private String name;

	//unique 项目编码 格式要求：公司英文简称-年月日-序号（两位数）；如:XCJ-20170222-01/JD-20170222-01
	@Indexed
	private String code;

	//框架合同
	@Indexed
	@DBRef(lazy = true)
	private FrameworkContract frameworkContract;

	//排序（项目监管页面显示用ASC方式显示）
	@Indexed
	private String sort;

	//项目类型* 枚举值：工程类；资源类；技术服务类
	@Indexed
	@DBRef(lazy = true)
	private ProjectType projectType;

	//甲方单位
	@Indexed
	@DBRef(lazy = true)
	private FirstParty firstParty;

	//项目交付方式 内部承包；外部承包；外部挂靠；内部自营
	@Indexed
	@DBRef(lazy = true)
	private ProjectDeliveryType deliveryType;

	//产值利润率
	private BigDecimal outputProfitRate;

	//回款利润率
	private BigDecimal collectProfitRate;

	//项目周期
	private Date planStart;

	//项目周期
	private Date planEnd;

	//立项日期
	private Date setupDate;

	//结项日期
	private Date closeDate;

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

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
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

	public FrameworkContract getFrameworkContract() {
		return frameworkContract;
	}

	public void setFrameworkContract(FrameworkContract frameworkContract) {
		this.frameworkContract = frameworkContract;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public ProjectType getProjectType() {
		return projectType;
	}

	public void setProjectType(ProjectType projectType) {
		this.projectType = projectType;
	}

	public FirstParty getFirstParty() {
		return firstParty;
	}

	public void setFirstParty(FirstParty firstParty) {
		this.firstParty = firstParty;
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

	public ProjectDeliveryType getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(ProjectDeliveryType deliveryType) {
		this.deliveryType = deliveryType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
