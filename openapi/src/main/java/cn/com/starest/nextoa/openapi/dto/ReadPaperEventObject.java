package cn.com.starest.nextoa.openapi.dto;

import cn.com.starest.nextoa.model.Paper;
import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.model.dtr.ReadPaperEvent;

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
