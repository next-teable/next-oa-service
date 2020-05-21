package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.project.domain.model.IssueInvoice;

/**
 * @author dz
 */
public class IssueInvoiceDetail extends IssueInvoiceSummary {

	public static void convert(IssueInvoice fromObj, IssueInvoiceDetail result) {
		IssueInvoiceSummary.convert(fromObj, result);
	}

	public static IssueInvoiceDetail from(IssueInvoice fromObj) {
		if (fromObj == null) {
			return null;
		}
		IssueInvoiceDetail result = new IssueInvoiceDetail();
		convert(fromObj, result);
		return result;
	}

}
