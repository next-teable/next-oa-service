package cn.com.starest.nextoa.project.domain.rule;

import cn.com.starest.nextoa.project.domain.model.IssueInvoice;

/**
 * @author dz
 */
public interface IssueInvoiceReference {

	boolean hasReference(IssueInvoice target);

}
