package cn.com.starest.nextoa.project.domain.rule;

import cn.com.starest.nextoa.project.domain.model.IssueInvoice;

/**
 * The observer for IssueInvoice
 *
 * @author dz
 */
public interface IssueInvoiceObserver {

	/**
	 * @param issueInvoice
	 */
	void onChange(IssueInvoice issueInvoice);

}
