package cn.com.starest.nextoa.model.dtr;

import cn.com.starest.nextoa.model.Attachment;
import cn.com.starest.nextoa.model.User;

/**
 */
public interface DownloadAttachmentEvent {

	String EVENT_NAME = DownloadAttachmentEvent.class.getName();

	Attachment getAttachment();

	User getUser();

}
