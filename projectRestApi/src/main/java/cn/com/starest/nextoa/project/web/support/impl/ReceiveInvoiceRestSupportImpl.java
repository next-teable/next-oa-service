package cn.com.starest.nextoa.project.web.support.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.ReceiveInvoice;
import cn.com.starest.nextoa.project.domain.request.SaveReceiveInvoiceRequest;
import cn.com.starest.nextoa.project.service.ReceiveInvoiceService;
import cn.com.starest.nextoa.project.web.dto.PermissionAwarePageImpl;
import cn.com.starest.nextoa.project.web.dto.ReceiveInvoiceDetail;
import cn.com.starest.nextoa.project.web.dto.ReceiveInvoiceQueryParameter;
import cn.com.starest.nextoa.project.web.dto.ReceiveInvoiceSummary;
import cn.com.starest.nextoa.project.web.support.ReceiveInvoiceRestSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * @author dz
 */
@Component
public class ReceiveInvoiceRestSupportImpl implements ReceiveInvoiceRestSupport {

	@Autowired
	private ReceiveInvoiceService receiveInvoiceService;

	@Override
	public ReceiveInvoiceSummary createReceiveInvoice(SaveReceiveInvoiceRequest request, User byWho) {
		ReceiveInvoice receiveInvoice = receiveInvoiceService.createReceiveInvoice(request, byWho);
		ReceiveInvoiceSummary summary = ReceiveInvoiceSummary.from(receiveInvoice);
		summary.setPaymentAmount(receiveInvoiceService.calculatePaymentAmount(receiveInvoice));
		summary.setGrantedActions(receiveInvoiceService.resolveGrantedActions(receiveInvoice, byWho));
		return summary;
	}

	@Override
	public ReceiveInvoiceSummary updateReceiveInvoice(String id, SaveReceiveInvoiceRequest request, User byWho) {
		ReceiveInvoice receiveInvoice = receiveInvoiceService.updateReceiveInvoice(id, request, byWho);
		ReceiveInvoiceSummary summary = ReceiveInvoiceSummary.from(receiveInvoice);
		summary.setPaymentAmount(receiveInvoiceService.calculatePaymentAmount(receiveInvoice));
		summary.setGrantedActions(receiveInvoiceService.resolveGrantedActions(receiveInvoice, byWho));
		return summary;
	}

	@Override
	public ReceiveInvoiceDetail findReceiveInvoiceById(String id, User byWho) {
		ReceiveInvoice receiveInvoice = receiveInvoiceService.findReceiveInvoiceById(id, byWho);
		ReceiveInvoiceDetail summary = ReceiveInvoiceDetail.from(receiveInvoice);
		summary.setPaymentAmount(receiveInvoiceService.calculatePaymentAmount(receiveInvoice));
		summary.setGrantedActions(receiveInvoiceService.resolveGrantedActions(receiveInvoice, byWho));
		return summary;
	}

	@Override
	public Page<ReceiveInvoiceSummary> listReceiveInvoices(ReceiveInvoiceQueryParameter request, User byWho) {
		Page<ReceiveInvoice> receiveInvoicePage = receiveInvoiceService.listReceiveInvoices(request, byWho);
		return new PermissionAwarePageImpl<>(receiveInvoicePage.getContent().stream().map(item -> {
			ReceiveInvoiceSummary summary = ReceiveInvoiceSummary.from(item);
			summary.setPaymentAmount(receiveInvoiceService.calculatePaymentAmount(item));
			summary.setGrantedActions(receiveInvoiceService.resolveGrantedActions(item, byWho));
			return summary;
		}).collect(Collectors.toList()),
											 new PageRequest(request.getStart(), request.getLimit()),
											 receiveInvoicePage.getTotalElements(),
											 receiveInvoiceService.resolveGrantedActions(byWho));
	}

	@Override
	public void deleteReceiveInvoiceById(String id, User user) {
		receiveInvoiceService.deleteReceiveInvoiceById(id, user);
	}

}
