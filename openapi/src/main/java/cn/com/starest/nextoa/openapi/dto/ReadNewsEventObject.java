package cn.com.starest.nextoa.openapi.dto;

import cn.com.starest.nextoa.model.News;
import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.model.dtr.ReadNewsEvent;

/**
 */
public class ReadNewsEventObject implements ReadNewsEvent {

	private News news;

	private User user;

	public ReadNewsEventObject(News news, User user) {
		this.news = news;
		this.user = user;
	}

	@Override
	public News getNews() {
		return news;
	}

	@Override
	public User getUser() {
		return user;
	}
}
