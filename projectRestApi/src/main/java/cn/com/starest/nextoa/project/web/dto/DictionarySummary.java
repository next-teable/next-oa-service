package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.model.shared.StringIdentifier;
import cn.com.starest.nextoa.project.domain.model.AbstractType;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @author dz
 */
public class DictionarySummary extends StringIdentifier {

	public static DictionarySummary from(AbstractType dictionary) {
		if (dictionary == null) {
			return null;
		}
		DictionarySummary result = new DictionarySummary();
		BeanUtils.copyProperties(dictionary, result);
		if (dictionary.getModifiedBy() != null) {
			result.setModifiedById(dictionary.getModifiedBy().getId());
			result.setModifiedByName(dictionary.getModifiedBy().getUsername());
		}
		return result;
	}

	private String name;

	private String description;

	private String modifiedById;

	private String modifiedByName;

	private Date modifiedAt;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getModifiedById() {
		return modifiedById;
	}

	public void setModifiedById(String modifiedById) {
		this.modifiedById = modifiedById;
	}

	public String getModifiedByName() {
		return modifiedByName;
	}

	public void setModifiedByName(String modifiedByName) {
		this.modifiedByName = modifiedByName;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
}
