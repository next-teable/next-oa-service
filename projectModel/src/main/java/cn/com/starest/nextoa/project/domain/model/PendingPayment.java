package cn.com.starest.nextoa.project.domain.model;

import cn.com.starest.nextoa.model.BaseModel;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 待付款
 *
 * @author dz
 */
@Document(collection = "PendingPayments")
public class PendingPayment extends BaseModel {

	@Indexed
	private int year;

	@Indexed
	private ReceivedPaymentSource receivedPaymentSource;

	//回款流程ID
	@Indexed
	private String receivedPaymentCode;

	//回款所属公司（冗余字段,后台通过projectReceivedPaymentCode自动关联）
	@Indexed
	@DBRef(lazy = true)
	private Company company;

	//回款所属项目（冗余字段,后台通过projectReceivedPaymentCode自动关联）
	@Indexed
	@DBRef(lazy = true)
	private Project project;

	@Indexed
	@DBRef(lazy = true)
	private BizDepartment bizDepartment;

	//回款（冗余字段,后台通过projectReceivedPaymentCode自动关联）
	@Indexed
	@DBRef(lazy = true)
	private ProjectReceivedPayment projectReceivedPayment;

	//回款（冗余字段,后台通过projectReceivedPaymentCode自动关联）
	@Indexed
	@DBRef(lazy = true)
	private CompanyReceivedPayment companyReceivedPayment;

	// 回款日期 (来自项目回款和公司回款,作为待支付的财务日期)
	@Indexed
	private Date pendingAt;

	//待付款金额
	private BigDecimal amount;

	//付款单位（分包单位）
	@DBRef(lazy = true)
	private SubContractor subContractor;

	@Indexed
	private boolean payed;

	@Indexed
	@DBRef(lazy = true)
	private Payment payment;

	//备注
	private String description;

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public ReceivedPaymentSource getReceivedPaymentSource() {
		return receivedPaymentSource;
	}

	public void setReceivedPaymentSource(ReceivedPaymentSource receivedPaymentSource) {
		this.receivedPaymentSource = receivedPaymentSource;
	}

	public String getReceivedPaymentCode() {
		return receivedPaymentCode;
	}

	public void setReceivedPaymentCode(String receivedPaymentCode) {
		this.receivedPaymentCode = receivedPaymentCode;
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

	public BizDepartment getBizDepartment() {
		return bizDepartment;
	}

	public void setBizDepartment(BizDepartment bizDepartment) {
		this.bizDepartment = bizDepartment;
	}

	public ProjectReceivedPayment getProjectReceivedPayment() {
		return projectReceivedPayment;
	}

	public void setProjectReceivedPayment(ProjectReceivedPayment projectReceivedPayment) {
		this.projectReceivedPayment = projectReceivedPayment;
	}

	public CompanyReceivedPayment getCompanyReceivedPayment() {
		return companyReceivedPayment;
	}

	public void setCompanyReceivedPayment(CompanyReceivedPayment companyReceivedPayment) {
		this.companyReceivedPayment = companyReceivedPayment;
	}

	public Date getPendingAt() {
		return pendingAt;
	}

	public void setPendingAt(Date pendingAt) {
		this.pendingAt = pendingAt;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public SubContractor getSubContractor() {
		return subContractor;
	}

	public void setSubContractor(SubContractor subContractor) {
		this.subContractor = subContractor;
	}

	public boolean isPayed() {
		return payed;
	}

	public void setPayed(boolean payed) {
		this.payed = payed;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
