package cn.com.starest.nextoa.project.domain.model;

import java.util.List;

/**
 * @author dz
 */
public class CompanyCapitalReport {

	private String companyId;

	private String companyName;

	int year;

	private List<CompanyCapitalAggregation> items;

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public List<CompanyCapitalAggregation> getItems() {
		return items;
	}

	public void setItems(List<CompanyCapitalAggregation> items) {
		this.items = items;
	}

}
