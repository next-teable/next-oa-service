package cn.com.starest.nextoa.model.dtr;

import cn.com.starest.nextoa.model.News;
import cn.com.starest.nextoa.model.User;

/**
 */
public interface ReadNewsEvent {

	String EVENT_NAME = ReadNewsEvent.class.getName();

	News getNews();

	User getUser();

}
