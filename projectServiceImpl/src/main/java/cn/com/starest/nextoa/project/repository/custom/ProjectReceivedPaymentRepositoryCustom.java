package cn.com.starest.nextoa.project.repository.custom;

import cn.com.starest.nextoa.project.domain.model.*;
import cn.com.starest.nextoa.project.domain.request.ProjectReceivedPaymentQueryRequest;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

/**
 * @author dz
 */
public interface ProjectReceivedPaymentRepositoryCustom {

	/**
	 * @param request
	 * @return
	 */
	Page<ProjectReceivedPayment> queryPage(ProjectReceivedPaymentQueryRequest request);

	/**
	 * 统计公司回款金额
	 *
	 * @param company
	 * @return
	 */
	BigDecimal calculateTotalAmount(Company company, int year);

	/**
	 * 统计公司回款金额
	 *
	 * @param company
	 * @return
	 */
	BigDecimal calculateTotalAmount(Company company, int year, int month);

	/**
	 * 统计项目回款金额
	 *
	 * @param project
	 * @return
	 */
	BigDecimal calculateTotalAmount(Project project);

	/**
	 * 统计项目回款金额
	 *
	 * @param project
	 * @return
	 */
	BigDecimal calculateTotalAmount(Project project, int year);

	/**
	 * 统计开票回款金额
	 *
	 * @param issueInvoice
	 * @return
	 */
	BigDecimal calculateTotalAmount(IssueInvoice issueInvoice);

	/**
	 * The total amount by specified order
	 *
	 * @param order
	 * @return
	 */
	BigDecimal calculateTotalAmount(Order order);

	/**
	 * @param contract
	 * @return
	 */
	BigDecimal calculateTotalAmount(Contract contract);

}
