package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.project.domain.request.SaveDepositRequest;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dz
 */
public class SaveDepositParameter implements SaveDepositRequest {

	@NotNull(message = "保证金类型不能为空")
	private String depositTypeId;

	// 公司
	@NotNull(message = "公司不能为空")
	private String companyId;

	//招标代理单位
	private String biddingAgentId;

	@NotNull(message = "是否已立项")
	private Boolean isSetupProject;

	// 项目名称（区域）*
	private String projectId;

	private String projectName;

	// 保证金金额*
	@NotNull(message = "金额不能为空")
	@DecimalMin(value = "0", inclusive = true, message = "金额不能小于零")
	private BigDecimal amount;

	// 转账日期*
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "转账日期不能为空")
	private Date transferDate;

	// 转账单位*
	@NotNull(message = "转账单位不能为空")
	private String transferTo;

	// 经办人
	private String transferOperator;

	// 退回日期
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date returnedDate;

	// 退回金额
	@DecimalMin(value = "0", inclusive = true, message = "金额不能小于零")
	private BigDecimal returnedAmount;

	// 备注
	private String description;

	@Override
	public String getDepositTypeId() {
		return depositTypeId;
	}

	public void setDepositTypeId(String depositTypeId) {
		this.depositTypeId = depositTypeId;
	}

	@Override
	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	@Override
	public String getBiddingAgentId() {
		return biddingAgentId;
	}

	public void setBiddingAgentId(String biddingAgentId) {
		this.biddingAgentId = biddingAgentId;
	}

	@Override
	public Boolean getSetupProject() {
		return isSetupProject;
	}

	public void setSetupProject(Boolean setupProject) {
		isSetupProject = setupProject;
	}

	@Override
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	@Override
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Override
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		if (amount != null) {
			amount = amount.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		this.amount = amount;
	}

	@Override
	public Date getTransferDate() {
		return transferDate;
	}

	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}

	@Override
	public String getTransferTo() {
		return transferTo;
	}

	public void setTransferTo(String transferTo) {
		this.transferTo = transferTo;
	}

	@Override
	public String getTransferOperator() {
		return transferOperator;
	}

	public void setTransferOperator(String transferOperator) {
		this.transferOperator = transferOperator;
	}

	@Override
	public Date getReturnedDate() {
		return returnedDate;
	}

	public void setReturnedDate(Date returnedDate) {
		this.returnedDate = returnedDate;
	}

	@Override
	public BigDecimal getReturnedAmount() {
		return returnedAmount;
	}

	public void setReturnedAmount(BigDecimal returnedAmount) {
		this.returnedAmount = returnedAmount;
	}

	@Override
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
