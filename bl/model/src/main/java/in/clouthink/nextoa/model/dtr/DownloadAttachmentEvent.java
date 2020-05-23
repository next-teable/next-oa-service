package in.clouthink.nextoa.model.dtr;

import in.clouthink.nextoa.model.Attachment;
import in.clouthink.nextoa.model.User;

/**
 */
public interface DownloadAttachmentEvent {

	String EVENT_NAME = DownloadAttachmentEvent.class.getName();

	Attachment getAttachment();

	User getUser();

}
