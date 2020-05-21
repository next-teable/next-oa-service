package cn.com.starest.nextoa.project.repository.custom;

import cn.com.starest.nextoa.project.domain.model.Company;
import cn.com.starest.nextoa.project.domain.model.CompanyReceivedPayment;
import cn.com.starest.nextoa.project.domain.request.CompanyReceivedPaymentQueryRequest;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

/**
 * @author dz
 */
public interface CompanyReceivedPaymentRepositoryCustom {

	/**
	 * @param request
	 * @return
	 */
	Page<CompanyReceivedPayment> queryPage(CompanyReceivedPaymentQueryRequest request);

	/**
	 * 按年统计公司回款金额
	 *
	 * @param company
	 * @param year
	 * @return
	 */
	BigDecimal calculateTotalAmount(Company company, int year);

	/**
	 * 按月统计公司回款金额
	 *
	 * @param company
	 * @param year
	 * @param month
	 * @return
	 */
	BigDecimal calculateTotalAmount(Company company, int year, int month);

}
