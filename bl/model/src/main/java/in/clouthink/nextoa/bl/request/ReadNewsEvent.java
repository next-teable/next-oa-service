package in.clouthink.nextoa.bl.request;

import in.clouthink.nextoa.bl.model.News;
import in.clouthink.nextoa.bl.model.User;

/**
 */
public interface ReadNewsEvent {

	String EVENT_NAME = ReadNewsEvent.class.getName();

	News getNews();

	User getUser();

}
