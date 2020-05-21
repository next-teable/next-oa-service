package cn.com.starest.nextoa.project.domain.model;

import cn.com.starest.nextoa.model.BaseModel;
import cn.com.starest.nextoa.model.User;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 借(还)款
 *
 * @author dz
 */
@Document(collection = "Lending")
public class Lending extends BaseModel {

	// 公司 - 借出方
	@Indexed
	@DBRef(lazy = true)
	private Company company;

	// 借款对象
	private LendingObject lendingObject;

	// 借款人
	@Indexed
	@DBRef(lazy = true)
	private User lendingBy;

	// 借款公司 - 借入方
	@Indexed
	@DBRef(lazy = true)
	private Company lendingTo;

	// 借款类型
	@Indexed
	private LendingType lendingType;

	// 借款金额
	private BigDecimal amount;

	// 借(还)款日期
	@Indexed
	private Date handleAt;

	// 借款原因
	private String reason;

	// 备注
	private String description;

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public LendingObject getLendingObject() {
		return lendingObject;
	}

	public void setLendingObject(LendingObject lendingObject) {
		this.lendingObject = lendingObject;
	}

	public User getLendingBy() {
		return lendingBy;
	}

	public void setLendingBy(User lendingBy) {
		this.lendingBy = lendingBy;
	}

	public Company getLendingTo() {
		return lendingTo;
	}

	public void setLendingTo(Company lendingTo) {
		this.lendingTo = lendingTo;
	}

	public LendingType getLendingType() {
		return lendingType;
	}

	public void setLendingType(LendingType lendingType) {
		this.lendingType = lendingType;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getHandleAt() {
		return handleAt;
	}

	public void setHandleAt(Date handleAt) {
		this.handleAt = handleAt;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
