package cn.com.starest.nextoa.model.dtr;

import cn.com.starest.nextoa.model.Notice;
import cn.com.starest.nextoa.model.User;

/**
 */
public interface ReadNoticeEvent {

	String EVENT_NAME = ReadNoticeEvent.class.getName();

	Notice getNotice();

	User getUser();

}
