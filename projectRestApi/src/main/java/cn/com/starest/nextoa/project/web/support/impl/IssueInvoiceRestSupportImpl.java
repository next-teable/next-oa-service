package cn.com.starest.nextoa.project.web.support.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.IssueInvoice;
import cn.com.starest.nextoa.project.domain.request.SaveIssueInvoiceRequest;
import cn.com.starest.nextoa.project.service.IssueInvoiceService;
import cn.com.starest.nextoa.project.web.dto.IssueInvoiceDetail;
import cn.com.starest.nextoa.project.web.dto.IssueInvoiceQueryParameter;
import cn.com.starest.nextoa.project.web.dto.IssueInvoiceSummary;
import cn.com.starest.nextoa.project.web.dto.PermissionAwarePageImpl;
import cn.com.starest.nextoa.project.web.support.IssueInvoiceRestSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * @author dz
 */
@Component
public class IssueInvoiceRestSupportImpl implements IssueInvoiceRestSupport {

	@Autowired
	private IssueInvoiceService issueInvoiceService;

	@Override
	public IssueInvoiceSummary createIssueInvoice(SaveIssueInvoiceRequest request, User byWho) {
		IssueInvoice issueInvoice = issueInvoiceService.createIssueInvoice(request, byWho);
		IssueInvoiceSummary summary = IssueInvoiceSummary.from(issueInvoice);
		summary.setReceivedAmount(issueInvoiceService.calculateReceivedAmount(issueInvoice));
		summary.setGrantedActions(issueInvoiceService.resolveGrantedActions(issueInvoice, byWho));
		return summary;
	}

	@Override
	public IssueInvoiceSummary updateIssueInvoice(String id, SaveIssueInvoiceRequest request, User byWho) {
		IssueInvoice issueInvoice = issueInvoiceService.updateIssueInvoice(id, request, byWho);
		IssueInvoiceSummary summary = IssueInvoiceSummary.from(issueInvoice);
		summary.setReceivedAmount(issueInvoiceService.calculateReceivedAmount(issueInvoice));
		summary.setGrantedActions(issueInvoiceService.resolveGrantedActions(issueInvoice, byWho));
		return summary;
	}

	@Override
	public IssueInvoiceDetail findIssueInvoiceById(String id, User byWho) {
		IssueInvoice issueInvoice = issueInvoiceService.findIssueInvoiceById(id, byWho);
		IssueInvoiceDetail summary = IssueInvoiceDetail.from(issueInvoice);
		summary.setReceivedAmount(issueInvoiceService.calculateReceivedAmount(issueInvoice));
		summary.setGrantedActions(issueInvoiceService.resolveGrantedActions(issueInvoice, byWho));
		return summary;
	}

	@Override
	public Page<IssueInvoiceSummary> listIssueInvoices(IssueInvoiceQueryParameter request, User byWho) {
		Page<IssueInvoice> issueInvoicePage = issueInvoiceService.listIssueInvoices(request, byWho);
		return new PermissionAwarePageImpl<>(issueInvoicePage.getContent().stream().map(item -> {
			IssueInvoiceSummary summary = IssueInvoiceSummary.from(item);
			summary.setReceivedAmount(issueInvoiceService.calculateReceivedAmount(item));
			summary.setGrantedActions(issueInvoiceService.resolveGrantedActions(item, byWho));
			return summary;
		}).collect(Collectors.toList()),
											 new PageRequest(request.getStart(), request.getLimit()),
											 issueInvoicePage.getTotalElements(),
											 issueInvoiceService.resolveGrantedActions(byWho));
	}

	@Override
	public void deleteIssueInvoiceById(String id, User user) {
		issueInvoiceService.deleteIssueInvoiceById(id, user);
	}

}
