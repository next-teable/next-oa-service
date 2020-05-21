package cn.com.starest.nextoa.project.domain.model;

import java.util.List;

/**
 * @author dz
 */
public class CompanyFinancialReport {

	private String companyId;

	private String companyName;

	private List<CompanyFinancialAggregation> items;

	private CompanyFinancialAggregation sum;

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

	public List<CompanyFinancialAggregation> getItems() {
		return items;
	}

	public void setItems(List<CompanyFinancialAggregation> items) {
		this.items = items;
	}

	public CompanyFinancialAggregation getSum() {
		return sum;
	}

	public void setSum(CompanyFinancialAggregation sum) {
		this.sum = sum;
	}
}
