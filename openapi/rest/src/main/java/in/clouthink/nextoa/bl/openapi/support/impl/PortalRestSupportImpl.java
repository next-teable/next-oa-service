package in.clouthink.nextoa.bl.openapi.support.impl;

import in.clouthink.daas.edm.Edms;
import in.clouthink.nextoa.bl.model.*;
import in.clouthink.nextoa.bl.openapi.dto.*;
import in.clouthink.nextoa.bl.openapi.support.PortalRestSupport;
import in.clouthink.nextoa.bl.request.MessageQueryRequest;
import in.clouthink.nextoa.bl.request.ReadNewsEvent;
import in.clouthink.nextoa.bl.request.ReadNoticeEvent;
import in.clouthink.nextoa.bl.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.stream.Collectors;

/**
 *
 */
@Service
public class PortalRestSupportImpl implements PortalRestSupport {

	@Autowired
	private NewsService newsService;

	@Autowired
	private NoticeService noticeService;

	@Autowired
	private AttachmentService attachmentService;

	@Autowired
	private MessageService messageService;

	@Autowired
	private PaperService paperService;

	@Override
	public Page<NewsSummary> listNews(NewsQueryParameter queryRequest, User user) {
		if (user.isRestricted()) {
			return new PageImpl<>(Collections.emptyList(),
								  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
								  0);
		}
		queryRequest.setPublished(true);
		queryRequest.setNewsType(null);
		Page<News> newsPage = newsService.listNews(queryRequest);
		return new PageImpl<>(newsPage.getContent()
									  .stream()
									  .map(news -> NewsSummary.from(news, newsService.isNewsReadByUser(news, user)))
									  .collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  newsPage.getTotalElements());
	}

	@Override
	public Page<NewsSummary> listNormalNews(NewsQueryParameter queryRequest, User user) {
		if (user.isRestricted()) {
			return new PageImpl<>(Collections.emptyList(),
								  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
								  0);
		}
		queryRequest.setPublished(true);
		queryRequest.setNewsType(News.NewsType.NORMAL);
		Page<News> newsPage = newsService.listNews(queryRequest);
		return new PageImpl<>(newsPage.getContent()
									  .stream()
									  .map(news -> NewsSummary.from(news, newsService.isNewsReadByUser(news, user)))
									  .collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  newsPage.getTotalElements());
	}

	@Override
	public Page<ImageNewsSummary> listImageNews(NewsQueryParameter queryRequest, User user) {
		if (user.isRestricted()) {
			return new PageImpl<>(Collections.emptyList(),
								  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
								  0);
		}
		queryRequest.setPublished(true);
		queryRequest.setNewsType(News.NewsType.IMAGE);
		Page<News> newsPage = newsService.listNews(queryRequest);
		return new PageImpl<>(newsPage.getContent()
									  .stream()
									  .map(news -> ImageNewsSummary.from(news, newsService.getImageFileObjectId(news)))
									  .collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  newsPage.getTotalElements());
	}

	@Override
	public NewsDetail getNewsDetail(String id, User user) {
		if (user.isRestricted()) {
			return null;
		}
		News news = newsService.findNewsById(id);
		Edms.getEdm().dispatch(ReadNewsEvent.EVENT_NAME, new ReadNewsEventObject(news, user));
		return NewsDetail.from(news, newsService.countNewsReadHistory(news));
	}

	@Override
	public Page<NoticeSummary> listNotices(NoticeQueryParameter queryRequest, User user) {
		if (user.isRestricted()) {
			return new PageImpl<>(Collections.emptyList(),
								  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
								  0);
		}
		queryRequest.setPublished(true);
		Page<Notice> noticePage = noticeService.listNotices(queryRequest);
		return new PageImpl<>(noticePage.getContent()
										.stream()
										.map(notice -> NoticeSummary.from(notice,
																		  noticeService.isNoticeReadByUser(notice,
																										   user)))
										.collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  noticePage.getTotalElements());
	}

	@Override
	public NoticeDetail getNoticeDetail(String id, User user) {
		if (user.isRestricted()) {
			return null;
		}
		Notice notice = noticeService.findNoticeById(id);
		Edms.getEdm().dispatch(ReadNoticeEvent.EVENT_NAME, new ReadNoticeEventObject(notice, user));
		return NoticeDetail.from(notice, noticeService.countNoticeReadHistory(notice));
	}

	@Override
	public Page<AttachmentSummary> listAttachments(AttachmentQueryParameter queryRequest, User user) {
		if (user.isRestricted()) {
			return new PageImpl<>(Collections.emptyList(),
								  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
								  0);
		}
		queryRequest.setPublished(true);
		Page<Attachment> attachmentPage = attachmentService.listAttachments(queryRequest);
		return new PageImpl<>(attachmentPage.getContent()
											.stream()
											.map(attachment -> AttachmentSummary.from(attachment,
																					  attachmentService.isAttachmentDownloadedByUser(
																							  attachment,
																							  user)))
											.collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  attachmentPage.getTotalElements());
	}

	@Override
	public AttachmentSummary getAttachmentDetail(String id, User user) {
		if (user.isRestricted()) {
			return null;
		}
		Attachment attachment = attachmentService.findAttachmentById(id);
		return AttachmentSummary.from(attachment, attachmentService.isAttachmentDownloadedByUser(attachment, user));
	}

	@Override
	public Page<MessageSummary> listPendingMessages(PendingMessageQueryParameter queryRequest, User user) {
		queryRequest.setMessageStatus(MessageStatus.PENDING);
		Page<Message> messagePage = messageService.listMessages(queryRequest,
																MessageQueryRequest.IncludeOrExcludeStatus.INCLUDE,
																user);
		return new PageImpl<>(messagePage.getContent().stream().map(message -> {
			Paper paper = paperService.findPaperById(message.getBizRefId());
			MessageSummary result = MessageSummary.from(message, paper);
			result.setFavorite(messageService.isFavorite(message, user));
			return result;
		}).collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  messagePage.getTotalElements());
	}

}
