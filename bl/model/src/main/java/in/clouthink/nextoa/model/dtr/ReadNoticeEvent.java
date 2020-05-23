package in.clouthink.nextoa.model.dtr;

import in.clouthink.nextoa.model.Notice;
import in.clouthink.nextoa.model.User;

/**
 */
public interface ReadNoticeEvent {

	String EVENT_NAME = ReadNoticeEvent.class.getName();

	Notice getNotice();

	User getUser();

}
