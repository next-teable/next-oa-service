package cn.com.starest.nextoa.project.domain.model;

import cn.com.starest.nextoa.model.BaseModel;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 项目回款
 *
 * @author dz
 */
@Document(collection = "ProjectReceivedPayments")
public class ProjectReceivedPayment extends BaseModel {

	@Indexed
	private int year;

	//generate by system
	@Indexed
	private String code;

	//from project
	@Indexed
	@DBRef(lazy = true)
	private Company company;

	@Indexed
	@DBRef(lazy = true)
	private AccountSubject accountSubject;

	@DBRef(lazy = true)
	private AccountSubject subAccountSubject;

	@Indexed
	@DBRef(lazy = true)
	private Project project;

	//主合同/协议名称*
	@Indexed
	@DBRef(lazy = true)
	private Contract contract;

	//主订单
	@Indexed
	@DBRef(lazy = true)
	private Order order;

	//回款类型*
	private ReceivedPaymentType type;

	@Indexed
	@DBRef(lazy = true)
	private IssueInvoice issueInvoice;

	//回款金额*
	private BigDecimal amount;

	//回款日期*
	@Indexed
	private Date receivedAt;

	//备注
	private String description;

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

	public ReceivedPaymentType getType() {
		return type;
	}

	public void setType(ReceivedPaymentType type) {
		this.type = type;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public AccountSubject getAccountSubject() {
		return accountSubject;
	}

	public void setAccountSubject(AccountSubject accountSubject) {
		this.accountSubject = accountSubject;
	}

	public AccountSubject getSubAccountSubject() {
		return subAccountSubject;
	}

	public void setSubAccountSubject(AccountSubject subAccountSubject) {
		this.subAccountSubject = subAccountSubject;
	}

	public IssueInvoice getIssueInvoice() {
		return issueInvoice;
	}

	public void setIssueInvoice(IssueInvoice issueInvoice) {
		this.issueInvoice = issueInvoice;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getReceivedAt() {
		return receivedAt;
	}

	public void setReceivedAt(Date receivedAt) {
		this.receivedAt = receivedAt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
