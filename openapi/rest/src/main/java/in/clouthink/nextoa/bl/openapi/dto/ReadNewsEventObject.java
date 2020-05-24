package in.clouthink.nextoa.bl.openapi.dto;

import in.clouthink.nextoa.bl.model.News;
import in.clouthink.nextoa.bl.model.User;
import in.clouthink.nextoa.bl.request.ReadNewsEvent;

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
