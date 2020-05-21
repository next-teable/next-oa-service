package cn.com.starest.nextoa.project.service;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.ModuleActions;
import cn.com.starest.nextoa.project.domain.model.ReceiveInvoice;
import cn.com.starest.nextoa.project.domain.request.ReceiveInvoiceQueryRequest;
import cn.com.starest.nextoa.project.domain.request.SaveReceiveInvoiceRequest;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author dz
 */
public interface ReceiveInvoiceService {

	ModuleActions.ModuleAction[] resolveGrantedActions(User byWho);

	ModuleActions.ModuleAction[] resolveGrantedActions(ReceiveInvoice receiveInvoice, User byWho);

	ReceiveInvoice createReceiveInvoice(SaveReceiveInvoiceRequest request, User byWho);

	ReceiveInvoice updateReceiveInvoice(String id, SaveReceiveInvoiceRequest request, User byWho);

	ReceiveInvoice findReceiveInvoiceById(String id, User byWho);

	Page<ReceiveInvoice> listReceiveInvoices(ReceiveInvoiceQueryRequest request, User byWho);

	void deleteReceiveInvoiceById(String id, User byWho);

	List<ReceiveInvoice> listReceiveInvoicesByProject(String id, User user);

	BigDecimal calculatePaymentAmount(ReceiveInvoice receiveInvoice);
}
