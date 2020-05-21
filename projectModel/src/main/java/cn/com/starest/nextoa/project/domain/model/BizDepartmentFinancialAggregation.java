package cn.com.starest.nextoa.project.domain.model;

import java.math.BigDecimal;

/**
 * @author dz
 */
public class BizDepartmentFinancialAggregation {

	int year;

	String bizDepartmentId;

	String bizDepartmentName;

	//预算成本
	BigDecimal budgetCost;

	//可用成本
	BigDecimal availableCost;

	//实际成本
	BigDecimal actualCost;

	//是否超预算
	boolean beyondBudget = false;

	BigDecimal totalPay;

	BigDecimal totalSalary;

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

	public BigDecimal getBudgetCost() {
		return budgetCost;
	}

	public void setBudgetCost(BigDecimal budgetCost) {
		this.budgetCost = budgetCost;
	}

	public BigDecimal getAvailableCost() {
		return availableCost;
	}

	public void setAvailableCost(BigDecimal availableCost) {
		this.availableCost = availableCost;
	}

	public BigDecimal getActualCost() {
		return actualCost;
	}

	public void setActualCost(BigDecimal actualCost) {
		this.actualCost = actualCost;
	}

	public boolean isBeyondBudget() {
		return beyondBudget;
	}

	public void setBeyondBudget(boolean beyondBudget) {
		this.beyondBudget = beyondBudget;
	}

	public BigDecimal getTotalPay() {
		return totalPay;
	}

	public void setTotalPay(BigDecimal totalPay) {
		this.totalPay = totalPay;
	}

	public BigDecimal getTotalSalary() {
		return totalSalary;
	}

	public void setTotalSalary(BigDecimal totalSalary) {
		this.totalSalary = totalSalary;
	}
}
