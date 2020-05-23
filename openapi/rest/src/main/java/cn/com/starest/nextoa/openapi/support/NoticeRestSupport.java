package cn.com.starest.nextoa.openapi.support;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.dto.*;
import org.springframework.data.domain.Page;

/**
 *
 */
public interface NoticeRestSupport {

	Page<NoticeSummary> listNotice(NoticeQueryParameter queryRequest);

	NoticeDetail getNoticeDetail(String id);

	String createNotice(SaveNoticeParameter request, User user);

	void updateNotice(String id, SaveNoticeParameter request, User user);

	void deleteNotice(String id, User user);

	void publishNotice(String id, User user);

	void unpublishNotice(String id, User user);

	Page<ReadSummary> listReadHistory(String id, PageQueryParameter queryParameter);

}
