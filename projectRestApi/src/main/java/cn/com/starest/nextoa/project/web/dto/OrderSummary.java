package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.model.dtr.AbstractBaseSummary;
import cn.com.starest.nextoa.project.domain.model.AbstractOrder;
import cn.com.starest.nextoa.project.domain.model.Contract;
import cn.com.starest.nextoa.project.domain.model.Order;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dz
 */
public class OrderSummary extends AbstractPermissionAware {

	public static void convert(AbstractOrder fromObj, OrderSummary result) {
		AbstractBaseSummary.convert(fromObj, result);
		BeanUtils.copyProperties(fromObj, result);

		if (fromObj.getProject() != null) {
			result.setProjectId(fromObj.getProject().getId());
			result.setProjectName(fromObj.getProject().getName());
		}
		if (fromObj.getContract() != null) {
			result.setContractId(fromObj.getContract().getId());
			result.setContractName(Contract.getContractName(fromObj.getContract()));
		}
		if (fromObj instanceof Order) {
			convert((Order) fromObj, result);
		}
	}

	public static void convert(Order fromObj, OrderSummary result) {
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
	}

	public static OrderSummary from(Order fromObj) {
		if (fromObj == null) {
			return null;
		}
		OrderSummary result = new OrderSummary();
		convert((AbstractOrder) fromObj, result);
		return result;
	}

	private int year;

	//unique 订单编号
	private String code;

	//unique 订单名称
	private String name;

	//项目名称（区域）*
	private String projectId;

	private String projectName;

	//主合同/协议名称*
	private String contractId;

	private String contractName;

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

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
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
