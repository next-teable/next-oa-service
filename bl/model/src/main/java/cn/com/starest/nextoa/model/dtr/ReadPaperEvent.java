package cn.com.starest.nextoa.model.dtr;

import cn.com.starest.nextoa.model.Paper;
import cn.com.starest.nextoa.model.User;

/**
 */
public interface ReadPaperEvent {

	String EVENT_NAME = ReadPaperEvent.class.getName();

	Paper getPaper();

	User getUser();

}
