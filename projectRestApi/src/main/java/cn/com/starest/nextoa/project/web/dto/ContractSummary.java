package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.model.dtr.AbstractBaseSummary;
import cn.com.starest.nextoa.project.domain.model.AbstractContract;
import cn.com.starest.nextoa.project.domain.model.Contract;
import cn.com.starest.nextoa.project.domain.model.ContractType;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dz
 */
public class ContractSummary extends AbstractPermissionAware {

	public static void convert(AbstractContract fromObj, ContractSummary result) {
		AbstractBaseSummary.convert(fromObj, result);
		BeanUtils.copyProperties(fromObj, result);

		if (fromObj.getFirstParty() != null) {
			result.setFirstPartyId(fromObj.getFirstParty().getId());
			result.setFirstPartyName(fromObj.getFirstParty().getName());
		}

		if (fromObj instanceof Contract) {
			convert((Contract) fromObj, result);
		}
	}

	public static void convert(Contract fromObj, ContractSummary result) {
		if (fromObj.isSettled()) {
			result.setName("[已结算]" + result.getName());
		}

		if (fromObj.getCompletedBy() != null) {
			result.setCompletedById(fromObj.getCompletedBy().getId());
			result.setCompletedByName(fromObj.getCompletedBy().getUsername());
		}

		if (fromObj.getSettledBy() != null) {
			result.setSettledById(fromObj.getSettledBy().getId());
			result.setSettledByName(fromObj.getSettledBy().getUsername());
		}

		if (fromObj.getPublishBy() != null) {
			result.setPublishById(fromObj.getPublishBy().getId());
			result.setPublishByName(fromObj.getPublishBy().getUsername());
		}
		if (fromObj.getProjects() != null && fromObj.getProjects().size() > 0) {
			result.setProjectNames(fromObj.getProjects()
										  .stream()
										  .map(item -> item.getName())
										  .reduce((a, b) -> a + "," + b)
										  .get());
		}
	}

	public static ContractSummary from(Contract fromObj) {
		if (fromObj == null) {
			return null;
		}
		ContractSummary result = new ContractSummary();
		convert((AbstractContract) fromObj, result);
		return result;
	}

	private ContractType contractType;

	// unique 主合同编号* 格式：分包单位简称-年月日-编号
	private String code;

	// unique 主合同/协议名称*
	private String name;

	// 主合同签订甲方名称*
	private String firstPartyId;

	private String firstPartyName;

	//	甲方项目负责人
	private String firstPartyManager;

	//	甲方联系电话
	private String firstPartyPhone;

	private String projectNames;

	private BigDecimal amount;

	private String description;

	private boolean published;

	private Date publishAt;

	private String publishById;

	private String publishByName;

	//竣工
	private boolean completed;

	private Date completedAt;

	private String completedById;

	private String completedByName;

	//结算
	private boolean settled;

	private Date settledAt;

	private String settledById;

	private String settledByName;

	public ContractType getContractType() {
		return contractType;
	}

	public void setContractType(ContractType contractType) {
		this.contractType = contractType;
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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public Date getPublishAt() {
		return publishAt;
	}

	public void setPublishAt(Date publishAt) {
		this.publishAt = publishAt;
	}

	public String getPublishById() {
		return publishById;
	}

	public void setPublishById(String publishById) {
		this.publishById = publishById;
	}

	public String getPublishByName() {
		return publishByName;
	}

	public void setPublishByName(String publishByName) {
		this.publishByName = publishByName;
	}

	public String getProjectNames() {
		return projectNames;
	}

	public void setProjectNames(String projectNames) {
		this.projectNames = projectNames;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public Date getCompletedAt() {
		return completedAt;
	}

	public void setCompletedAt(Date completedAt) {
		this.completedAt = completedAt;
	}

	public String getCompletedById() {
		return completedById;
	}

	public void setCompletedById(String completedById) {
		this.completedById = completedById;
	}

	public String getCompletedByName() {
		return completedByName;
	}

	public void setCompletedByName(String completedByName) {
		this.completedByName = completedByName;
	}

	public boolean isSettled() {
		return settled;
	}

	public void setSettled(boolean settled) {
		this.settled = settled;
	}

	public Date getSettledAt() {
		return settledAt;
	}

	public void setSettledAt(Date settledAt) {
		this.settledAt = settledAt;
	}

	public String getSettledById() {
		return settledById;
	}

	public void setSettledById(String settledById) {
		this.settledById = settledById;
	}

	public String getSettledByName() {
		return settledByName;
	}

	public void setSettledByName(String settledByName) {
		this.settledByName = settledByName;
	}
}
