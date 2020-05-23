package in.clouthink.nextoa.openapi.dto;

import in.clouthink.nextoa.model.Notice;
import in.clouthink.nextoa.model.User;
import in.clouthink.nextoa.model.dtr.ReadNoticeEvent;

/**
 */
public class ReadNoticeEventObject implements ReadNoticeEvent {

	private Notice notice;

	private User user;

	public ReadNoticeEventObject(Notice notice, User user) {
		this.notice = notice;
		this.user = user;
	}

	@Override
	public Notice getNotice() {
		return notice;
	}

	@Override
	public User getUser() {
		return user;
	}
}
