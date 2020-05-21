package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.model.shared.StringIdentifier;
import cn.com.starest.nextoa.project.domain.model.SalaryAggregation;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dz
 */
public class SalaryAggregationSummary extends StringIdentifier {

	public static void convert(SalaryAggregation fromObj, SalaryAggregationSummary result) {
		BeanUtils.copyProperties(fromObj, result);

		if (fromObj.getCompany() != null) {
			result.setCompanyId(fromObj.getCompany().getId());
			result.setCompanyName(fromObj.getCompany().getName());
		}

	}

	public static SalaryAggregationSummary from(SalaryAggregation fromObj) {
		if (fromObj == null) {
			return null;
		}
		SalaryAggregationSummary result = new SalaryAggregationSummary();
		convert(fromObj, result);
		return result;
	}

	private String companyId;

	private String companyName;

	//年
	private int year;

	//月
	private int month;

	//合计支出
	private BigDecimal totalPay;

	private Date modifiedAt;

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

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
}
