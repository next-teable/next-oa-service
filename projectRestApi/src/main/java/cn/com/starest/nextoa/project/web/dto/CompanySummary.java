package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.model.shared.StringIdentifier;
import cn.com.starest.nextoa.project.domain.model.Company;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

/**
 * @author dz
 */
public class CompanySummary extends StringIdentifier {

	public static void convert(Company fromObj, CompanySummary result) {
		BeanUtils.copyProperties(fromObj, result);
	}

	public static CompanySummary from(Company fromObj) {
		if (fromObj == null) {
			return null;
		}
		CompanySummary result = new CompanySummary();
		convert(fromObj, result);
		return result;
	}

	private String name;

	private String shortName;

	private String address;

	private String legalPerson;

	private String registeredPlace;

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
