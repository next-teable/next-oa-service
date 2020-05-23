package cn.com.starest.nextoa.openapi.dto;

import cn.com.starest.nextoa.model.Notice;
import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.model.dtr.ReadNoticeEvent;

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
