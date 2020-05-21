package cn.com.starest.nextoa.openapi.support.impl;

import cn.com.starest.nextoa.model.Notice;
import cn.com.starest.nextoa.model.NoticeReadHistory;
import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.dto.*;
import cn.com.starest.nextoa.openapi.support.NoticeRestSupport;
import cn.com.starest.nextoa.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 *
 */
@Service
public class NoticeRestSupportImpl implements NoticeRestSupport {

	@Autowired
	private NoticeService noticeService;

	@Override
	public Page<NoticeSummary> listNotice(NoticeQueryParameter queryRequest) {
		Page<Notice> noticePage = noticeService.listNotices(queryRequest);
		return new PageImpl<>(noticePage.getContent().stream().map(NoticeSummary::from).collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  noticePage.getTotalElements());
	}

	@Override
	public NoticeDetail getNoticeDetail(String id) {
		return NoticeDetail.from(noticeService.findNoticeById(id));
	}

	@Override
	public String createNotice(SaveNoticeParameter request, User user) {
		return noticeService.createNotice(request, user).getId();
	}

	@Override
	public void updateNotice(String id, SaveNoticeParameter request, User user) {
		noticeService.updateNotice(id, request, user);
	}

	@Override
	public void deleteNotice(String id, User user) {
		noticeService.deleteNotice(id, user);
	}

	@Override
	public void publishNotice(String id, User user) {
		noticeService.publishNotice(id, user);
	}

	@Override
	public void unpublishNotice(String id, User user) {
		noticeService.unpublishNotice(id, user);
	}

	@Override
	public Page<ReadSummary> listReadHistory(String id, PageQueryParameter queryParameter) {
		Page<NoticeReadHistory> readHistoryPage = noticeService.listReadHistory(id, queryParameter);
		return new PageImpl<>(readHistoryPage.getContent().stream().map(ReadSummary::from).collect(Collectors.toList()),
							  new PageRequest(queryParameter.getStart(), queryParameter.getLimit()),
							  readHistoryPage.getTotalElements());
	}
}
