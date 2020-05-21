package cn.com.starest.nextoa.project.domain.model;

import cn.com.starest.nextoa.model.BaseModel;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 公司回款
 *
 * @author dz
 */
@Document(collection = "CompanyReceivedPayments")
public class CompanyReceivedPayment extends BaseModel {

	@Indexed
	private int year;

	//generate by system
	@Indexed
	private String code;

	@Indexed
	@DBRef(lazy = true)
	private Company company;

	// 回款类型*
	private ReceivedPaymentType type;

	// 费用类型*
	@Indexed
	@DBRef(lazy = true)
	private AccountSubject accountSubject;

	@DBRef(lazy = true)
	private AccountSubject subAccountSubject;

	//项目名称（区域）*
	@Indexed
	@DBRef(lazy = true)
	private Project project;

	//报销部门
	@Indexed
	@DBRef(lazy = true)
	private BizDepartment bizDepartment;

	// 回款金额*
	private BigDecimal amount;

	// 回款日期*
	@Indexed
	private Date receivedAt;

	// 回款单位
	private String receivedFrom;

	// 备注
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

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public ReceivedPaymentType getType() {
		return type;
	}

	public void setType(ReceivedPaymentType type) {
		this.type = type;
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

	public String getReceivedFrom() {
		return receivedFrom;
	}

	public void setReceivedFrom(String receivedFrom) {
		this.receivedFrom = receivedFrom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
