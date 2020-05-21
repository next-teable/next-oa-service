package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.project.domain.request.SaveCompanyCapitalRequest;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author dz
 */
public class SaveCompanyCapitalParameter implements SaveCompanyCapitalRequest {

	// 公司
	@NotNull(message = "公司不能为空")
	private String companyId;

	@Min(value = 2012, message = "年份不能小于2012年")
	private int year;

	@Min(value = 1, message = "月份不能小于1")
	@Max(value = 12, message = "月份不能大于12")
	private int month;

	@NotNull(message = "金额不能为空")
	private BigDecimal amount;

	private String sort;

	// 备注
	private String description;

	@Override
	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	@Override
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	@Override
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	@Override
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
