package cn.com.starest.nextoa.project.web.dto;

import cn.com.starest.nextoa.openapi.dto.AttachmentSummary;
import cn.com.starest.nextoa.project.domain.model.AbstractSubOrder;
import cn.com.starest.nextoa.project.domain.model.SubOrder;

import java.util.List;

/**
 * @author dz
 */
public class SubOrderDetail extends SubOrderSummary {

	public static void convert(SubOrder fromObj, SubOrderDetail result) {
		SubOrderSummary.convert((AbstractSubOrder) fromObj, result);
	}

	public static SubOrderDetail from(SubOrder fromObj) {
		if (fromObj == null) {
			return null;
		}
		SubOrderDetail result = new SubOrderDetail();
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
