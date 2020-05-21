package cn.com.starest.nextoa.project.service;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.CompanyReceivedPayment;
import cn.com.starest.nextoa.project.domain.model.ModuleActions;
import cn.com.starest.nextoa.project.domain.request.CompanyReceivedPaymentQueryRequest;
import cn.com.starest.nextoa.project.domain.request.SaveCompanyReceivedPaymentRequest;
import org.springframework.data.domain.Page;

/**
 * @author dz
 */
public interface CompanyReceivedPaymentService {

	ModuleActions.ModuleAction[] resolveGrantedActions(User byWho);

	ModuleActions.ModuleAction[] resolveGrantedActions(CompanyReceivedPayment item, User byWho);

	CompanyReceivedPayment createCompanyReceivedPayment(SaveCompanyReceivedPaymentRequest request, User byWho);

	CompanyReceivedPayment updateCompanyReceivedPayment(String id,
														SaveCompanyReceivedPaymentRequest request,
														User byWho);

	CompanyReceivedPayment findCompanyReceivedPaymentById(String id, User byWho);

	void deleteCompanyReceivedPaymentById(String id, User byWho);

	Page<CompanyReceivedPayment> listCompanyReceivedPayments(CompanyReceivedPaymentQueryRequest request, User byWho);

}
