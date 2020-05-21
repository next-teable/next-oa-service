package cn.com.starest.nextoa.project.web.support;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.request.SavePendingPaymentRequest;
import cn.com.starest.nextoa.project.web.dto.PendingPaymentDetail;
import cn.com.starest.nextoa.project.web.dto.PendingPaymentQueryParameter;
import cn.com.starest.nextoa.project.web.dto.PendingPaymentSummary;
import org.springframework.data.domain.Page;

/**
 * @author dz
 */
public interface PendingPaymentRestSupport {

	/**
	 * @param request
	 * @param byWho
	 * @return
	 */
	PendingPaymentSummary createPendingPayment(SavePendingPaymentRequest request, User byWho);

	/**
	 * @param id
	 * @param request
	 * @param byWho
	 * @return
	 */
	PendingPaymentSummary updatePendingPayment(String id, SavePendingPaymentRequest request, User byWho);

	/**
	 * @param id
	 * @param byWho
	 * @return
	 */
	PendingPaymentDetail findPendingPaymentById(String id, User byWho);

	/**
	 * @param request
	 * @param byWho
	 * @return
	 */
	Page<PendingPaymentSummary> listPendingPayments(PendingPaymentQueryParameter request, User byWho);

	/**
	 * @param id
	 * @param user
	 */
	void deletePendingPaymentById(String id, User user);

}
