package cn.com.starest.nextoa.project.domain.request;

import cn.com.starest.nextoa.project.domain.model.ReceivedPaymentType;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dz
 */
public interface SaveCompanyReceivedPaymentRequest {

	int getYear();

	String getCompanyId();

	//回款类型*
	ReceivedPaymentType getType();

	//费用类型*
	/**
	 * @return 费用类型
	 */
	String getAccountSubjectId();

	/**
	 * @return 二级科目
	 */
	String getSubAccountSubjectId();

	/**
	 * @return 项目id（区域）*
	 */
	String getProjectId();

	/**
	 * @return 部门id
	 */
	String getBizDepartmentId();


	//回款单位*
	String getReceivedFrom();

	//回款金额*
	BigDecimal getAmount();

	//回款日期*
	Date getReceivedAt();

	//备注
	String getDescription();

}
