package cn.com.starest.nextoa.project.domain.request;

import cn.com.starest.nextoa.project.domain.model.InvoiceType;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dz
 */
public interface SaveIssueInvoiceRequest {

	String getCompanyId();

	InvoiceType getType();

	String getProjectId();

	String getContractId();

	String getOrderId();

	String getFirstPartyId();

	BigDecimal getAmount();

	BigDecimal getTaxRate();

	Date getHandleDate();

	String getDescription();
}
