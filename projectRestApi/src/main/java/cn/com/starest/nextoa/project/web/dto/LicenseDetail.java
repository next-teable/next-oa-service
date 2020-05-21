package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.project.domain.model.License;

/**
 * @author dz
 */
public class LicenseDetail extends LicenseSummary {

	public static void convert(License fromObj, LicenseDetail result) {
		LicenseSummary.convert(fromObj, result);
	}

	public static LicenseDetail from(License fromObj) {
		if (fromObj == null) {
			return null;
		}
		LicenseDetail result = new LicenseDetail();
		convert(fromObj, result);
		return result;
	}

	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
