package cn.com.starest.nextoa.project.domain.request;

import cn.com.starest.nextoa.project.domain.model.InvoiceType;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dz
 */
public interface SaveReceiveInvoiceRequest {

	InvoiceType getType();

	String getProjectId();

	String getSubContractId();

	String getSubOrderId();

	String getSubContractorId();

	BigDecimal getAmount();

	BigDecimal getTaxRate();

	Date getReceivedAt();

	String getDescription();

}
