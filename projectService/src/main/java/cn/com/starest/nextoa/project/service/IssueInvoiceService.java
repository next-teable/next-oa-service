package cn.com.starest.nextoa.project.service;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.IssueInvoice;
import cn.com.starest.nextoa.project.domain.model.ModuleActions;
import cn.com.starest.nextoa.project.domain.request.IssueInvoiceQueryRequest;
import cn.com.starest.nextoa.project.domain.request.SaveIssueInvoiceRequest;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author dz
 */
public interface IssueInvoiceService {

	ModuleActions.ModuleAction[] resolveGrantedActions(User byWho);

	ModuleActions.ModuleAction[] resolveGrantedActions(IssueInvoice issueInvoice, User byWho);

	IssueInvoice createIssueInvoice(SaveIssueInvoiceRequest request, User byWho);

	IssueInvoice updateIssueInvoice(String id, SaveIssueInvoiceRequest request, User byWho);

	IssueInvoice findIssueInvoiceById(String id, User byWho);

	Page<IssueInvoice> listIssueInvoices(IssueInvoiceQueryRequest request, User byWho);

	void deleteIssueInvoiceById(String id, User byWho);

	List<IssueInvoice> listIssueInvoicesByProject(String id, User user);

	/**
	 * @param issueInvoice
	 * @return
	 */
	BigDecimal calculateReceivedAmount(IssueInvoice issueInvoice);
}
