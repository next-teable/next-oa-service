package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.model.dtr.AbstractBaseSummary;
import cn.com.starest.nextoa.project.domain.model.CompanyCapital;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

/**
 * @author dz
 */
public class CompanyCapitalSummary extends AbstractPermissionAware {

	public static void convert(CompanyCapital fromObj, CompanyCapitalSummary result) {
		AbstractBaseSummary.convert(fromObj, result);
		BeanUtils.copyProperties(fromObj, result);

		if (fromObj.getCompany() != null) {
			result.setCompanyId(fromObj.getCompany().getId());
			result.setCompanyName(fromObj.getCompany().getName());
		}
	}

	public static CompanyCapitalSummary from(CompanyCapital fromObj) {
		if (fromObj == null) {
			return null;
		}
		CompanyCapitalSummary result = new CompanyCapitalSummary();
		convert(fromObj, result);
		return result;
	}

	// 公司
	private String companyId;

	private String companyName;

	private int year;

	private int month;

	private BigDecimal amount;

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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
