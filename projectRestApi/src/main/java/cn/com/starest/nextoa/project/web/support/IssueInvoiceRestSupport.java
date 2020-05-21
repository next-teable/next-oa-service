package cn.com.starest.nextoa.project.web.support;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.request.SaveIssueInvoiceRequest;
import cn.com.starest.nextoa.project.web.dto.IssueInvoiceDetail;
import cn.com.starest.nextoa.project.web.dto.IssueInvoiceQueryParameter;
import cn.com.starest.nextoa.project.web.dto.IssueInvoiceSummary;
import org.springframework.data.domain.Page;

/**
 * @author dz
 */
public interface IssueInvoiceRestSupport {

	IssueInvoiceSummary createIssueInvoice(SaveIssueInvoiceRequest request, User byWho);

	IssueInvoiceSummary updateIssueInvoice(String id, SaveIssueInvoiceRequest request, User byWho);

	IssueInvoiceDetail findIssueInvoiceById(String id, User byWho);

	Page<IssueInvoiceSummary> listIssueInvoices(IssueInvoiceQueryParameter request, User byWho);

	void deleteIssueInvoiceById(String id, User user);

}
