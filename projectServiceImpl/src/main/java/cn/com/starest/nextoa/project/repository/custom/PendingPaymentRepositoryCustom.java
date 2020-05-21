package cn.com.starest.nextoa.project.repository.custom;

import cn.com.starest.nextoa.project.domain.model.Company;
import cn.com.starest.nextoa.project.domain.model.PendingPayment;
import cn.com.starest.nextoa.project.domain.model.Project;
import cn.com.starest.nextoa.project.domain.request.PendingPaymentQueryRequest;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

/**
 * @author dz
 */
public interface PendingPaymentRepositoryCustom {

	Page<PendingPayment> queryPage(PendingPaymentQueryRequest request);

	/**
	 * Calculate the total pending payment amount of specified company within year
	 *
	 * @param company
	 * @param year
	 * @return
	 */
	BigDecimal calculateTotalAmount(Company company, int year);

	/**
	 * Calculate the total pending payment amount of the specified company (before the year and the month)
	 *
	 * @param company
	 * @param year
	 * @param month
	 * @return
	 */
	BigDecimal calculateTotalAmount(Company company, int year, int month);

	/**
	 * Calculate the total pending payment amount of specified project
	 *
	 * @param project
	 * @return
	 */
	BigDecimal calculateTotalAmount(Project project);

	/**
	 * Calculate the total pending payment amount of specified project within year
	 *
	 * @param project
	 * @param year
	 * @return
	 */
	BigDecimal calculateTotalAmount(Project project, int year);

	/**
	 * @param request
	 * @return
	 */
	BigDecimal calculateTotalAmount(PendingPaymentQueryRequest request);

	/**
	 * @param request
	 * @return
	 */
	BigDecimal calculateTotalPayedAmount(PendingPaymentQueryRequest request);

	/**
	 * @param request
	 * @return
	 */
	BigDecimal calculateTotalPendingAmount(PendingPaymentQueryRequest request);
}
