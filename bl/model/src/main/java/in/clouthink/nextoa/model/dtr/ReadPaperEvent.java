package in.clouthink.nextoa.model.dtr;

import in.clouthink.nextoa.model.Paper;
import in.clouthink.nextoa.model.User;

/**
 */
public interface ReadPaperEvent {

	String EVENT_NAME = ReadPaperEvent.class.getName();

	Paper getPaper();

	User getUser();

}
