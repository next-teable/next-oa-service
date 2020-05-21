package cn.com.starest.nextoa.project.domain.request;

import cn.com.starest.nextoa.project.domain.model.ReceivedPaymentType;

import java.math.BigDecimal;
import java.util.Date;

/**
 * SaveProjectReceivedPaymentRequest
 *
 * @author dz
 */
public interface SaveProjectReceivedPaymentRequest {

	int getYear();

	ReceivedPaymentType getType();

	/**
	 * @return 费用类型
	 */
	String getAccountSubjectId();

	/**
	 * @return 二级科目
	 */
	String getSubAccountSubjectId();

	/**
	 * @return 项目
	 */
	String getProjectId();

	/**
	 * @return 主合同
	 */
	String getContractId();

	/**
	 * @return 主订单
	 */
	String getOrderId();

	/**
	 * @return 开票
	 */
	String getIssueInvoiceId();

	/**
	 * @return 金额
	 */
	BigDecimal getAmount();

	/**
	 * @return 备注
	 */
	String getDescription();

	/**
	 * @return 收款时间
	 */
	Date getReceivedAt();

}
