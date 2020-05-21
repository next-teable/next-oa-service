package cn.com.starest.nextoa.project.service;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.ModuleActions;
import cn.com.starest.nextoa.project.domain.model.PendingPayment;
import cn.com.starest.nextoa.project.domain.model.PendingPaymentAggregation;
import cn.com.starest.nextoa.project.domain.request.PendingPaymentQueryRequest;
import cn.com.starest.nextoa.project.domain.request.SavePendingPaymentRequest;
import org.springframework.data.domain.Page;

/**
 * @author dz
 */
public interface PendingPaymentService {

	ModuleActions.ModuleAction[] resolveGrantedActions(User byWho);

	ModuleActions.ModuleAction[] resolveGrantedActions(PendingPayment item, User byWho);

	PendingPayment createPendingPayment(SavePendingPaymentRequest request, User byWho);

	PendingPayment updatePendingPayment(String id, SavePendingPaymentRequest request, User byWho);

	PendingPayment findPendingPaymentById(String id, User byWho);

	void deletePendingPaymentById(String id, User byWho);

	Page<PendingPayment> listPendingPayments(PendingPaymentQueryRequest request, User byWho);

	PendingPaymentAggregation aggregatePendingPayments(PendingPaymentQueryRequest request, User byWho);
}
