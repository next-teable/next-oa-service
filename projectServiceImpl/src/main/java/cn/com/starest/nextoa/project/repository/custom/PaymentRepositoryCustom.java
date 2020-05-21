package cn.com.starest.nextoa.project.repository.custom;

import cn.com.starest.nextoa.project.domain.model.*;
import cn.com.starest.nextoa.project.domain.request.PaymentQueryRequest;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

/**
 * @author dz
 */
public interface PaymentRepositoryCustom {

	/**
	 * 统计公司所有项目付款金额
	 *
	 * @param company
	 * @param year
	 * @return
	 */
	BigDecimal calculateProjectTotalAmount(Company company, int year);

	/**
	 * 统计公司所有项目付款金额
	 *
	 * @param company
	 * @param year
	 * @return
	 */
	BigDecimal calculateProjectTotalAmount(Company company, int year, int month);

	/**
	 * 统计公司所有项目付款金额
	 *
	 * @param company
	 * @param year
	 * @return
	 */
	BigDecimal calculateBizDepartmentTotalAmount(Company company, int year, int month);

	/**
	 * 统计公司所有部门付款金额
	 *
	 * @param company
	 * @param year
	 * @return
	 */
	BigDecimal calculateTotalAmount(Company company, int year);

	/**
	 * 统计部门所有付款金额
	 *
	 * @param bizDepartment
	 * @param year
	 * @return
	 */
	BigDecimal calculateTotalAmount(BizDepartment bizDepartment, int year);

	/**
	 * 统计项目付款金额
	 *
	 * @param project
	 * @return
	 */
	BigDecimal calculateTotalAmount(Project project);

	/**
	 * 统计项目付款金额
	 *
	 * @param project
	 * @param year
	 * @return
	 */
	BigDecimal calculateTotalAmount(Project project, int year);

	/**
	 * 统计收票付款金额
	 *
	 * @param receiveInvoice
	 * @return
	 */
	BigDecimal calculateTotalAmount(ReceiveInvoice receiveInvoice);

	/**
	 * 计算某关联业务的付款金额汇总
	 *
	 * @param bizRefId
	 * @param payToId
	 * @return
	 */
	BigDecimal calculateTotalAmount(String bizRefId, String payToId);

	/**
	 * 动态查询
	 *
	 * @param request
	 * @return
	 */
	Page<Payment> queryPage(PaymentQueryRequest request);

}
