package cn.com.starest.nextoa.project.domain.model;

import cn.com.starest.nextoa.model.BaseModel;
import cn.com.starest.nextoa.model.User;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.math.BigDecimal;

/**
 * The super class for Payment and Reimburse
 *
 * @author dz
 */
public abstract class AbstractPayment extends BaseModel {

	@Indexed
	private int year;

	@Indexed
	@DBRef(lazy = true)
	private AccountSubject accountSubject;

	@DBRef(lazy = true)
	private AccountSubject subAccountSubject;

	@Indexed
	@DBRef(lazy = true)
	private Company company;

	@Indexed
	@DBRef(lazy = true)
	private Project project;

	@Indexed
	@DBRef(lazy = true)
	private BizDepartment bizDepartment;

	@Indexed
	@DBRef(lazy = true)
	private Contract contract;

	//分包合同
	@Indexed
	@DBRef(lazy = true)
	private SubContract subContract;

	//主订单
	@Indexed
	@DBRef(lazy = true)
	private Order order;

	//分包订单
	@Indexed
	@DBRef(lazy = true)
	private SubOrder subOrder;

	private BigDecimal amount;

	//******************************
	//注意:付款单位,付款人员,其他只有一个有值
	//******************************

	private PaymentObject paymentObject;

	//付款单位（分包单位）
	@DBRef(lazy = true)
	private SubContractor subContractor;

	@Indexed
	@DBRef(lazy = true)
	private ReceiveInvoice receiveInvoice;

	//付款人员（用户）
	@DBRef(lazy = true)
	private User payTo;

	//其他付款对象
	private String other;

	//	报销选择外协单位支付时，必须选择对应的待支付数据（根据付款单位带出待付款）
	// 选择对应待付款后，系统自动让选择的待付款数据失效，同时在对应待支付数据后填入付款时间。不对比待付款金额与实际报销金额。
	@Indexed
	@DBRef(lazy = true)
	private PendingPayment pendingPayment;

	//和其他业务的关联关系, 例如: 结算提成报销的时候,需要关联到项目结算表
	@Indexed
	private String bizRefId;

	//备注
	private String description;

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
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

	public ReceiveInvoice getReceiveInvoice() {
		return receiveInvoice;
	}

	public void setReceiveInvoice(ReceiveInvoice receiveInvoice) {
		this.receiveInvoice = receiveInvoice;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public PaymentObject getPaymentObject() {
		return paymentObject;
	}

	public void setPaymentObject(PaymentObject paymentObject) {
		this.paymentObject = paymentObject;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public SubContract getSubContract() {
		return subContract;
	}

	public void setSubContract(SubContract subContract) {
		this.subContract = subContract;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public SubOrder getSubOrder() {
		return subOrder;
	}

	public void setSubOrder(SubOrder subOrder) {
		this.subOrder = subOrder;
	}

	public SubContractor getSubContractor() {
		return subContractor;
	}

	public void setSubContractor(SubContractor subContractor) {
		this.subContractor = subContractor;
	}

	public User getPayTo() {
		return payTo;
	}

	public void setPayTo(User payTo) {
		this.payTo = payTo;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public PendingPayment getPendingPayment() {
		return pendingPayment;
	}

	public void setPendingPayment(PendingPayment pendingPayment) {
		this.pendingPayment = pendingPayment;
	}

	public String getBizRefId() {
		return bizRefId;
	}

	public void setBizRefId(String bizRefId) {
		this.bizRefId = bizRefId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
