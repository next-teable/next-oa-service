package in.clouthink.nextoa.openapi.controller;

import in.clouthink.nextoa.model.User;
import cn.com.starest.nextoa.openapi.dto.*;
import in.clouthink.nextoa.openapi.dto.*;
import in.clouthink.nextoa.openapi.security.SecurityContexts;
import in.clouthink.nextoa.openapi.support.MessageRestSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@Api("我的任务（消息）")
@RestController
@RequestMapping("/api")
public class MessageRestController {

	@Autowired
	private MessageRestSupport messageRestSupport;

	@ApiOperation(value = "快速查询-标题")
	@RequestMapping(value = "/messages/byTitle", method = RequestMethod.GET)
	public Page<MessageSummary> listMessagesByTitle(MessageTitleQueryParameter queryParameter) {
		User user = SecurityContexts.getContext().requireUser();
		return messageRestSupport.listMessagesByTitle(queryParameter.getTitle(), queryParameter, user);
	}

	@ApiOperation(value = "快速查询-发起人")
	@RequestMapping(value = "/messages/byCreator", method = RequestMethod.GET)
	public Page<MessageSummary> listMessagesByPaperCreator(MessageCreatorNameQueryParameter queryParameter) {
		User user = SecurityContexts.getContext().requireUser();
		return messageRestSupport.listMessagesByPaperCreator(queryParameter.getCreatorName(), queryParameter, user);
	}

	@ApiOperation(value = "快速查询-接收人")
	@RequestMapping(value = "/messages/byReceiver", method = RequestMethod.GET)
	public Page<MessageSummary> listMessagesByReceiver(MessageReceiverNameQueryParameter queryParameter) {
		User user = SecurityContexts.getContext().requireUser();
		return messageRestSupport.listMessagesByReceiver(queryParameter.getReceiverName(), queryParameter, user);
	}

	@ApiOperation(value = "我的所有消息（不区分状态）,支持分页,支持动态查询")
	@RequestMapping(value = "/messages", method = RequestMethod.GET)
	public Page<MessageSummary> listAllMessages(MessageQueryParameter queryRequest) {
		User user = SecurityContexts.getContext().requireUser();
		return messageRestSupport.listAllMessages(queryRequest, user);
	}

	@ApiOperation(value = "我的待处理任务,支持分页,支持动态查询")
	@RequestMapping(value = "/messages/pending", method = RequestMethod.GET)
	public Page<MessageSummary> listPendingMessages(MessageQueryParameter queryRequest) {
		User user = SecurityContexts.getContext().requireUser();
		return messageRestSupport.listPendingMessages(queryRequest, user);
	}

	@ApiOperation(value = "我的已处理任务,支持分页,支持动态查询")
	@RequestMapping(value = "/messages/processed", method = RequestMethod.GET)
	public Page<MessageSummary> listProcessedMessages(MessageQueryParameter queryRequest) {
		User user = SecurityContexts.getContext().requireUser();
		return messageRestSupport.listProcessedMessages(queryRequest, user);
	}

	@ApiOperation(value = "我的已结束任务,支持分页,支持动态查询")
	@RequestMapping(value = "/messages/ended", method = RequestMethod.GET)
	public Page<MessageSummary> listEndedMessages(MessageQueryParameter queryRequest) {
		User user = SecurityContexts.getContext().requireUser();
		return messageRestSupport.listEndedMessages(queryRequest, user);
	}

	@ApiOperation(value = "我的未结束任务,支持分页,支持动态查询")
	@RequestMapping(value = "/messages/notend", method = RequestMethod.GET)
	public Page<MessageSummary> listNotEndMessages(MessageQueryParameter queryRequest) {
		User user = SecurityContexts.getContext().requireUser();
		return messageRestSupport.listNotEndMessages(queryRequest, user);
	}

	@ApiOperation(value = "我收藏的任务,支持分页,支持动态查询")
	@RequestMapping(value = "/messages/favorite", method = RequestMethod.GET)
	public Page<MessageSummary> listFavoriteMessages(MessageQueryParameter queryRequest) {
		User user = SecurityContexts.getContext().requireUser();
		return messageRestSupport.listFavoriteMessages(queryRequest, user);
	}

	@ApiOperation(value = "数据总数合计 - 我的所有消息（不区分状态）, 支持动态查询")
	@RequestMapping(value = "/messages/countOfAll", method = RequestMethod.GET)
	public long countOfAllMessages(MessageQueryParameter queryRequest) {
		User user = SecurityContexts.getContext().requireUser();
		return messageRestSupport.getCountOfAllMessages(queryRequest, user);
	}

	@ApiOperation(value = "数据总数合计 - 我的待处理任务, 支持动态查询")
	@RequestMapping(value = "/messages/countOfPending", method = RequestMethod.GET)
	public long countOfPendingMessages(MessageQueryParameter queryRequest) {
		User user = SecurityContexts.getContext().requireUser();
		return messageRestSupport.getCountOfPendingMessages(queryRequest, user);
	}

	@ApiOperation(value = "数据总数合计 - 我的已处理任务, 支持动态查询")
	@RequestMapping(value = "/messages/countOfProcessed", method = RequestMethod.GET)
	public long countOfProcessedMessages(MessageQueryParameter queryRequest) {
		User user = SecurityContexts.getContext().requireUser();
		return messageRestSupport.getCountOfProcessedMessages(queryRequest, user);
	}

	@ApiOperation(value = "数据总数合计 - 已结束的任务, 支持动态查询")
	@RequestMapping(value = "/messages/countOfEnded", method = RequestMethod.GET)
	public long countOfEndedMessages(MessageQueryParameter queryRequest) {
		User user = SecurityContexts.getContext().requireUser();
		return messageRestSupport.getCountOfEndedMessages(queryRequest, user);
	}

	@ApiOperation(value = "数据总数合计 - 未结束的任务, 支持动态查询")
	@RequestMapping(value = "/messages/countOfNotEnd", method = RequestMethod.GET)
	public long countOfNotEndMessages(MessageQueryParameter queryRequest) {
		User user = SecurityContexts.getContext().requireUser();
		return messageRestSupport.getCountOfNotEndMessages(queryRequest, user);
	}

	@ApiOperation(value = "数据总数合计 - 我收藏的任务, 支持动态查询")
	@RequestMapping(value = "/messages/countOfFavorite", method = RequestMethod.GET)
	public long countOfFavoriteMessages(PageQueryParameter queryRequest) {
		User user = SecurityContexts.getContext().requireUser();
		return messageRestSupport.getCountOfFavoriteMessages(queryRequest, user);
	}

	@ApiOperation(value = "查看任务（消息）明细")
	@RequestMapping(value = "/messages/{id}", method = RequestMethod.GET)
	public MessageDetail getMessageDetail(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		return messageRestSupport.getMessageDetail(id, user);
	}

	@ApiOperation(value = "查看任务（消息）- 参与人")
	@RequestMapping(value = "/messages/{id}/participant", method = RequestMethod.GET)
	public MessageParticipant getMessageParticipant(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		return messageRestSupport.getMessageParticipant(id, user);
	}

	@ApiOperation(value = "收藏任务（重复收藏自动忽略）")
	@RequestMapping(value = "/messages/{id}/favorite", method = RequestMethod.POST)
	public void addMessageToFavorite(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		messageRestSupport.addMessageToFavorite(id, user);
	}

	@ApiOperation(value = "取消收藏任务（重复取消自动忽略）")
	@RequestMapping(value = "/messages/{id}/favorite", method = RequestMethod.DELETE)
	public void removeMessageFromFavorite(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		messageRestSupport.removeMessageFromFavorite(id, user);
	}

}
