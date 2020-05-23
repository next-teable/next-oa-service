package in.clouthink.nextoa.openapi.support.impl;

import cn.com.starest.nextoa.model.*;
import in.clouthink.nextoa.model.dtr.MessageQueryRequest;
import in.clouthink.nextoa.model.dtr.PageQueryRequest;
import cn.com.starest.nextoa.openapi.dto.*;
import in.clouthink.nextoa.openapi.dto.*;
import in.clouthink.nextoa.openapi.support.MessageRestSupport;
import in.clouthink.nextoa.service.MessageService;
import in.clouthink.nextoa.service.PaperService;
import in.clouthink.nextoa.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.stream.Collectors;

/**
 *
 */
@Service
public class MessageRestSupportImpl implements MessageRestSupport {

	@Autowired
	private MessageService messageService;

	@Autowired
	private PaperService paperService;

	@Override
	public Page<MessageSummary> listMessagesByTitle(String title, PageQueryRequest queryRequest, User user) {
		MessageQueryParameter messageQueryParameter = new MessageQueryParameter();
		messageQueryParameter.setTitle(title);
		messageQueryParameter.setStart(queryRequest.getStart());
		messageQueryParameter.setLimit(queryRequest.getLimit());

		Page<Message> messagePage = messageService.listMessages(messageQueryParameter, null, user);

		return new PageImpl<>(messagePage.getContent()
										 .stream()
										 .map(message -> convertToMessageSummary(user, message))
										 .collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  messagePage.getTotalElements());
	}

	@Override
	public Page<MessageSummary> listMessagesByPaperCreator(String creatorName,
														   PageQueryRequest queryRequest,
														   User user) {
		Page<Message> messagePage = messageService.listMessagesByPaperCreator(creatorName, queryRequest, user);
		return new PageImpl<>(messagePage.getContent()
										 .stream()
										 .map(message -> convertToMessageSummary(user, message))
										 .collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  messagePage.getTotalElements());
	}

	@Override
	public Page<MessageSummary> listMessagesByReceiver(String receiverName, PageQueryRequest queryRequest, User user) {
		Page<Message> messagePage = messageService.listMessagesByReceiver(receiverName, queryRequest, user);
		return new PageImpl<>(messagePage.getContent()
										 .stream()
										 .map(message -> convertToMessageSummary(user, message))
										 .collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  messagePage.getTotalElements());
	}

	@Override
	public Page<MessageSummary> listAllMessages(MessageQueryParameter queryRequest, User user) {
		queryRequest.setMessageStatus(null);
		Page<Message> messagePage = messageService.listMessages(queryRequest,
																MessageQueryRequest.IncludeOrExcludeStatus.INCLUDE,
																user);
		return new PageImpl<>(messagePage.getContent()
										 .stream()
										 .map(message -> convertToMessageSummary(user, message))
										 .collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  messagePage.getTotalElements());
	}

	@Override
	public Page<MessageSummary> listPendingMessages(MessageQueryParameter queryRequest, User user) {
		queryRequest.setMessageStatus(MessageStatus.PENDING);
		Page<Message> messagePage = messageService.listMessages(queryRequest,
																MessageQueryRequest.IncludeOrExcludeStatus.INCLUDE,
																user);
		return new PageImpl<>(messagePage.getContent()
										 .stream()
										 .map(message -> convertToMessageSummary(user, message))
										 .collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  messagePage.getTotalElements());
	}

	@Override
	public Page<MessageSummary> listProcessedMessages(MessageQueryParameter queryRequest, User user) {
		queryRequest.setMessageStatus(MessageStatus.PROCESSED);
		Page<Message> messagePage = messageService.listMessages(queryRequest,
																MessageQueryRequest.IncludeOrExcludeStatus.INCLUDE,
																user);
		return new PageImpl<>(messagePage.getContent()
										 .stream()
										 .map(message -> convertToMessageSummary(user, message))
										 .collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  messagePage.getTotalElements());
	}

	@Override
	public Page<MessageSummary> listNotEndMessages(MessageQueryParameter queryRequest, User user) {
		queryRequest.setMessageStatus(MessageStatus.ENDED);
		Page<Message> messagePage = messageService.listMessages(queryRequest,
																MessageQueryRequest.IncludeOrExcludeStatus.EXCLUDE,
																user);
		return new PageImpl<>(messagePage.getContent()
										 .stream()
										 .map(message -> convertToMessageSummary(user, message))
										 .collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  messagePage.getTotalElements());
	}

	@Override
	public Page<MessageSummary> listEndedMessages(MessageQueryParameter queryRequest, User user) {
		queryRequest.setMessageStatus(MessageStatus.ENDED);
		Page<Message> messagePage = messageService.listMessages(queryRequest,
																MessageQueryRequest.IncludeOrExcludeStatus.INCLUDE,
																user);
		return new PageImpl<>(messagePage.getContent()
										 .stream()
										 .map(message -> convertToMessageSummary(user, message))
										 .collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  messagePage.getTotalElements());
	}

	@Override
	public Page<MessageSummary> listFavoriteMessages(MessageQueryParameter queryRequest, User user) {
		Page<Message> messagePage = messageService.listFavoriteMessages(queryRequest, user);
		return new PageImpl<>(messagePage.getContent().stream().map(message -> {
			if(null != message){
				Paper paper = paperService.findPaperById(message.getBizRefId());
				MessageSummary result = MessageSummary.from(message, paper);
				result.setRead(paperService.isRead(paper, user));
				result.setFavorite(true);
				return result;
			}
			return null;
		}).collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  messagePage.getTotalElements());
	}

	@Override
	public MessageDetail getMessageDetail(String id, User user) {
		Message message = messageService.findMessageById(id, user);
		if (message == null) {
			return null;
		}
		Paper paper = paperService.findPaperById(message.getBizRefId(), user);
		MessageDetail result = MessageDetail.from(message, paper);
		result.setRead(paperService.isRead(paper, user));
		result.setFavorite(messageService.isFavorite(message, user));
		return result;
	}

	@Override
	public MessageParticipant getMessageParticipant(String id, User user) {
		Message message = messageService.findMessageById(id, user);
		if (message == null) {
			return null;
		}
		String actionRefId = message.getActionRefId();
		if (StringUtils.isEmpty(actionRefId)) {
			return null;
		}
		PaperAction paperAction = paperService.findPaperActionById(actionRefId);
		if (paperAction == null) {
			return null;
		}

		return MessageParticipant.from(message.getSender(), user, paperAction);
	}

	@Override
	public long getCountOfAllMessages(MessageQueryParameter queryRequest, User user) {
		queryRequest.setMessageStatus(null);
		return messageService.countOfMessages(queryRequest, MessageQueryRequest.IncludeOrExcludeStatus.INCLUDE, user);
	}

	@Override
	public long getCountOfPendingMessages(MessageQueryParameter queryRequest, User user) {
		queryRequest.setMessageStatus(MessageStatus.PENDING);
		return messageService.countOfMessages(queryRequest, MessageQueryRequest.IncludeOrExcludeStatus.INCLUDE, user);
	}

	@Override
	public long getCountOfProcessedMessages(MessageQueryParameter queryRequest, User user) {
		queryRequest.setMessageStatus(MessageStatus.PROCESSED);
		return messageService.countOfMessages(queryRequest, MessageQueryRequest.IncludeOrExcludeStatus.INCLUDE, user);
	}

	@Override
	public long getCountOfEndedMessages(MessageQueryParameter queryRequest, User user) {
		queryRequest.setMessageStatus(MessageStatus.ENDED);
		return messageService.countOfMessages(queryRequest, MessageQueryRequest.IncludeOrExcludeStatus.INCLUDE, user);
	}

	@Override
	public long getCountOfNotEndMessages(MessageQueryParameter queryRequest, User user) {
		queryRequest.setMessageStatus(MessageStatus.ENDED);
		return messageService.countOfMessages(queryRequest, MessageQueryRequest.IncludeOrExcludeStatus.EXCLUDE, user);
	}

	@Override
	public long getCountOfFavoriteMessages(PageQueryParameter queryRequest, User user) {
		return messageService.countOfFavoriteMessages(queryRequest, user);
	}

	@Override
	public void addMessageToFavorite(String id, User user) {
		messageService.addMessageToFavorite(id, user);
	}

	@Override
	public void removeMessageFromFavorite(String id, User user) {
		messageService.removeMessageFromFavorite(id, user);
	}


	private MessageSummary convertToMessageSummary(User user, Message message) {
		Paper paper = paperService.findPaperById(message.getBizRefId(), user);
		MessageSummary result = MessageSummary.from(message, paper);
		result.setFavorite(messageService.isFavorite(message, user));
		return result;
	}

}
