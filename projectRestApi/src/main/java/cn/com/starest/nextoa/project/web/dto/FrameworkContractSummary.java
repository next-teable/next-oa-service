package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.model.shared.StringIdentifier;
import cn.com.starest.nextoa.project.domain.model.FrameworkContract;
import cn.com.starest.nextoa.project.domain.model.FrameworkContractType;
import org.springframework.beans.BeanUtils;

/**
 * @author dz
 */
public class FrameworkContractSummary extends StringIdentifier {

	public static void convert(FrameworkContract fromObj, FrameworkContractSummary result) {
		BeanUtils.copyProperties(fromObj, result);
	}

	public static FrameworkContractSummary from(FrameworkContract fromObj) {
		if (fromObj == null) {
			return null;
		}
		FrameworkContractSummary result = new FrameworkContractSummary();
		convert(fromObj, result);
		return result;
	}

	private String name;

	private FrameworkContractType type;

	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FrameworkContractType getType() {
		return type;
	}

	public void setType(FrameworkContractType type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
