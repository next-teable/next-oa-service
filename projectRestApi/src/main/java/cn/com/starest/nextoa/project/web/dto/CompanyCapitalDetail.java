package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.project.domain.model.CompanyCapital;

/**
 * @author dz
 */
public class CompanyCapitalDetail extends CompanyCapitalSummary {

	public static void convert(CompanyCapital fromObj, CompanyCapitalDetail result) {
		CompanyCapitalSummary.convert(fromObj, result);
	}

	public static CompanyCapitalDetail from(CompanyCapital fromObj) {
		if (fromObj == null) {
			return null;
		}
		CompanyCapitalDetail result = new CompanyCapitalDetail();
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
