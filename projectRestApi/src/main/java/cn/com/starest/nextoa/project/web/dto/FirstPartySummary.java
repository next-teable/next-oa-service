package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.model.shared.StringIdentifier;
import cn.com.starest.nextoa.project.domain.model.FirstParty;
import org.springframework.beans.BeanUtils;

/**
 * @author dz
 */
public class FirstPartySummary extends StringIdentifier {

	public static void convert(FirstParty fromObj, FirstPartySummary result) {
		BeanUtils.copyProperties(fromObj, result);
	}

	public static FirstPartySummary from(FirstParty fromObj) {
		if (fromObj == null) {
			return null;
		}
		FirstPartySummary result = new FirstPartySummary();
		convert(fromObj, result);
		return result;
	}

	private String name;

	private String province;

	private String city;

	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
