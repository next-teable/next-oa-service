package in.clouthink.nextoa.openapi.dto;

import in.clouthink.nextoa.model.Attachment;
import in.clouthink.nextoa.model.User;
import in.clouthink.nextoa.model.dtr.DownloadAttachmentEvent;

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
