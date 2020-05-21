package cn.com.starest.nextoa.project.web.support;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.request.SaveReceiveInvoiceRequest;
import cn.com.starest.nextoa.project.web.dto.ReceiveInvoiceDetail;
import cn.com.starest.nextoa.project.web.dto.ReceiveInvoiceQueryParameter;
import cn.com.starest.nextoa.project.web.dto.ReceiveInvoiceSummary;
import org.springframework.data.domain.Page;

/**
 * @author dz
 */
public interface ReceiveInvoiceRestSupport {

	ReceiveInvoiceSummary createReceiveInvoice(SaveReceiveInvoiceRequest request, User byWho);

	ReceiveInvoiceSummary updateReceiveInvoice(String id, SaveReceiveInvoiceRequest request, User byWho);

	ReceiveInvoiceDetail findReceiveInvoiceById(String id, User byWho);

	Page<ReceiveInvoiceSummary> listReceiveInvoices(ReceiveInvoiceQueryParameter request, User byWho);

	void deleteReceiveInvoiceById(String id, User user);

}
