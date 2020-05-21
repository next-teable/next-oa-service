package cn.com.starest.nextoa.project.domain.model;

import cn.com.starest.nextoa.model.BaseModel;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dz
 */
public class AbstractContract extends BaseModel {

	private ContractType contractType;

	//unique 主合同编号* 格式：分包单位简称-年月日-编号
	@Indexed
	private String code;

	//unique 主合同/协议名称*
	@Indexed
	private String name;

	//项目名称（区域）*
	@Indexed
	@DBRef(lazy = true)
	private List<Project> projects = new ArrayList<>();

	//主合同签订甲方名称*
	@Indexed
	@DBRef(lazy = true)
	private FirstParty firstParty;

	//	甲方项目负责人
	private String firstPartyManager;

	//	甲方联系电话
	private String firstPartyPhone;

	private BigDecimal amount;

	private String description;

	public ContractType getContractType() {
		return contractType;
	}

	public void setContractType(ContractType contractType) {
		this.contractType = contractType;
	}

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

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public FirstParty getFirstParty() {
		return firstParty;
	}

	public void setFirstParty(FirstParty firstParty) {
		this.firstParty = firstParty;
	}

	public String getFirstPartyManager() {
		return firstPartyManager;
	}

	public void setFirstPartyManager(String firstPartyManager) {
		this.firstPartyManager = firstPartyManager;
	}

	public String getFirstPartyPhone() {
		return firstPartyPhone;
	}

	public void setFirstPartyPhone(String firstPartyPhone) {
		this.firstPartyPhone = firstPartyPhone;
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
