package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.openapi.dto.AttachmentSummary;
import cn.com.starest.nextoa.project.domain.model.AbstractOrder;
import cn.com.starest.nextoa.project.domain.model.Order;

import java.util.List;

/**
 * @author dz
 */
public class OrderDetail extends OrderSummary {

	public static void convert(Order fromObj, OrderSummary result) {
		OrderSummary.convert((AbstractOrder) fromObj, result);
	}

	public static OrderDetail from(Order fromObj) {
		if (fromObj == null) {
			return null;
		}
		OrderDetail result = new OrderDetail();
		convert(fromObj, result);
		return result;
	}

	private List<AttachmentSummary> attachments;

	public List<AttachmentSummary> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<AttachmentSummary> attachments) {
		this.attachments = attachments;
	}

}
