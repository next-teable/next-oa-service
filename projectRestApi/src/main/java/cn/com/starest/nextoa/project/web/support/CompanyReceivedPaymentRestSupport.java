package cn.com.starest.nextoa.project.web.support;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.request.SaveCompanyReceivedPaymentRequest;
import cn.com.starest.nextoa.project.web.dto.CompanyReceivedPaymentDetail;
import cn.com.starest.nextoa.project.web.dto.CompanyReceivedPaymentQueryParameter;
import cn.com.starest.nextoa.project.web.dto.CompanyReceivedPaymentSummary;
import org.springframework.data.domain.Page;

/**
 * @author dz
 */
public interface CompanyReceivedPaymentRestSupport {

	/**
	 * @param request
	 * @param byWho
	 * @return
	 */
	CompanyReceivedPaymentSummary createCompanyReceivedPayment(SaveCompanyReceivedPaymentRequest request, User byWho);

	/**
	 * @param id
	 * @param request
	 * @param byWho
	 * @return
	 */
	CompanyReceivedPaymentSummary updateCompanyReceivedPayment(String id,
															   SaveCompanyReceivedPaymentRequest request,
															   User byWho);

	/**
	 * @param id
	 * @param byWho
	 * @return
	 */
	CompanyReceivedPaymentDetail findCompanyReceivedPaymentById(String id, User byWho);

	/**
	 * @param request
	 * @param byWho
	 * @return
	 */
	Page<CompanyReceivedPaymentSummary> listCompanyReceivedPayments(CompanyReceivedPaymentQueryParameter request,
																	User byWho);

	/**
	 * @param id
	 * @param user
	 */
	void deleteCompanyReceivedPaymentById(String id, User user);

}
