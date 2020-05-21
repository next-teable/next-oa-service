package cn.com.starest.nextoa.project.domain.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dz
 */
public class BizDepartmentFinancialReport {

	int year;

	private String bizDepartmentId;

	private String bizDepartmentName;

	private List<BizDepartmentFinancialAggregation> items = new ArrayList<>();

	private BizDepartmentFinancialAggregation sum;

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getBizDepartmentId() {
		return bizDepartmentId;
	}

	public void setBizDepartmentId(String bizDepartmentId) {
		this.bizDepartmentId = bizDepartmentId;
	}

	public String getBizDepartmentName() {
		return bizDepartmentName;
	}

	public void setBizDepartmentName(String bizDepartmentName) {
		this.bizDepartmentName = bizDepartmentName;
	}

	public List<BizDepartmentFinancialAggregation> getItems() {
		return items;
	}

	public void setItems(List<BizDepartmentFinancialAggregation> items) {
		this.items = items;
	}

	public BizDepartmentFinancialAggregation getSum() {
		return sum;
	}

	public void setSum(BizDepartmentFinancialAggregation sum) {
		this.sum = sum;
	}
}
