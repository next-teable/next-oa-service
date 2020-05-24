package in.clouthink.nextoa.bl.service.impl;

import in.clouthink.daas.edm.Edms;
import in.clouthink.nextoa.bl.exception.PaperNotFoundException;
import in.clouthink.nextoa.bl.model.*;
import in.clouthink.nextoa.bl.repository.*;
import in.clouthink.nextoa.bl.request.DefaultMessageNotifyRequest;
import in.clouthink.nextoa.bl.request.MessageNotifyRequest;
import in.clouthink.nextoa.bl.service.PaperInnerService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 */
@Service
public class PaperInnerServiceImpl implements PaperInnerService {

	private static final Log logger = LogFactory.getLog(PaperInnerServiceImpl.class);

	@Autowired
	private PaperRepository paperRepository;

	@Autowired
	private PaperActionRepository paperActionRepository;

	@Autowired
	private PaperTransitionRepository paperTransitionRepository;

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private RecentUserRelationshipRepository recentUserRelationshipRepository;

	@Override
	public void markPaperAsRead(String id, User user) {
		Paper paper = paperRepository.findById(id);
		if (paper == null) {
			throw new PaperNotFoundException(id);
		}

		markPaperAsRead(paper, user);
	}

	@Override
	public void markPaperAsRead(Paper paper, User user) {
		PaperAction readPaperAction = paperActionRepository.findFirstByPaperAndTypeAndCreatedBy(paper,
																								PaperActionType.READ,
																								user);
		if (readPaperAction == null) {
			readPaperAction = new PaperAction();
			readPaperAction.setPaper(paper);
			readPaperAction.setType(PaperActionType.READ);
			readPaperAction.setCreatedBy(user);
			readPaperAction.setCreatedAt(new Date());
		}
		readPaperAction.setModifiedAt(new Date());
		paperActionRepository.save(readPaperAction);

		Message message = messageRepository.findByBizRefIdAndReceiver(paper.getId(), user);
		if (message != null) {
			messageRepository.markAsRead(message.getId());
		}

		int readCounter = paperActionRepository.countByPaperAndType(paper, PaperActionType.READ);
		paperRepository.updateReadCounter(paper.getId(), readCounter);
	}

	@Override
	public void handleStartPaperAction(PaperAction startPaperAction) {
		startPaperAction = paperActionRepository.save(startPaperAction);

		Paper paper = startPaperAction.getPaper();
		paper.setAllowedActions(startPaperAction.getAllowedActions());
		paper.setStatus(PaperStatus.IN_PROGRESS);
		paper.setStartPaperAction(startPaperAction);
		paper.setLatestPaperAction(startPaperAction);
		paper.setStartedAt(new Date());
		paperRepository.save(paper);

		handleMessage(startPaperAction);

		//create message for the starter ( and make its status as processed )
		Message message = messageRepository.findByBizRefIdAndReceiver(paper.getId(), paper.getCreatedBy());
		if (message == null) {
			Date now = new Date();
			message = new Message();
			message.setCategory(paper.getCategory());
			message.setTitle(paper.getTitle());
			message.setBizRefType(BizRefType.PAPER);
			message.setBizRefId(paper.getId());
			message.setActionRefId(startPaperAction.getId());
			message.setInitiator(paper.getCreatedBy());
			message.setSender(paper.getCreatedBy());
			message.setReceiver(paper.getCreatedBy());
			message.setReceivedAt(now);
			message.setModifiedAt(now);
			message.setStatus(MessageStatus.PROCESSED);
			messageRepository.save(message);
		}

		//handle the transition
		PaperTransition paperTransition = new PaperTransition();
		paperTransition.setId(startPaperAction.getId());
		paperTransition.setPaper(paper);
		paperTransition.setPaperActionType(startPaperAction.getType());
		paperTransition.setCreatorId(startPaperAction.getCreatedBy().getId());
		paperTransition.setCreatedBy(startPaperAction.getCreatedBy());
		paperTransition.setCreatedAt(startPaperAction.getCreatedAt());
		paperTransition.setCurrentActionId(startPaperAction.getId());
		paperTransition.setParticipantIds(resolveParticipants(startPaperAction));
		paperTransitionRepository.save(paperTransition);
	}

	@Override
	public void handleReplyPaperAction(PaperAction previousPaperAction, PaperAction replyPaperAction) {
		Paper reloadPaper = paperRepository.findById(replyPaperAction.getPaper().getId());
		if (reloadPaper.getStatus() == PaperStatus.REVOKED) {
			processFailedPaperAction(replyPaperAction, "快文已经被撤回,不能进行回复操作!");
			return;
		}

		if (previousPaperAction!=null) {
			replyPaperAction.setPreviousActionId(previousPaperAction.getId());
		}

		replyPaperAction = paperActionRepository.save(replyPaperAction);

		Paper paper = replyPaperAction.getPaper();
		if (Paper.isEditAllowed(paper) && !StringUtils.isEmpty(replyPaperAction.getPaperContent())) {
			String crc32NewValue = Paper.crc32(replyPaperAction.getPaperContent());
			if (!crc32NewValue.equals(paper.getContentCrc32())) {
				paper.setContent(replyPaperAction.getPaperContent());
			}
		}
		paper.setLatestPaperAction(replyPaperAction);
		paperRepository.save(paper);

		handleMessage(replyPaperAction);

		//handle the transition
		PaperTransition previousPaperTransition = paperTransitionRepository.findById(previousPaperAction.getId());
		if (previousPaperTransition != null) {
			previousPaperTransition.setNextActionId(replyPaperAction.getId());
			paperTransitionRepository.save(previousPaperTransition);
		}

		PaperTransition paperTransition = new PaperTransition();
		paperTransition.setId(replyPaperAction.getId());
		paperTransition.setPaper(paper);
		paperTransition.setPaperActionType(replyPaperAction.getType());
		paperTransition.setCreatorId(replyPaperAction.getCreatedBy().getId());
		paperTransition.setCreatedBy(replyPaperAction.getCreatedBy());
		paperTransition.setCreatedAt(replyPaperAction.getCreatedAt());
		paperTransition.setCurrentActionId(replyPaperAction.getId());
		paperTransition.setPreviousActionId(previousPaperAction.getId());
		paperTransition.setParticipantIds(resolveParticipants(replyPaperAction));
		paperTransitionRepository.save(paperTransition);
	}

	@Override
	public void handleForwardPaperAction(PaperAction previousPaperAction, PaperAction forwardPaperAction) {
		Paper reloadPaper = paperRepository.findById(forwardPaperAction.getPaper().getId());
		if (reloadPaper.getStatus() == PaperStatus.REVOKED) {
			processFailedPaperAction(forwardPaperAction, "快文已经被撤回,不能进行转发操作!");
			return;
		}

		if (previousPaperAction!=null) {
			forwardPaperAction.setPreviousActionId(previousPaperAction.getId());
		}

		forwardPaperAction = paperActionRepository.save(forwardPaperAction);

		Paper paper = forwardPaperAction.getPaper();
		if (Paper.isEditAllowed(paper) && !StringUtils.isEmpty(forwardPaperAction.getPaperContent())) {
			String crc32NewValue = Paper.crc32(forwardPaperAction.getPaperContent());
			if (!crc32NewValue.equals(paper.getContentCrc32())) {
				paper.setContent(forwardPaperAction.getPaperContent());
			}
		}
		paper.setLatestPaperAction(forwardPaperAction);
		paperRepository.save(paper);

		handleMessage(forwardPaperAction);

		//handle the transition
		PaperTransition previousPaperTransition = paperTransitionRepository.findById(previousPaperAction.getId());
		if (previousPaperTransition != null) {
			previousPaperTransition.setNextActionId(forwardPaperAction.getId());
			paperTransitionRepository.save(previousPaperTransition);
		}

		PaperTransition paperTransition = new PaperTransition();
		paperTransition.setId(forwardPaperAction.getId());
		paperTransition.setPaper(paper);
		paperTransition.setPaperActionType(forwardPaperAction.getType());
		paperTransition.setCreatorId(forwardPaperAction.getCreatedBy().getId());
		paperTransition.setCreatedBy(forwardPaperAction.getCreatedBy());
		paperTransition.setCreatedAt(forwardPaperAction.getCreatedAt());
		paperTransition.setCurrentActionId(forwardPaperAction.getId());
		paperTransition.setPreviousActionId(previousPaperAction.getId());
		paperTransition.setParticipantIds(resolveParticipants(forwardPaperAction));
		paperTransitionRepository.save(paperTransition);
	}

	@Override
	public void handleRevokePaperAction(PaperAction revokePaperAction) {
		Paper reloadPaper = paperRepository.findById(revokePaperAction.getPaper().getId());
		if (!reloadPaper.getStartPaperAction().getId().equals(reloadPaper.getLatestPaperAction().getId())) {
			processFailedPaperAction(revokePaperAction, "快文已被处理,不能进行撤回操作!");
			return;
		}

		//clean the paper message
		List<Message> messageList = messageRepository.findByBizRefId(reloadPaper.getId());
		for (Message message : messageList) {
			if (!message.getReceiver().getId().equalsIgnoreCase(reloadPaper.getCreatedBy().getId()) &&
				message.getStatus() == MessageStatus.PROCESSED) {
				processFailedPaperAction(revokePaperAction, "快文已被处理,不能进行撤回操作!");
				return;
			}
		}
		messageRepository.delete(messageList);

		//clean the paper actions
		paperActionRepository.delete(paperActionRepository.findListByPaper(reloadPaper));

		reloadPaper.setStatus(PaperStatus.REVOKED);
		reloadPaper.setRevokedAt(new Date());
		reloadPaper.setModifiedAt(new Date());
		//撤回后,重置action,恢复和草稿一样的状态
		reloadPaper.setStartPaperAction(null);
		reloadPaper.setLatestPaperAction(null);
		paperRepository.save(reloadPaper);
	}

	@Override
	public void handleEndPaperAction(PaperAction endPaperAction) {
		Paper reloadPaper = paperRepository.findById(endPaperAction.getPaper().getId());
		if (reloadPaper.getStatus() == PaperStatus.REVOKED) {
			processFailedPaperAction(endPaperAction, "快文已经被撤回,不能进行结束操作!");
			return;
		}

		paperActionRepository.save(endPaperAction);

		Message message = messageRepository.findByBizRefIdAndReceiver(endPaperAction.getPaper().getId(),
																	  endPaperAction.getCreatedBy());
		if (message != null) {
			message.setStatus(MessageStatus.ENDED);
			message.setModifiedAt(new Date());
			messageRepository.save(message);
		}
	}

	@Override
	public void handleTerminatePaperAction(PaperAction paperAction) {
		Paper reloadPaper = paperRepository.findById(paperAction.getPaper().getId());
		if (reloadPaper.getStatus() != PaperStatus.TERMINATED) {
			paperActionRepository.save(paperAction);

			reloadPaper.setStatus(PaperStatus.TERMINATED);
			reloadPaper.setModifiedAt(new Date());
			paperRepository.save(reloadPaper);
		}

		List<Message> messageList = messageRepository.findByBizRefId(paperAction.getPaper().getId());
		messageList.stream().forEach(message -> {
			if (message.getStatus() != MessageStatus.TERMINATED) {
				message.setStatus(MessageStatus.TERMINATED);
				message.setModifiedAt(new Date());
				messageRepository.save(message);
			}
		});
	}

	//***************************
	// private
	//***************************
	private void handleMessage(PaperAction paperAction) {
		//
		Message message = messageRepository.findByBizRefIdAndReceiver(paperAction.getPaper().getId(),
																	  paperAction.getCreatedBy());
		if (message != null) {
			message.setSender(paperAction.getCreatedBy());
			message.setActionRefId(paperAction.getId());
			message.setStatus(MessageStatus.PROCESSED);
			message.setModifiedAt(new Date());
			messageRepository.save(message);
		}

		Paper paper = paperAction.getPaper();
		List<Receiver> toReceivers = paperAction.getToReceivers();
		List<Receiver> ccReceivers = paperAction.getCcReceivers();

		List<MessageNotifyRequest> messageNotifyRequests = new ArrayList<>();
		List<Message> messages = createMessageForReceiver(paper,
														  paperAction,
														  toReceivers,
														  ccReceivers,
														  messageNotifyRequests);
//		messageRepository.save(messages);

		handleRecentUsers(paperAction.getCreatedBy(),
						  messages.stream().map(msg -> msg.getReceiver()).collect(Collectors.toList()));

//		if (toReceivers != null) {
//			buildMessageNotifyRequest(paperAction, paper, toReceivers, messageNotifyRequests);
//		}
//		if (ccReceivers != null) {
//			buildMessageNotifyRequest(paperAction, paper, ccReceivers, messageNotifyRequests);
//		}

		Edms.getEdm("sms").dispatch(MessageNotifyRequest.MESSAGE_NOTIFY, messageNotifyRequests);
	}

	/**
	 * Keep the recent 10 contact users
	 *
	 * @param byWho
	 * @param recentUsers
	 */
	private void handleRecentUsers(User byWho, List<User> recentUsers) {
		if (recentUsers == null || recentUsers.isEmpty()) {
			return;
		}
		try {
			List<RecentUserRelationship> recentUserRelationships = recentUserRelationshipRepository.findListByUserOrderByCreatedAtDesc(
					byWho);
			//key : userId , value : relationId
			Map<String,RecentUserRelationship> relationshipMap = new HashMap<>();
			Set<String> existedUserIds = new HashSet<>();
			recentUserRelationships.stream().forEach(relationship -> {
				existedUserIds.add(relationship.getRecentUser().getId());
				relationshipMap.put(relationship.getRecentUser().getId(), relationship);
			});

			recentUsers.stream().forEach(recentUser -> {
				if (!existedUserIds.contains(recentUser.getId())) {
					RecentUserRelationship recentUserRelationship = new RecentUserRelationship();
					recentUserRelationship.setUser(byWho);
					recentUserRelationship.setRecentUser(recentUser);
					recentUserRelationship.setCreatedAt(new Date());
					recentUserRelationshipRepository.save(recentUserRelationship);
				}
				else {
					RecentUserRelationship recentUserRelationship = relationshipMap.get(recentUser.getId());
					if (recentUserRelationship != null) {
						recentUserRelationship.setCreatedAt(new Date());
						recentUserRelationshipRepository.save(recentUserRelationship);
					}
				}
			});

			//remove the over stack users
			recentUserRelationships = recentUserRelationshipRepository.findListByUserOrderByCreatedAtDesc(byWho);
			int totalSize = recentUserRelationships.size();
			int overStackSize = recentUserRelationships.size() - 10;
			for (int i = 0; i < overStackSize; i++) {
				RecentUserRelationship overRelationship = recentUserRelationships.remove(totalSize - i - 1);
				recentUserRelationshipRepository.delete(overRelationship);
			}
		}
		catch (Exception e) {
		}
	}

	private void processFailedPaperAction(PaperAction paperAction, String errorMessage) {
		paperAction.setFailed(true);
		paperAction.setErrorMessage(errorMessage);
		paperActionRepository.save(paperAction);
		logger.warn(errorMessage);
	}

//	private void buildMessageNotifyRequest(PaperAction paperAction,
//										   Paper paper,
//										   Message message,
//										   List<Receiver> receivers,
//										   List<MessageNotifyRequest> messageNotifyRequests) {
//		receivers.stream().forEach(receiver -> {
//			if (receiver.isNotifyEnabled()) {
//				DefaultMessageNotifyRequest messageNotifyRequest = new DefaultMessageNotifyRequest();
//				messageNotifyRequest.setCellphone(receiver.getUser().getContactPhone());
//				messageNotifyRequest.setMessageId(message.getId());
//				messageNotifyRequest.setMessageSender(paperAction.getCreatedBy().getUsername());
//				messageNotifyRequest.setMessageTitle(paper.getTitle());
//				messageNotifyRequests.add(messageNotifyRequest);
//			}
//		});
//	}


	private List<Message> createMessageForReceiver(Paper paper,
												   PaperAction paperAction,
												   List<Receiver> toReceivers,
												   List<Receiver> ccReceivers,
												   List<MessageNotifyRequest> messageNotifyRequests) {
		List<Message> messages = new ArrayList<>();
		Set<String> userIds = new HashSet<>();

		doCreateMessageForReceiver(paper, paperAction, toReceivers, messages, userIds, messageNotifyRequests);

		if (ccReceivers != null) {
			doCreateMessageForReceiver(paper, paperAction, ccReceivers, messages, userIds, messageNotifyRequests);
		}

		return messages;
	}

	private void doCreateMessageForReceiver(Paper paper,
											PaperAction paperAction,
											List<Receiver> receivers,
											List<Message> messages,
											Set<String> userIds,
											List<MessageNotifyRequest> messageNotifyRequests) {
		receivers.stream().forEach(receiver -> {
			String userId = receiver.getUser().getId();
			if (!userIds.contains(userId)) {
				User fromUser = paperAction.getCreatedBy();
				User toUser = receiver.getUser();
				Message message = createOrUpdateMessage(paper, paperAction, fromUser, toUser );

				if (receiver.isNotifyEnabled()) {
					DefaultMessageNotifyRequest messageNotifyRequest = new DefaultMessageNotifyRequest();
					messageNotifyRequest.setCellphone(toUser.getContactPhone());
					messageNotifyRequest.setMessageId(message.getId());
					messageNotifyRequest.setMessageSender(paperAction.getCreatedBy().getUsername());
					messageNotifyRequest.setMessageTitle(paper.getTitle());
					messageNotifyRequests.add(messageNotifyRequest);
				}

				messages.add(message);
			}
			userIds.add(userId);
		});
	}

	private Message createOrUpdateMessage(Paper paper, PaperAction paperAction, User sender, User toUser) {
		Message message = messageRepository.findByBizRefIdAndReceiver(paper.getId(), toUser);
		Date now = new Date();
		if (message == null) {
			message = new Message();
			message.setCategory(paper.getCategory());
			message.setTitle(paper.getTitle());
			message.setBizRefType(BizRefType.PAPER);
			message.setBizRefId(paper.getId());
			message.setActionRefId(paperAction.getId());
			message.setInitiator(paper.getCreatedBy());
			message.setSender(sender);
			message.setReceiver(toUser);
			message.setReceivedAt(now);
			message.setModifiedAt(now);
			message.setStatus(MessageStatus.PENDING);
		}
		else {
			message.setActionRefId(paperAction.getId());
			message.setInitiator(paper.getCreatedBy());
			message.setSender(sender);
			message.setReceivedAt(now);
			message.setModifiedAt(now);
			message.setStatus(MessageStatus.PENDING);
		}
		return messageRepository.save(message);
	}

	private List<String> resolveParticipants(PaperAction paperAction) {
		List<String> result = new ArrayList<>();
		if (paperAction == null) {
			return result;
		}

		List<Receiver> to = paperAction.getToReceivers();
		if (to != null) {
			to.stream().forEach(receiver -> result.add(receiver.getUser().getId()));
		}

		List<Receiver> cc = paperAction.getCcReceivers();
		if (cc != null) {
			cc.stream().forEach(receiver -> result.add(receiver.getUser().getId()));
		}

		return result;
	}

}
