package cn.com.starest.nextoa.project.domain.model;

import cn.com.starest.nextoa.model.BaseModel;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 保证金
 *
 * @author dz
 */
@Document(collection = "Deposits")
public class Deposit extends BaseModel {

	@Indexed
	@DBRef(lazy = true)
	private DepositType type;

	@Indexed
	@DBRef(lazy = true)
	private Company company;

	@Indexed
	@DBRef(lazy = true)
	private BiddingAgent biddingAgent;

	// 是否已立项
	private boolean isSetupProject;

	// 项目名称（区域）*
	@Indexed
	@DBRef(lazy = true)
	private Project project;

	// 未立项目 项目名称
	private String projectName;

	// 保证金金额*
	private BigDecimal amount;

	// 转账日期*
	@Indexed
	private Date transferDate;

	// 转账单位*
	@Indexed
	private String transferTo;

	// 经办人
	@Indexed
	private String transferOperator;

	// 退回日期
	@Indexed
	private Date returnedDate;

	// 退回金额
	private BigDecimal returnedAmount;

	// 备注
	private String description;

	public DepositType getType() {
		return type;
	}

	public void setType(DepositType type) {
		this.type = type;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public BiddingAgent getBiddingAgent() {
		return biddingAgent;
	}

	public void setBiddingAgent(BiddingAgent biddingAgent) {
		this.biddingAgent = biddingAgent;
	}

	public boolean isSetupProject() {
		return isSetupProject;
	}

	public void setSetupProject(boolean setupProject) {
		isSetupProject = setupProject;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
