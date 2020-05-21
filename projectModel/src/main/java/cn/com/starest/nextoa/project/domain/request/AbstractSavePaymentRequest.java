package cn.com.starest.nextoa.project.domain.request;

import cn.com.starest.nextoa.project.domain.model.PaymentObject;

import java.math.BigDecimal;

/**
 * @author dz
 */
public interface AbstractSavePaymentRequest {

	int getYear();

	/**
	 * @return 公司
	 */
	String getCompanyId();

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
	 * @return 部门id
	 */
	String getBizDepartmentId();

	/**
	 * @return 主合同
	 */
	String getContractId();

	/**
	 * @return 分包合同
	 */
	String getSubContractId();

	/**
	 * @return 主订单
	 */
	String getOrderId();

	/**
	 * @return 分包订单
	 */
	String getSubOrderId();

	/**
	 * @return 金额
	 */
	BigDecimal getAmount();

	/**
	 * @return 备注
	 */
	String getDescription();

	PaymentObject getPaymentObject();

	/**
	 * @return 付款单位（分包单位）
	 */
	String getSubContractorId();

	/**
	 * @return 付款人员（用户）
	 */
	String getPayToId();

	/**
	 * @return
	 */
	String getOther();

	/**
	 * @return 收票id
	 */
	String getReceiveInvoiceId();

}
