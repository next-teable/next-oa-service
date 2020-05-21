package cn.com.starest.nextoa.project.domain.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author dz
 */
@JsonSerialize(using = ModuleFeatureSerializer.class)
public enum Module {

	PROJECT("项目"),
	CONTRACT("合同"),
	SUBCONTRACT("分包合同"),
	ORDER("订单"),
	SUBORDER("分包订单"),
	PROJECT_COMPLETION("竣工"),
	PROJECT_SETTLEMENT("项目结算"),
	LICENSE("外管证"),
	ISSUE_INVOICE("开票"),
	RECEIVE_INVOICE("收票"),
	PAYMENT("付款"),
	REIMBURSE_DEVOPS("报销维护"),
	REIMBURSE("报销"),
	PENDING_PAYMENT("待付款"),
	PROJECT_RECEIVED_PAYMENT("项目回款"),
	COMPANY_RECEIVED_PAYMENT("公司回款"),
	DEPOSIT("保证金"),
	LENDING("借款还款"),
	SALARY("工资"),
	COMPANY_CAPITAL("公司资金");

	public final static Module[] EMPTY_MODULES = new Module[0];

	private String displayLabel;

	Module(String displayLabel) {
		this.displayLabel = displayLabel;
	}

	public String getDisplayLabel() {
		return this.displayLabel;
	}

}
