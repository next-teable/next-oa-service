package cn.com.starest.nextoa.project.domain.model;

import java.math.BigDecimal;

/**
 * @author dz
 */
public class CompanyCapitalAggregation {

	public static CompanyCapitalAggregation from(Company company, int year, int month) {
		CompanyCapitalAggregation result = new CompanyCapitalAggregation();
		result.setCompanyId(company.getId());
		result.setCompanyName(company.getName());
		result.setYear(year);
		result.setMonth(month);
		return result;
	}

	String companyId;

	String companyName;

	int year;

	int month;

	//初期现金余额
	BigDecimal initialCapitalAmount;

	//项目收入
	BigDecimal projectIncomeAmount;

	//公司运营收入
	BigDecimal companyIncomeAmount;

	//收入合计
	BigDecimal incomeTotalAmount;

	//员工工资
	BigDecimal salaryAmount;

	//公司运营费支出
	BigDecimal bizDepartmentPaymentAmount;

	//项目运营支出
	BigDecimal projectPaymentAmount;

	//支出合计
	BigDecimal paymentTotalAmount;

	//现金余额
	BigDecimal reservedCashAmount;

	//借还款情况
	BigDecimal lendingBalanceAmount;

	//保证金
	BigDecimal depositAmount;

	//账面现金余额
	BigDecimal accountCashAmount;

	//待支付
	BigDecimal pendingPaymentAmount;

	//可用现金余额
	BigDecimal availableCashAmount;

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

	public BigDecimal getInitialCapitalAmount() {
		return initialCapitalAmount;
	}

	public void setInitialCapitalAmount(BigDecimal initialCapitalAmount) {
		this.initialCapitalAmount = initialCapitalAmount;
	}

	public BigDecimal getProjectIncomeAmount() {
		return projectIncomeAmount;
	}

	public void setProjectIncomeAmount(BigDecimal projectIncomeAmount) {
		this.projectIncomeAmount = projectIncomeAmount;
	}

	public BigDecimal getCompanyIncomeAmount() {
		return companyIncomeAmount;
	}

	public void setCompanyIncomeAmount(BigDecimal companyIncomeAmount) {
		this.companyIncomeAmount = companyIncomeAmount;
	}

	public BigDecimal getIncomeTotalAmount() {
		return incomeTotalAmount;
	}

	public void setIncomeTotalAmount(BigDecimal incomeTotalAmount) {
		this.incomeTotalAmount = incomeTotalAmount;
	}

	public BigDecimal getSalaryAmount() {
		return salaryAmount;
	}

	public void setSalaryAmount(BigDecimal salaryAmount) {
		this.salaryAmount = salaryAmount;
	}

	public BigDecimal getBizDepartmentPaymentAmount() {
		return bizDepartmentPaymentAmount;
	}

	public void setBizDepartmentPaymentAmount(BigDecimal bizDepartmentPaymentAmount) {
		this.bizDepartmentPaymentAmount = bizDepartmentPaymentAmount;
	}

	public BigDecimal getProjectPaymentAmount() {
		return projectPaymentAmount;
	}

	public void setProjectPaymentAmount(BigDecimal projectPaymentAmount) {
		this.projectPaymentAmount = projectPaymentAmount;
	}

	public BigDecimal getPaymentTotalAmount() {
		return paymentTotalAmount;
	}

	public void setPaymentTotalAmount(BigDecimal paymentTotalAmount) {
		this.paymentTotalAmount = paymentTotalAmount;
	}

	public BigDecimal getReservedCashAmount() {
		return reservedCashAmount;
	}

	public void setReservedCashAmount(BigDecimal reservedCashAmount) {
		this.reservedCashAmount = reservedCashAmount;
	}

	public BigDecimal getLendingBalanceAmount() {
		return lendingBalanceAmount;
	}

	public void setLendingBalanceAmount(BigDecimal lendingBalanceAmount) {
		this.lendingBalanceAmount = lendingBalanceAmount;
	}

	public BigDecimal getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(BigDecimal depositAmount) {
		this.depositAmount = depositAmount;
	}

	public BigDecimal getAccountCashAmount() {
		return accountCashAmount;
	}

	public void setAccountCashAmount(BigDecimal accountCashAmount) {
		this.accountCashAmount = accountCashAmount;
	}

	public BigDecimal getPendingPaymentAmount() {
		return pendingPaymentAmount;
	}

	public void setPendingPaymentAmount(BigDecimal pendingPaymentAmount) {
		this.pendingPaymentAmount = pendingPaymentAmount;
	}

	public BigDecimal getAvailableCashAmount() {
		return availableCashAmount;
	}

	public void setAvailableCashAmount(BigDecimal availableCashAmount) {
		this.availableCashAmount = availableCashAmount;
	}
}
