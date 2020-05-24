package in.clouthink.nextoa.bl.request;

import in.clouthink.nextoa.bl.model.Attachment;
import in.clouthink.nextoa.bl.model.User;

/**
 */
public interface DownloadAttachmentEvent {

	String EVENT_NAME = DownloadAttachmentEvent.class.getName();

	Attachment getAttachment();

	User getUser();

}
