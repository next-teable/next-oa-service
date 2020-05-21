package cn.com.starest.nextoa.project.domain.model;

import cn.com.starest.nextoa.model.BaseModel;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 收票
 *
 * @author dz
 */
@Document(collection = "ReceiveInvoices")
public class ReceiveInvoice extends BaseModel {

	//票据类型*
	private InvoiceType type;

	//公司
	@Indexed
	@DBRef(lazy = true)
	private Company company;

	//项目名称（区域）*
	@Indexed
	@DBRef(lazy = true)
	private Project project;

	//分包合同
	@Indexed
	@DBRef(lazy = true)
	private SubContract subContract;

	//分包订单
	@Indexed
	@DBRef(lazy = true)
	private SubOrder subOrder;

	//收票单位
	@Indexed
	@DBRef(lazy = true)
	private SubContractor subContractor;

	//收票金额*
	private BigDecimal amount;

	//收票类型*
	private BigDecimal taxRate;

	//收票日期*
	private Date receivedAt;

	private String description;

	public InvoiceType getType() {
		return type;
	}

	public void setType(InvoiceType type) {
		this.type = type;
	}

	public SubContractor getSubContractor() {
		return subContractor;
	}

	public void setSubContractor(SubContractor subContractor) {
		this.subContractor = subContractor;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public SubContract getSubContract() {
		return subContract;
	}

	public void setSubContract(SubContract subContract) {
		this.subContract = subContract;
	}

	public SubOrder getSubOrder() {
		return subOrder;
	}

	public void setSubOrder(SubOrder subOrder) {
		this.subOrder = subOrder;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
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
