package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.model.dtr.AbstractBaseSummary;
import cn.com.starest.nextoa.project.domain.model.Contract;
import cn.com.starest.nextoa.project.domain.model.License;
import cn.com.starest.nextoa.project.domain.model.Order;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dz
 */
public class LicenseSummary extends AbstractPermissionAware {

	public static void convert(License fromObj, LicenseSummary result) {
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

		if (fromObj.getOrder() != null) {
			result.setOrderId(fromObj.getOrder().getId());
			result.setOrderName(Order.getOrderName(fromObj.getOrder()));
		}
	}

	public static LicenseSummary from(License fromObj) {
		if (fromObj == null) {
			return null;
		}
		LicenseSummary result = new LicenseSummary();
		convert(fromObj, result);
		return result;
	}

	private String code;

	//项目名称（区域）*
	private String projectId;

	private String projectName;

	//主合同*
	private String contractId;

	private String contractName;

	//主订单
	private String orderId;

	private String orderName;

	private BigDecimal amount;

	//外管证办理日期*
	private Date handleDate;

	//	外管证有效期*
	private Date expireDate;

	private boolean cancelled;

	//注销时间
	private Date cancelledAt;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getHandleDate() {
		return handleDate;
	}

	public void setHandleDate(Date handleDate) {
		this.handleDate = handleDate;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	public Date getCancelledAt() {
		return cancelledAt;
	}

	public void setCancelledAt(Date cancelledAt) {
		this.cancelledAt = cancelledAt;
	}
}
