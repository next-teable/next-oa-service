package in.clouthink.nextoa.openapi.controller;

import in.clouthink.nextoa.model.User;
import cn.com.starest.nextoa.openapi.dto.*;
import in.clouthink.nextoa.openapi.dto.*;
import in.clouthink.nextoa.openapi.security.SecurityContexts;
import in.clouthink.nextoa.openapi.support.PortalRestSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 */
@Api("我的门户")
@RestController
@RequestMapping("/api/portal")
public class PortalRestController {

	@Autowired
	private PortalRestSupport portalRestSupport;

	@ApiOperation(value = "所有新闻列表,支持分页,支持动态查询（标题,分类,日期等）,按新闻发布的时间逆序排列")
	@RequestMapping(value = "/news", method = RequestMethod.GET)
	public Page<NewsSummary> listNewsSummaryPage(NewsQueryParameter queryRequest) {
		User user = SecurityContexts.getContext().requireUser();
		return portalRestSupport.listNews(queryRequest, user);
	}

	@ApiOperation(value = "普通新闻列表（不含图片）,支持分页,支持动态查询（标题,分类,日期等）,按新闻发布的时间逆序排列")
	@RequestMapping(value = "/normalNews", method = RequestMethod.GET)
	public Page<NewsSummary> listNormalNewsSummaryPage(NewsQueryParameter queryRequest) {
		User user = SecurityContexts.getContext().requireUser();
		return portalRestSupport.listNormalNews(queryRequest, user);
	}

	@ApiOperation(value = "图片新闻列表,支持分页,支持动态查询（标题,分类,日期等）,按新闻发布的时间逆序排列")
	@RequestMapping(value = "/imageNews", method = RequestMethod.GET)
	public Page<ImageNewsSummary> listImageNewsSummaryPage(NewsQueryParameter queryRequest) {
		User user = SecurityContexts.getContext().requireUser();
		return portalRestSupport.listImageNews(queryRequest, user);
	}

	@ApiOperation(value = "查看新闻详情")
	@RequestMapping(value = "/news/{id}", method = RequestMethod.GET)
	public NewsDetail getNewsDetail(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		return portalRestSupport.getNewsDetail(id, user);
	}

	@ApiOperation(value = "通知列表,支持分页,支持动态查询（标题,分类,日期等）,按通知发布的时间逆序排列")
	@RequestMapping(value = "/notices", method = RequestMethod.GET)
	public Page<NoticeSummary> listNoticeSummaryPage(NoticeQueryParameter queryRequest) {
		User user = SecurityContexts.getContext().requireUser();
		return portalRestSupport.listNotices(queryRequest, user);
	}

	@ApiOperation(value = "查看通知详情")
	@RequestMapping(value = "/notices/{id}", method = RequestMethod.GET)
	public NoticeDetail getNoticeDetail(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		return portalRestSupport.getNoticeDetail(id, user);
	}

	@ApiOperation(value = "附件列表,支持分页,支持动态查询（标题,分类,日期等）,按附件发布的时间逆序排列")
	@RequestMapping(value = "/attachments", method = RequestMethod.GET)
	public Page<AttachmentSummary> listAttachmentSummaryPage(AttachmentQueryParameter queryRequest) {
		User user = SecurityContexts.getContext().requireUser();
		return portalRestSupport.listAttachments(queryRequest, user);
	}

	@ApiOperation(value = "查看附件详情")
	@RequestMapping(value = "/attachments/{id}", method = RequestMethod.GET)
	public AttachmentSummary getAttachmentDetail(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		return portalRestSupport.getAttachmentDetail(id, user);
	}

	@ApiOperation(value = "我的待办任务（消息）,支持分页")
	@RequestMapping(value = "/messages", method = RequestMethod.GET)
	public Page<MessageSummary> listPendingMessageSummaryPage(PendingMessageQueryParameter queryRequest) {
		User user = SecurityContexts.getContext().requireUser();
		return portalRestSupport.listPendingMessages(queryRequest, user);
	}

}
