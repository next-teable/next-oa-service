package in.clouthink.nextoa.bl.request;

import in.clouthink.nextoa.bl.model.Paper;
import in.clouthink.nextoa.bl.model.User;

/**
 */
public interface ReadPaperEvent {

	String EVENT_NAME = ReadPaperEvent.class.getName();

	Paper getPaper();

	User getUser();

}
