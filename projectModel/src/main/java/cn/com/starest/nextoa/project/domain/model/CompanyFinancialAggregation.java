package cn.com.starest.nextoa.project.domain.model;

import java.math.BigDecimal;

/**
 * @author dz
 */
public class CompanyFinancialAggregation {

	String companyId;

	String companyName;

	int year;

	int month;

	//全年收入
	BigDecimal incomeAmount;

	//项目支出
	BigDecimal projectExpenseAmount;

	//公司运营支出
	BigDecimal operationExpenseAmount;

	//员工借款
	BigDecimal employeeLendingAmount;

	//保证金
	BigDecimal depositAmount;

	//待支付
	BigDecimal pendingPaymentAmount;

	//合计
	BigDecimal totalAmount;

	//余额
	BigDecimal balanceAmount;

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

	public BigDecimal getIncomeAmount() {
		return incomeAmount;
	}

	public void setIncomeAmount(BigDecimal incomeAmount) {
		this.incomeAmount = incomeAmount;
	}

	public BigDecimal getProjectExpenseAmount() {
		return projectExpenseAmount;
	}

	public void setProjectExpenseAmount(BigDecimal projectExpenseAmount) {
		this.projectExpenseAmount = projectExpenseAmount;
	}

	public BigDecimal getOperationExpenseAmount() {
		return operationExpenseAmount;
	}

	public void setOperationExpenseAmount(BigDecimal operationExpenseAmount) {
		this.operationExpenseAmount = operationExpenseAmount;
	}

	public BigDecimal getEmployeeLendingAmount() {
		return employeeLendingAmount;
	}

	public void setEmployeeLendingAmount(BigDecimal employeeLendingAmount) {
		this.employeeLendingAmount = employeeLendingAmount;
	}

	public BigDecimal getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(BigDecimal depositAmount) {
		this.depositAmount = depositAmount;
	}

	public BigDecimal getPendingPaymentAmount() {
		return pendingPaymentAmount;
	}

	public void setPendingPaymentAmount(BigDecimal pendingPaymentAmount) {
		this.pendingPaymentAmount = pendingPaymentAmount;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(BigDecimal balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

}
