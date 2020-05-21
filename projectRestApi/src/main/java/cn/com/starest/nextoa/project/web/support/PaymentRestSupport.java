package cn.com.starest.nextoa.project.web.support;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.request.SavePaymentRequest;
import cn.com.starest.nextoa.project.web.dto.PaymentDetail;
import cn.com.starest.nextoa.project.web.dto.PaymentQueryParameter;
import cn.com.starest.nextoa.project.web.dto.PaymentSummary;
import org.springframework.data.domain.Page;

/**
 * @author dz
 */
public interface PaymentRestSupport {

	/**
	 * @param request
	 * @param byWho
	 * @return
	 */
	PaymentSummary createPayment(SavePaymentRequest request, User byWho);

	/**
	 * @param id
	 * @param request
	 * @param byWho
	 * @return
	 */
	PaymentSummary updatePayment(String id, SavePaymentRequest request, User byWho);

	/**
	 * @param id
	 * @param byWho
	 * @return
	 */
	PaymentDetail findPaymentById(String id, User byWho);

	/**
	 * @param request
	 * @param byWho
	 * @return
	 */
	Page<PaymentSummary> listPayments(PaymentQueryParameter request, User byWho);

	/**
	 * @param id
	 * @param user
	 */
	void deletePaymentById(String id, User user);

}
