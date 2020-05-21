package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.model.dtr.AbstractBaseSummary;
import cn.com.starest.nextoa.project.domain.model.Deposit;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dz
 */
public class DepositSummary extends AbstractPermissionAware {

	public static void convert(Deposit fromObj, DepositSummary result) {
		AbstractBaseSummary.convert(fromObj, result);
		BeanUtils.copyProperties(fromObj, result);

		if (fromObj.getType() != null) {
			result.setDepositTypeId(fromObj.getType().getId());
			result.setDepositTypeName(fromObj.getType().getName());
		}

		if (fromObj.getProject() != null) {
			result.setProjectId(fromObj.getProject().getId());
			result.setProjectName(fromObj.getProject().getName());
		}

		if (fromObj.getCompany() != null) {
			result.setCompanyId(fromObj.getCompany().getId());
			result.setCompanyName(fromObj.getCompany().getName());
		}

		if (fromObj.getBiddingAgent() != null) {
			result.setBiddingAgentId(fromObj.getBiddingAgent().getId());
			result.setBiddingAgentName(fromObj.getBiddingAgent().getName());
		}
	}

	public static DepositSummary from(Deposit fromObj) {
		if (fromObj == null) {
			return null;
		}
		DepositSummary result = new DepositSummary();
		convert(fromObj, result);
		return result;
	}

	private String depositTypeId;

	private String depositTypeName;

	// 公司
	private String companyId;

	private String companyName;

	private boolean isSetupProject;

	//
	private String biddingAgentId;

	private String biddingAgentName;

	// 项目名称（区域）*
	private String projectId;

	private String projectName;

	// 保证金金额*
	private BigDecimal amount;

	// 转账日期*
	private Date transferDate;

	// 转账单位*
	private String transferTo;

	// 经办人
	private String transferOperator;

	// 退回日期
	private Date returnedDate;

	// 退回金额
	private BigDecimal returnedAmount;

	public String getDepositTypeId() {
		return depositTypeId;
	}

	public void setDepositTypeId(String depositTypeId) {
		this.depositTypeId = depositTypeId;
	}

	public String getDepositTypeName() {
		return depositTypeName;
	}

	public void setDepositTypeName(String depositTypeName) {
		this.depositTypeName = depositTypeName;
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

	public boolean isSetupProject() {
		return isSetupProject;
	}

	public void setSetupProject(boolean setupProject) {
		isSetupProject = setupProject;
	}

	public String getBiddingAgentId() {
		return biddingAgentId;
	}

	public void setBiddingAgentId(String biddingAgentId) {
		this.biddingAgentId = biddingAgentId;
	}

	public String getBiddingAgentName() {
		return biddingAgentName;
	}

	public void setBiddingAgentName(String biddingAgentName) {
		this.biddingAgentName = biddingAgentName;
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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getTransferDate() {
		return transferDate;
	}

	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}

	public String getTransferTo() {
		return transferTo;
	}

	public void setTransferTo(String transferTo) {
		this.transferTo = transferTo;
	}

	public String getTransferOperator() {
		return transferOperator;
	}

	public void setTransferOperator(String transferOperator) {
		this.transferOperator = transferOperator;
	}

	public Date getReturnedDate() {
		return returnedDate;
	}

	public void setReturnedDate(Date returnedDate) {
		this.returnedDate = returnedDate;
	}

	public BigDecimal getReturnedAmount() {
		return returnedAmount;
	}

	public void setReturnedAmount(BigDecimal returnedAmount) {
		this.returnedAmount = returnedAmount;
	}
}
