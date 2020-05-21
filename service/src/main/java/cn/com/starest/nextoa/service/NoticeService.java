package cn.com.starest.nextoa.service;

import cn.com.starest.nextoa.model.Notice;
import cn.com.starest.nextoa.model.NoticeReadHistory;
import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.model.dtr.NoticeQueryRequest;
import cn.com.starest.nextoa.model.dtr.PageQueryRequest;
import cn.com.starest.nextoa.model.dtr.SaveNoticeRequest;
import org.springframework.data.domain.Page;

/**
 *
 */
public interface NoticeService {

	Page<Notice> listNotices(NoticeQueryRequest queryParameter);

	Notice findNoticeById(String id);

	void markNoticeAsRead(Notice notice, User user);

	boolean isNoticeReadByUser(Notice notice, User user);

	int countNoticeReadHistory(Notice notice);

	Notice createNotice(SaveNoticeRequest notice, User user);

	void updateNotice(String id, SaveNoticeRequest notice, User user);

	void deleteNotice(String id, User user);

	void publishNotice(String id, User user);

	void unpublishNotice(String id, User user);

	Page<NoticeReadHistory> listReadHistory(String id, PageQueryRequest queryRequest);

}
