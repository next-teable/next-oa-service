package cn.com.starest.nextoa.project.service;

import cn.com.starest.nextoa.model.User;

/**
 * 项目运维服务,当项目有关模块数据错误的时候,通过本接口实现来修复或者升级
 *
 * @author dz
 */
public interface ProjectDevOpsService {

	/**
	 * 修复付款的付款时间（来自报销流程）
	 *
	 * @param user
	 */
	void fixedPaymentTimeOfReimburse(User user);

	/**
	 * 修复借还款的借款对象（如果是公司,则个人部分要清空,防止统计出错,反之亦然）
	 *
	 * @param user
	 */
	void fixedLendingObject(User user);

	/**
	 * 删除所有没有关联到公司的工资
	 *
	 * @param user
	 */
	void fixedDetachedSalary(User user);

	/**
	 * @param user
	 */
	void fixSalaryAggregationByYear(User user);

	/**
	 * @param user
	 */
	void fixPendingPaymentDate(User user);

	/**
	 *
	 * @param user
	 */
	void calculateCompanyInitialCapital(User user);
}
