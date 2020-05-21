package cn.com.starest.nextoa.project.domain.model;

import cn.com.starest.nextoa.model.BaseModel;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.math.BigDecimal;

/**
 * @author dz
 */
public class AbstractSubOrder extends BaseModel {

	//unique 订单编号
	@Indexed
	private String code;

	//unique 订单名称
	@Indexed
	private String name;

	//项目名称（区域）*
	@Indexed
	@DBRef(lazy = true)
	private Project project;

	//主订单*
	@Indexed
	@DBRef(lazy = true)
	private Order order;

	//分包合同/协议名称*
	@Indexed
	@DBRef(lazy = true)
	private SubContract subContract;

	private BigDecimal amount;

	private String description;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public SubContract getSubContract() {
		return subContract;
	}

	public void setSubContract(SubContract subContract) {
		this.subContract = subContract;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
