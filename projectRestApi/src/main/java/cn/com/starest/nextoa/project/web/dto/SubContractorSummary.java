package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.model.shared.StringIdentifier;
import cn.com.starest.nextoa.project.domain.model.SubContractor;
import org.springframework.beans.BeanUtils;

/**
 * @author dz
 */
public class SubContractorSummary extends StringIdentifier {

	public static void convert(SubContractor fromObj, SubContractorSummary result) {
		BeanUtils.copyProperties(fromObj, result);
	}

	public static SubContractorSummary from(SubContractor fromObj) {
		if (fromObj == null) {
			return null;
		}
		SubContractorSummary result = new SubContractorSummary();
		convert(fromObj, result);
		return result;
	}

	private String name;

	private String contactPerson;

	private String contactPhone;

	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
