package in.clouthink.nextoa.service.impl;

import in.clouthink.nextoa.exception.MessageException;
import in.clouthink.nextoa.exception.MessageNotFoundException;
import in.clouthink.nextoa.exception.PaperException;
import in.clouthink.nextoa.model.FavoriteMessage;
import in.clouthink.nextoa.model.Message;
import in.clouthink.nextoa.model.SysRole;
import in.clouthink.nextoa.model.User;
import in.clouthink.nextoa.model.dtr.MessageQueryRequest;
import in.clouthink.nextoa.model.dtr.PageQueryRequest;
import in.clouthink.nextoa.repository.FavoriteMessageRepository;
import in.clouthink.nextoa.repository.MessageRepository;
import in.clouthink.nextoa.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private FavoriteMessageRepository favoriteMessageRepository;

	@Override
	public Page<Message> listMessagesByPaperCreator(String creatorName, PageQueryRequest queryParameter, User user) {
		if (StringUtils.isEmpty(creatorName)) {
			return new PageImpl<>(Collections.emptyList(),
								  new PageRequest(queryParameter.getStart(), queryParameter.getLimit()),
								  0);
		}
		if (creatorName.length() < 2) {
			throw new MessageException("为提高搜索结果的准确度,发起人不能少于2个字.");
		}

		return messageRepository.queryPageByPaperCreator(creatorName,
														 user,
														 new PageRequest(queryParameter.getStart(),
																		 queryParameter.getLimit(),
																		 new Sort(Sort.Direction.DESC, "receivedAt")));
	}

	@Override
	public Page<Message> listMessagesByReceiver(String receiverName, PageQueryRequest queryParameter, User user) {
		if (StringUtils.isEmpty(receiverName)) {
			return new PageImpl<>(Collections.emptyList(),
								  new PageRequest(queryParameter.getStart(), queryParameter.getLimit()),
								  0);
		}
		if (receiverName.length() < 2) {
			throw new MessageException("为提高搜索结果的准确度,接收人不能少于2个字.");
		}

		return messageRepository.queryPageByReceiver(receiverName,
													 user,
													 new PageRequest(queryParameter.getStart(),
																	 queryParameter.getLimit(),
																	 new Sort(Sort.Direction.DESC, "receivedAt")));
	}

	@Override
	public Message findMessageById(String id) {
		return messageRepository.findById(id);
	}

	@Override
	public Message findMessageById(String id, User user) {
		Message message = messageRepository.findById(id);
		if (message == null) {
			return null;
		}

		if (user.getAuthorities().contains(SysRole.ROLE_ADMIN) || user.getAuthorities().contains(SysRole.ROLE_MGR)) {
			return message;
		}
		if (message.getReceiver().getId().equals(user.getId())) {
			return message;
		}

		throw new PaperException("无查看该消息的权限");
	}

	@Override
	public Page<Message> listMessages(MessageQueryRequest request,
									  MessageQueryRequest.IncludeOrExcludeStatus includeOrExcludeStatus,
									  User user) {
		return messageRepository.queryPage(user, request, includeOrExcludeStatus);
	}

	@Override
	public Page<Message> listPaperMessages(String bizRefId, PageQueryRequest queryParameter) {
		return messageRepository.findPageByBizRefId(bizRefId,
													new PageRequest(queryParameter.getStart(),
																	queryParameter.getLimit(),
																	new Sort(Sort.Direction.DESC, "receivedAt")));
	}

	@Override
	public Page<Message> listFavoriteMessages(MessageQueryRequest request, User user) {
		Pageable pageable = new PageRequest(request.getStart(), request.getLimit());
		Page<FavoriteMessage> favoriteMessages = favoriteMessageRepository.queryPage(user, request);
		List<Message> messages = favoriteMessages.getContent()
												 .stream()
												 .map(favoriteMessage -> favoriteMessage.getMessage())
												 .collect(Collectors.toList());
		return new PageImpl<>(messages, pageable, favoriteMessages.getTotalElements());
	}

	@Override
	public long countOfMessages(MessageQueryRequest request,
								MessageQueryRequest.IncludeOrExcludeStatus includeOrExcludeStatus,
								User user) {
		return messageRepository.queryCount(user, request, includeOrExcludeStatus);
	}

	@Override
	public long countOfFavoriteMessages(PageQueryRequest request, User user) {
		return favoriteMessageRepository.countByCreatedBy(user);
	}

	@Override
	public FavoriteMessage addMessageToFavorite(String id, User user) {
		Message message = messageRepository.findById(id);
		if (message == null) {
			throw new MessageNotFoundException(id);
		}
		FavoriteMessage favoriteMessage = favoriteMessageRepository.findByMessageAndCreatedBy(message, user);
		if (favoriteMessage != null) {
			return favoriteMessage;
		}
		favoriteMessage = new FavoriteMessage();
		favoriteMessage.setMessage(message);
		favoriteMessage.setCreatedBy(user);
		favoriteMessage.setCreatedAt(new Date());
		return favoriteMessageRepository.save(favoriteMessage);
	}

	@Override
	public void removeMessageFromFavorite(String id, User user) {
		Message message = messageRepository.findById(id);
		if (message == null) {
			throw new MessageNotFoundException(id);
		}
		FavoriteMessage favoriteMessage = favoriteMessageRepository.findByMessageAndCreatedBy(message, user);
		if (favoriteMessage == null) {
			return;
		}
		favoriteMessageRepository.delete(favoriteMessage);
	}

	@Override
	public boolean isFavorite(Message message, User user) {
		return favoriteMessageRepository.findByMessageAndCreatedBy(message, user) != null;
	}

}
