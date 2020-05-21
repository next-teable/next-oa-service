package cn.com.starest.nextoa.project.service;

import cn.com.starest.nextoa.project.domain.model.Project;

import java.math.BigDecimal;

/**
 * @author dz
 */
public interface AggregationService {

	BigDecimal getContractTotalAmount(Project project);

	BigDecimal getOrderTotalAmount(Project project);

	BigDecimal getIssueInvoiceTotalAmount(Project project);

	BigDecimal getProjectReceivedPaymentTotalAmount(Project project);

	BigDecimal getSubOrderTotalAmount(Project project);

	BigDecimal getReceiveInvoiceTotalAmount(Project project);

	BigDecimal getPaymentTotalAmount(Project project);

	BigDecimal getDepositTotalAmount(Project project);

	BigDecimal getPendingPaymentTotalAmount(Project project);

	BigDecimal getSalaryTotalAmount(Project project);
}
