package cn.com.starest.nextoa.project.domain.model;

import cn.com.starest.nextoa.model.shared.StringIdentifier;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Salary
 *
 * @author dz
 */
@Document(collection = "SalaryAggregations")
public class SalaryAggregation extends StringIdentifier {

	public static enum AggregationType {
		BY_YEAR, BY_MONTH,
	}

	public static class SalaryAggregationCriteria {

		public static SalaryAggregationCriteria from(Salary salary) {
			SalaryAggregation.SalaryAggregationCriteria criteria = new SalaryAggregation.SalaryAggregationCriteria();
			criteria.setCompany(salary.getCompany());
			criteria.setYear(salary.getYear());
			criteria.setMonth(salary.getMonth());
			return criteria;
		}

		Company company;

		int year;

		int month;

		public Company getCompany() {
			return company;
		}

		public void setCompany(Company company) {
			this.company = company;
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

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}

			SalaryAggregationCriteria that = (SalaryAggregationCriteria) o;

			if (year != that.year) {
				return false;
			}
			if (month != that.month) {
				return false;
			}
			return (company != null &&
					company.getId() != null &&
					that.company != null &&
					that.company.getId() != null) ? company.getId().equals(that.company.getId()) : false;
		}

		@Override
		public int hashCode() {
			int result = (company != null && company.getId() != null) ? company.getId().hashCode() : 0;
			result = 31 * result + year;
			result = 31 * result + month;
			return result;
		}
	}

	@Indexed
	@DBRef(lazy = true)
	private Company company;

	//年
	@Indexed
	private int year;

	//月
	private int month;

	//合计支出
	private BigDecimal totalPay;

	private Date modifiedAt;

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
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
