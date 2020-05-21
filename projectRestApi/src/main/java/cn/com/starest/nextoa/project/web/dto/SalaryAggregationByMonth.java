package cn.com.starest.nextoa.project.web.dto;

import java.math.BigDecimal;

/**
 * @author dz
 */
public class SalaryAggregationByMonth {

	private String subjectId;

	private String subjectName;

	//年
	private int year;

	//月
	private int month;

	//合计支出
	private BigDecimal totalPay;

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public BigDecimal getTotalPay() {
		return totalPay;
	}

	public void setTotalPay(BigDecimal totalPay) {
		this.totalPay = totalPay;
	}

}
