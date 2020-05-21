package cn.com.starest.nextoa.project.service;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.ModuleActions;
import cn.com.starest.nextoa.project.domain.model.Payment;
import cn.com.starest.nextoa.project.domain.model.Reimburse;
import cn.com.starest.nextoa.project.domain.request.PaymentQueryRequest;
import cn.com.starest.nextoa.project.domain.request.SavePaymentRequest;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author dz
 */
public interface PaymentService {

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

	/**
	 * 人工输入
	 *
	 * @param request
	 * @param byWho
	 * @return
	 */
	Payment createPayment(SavePaymentRequest request, User byWho);

	/**
	 * 通过报销创建
	 *
	 * @param reimburse
	 * @param byWho
	 * @return
	 */
	Payment createPayment(Reimburse reimburse, User byWho);

	Payment updatePayment(String id, SavePaymentRequest request, User byWho);

	Payment findPaymentById(String id, User byWho);

	void deletePaymentById(String id, User byWho);

	Page<Payment> listPayments(PaymentQueryRequest request, User byWho);

	List<Payment> listPayments(String id, String userId);
}
