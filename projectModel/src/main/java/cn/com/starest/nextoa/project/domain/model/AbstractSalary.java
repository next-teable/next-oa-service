package cn.com.starest.nextoa.project.domain.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dz
 */
public class AbstractSalary {

	//年
	@Min(value = 2012, message = "年份不能小于2012年")
	int year;

	//月
	@Min(value = 1, message = "月份最小为1月")
	@Max(value = 12, message = "月份最大为12月")
	int month;

	//员工姓名
	@NotNull(message = "员工姓名不能为空")
	String employee;

	//员工住址
	String address;

	//入职时间
	Date hireDate;

	//本月服务时长
	BigDecimal servicedTime;

	//省内出差时长
	BigDecimal businessTripInsideTime;

	//省外出差时长
	BigDecimal businessTripOutsideTime;

	//基本薪酬
	BigDecimal baseSalary;

	//绩效
	BigDecimal performanceSalary;

	//薪酬小计
	BigDecimal sumSalary;

	//省内差旅补贴
	BigDecimal businessTripInsideSubsidy;

	//省内差旅补贴
	BigDecimal businessTripOutsideSubsidy;

	//加班补贴
	BigDecimal overtimeSubsidy;

	//电脑补贴
	BigDecimal computerSubsidy;

	//临时补贴
	BigDecimal tempSubsidy;

	//特殊岗位补贴
	BigDecimal specialSubsidy;

	//甲方表扬补贴
	BigDecimal firstPartySubsidy;

	//年终奖
	BigDecimal yearEndBonus;

	//补贴小计
	BigDecimal sumSubsidy;

	//其他扣款
	BigDecimal otherDeductMoney;

	//应发薪酬
	BigDecimal calculatedSalary;

	//个人所得税
	BigDecimal personalIncomeTax;

	//个人社保
	BigDecimal socialInsurance;

	//个人公积金
	BigDecimal publicReserveFund;

	//应扣小计
	BigDecimal sumDeductMoney;

	//实发薪酬
	BigDecimal finalSalary;

	//单位社保
	BigDecimal corpSocialInsurance;

	//单位公积金
	BigDecimal corpPublicReserveFund;

	//合计支出
	BigDecimal totalPay;

	//备注
	String description;

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

	public String getEmployee() {
		return employee;
	}

	public void setEmployee(String employee) {
		if (employee != null) {
			employee = employee.trim();
		}

		this.employee = employee;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public BigDecimal getServicedTime() {
		return servicedTime;
	}

	public void setServicedTime(BigDecimal servicedTime) {
		this.servicedTime = servicedTime;
	}

	public BigDecimal getBusinessTripInsideTime() {
		return businessTripInsideTime;
	}

	public void setBusinessTripInsideTime(BigDecimal businessTripInsideTime) {
		this.businessTripInsideTime = businessTripInsideTime;
	}

	public BigDecimal getBusinessTripOutsideTime() {
		return businessTripOutsideTime;
	}

	public void setBusinessTripOutsideTime(BigDecimal businessTripOutsideTime) {
		this.businessTripOutsideTime = businessTripOutsideTime;
	}

	public BigDecimal getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(BigDecimal baseSalary) {
		this.baseSalary = baseSalary;
	}

	public BigDecimal getPerformanceSalary() {
		return performanceSalary;
	}

	public void setPerformanceSalary(BigDecimal performanceSalary) {
		this.performanceSalary = performanceSalary;
	}

	public BigDecimal getSumSalary() {
		return sumSalary;
	}

	public void setSumSalary(BigDecimal sumSalary) {
		this.sumSalary = sumSalary;
	}

	public BigDecimal getBusinessTripInsideSubsidy() {
		return businessTripInsideSubsidy;
	}

	public void setBusinessTripInsideSubsidy(BigDecimal businessTripInsideSubsidy) {
		this.businessTripInsideSubsidy = businessTripInsideSubsidy;
	}

	public BigDecimal getBusinessTripOutsideSubsidy() {
		return businessTripOutsideSubsidy;
	}

	public void setBusinessTripOutsideSubsidy(BigDecimal businessTripOutsideSubsidy) {
		this.businessTripOutsideSubsidy = businessTripOutsideSubsidy;
	}

	public BigDecimal getOvertimeSubsidy() {
		return overtimeSubsidy;
	}

	public void setOvertimeSubsidy(BigDecimal overtimeSubsidy) {
		this.overtimeSubsidy = overtimeSubsidy;
	}

	public BigDecimal getComputerSubsidy() {
		return computerSubsidy;
	}

	public void setComputerSubsidy(BigDecimal computerSubsidy) {
		this.computerSubsidy = computerSubsidy;
	}

	public BigDecimal getTempSubsidy() {
		return tempSubsidy;
	}

	public void setTempSubsidy(BigDecimal tempSubsidy) {
		this.tempSubsidy = tempSubsidy;
	}

	public BigDecimal getSpecialSubsidy() {
		return specialSubsidy;
	}

	public void setSpecialSubsidy(BigDecimal specialSubsidy) {
		this.specialSubsidy = specialSubsidy;
	}

	public BigDecimal getFirstPartySubsidy() {
		return firstPartySubsidy;
	}

	public void setFirstPartySubsidy(BigDecimal firstPartySubsidy) {
		this.firstPartySubsidy = firstPartySubsidy;
	}

	public BigDecimal getYearEndBonus() {
		return yearEndBonus;
	}

	public void setYearEndBonus(BigDecimal yearEndBonus) {
		this.yearEndBonus = yearEndBonus;
	}

	public BigDecimal getSumSubsidy() {
		return sumSubsidy;
	}

	public void setSumSubsidy(BigDecimal sumSubsidy) {
		this.sumSubsidy = sumSubsidy;
	}

	public BigDecimal getOtherDeductMoney() {
		return otherDeductMoney;
	}

	public void setOtherDeductMoney(BigDecimal otherDeductMoney) {
		this.otherDeductMoney = otherDeductMoney;
	}

	public BigDecimal getCalculatedSalary() {
		return calculatedSalary;
	}

	public void setCalculatedSalary(BigDecimal calculatedSalary) {
		this.calculatedSalary = calculatedSalary;
	}

	public BigDecimal getPersonalIncomeTax() {
		return personalIncomeTax;
	}

	public void setPersonalIncomeTax(BigDecimal personalIncomeTax) {
		this.personalIncomeTax = personalIncomeTax;
	}

	public BigDecimal getSocialInsurance() {
		return socialInsurance;
	}

	public void setSocialInsurance(BigDecimal socialInsurance) {
		this.socialInsurance = socialInsurance;
	}

	public BigDecimal getPublicReserveFund() {
		return publicReserveFund;
	}

	public void setPublicReserveFund(BigDecimal publicReserveFund) {
		this.publicReserveFund = publicReserveFund;
	}

	public BigDecimal getSumDeductMoney() {
		return sumDeductMoney;
	}

	public void setSumDeductMoney(BigDecimal sumDeductMoney) {
		this.sumDeductMoney = sumDeductMoney;
	}

	public BigDecimal getFinalSalary() {
		return finalSalary;
	}

	public void setFinalSalary(BigDecimal finalSalary) {
		this.finalSalary = finalSalary;
	}

	public BigDecimal getCorpSocialInsurance() {
		return corpSocialInsurance;
	}

	public void setCorpSocialInsurance(BigDecimal corpSocialInsurance) {
		this.corpSocialInsurance = corpSocialInsurance;
	}

	public BigDecimal getCorpPublicReserveFund() {
		return corpPublicReserveFund;
	}

	public void setCorpPublicReserveFund(BigDecimal corpPublicReserveFund) {
		this.corpPublicReserveFund = corpPublicReserveFund;
	}

	public BigDecimal getTotalPay() {
		return totalPay;
	}

	public void setTotalPay(BigDecimal totalPay) {
		this.totalPay = totalPay;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
