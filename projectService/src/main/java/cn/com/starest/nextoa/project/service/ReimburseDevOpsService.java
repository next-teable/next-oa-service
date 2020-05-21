package cn.com.starest.nextoa.project.service;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.ModuleActions;
import cn.com.starest.nextoa.project.domain.model.Payment;
import cn.com.starest.nextoa.project.domain.request.PaymentQueryRequest;
import cn.com.starest.nextoa.project.domain.request.SavePaymentRequest;
import org.springframework.data.domain.Page;

/**
 * @author dz
 */
public interface ReimburseDevOpsService {

	/**
	 * @param byWho
	 * @return
	 */
	ModuleActions.ModuleAction[] resolveGrantedActions(User byWho);

	/**
	 * @param item
	 * @param byWho
	 * @return
	 */
	ModuleActions.ModuleAction[] resolveGrantedActions(Payment item, User byWho);

	Payment updatePayment(String id, SavePaymentRequest request, User byWho);

	Payment findPaymentById(String id, User byWho);

	Page<Payment> listPayments(PaymentQueryRequest request, User byWho);

}
