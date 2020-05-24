package in.clouthink.nextoa.bl.openapi.support;

import in.clouthink.nextoa.bl.model.User;
import in.clouthink.nextoa.bl.openapi.dto.*;
import in.clouthink.nextoa.shared.domain.params.PageQueryParameter;
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
