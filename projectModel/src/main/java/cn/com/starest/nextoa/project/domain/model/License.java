package cn.com.starest.nextoa.project.domain.model;

import cn.com.starest.nextoa.model.BaseModel;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 许可（各种证件,例如外管证）
 *
 * @author dz
 */
@Document(collection = "Licenses")
public class License extends BaseModel {

	private String code;

	//项目名称（区域）*
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

	private BigDecimal amount;

	//外管证办理日期*
	private Date handleDate;

	//	外管证有效期*
	@Indexed
	private Date expireDate;

	//注销
	@Indexed
	private boolean cancelled;

	//注销时间
	private Date cancelledAt;

	private String description;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getHandleDate() {
		return handleDate;
	}

	public void setHandleDate(Date handleDate) {
		this.handleDate = handleDate;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	public Date getCancelledAt() {
		return cancelledAt;
	}

	public void setCancelledAt(Date cancelledAt) {
		this.cancelledAt = cancelledAt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
