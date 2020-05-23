package in.clouthink.nextoa.openapi.dto;

import in.clouthink.nextoa.model.Paper;
import in.clouthink.nextoa.model.User;
import in.clouthink.nextoa.model.dtr.ReadPaperEvent;

/**
 */
public class ReadPaperEventObject implements ReadPaperEvent {

	private Paper paper;

	private User user;

	public ReadPaperEventObject(Paper paper, User user) {
		this.paper = paper;
		this.user = user;
	}

	@Override
	public Paper getPaper() {
		return paper;
	}

	@Override
	public User getUser() {
		return user;
	}
}
