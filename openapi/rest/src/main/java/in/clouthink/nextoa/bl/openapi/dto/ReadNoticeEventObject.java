package in.clouthink.nextoa.bl.openapi.dto;

import in.clouthink.nextoa.bl.model.Notice;
import in.clouthink.nextoa.bl.model.User;
import in.clouthink.nextoa.bl.request.ReadNoticeEvent;

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
