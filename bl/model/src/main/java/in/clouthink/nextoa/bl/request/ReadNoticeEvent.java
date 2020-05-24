package in.clouthink.nextoa.bl.request;

import in.clouthink.nextoa.bl.model.Notice;
import in.clouthink.nextoa.bl.model.User;

/**
 */
public interface ReadNoticeEvent {

	String EVENT_NAME = ReadNoticeEvent.class.getName();

	Notice getNotice();

	User getUser();

}
