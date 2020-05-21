package cn.com.starest.nextoa.openapi.support;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.dto.*;
import org.springframework.data.domain.Page;

/**
 *
 */
public interface PortalRestSupport {

	/**
	 *
	 * @param queryRequest
	 * @param user
	 * @return
	 */
	Page<NewsSummary> listNews(NewsQueryParameter queryRequest, User user);

	/**
	 *
	 * @param queryRequest
	 * @param user
	 * @return
	 */
	Page<NewsSummary> listNormalNews(NewsQueryParameter queryRequest, User user);

	/**
	 *
	 * @param queryRequest
	 * @param user
	 * @return
	 */
	Page<ImageNewsSummary> listImageNews(NewsQueryParameter queryRequest, User user);

	/**
	 *
	 * @param id
	 * @param user
	 * @return
	 */
	NewsDetail getNewsDetail(String id, User user);

	/**
	 *
	 * @param queryRequest
	 * @param user
	 * @return
	 */
	Page<NoticeSummary> listNotices(NoticeQueryParameter queryRequest, User user);

	/**
	 *
	 * @param id
	 * @param user
	 * @return
	 */
	NoticeDetail getNoticeDetail(String id, User user);

	/**
	 *
	 * @param queryRequest
	 * @param user
	 * @return
	 */
	Page<AttachmentSummary> listAttachments(AttachmentQueryParameter queryRequest, User user);

	/**
	 *
	 * @param id
	 * @param user
	 * @return
	 */
	AttachmentSummary getAttachmentDetail(String id, User user);

	/**
	 *
	 * @param queryRequest
	 * @param user
	 * @return
	 */
	Page<MessageSummary> listPendingMessages(PendingMessageQueryParameter queryRequest, User user);

}
