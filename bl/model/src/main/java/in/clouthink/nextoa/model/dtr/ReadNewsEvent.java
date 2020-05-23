package in.clouthink.nextoa.model.dtr;

import in.clouthink.nextoa.model.News;
import in.clouthink.nextoa.model.User;

/**
 */
public interface ReadNewsEvent {

	String EVENT_NAME = ReadNewsEvent.class.getName();

	News getNews();

	User getUser();

}
