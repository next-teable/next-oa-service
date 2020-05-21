package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.model.dtr.AbstractBaseSummary;
import cn.com.starest.nextoa.project.domain.model.Contract;
import cn.com.starest.nextoa.project.domain.model.Order;
import cn.com.starest.nextoa.project.domain.model.ProjectCompletion;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @author dz
 */
public class ProjectCompletionSummary extends AbstractPermissionAware {

	public static void convert(ProjectCompletion fromObj, ProjectCompletionSummary result) {
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

	public static ProjectCompletionSummary from(ProjectCompletion fromObj) {
		if (fromObj == null) {
			return null;
		}
		ProjectCompletionSummary result = new ProjectCompletionSummary();
		convert(fromObj, result);
		return result;
	}

	//项目名称（区域）*
	private String projectId;

	private String projectName;

	//主合同*
	private String contractId;

	private String contractName;

	//主订单
	private String orderId;

	private String orderName;

	//竣工时间
	private Date completeAt;

	private ProjectCompletion.ProjectCompletionStatus status;

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

	public Date getCompleteAt() {
		return completeAt;
	}

	public void setCompleteAt(Date completeAt) {
		this.completeAt = completeAt;
	}

	public ProjectCompletion.ProjectCompletionStatus getStatus() {
		return status;
	}

	public void setStatus(ProjectCompletion.ProjectCompletionStatus status) {
		this.status = status;
	}
}
