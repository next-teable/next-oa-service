package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.model.dtr.AbstractBaseSummary;
import cn.com.starest.nextoa.project.domain.model.AbstractSubOrder;
import cn.com.starest.nextoa.project.domain.model.Order;
import cn.com.starest.nextoa.project.domain.model.SubOrder;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dz
 */
public class SubOrderSummary extends AbstractPermissionAware {

	public static void convert(AbstractSubOrder fromObj, SubOrderSummary result) {
		AbstractBaseSummary.convert(fromObj, result);
		BeanUtils.copyProperties(fromObj, result);

		if (fromObj.getProject() != null) {
			result.setProjectId(fromObj.getProject().getId());
			result.setProjectName(fromObj.getProject().getName());
		}
		if (fromObj.getOrder() != null) {
			result.setOrderId(fromObj.getOrder().getId());
			result.setOrderName(Order.getOrderName(fromObj.getOrder()));
		}
		if (fromObj.getSubContract() != null) {
			result.setSubContractId(fromObj.getSubContract().getId());
			result.setSubContractName(fromObj.getSubContract().getName());
		}
		if (fromObj instanceof SubOrder) {
			convert((SubOrder) fromObj, result);
		}
	}

	public static void convert(SubOrder fromObj, SubOrderSummary result) {
		if (fromObj.getPublishBy() != null) {
			result.setPublishById(fromObj.getPublishBy().getId());
			result.setPublishByName(fromObj.getPublishBy().getUsername());
		}
	}

	public static SubOrderSummary from(SubOrder fromObj) {
		if (fromObj == null) {
			return null;
		}
		SubOrderSummary result = new SubOrderSummary();
		convert((AbstractSubOrder) fromObj, result);
		return result;
	}

	//unique 订单编号
	private String code;

	//unique 订单名称
	private String name;

	//项目名称（区域）*
	private String projectId;

	private String projectName;

	//分包合同/协议名称*
	private String orderId;

	private String orderName;

	//分包合同/协议名称*
	private String subContractId;

	private String subContractName;

	private BigDecimal amount;

	private String description;

	private boolean published;

	private Date publishAt;

	private String publishById;

	private String publishByName;

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

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getSubContractId() {
		return subContractId;
	}

	public void setSubContractId(String subContractId) {
		this.subContractId = subContractId;
	}

	public String getSubContractName() {
		return subContractName;
	}

	public void setSubContractName(String subContractName) {
		this.subContractName = subContractName;
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
}
