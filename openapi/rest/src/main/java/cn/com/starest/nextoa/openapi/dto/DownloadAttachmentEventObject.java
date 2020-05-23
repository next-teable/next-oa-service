package cn.com.starest.nextoa.openapi.dto;

import cn.com.starest.nextoa.model.Attachment;
import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.model.dtr.DownloadAttachmentEvent;

/**
 */
public class DownloadAttachmentEventObject implements DownloadAttachmentEvent {

	private Attachment attachment;

	private User user;

	public DownloadAttachmentEventObject(Attachment attachment, User user) {
		this.attachment = attachment;
		this.user = user;
	}

	@Override
	public Attachment getAttachment() {
		return attachment;
	}

	@Override
	public User getUser() {
		return user;
	}

}
