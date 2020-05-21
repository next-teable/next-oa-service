package cn.com.starest.nextoa.project.domain.model;

import java.math.BigDecimal;

/**
 * @author dz
 */
public class ProjectFinancialAggregation {

	String companyId;

	String companyName;

	String province;

	String city;

	int year;

	int month;

	String projectId;

	String projectName;

	//主合同金额
	BigDecimal contractAmount;

	//主订单金额
	BigDecimal orderAmount;

	//回款金额
	BigDecimal receivedPaymentAmount;

	//应收金额
	BigDecimal receivableAmount;

	//待支付
	BigDecimal pendingPaymentAmount;

	//项目成本
	BigDecimal costAmount;

	//产值项目利润
	BigDecimal outputProfitAmount;

	//产值利润率
	BigDecimal outputProfitRate;

	//产值利润率(项目阈值)
	BigDecimal outputProfitRateThreshold;

	//回款项目利润
	BigDecimal collectProfitAmount;

	//回款利润率
	BigDecimal collectProfitRate;

	//回款利润率(项目阈值)
	BigDecimal collectProfitRateThreshold;

	BigDecimal profitPercent;

	//开票金额
	BigDecimal issueInvoiceAmount;

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

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public BigDecimal getContractAmount() {
		return contractAmount;
	}

	public void setContractAmount(BigDecimal contractAmount) {
		this.contractAmount = contractAmount;
	}

	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	public BigDecimal getReceivedPaymentAmount() {
		return receivedPaymentAmount;
	}

	public void setReceivedPaymentAmount(BigDecimal receivedPaymentAmount) {
		this.receivedPaymentAmount = receivedPaymentAmount;
	}

	public BigDecimal getReceivableAmount() {
		return receivableAmount;
	}

	public void setReceivableAmount(BigDecimal receivableAmount) {
		this.receivableAmount = receivableAmount;
	}

	public BigDecimal getPendingPaymentAmount() {
		return pendingPaymentAmount;
	}

	public void setPendingPaymentAmount(BigDecimal pendingPaymentAmount) {
		this.pendingPaymentAmount = pendingPaymentAmount;
	}

	public BigDecimal getCostAmount() {
		return costAmount;
	}

	public void setCostAmount(BigDecimal costAmount) {
		this.costAmount = costAmount;
	}

	public BigDecimal getOutputProfitAmount() {
		return outputProfitAmount;
	}

	public void setOutputProfitAmount(BigDecimal outputProfitAmount) {
		this.outputProfitAmount = outputProfitAmount;
	}

	public BigDecimal getOutputProfitRate() {
		return outputProfitRate;
	}

	public void setOutputProfitRate(BigDecimal outputProfitRate) {
		this.outputProfitRate = outputProfitRate;
	}

	public BigDecimal getCollectProfitAmount() {
		return collectProfitAmount;
	}

	public void setCollectProfitAmount(BigDecimal collectProfitAmount) {
		this.collectProfitAmount = collectProfitAmount;
	}

	public BigDecimal getCollectProfitRate() {
		return collectProfitRate;
	}

	public void setCollectProfitRate(BigDecimal collectProfitRate) {
		this.collectProfitRate = collectProfitRate;
	}

	public BigDecimal getOutputProfitRateThreshold() {
		return outputProfitRateThreshold;
	}

	public void setOutputProfitRateThreshold(BigDecimal outputProfitRateThreshold) {
		this.outputProfitRateThreshold = outputProfitRateThreshold;
	}

	public BigDecimal getCollectProfitRateThreshold() {
		return collectProfitRateThreshold;
	}

	public void setCollectProfitRateThreshold(BigDecimal collectProfitRateThreshold) {
		this.collectProfitRateThreshold = collectProfitRateThreshold;
	}

	public BigDecimal getProfitPercent() {
		return profitPercent;
	}

	public void setProfitPercent(BigDecimal profitPercent) {
		this.profitPercent = profitPercent;
	}

	public BigDecimal getIssueInvoiceAmount() {
		return issueInvoiceAmount;
	}

	public void setIssueInvoiceAmount(BigDecimal issueInvoiceAmount) {
		this.issueInvoiceAmount = issueInvoiceAmount;
	}
}
