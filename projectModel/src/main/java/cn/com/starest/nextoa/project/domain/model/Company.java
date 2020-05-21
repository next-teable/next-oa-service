package cn.com.starest.nextoa.project.domain.model;

import cn.com.starest.nextoa.model.BaseModel;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

/**
 * 公司
 *
 * @author dz
 */
@Document(collection = "Companies")
public class Company extends BaseModel {

	//unique
	@NotEmpty(message = "名称不能为空")
	@Indexed
	private String name;

	@NotEmpty(message = "简称不能为空")
	private String shortName;

	private String address;

	private String legalPerson;

	private String registeredPlace;

	@DecimalMin(value = "0", inclusive = true, message = "注册资金不能小于零")
	private BigDecimal registeredCapital;

	private String sort;

	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getRegisteredPlace() {
		return registeredPlace;
	}

	public void setRegisteredPlace(String registeredPlace) {
		this.registeredPlace = registeredPlace;
	}

	public BigDecimal getRegisteredCapital() {
		return registeredCapital;
	}

	public void setRegisteredCapital(BigDecimal registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
