package cn.com.starest.nextoa.project.domain.rule;

import cn.com.starest.nextoa.project.domain.model.ReceiveInvoice;

/**
 * @author dz
 */
public interface ReceiveInvoiceReference {

	boolean hasReference(ReceiveInvoice target);

}
