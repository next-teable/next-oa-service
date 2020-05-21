package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.project.domain.model.ReceiveInvoice;

/**
 * @author dz
 */
public class ReceiveInvoiceDetail extends ReceiveInvoiceSummary {

	public static void convert(ReceiveInvoice fromObj, ReceiveInvoiceDetail result) {
		ReceiveInvoiceSummary.convert(fromObj, result);
	}

	public static ReceiveInvoiceDetail from(ReceiveInvoice fromObj) {
		if (fromObj == null) {
			return null;
		}
		ReceiveInvoiceDetail result = new ReceiveInvoiceDetail();
		convert(fromObj, result);
		return result;
	}

}
