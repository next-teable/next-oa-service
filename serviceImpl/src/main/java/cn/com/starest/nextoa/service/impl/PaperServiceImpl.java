package cn.com.starest.nextoa.service.impl;

import cn.com.starest.nextoa.exception.PaperAttachmentException;
import cn.com.starest.nextoa.exception.PaperException;
import cn.com.starest.nextoa.exception.PaperNotFoundException;
import cn.com.starest.nextoa.model.*;
import cn.com.starest.nextoa.model.dtr.*;
import cn.com.starest.nextoa.repository.*;
import cn.com.starest.nextoa.service.PaperService;
import com.google.common.collect.Lists;
import in.clouthink.daas.edm.Edms;
import in.clouthink.daas.fss.core.FileObject;
import in.clouthink.daas.fss.spi.FileObjectService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 *
 */
@Service
public class PaperServiceImpl implements PaperService {

	private static final Log logger = LogFactory.getLog(PaperServiceImpl.class);

	@Autowired
	private PaperRepository paperRepository;

	@Autowired
	private PaperActionRepository paperActionRepository;

	@Autowired
	private PaperTransitionRepository paperTransitionRepository;

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private FavoriteMessageRepository favoriteMessageRepository;

	@Autowired
	private FileObjectService fileObjectService;

	@Override
	public Page<Paper> listPapers(PaperQueryRequest request) {
		return paperRepository.queryPage(null, request, PaperQueryRequest.IncludeOrExcludeStatus.INCLUDE);
	}

	@Override
	public Page<Paper> listPapers(PaperQueryRequest request,
								  PaperQueryRequest.IncludeOrExcludeStatus includeOrExcludeStatus,
								  User user) {
		return paperRepository.queryPage(user, request, includeOrExcludeStatus);
	}

	@Override
	public long countOfPapers(PaperQueryRequest request,
							  PaperQueryRequest.IncludeOrExcludeStatus includeOrExcludeStatus,
							  User user) {
		return paperRepository.queryCount(user, request, includeOrExcludeStatus);
	}

	@Override
	public Paper findPaperById(String id) {
		return paperRepository.findById(id);
	}

	@Override
	public Paper findPaperById(String id, User user) {
		Paper paper = paperRepository.findById(id);
		if (paper == null) {
			return null;
		}

		if (paper.getStatus() == PaperStatus.TERMINATED && !user.getAuthorities().contains(SysRole.ROLE_ADMIN)) {
			throw new PaperException("该快文已终止,只有超级管理员能查看.");
		}

		if (user.getAuthorities().contains(SysRole.ROLE_ADMIN) || user.getAuthorities().contains(SysRole.ROLE_MGR)) {
			return paper;
		}
		if (paper.getCreatedBy().getId().equals(user.getId())) {
			return paper;
		}

		Message message = messageRepository.findByBizRefIdAndReceiver(id, user);
		if (message != null) {
			return paper;
		}

		throw new PaperException("无查看该快文的权限");
	}

	@Override
	public Paper createPaper(SavePaperRequest request, User user) {
		checkUser(user);
		checkSavePaperRequest(request);

		Paper paper = new Paper();
		paper.setTitle(request.getTitle());
		paper.setType(request.getType());
		paper.setCategory(request.getCategory());
		paper.setUrgent(request.getUrgent());
		paper.setContent(request.getContent());
		paper.setCreatedAt(new Date());
		paper.setCreatedBy(user);
		paper.setStatus(PaperStatus.DRAFT);
		paper.setVersion(2);
		return paperRepository.save(paper);
	}

	@Override
	public Paper copyPaper(String id, User user) {
		Paper existedPaper = paperRepository.findById(id);
		if (existedPaper == null) {
			throw new PaperNotFoundException(id);
		}
		if (existedPaper.getStatus() == PaperStatus.TERMINATED) {
			throw new PaperException("该快文已终止,禁止操作.");
		}

		if (!Paper.isCopyAllowed(existedPaper)) {
			throw new PaperException("该快文禁止再处理.");
		}

		Paper paper = new Paper();
		paper.setTitle(existedPaper.getTitle());
		paper.setType(existedPaper.getType());
		paper.setCategory(existedPaper.getCategory());
		paper.setUrgent(existedPaper.isUrgent());
		paper.setContent(existedPaper.getContent());
		paper.setCreatedAt(new Date());
		paper.setCreatedBy(user);
		paper.setStatus(PaperStatus.DRAFT);
		paper.setVersion(2);
		return paperRepository.save(paper);
	}

	@Override
	public void updatePaper(String id, SavePaperRequest request, User user) {
		checkUser(user);
		checkSavePaperRequest(request);

		Paper paper = paperRepository.findById(id);
		if (paper == null) {
			throw new PaperNotFoundException(id);
		}
		if (paper.getStatus() == PaperStatus.TERMINATED) {
			throw new PaperException("该快文已终止,禁止操作.");
		}

		if (paper.getStatus() == PaperStatus.IN_PROGRESS) {
			if (!paper.getCreatedBy().getId().equals(user.getId())) {
				//流转过程中,且快文禁止编辑
				if (paper.getAllowedActions() == null || !paper.getAllowedActions().contains(PaperActionType.EDIT)) {
					throw new PaperException("不能修改非草稿或非撤回状态的快文");
				}
			}
		}

		paper.setType(request.getType());
		paper.setContent(request.getContent());
		paper.setUrgent(request.getUrgent());
		paper.setTitle(request.getTitle());
		paper.setCategory(request.getCategory());
		paper.setModifiedAt(new Date());
		paperRepository.save(paper);
	}

	@Override
	public void deletePaper(String id, User user) {
		checkUser(user);
		Paper paper = paperRepository.findById(id);
		if (paper == null) {
			return;
		}
		if (paper.getStatus() == PaperStatus.TERMINATED) {
			throw new PaperException("该快文已终止,禁止操作.");
		}

		// 只能删除草稿,撤回状态的快文
		if (paper.getStatus() == PaperStatus.IN_PROGRESS) {
			throw new PaperException("不能删除流转中快文");
		}

		if (paper.getStatus() == PaperStatus.REVOKED) {
			List<Message> messageList = messageRepository.findByBizRefId(paper.getId());
			messageRepository.delete(messageList);
		}

		paperRepository.delete(paper);
	}

	@Override
	public void revokePaper(String id, User user) {
		checkUser(user);
		Paper paper = paperRepository.findById(id);
		if (paper == null) {
			throw new PaperNotFoundException(id);
		}
		if (paper.getStatus() == PaperStatus.TERMINATED) {
			throw new PaperException("该快文已终止,禁止操作.");
		}

		if (!paper.getCreatedBy().getId().equalsIgnoreCase(user.getId())) {
			throw new PaperException("只能快文的创建人能撤回快文");
		}

		if (paper.getStatus() != PaperStatus.IN_PROGRESS) {
			throw new PaperException("只能撤回已流转的快文");
		}

		if (!paper.getStartPaperAction().getId().equals(paper.getLatestPaperAction().getId())) {
			throw new PaperException("快文已被处理,不能进行撤回操作.");
		}

		List<Message> messageList = messageRepository.findByBizRefId(paper.getId());
		for (Message message : messageList) {
			if (!message.getReceiver().getId().equalsIgnoreCase(paper.getCreatedBy().getId()) &&
				message.getStatus() == MessageStatus.PROCESSED) {
				throw new PaperException("快文已被处理,不能进行撤回操作.");
			}
		}

		PaperAction paperAction = new PaperAction();
		paperAction.setCreatedAt(new Date());
		paperAction.setCreatedBy(user);
		paperAction.setPaper(paper);
		paperAction.setType(PaperActionType.REVOKE);

		Edms.getEdm("paper").dispatch(PaperAction.REVOKE_ACTION, paperAction);
	}

	@Override
	public void startPaper(String id, StartPaperRequest request, User user) {
		checkUser(user);
		Paper paper = paperRepository.findById(id);
		if (paper == null) {
			throw new PaperNotFoundException(id);
		}
		checkSavedPaper(paper);
		if (paper.getStatus() == PaperStatus.TERMINATED) {
			throw new PaperException("该快文已终止,禁止操作.");
		}

		if (paper.getStatus() == PaperStatus.IN_PROGRESS) {
			throw new PaperException("该快文已经进入流转,请勿重复提交");
		}

		List<Receiver> toReceivers = request.getToReceivers();
		if (toReceivers == null || toReceivers.isEmpty()) {
			throw new PaperException("请选择主送人后再提交快文.");
		}

		List<Receiver> ccReceivers = request.getCcReceivers();

		List<PaperActionType> disabledActions = request.getDisabledActions();
		List<PaperActionType> allowedActions = Lists.newArrayList(PaperActionType.PRINT,
																  PaperActionType.FORWARD,
																  PaperActionType.EDIT,
																  PaperActionType.COPY);
		disabledActions.stream().forEach(disabledAction -> allowedActions.remove(disabledAction));

		PaperAction startPaperAction = new PaperAction();
		startPaperAction.setPaper(paper);
		startPaperAction.setType(PaperActionType.START);
		startPaperAction.setToReceivers(toReceivers);
		startPaperAction.setCcReceivers(ccReceivers);
		startPaperAction.setAllowedActions(allowedActions);
		startPaperAction.setCreatedBy(user);
		startPaperAction.setCreatedAt(new Date());

		Edms.getEdm("paper").dispatch(PaperAction.START_ACTION, startPaperAction);
	}

	@Override
	public void printPaper(String id, User user) {
		checkUser(user);
		Paper paper = paperRepository.findById(id);
		if (paper == null) {
			throw new PaperNotFoundException(id);
		}
		if (paper.getStatus() == PaperStatus.TERMINATED) {
			throw new PaperException("该快文已终止,禁止操作.");
		}

		List<PaperActionType> allowedActions = paper.getAllowedActions();
		if (allowedActions == null || !allowedActions.contains(PaperActionType.PRINT)) {
			throw new PaperException("该快文禁止打印.");
		}
		PaperAction printPaperAction = new PaperAction();
		printPaperAction.setPaper(paper);
		printPaperAction.setType(PaperActionType.PRINT);
		printPaperAction.setCreatedBy(user);
		printPaperAction.setCreatedAt(new Date());
		paperActionRepository.save(printPaperAction);
	}

	@Override
	public void replyPaper(String id, ReplyPaperRequest request, User user) {
		checkUser(user);

		Message message = messageRepository.findByBizRefIdAndReceiver(id, user);
		if (message != null && message.getStatus() == MessageStatus.PROCESSED) {
			throw new PaperException("该快文任务已经处理过了,不能重复处理.");
		}

		Paper paper = paperRepository.findById(id);
		if (paper == null) {
			throw new PaperNotFoundException(id);
		}
		if (paper.getStatus() == PaperStatus.TERMINATED) {
			throw new PaperException("该快文已终止,禁止操作.");
		}
		if (paper.getStatus() == PaperStatus.REVOKED) {
			throw new PaperException("该快文已撤回,不能回复");
		}
		if (paper.getStatus() != PaperStatus.IN_PROGRESS) {
			throw new PaperException("该快文未进入流转,不能回复");
		}

		List<Receiver> toReceivers = request.getToReceivers();
		if (toReceivers == null || toReceivers.isEmpty()) {
			throw new PaperException("请选择回复人.");
		}

		List<Receiver> ccReceivers = request.getCcReceivers();

		PaperAction replyPaperAction = new PaperAction();
		replyPaperAction.setPaper(paper);
		if (Paper.isEditAllowed(paper)) {
			replyPaperAction.setPaperContent(request.getPaperContent());
		}
		replyPaperAction.setType(PaperActionType.REPLY);
		replyPaperAction.setToReceivers(toReceivers);
		replyPaperAction.setCcReceivers(ccReceivers);
		replyPaperAction.setContent(request.getContent());
		replyPaperAction.setCreatedBy(user);
		replyPaperAction.setCreatedAt(new Date());

		PaperAction previousPaperAction = resolvePaperAction(request.getMessageId());

		Edms.getEdm("paper")
			.dispatch(PaperAction.REPLY_ACTION, new PaperTransitionRequest(previousPaperAction, replyPaperAction));
	}

	@Override
	public void forwardPaper(String id, ForwardPaperRequest request, User user) {
		checkUser(user);

		Message message = messageRepository.findByBizRefIdAndReceiver(id, user);
		if (message != null && message.getStatus() == MessageStatus.PROCESSED) {
			throw new PaperException("该快文任务已经处理过了,不能重复处理.");
		}

		Paper paper = paperRepository.findById(id);
		if (paper == null) {
			throw new PaperNotFoundException(id);
		}
		if (paper.getStatus() == PaperStatus.TERMINATED) {
			throw new PaperException("该快文已终止,禁止操作.");
		}
		if (paper.getStatus() == PaperStatus.REVOKED) {
			throw new PaperException("该快文已撤回,不能转发");
		}
		if (paper.getStatus() != PaperStatus.IN_PROGRESS) {
			throw new PaperException("该快文未进入流转,不能转发");
		}

		List<PaperActionType> allowedActions = paper.getAllowedActions();
		if (allowedActions == null || !allowedActions.contains(PaperActionType.FORWARD)) {
			throw new PaperException("该快文禁止转发.");
		}

		List<Receiver> toReceivers = request.getToReceivers();
		if (toReceivers == null || toReceivers.isEmpty()) {
			throw new PaperException("请选择主送人后再转发快文.");
		}

		List<Receiver> ccReceivers = request.getCcReceivers();

		PaperAction forwardPaperAction = new PaperAction();
		forwardPaperAction.setPaper(paper);
		if (Paper.isEditAllowed(paper)) {
			forwardPaperAction.setPaperContent(request.getPaperContent());
		}
		forwardPaperAction.setType(PaperActionType.FORWARD);
		forwardPaperAction.setToReceivers(toReceivers);
		forwardPaperAction.setCcReceivers(ccReceivers);
		forwardPaperAction.setContent(request.getContent());
		forwardPaperAction.setCreatedBy(user);
		forwardPaperAction.setCreatedAt(new Date());

		PaperAction previousPaperAction = resolvePaperAction(request.getMessageId());

		Edms.getEdm("paper")
			.dispatch(PaperAction.FORWARD_ACTION, new PaperTransitionRequest(previousPaperAction, forwardPaperAction));
	}

	@Override
	public void endPaper(String id, User user) {
		checkUser(user);

		Message message = messageRepository.findByBizRefIdAndReceiver(id, user);
		if (message != null && message.getStatus() == MessageStatus.PROCESSED) {
			throw new PaperException("该快文任务已经处理过了,不能重复处理.");
		}

		Paper paper = paperRepository.findById(id);
		if (paper == null) {
			throw new PaperNotFoundException(id);
		}

		endPaper(paper, user);
	}

	@Override
	public void endPaper(Paper paper, User user) {
		checkUser(user);
		if (paper.getStatus() == PaperStatus.TERMINATED) {
			throw new PaperException("该快文已终止,禁止操作.");
		}
		if (paper.getStatus() == PaperStatus.REVOKED) {
			throw new PaperException("该快文已撤回,不能结束");
		}
		if (paper.getStatus() != PaperStatus.IN_PROGRESS) {
			throw new PaperException("该快文未进入流转,不能结束");
		}

		PaperAction endPaperAction = new PaperAction();
		endPaperAction.setPaper(paper);
		endPaperAction.setType(PaperActionType.END);
		endPaperAction.setCreatedBy(user);
		endPaperAction.setCreatedAt(new Date());

		Edms.getEdm("paper").dispatch(PaperAction.END_ACTION, endPaperAction);
	}

	@Override
	public void terminatePaper(String id, User user) {
		Paper paper = paperRepository.findById(id);
		if (paper == null) {
			throw new PaperNotFoundException(id);
		}

		terminatePaper(paper, user);
	}

	@Override
	public void terminatePaper(Paper paper, User user) {
		if (!user.getAuthorities().contains(SysRole.ROLE_ADMIN)) {
			throw new PaperException("只有超级管理员能终止快文");
		}

		PaperAction terminatePaperAction = new PaperAction();
		terminatePaperAction.setPaper(paper);
		terminatePaperAction.setType(PaperActionType.TERMINATE);
		terminatePaperAction.setCreatedBy(user);
		terminatePaperAction.setCreatedAt(new Date());

		Edms.getEdm("paper").dispatch(PaperAction.TERMINATE_ACTION, terminatePaperAction);
	}

	@Override
	public PaperAction findPaperActionById(String id) {
		return paperActionRepository.findById(id);
	}

	@Override
	public Page<PaperAction> getPaperActionHistory(String id, PaperActionQueryRequest request) {
		Paper paper = paperRepository.findById(id);
		if (paper == null) {
			throw new PaperNotFoundException(id);
		}

		return paperActionRepository.queryPage(paper, request);
	}

	@Override
	@Deprecated
	public List<PaperAction> getPaperActionHistoryList(String id, PaperActionQueryRequest queryRequest) {
		Paper paper = paperRepository.findById(id);
		if (paper == null) {
			throw new PaperNotFoundException(id);
		}
		return paperActionRepository.queryList(paper, queryRequest);
	}

	@Override
	public List<PaperAction> getPaperProcessHistoryList(String id, User user) {
		//Caution: to improve the performance , the paper transition id equals the paper action id whatever it's current, previous or next
		Paper paper = paperRepository.findById(id);
		if (paper == null) {
			throw new PaperNotFoundException(id);
		}
		String currentUserId = user.getId();
		List<PaperTransition> paperTransitions = paperTransitionRepository.findListByPaperOrderByCreatedAtDesc(paper);
		List<String> matchedPaperActionIds = new ArrayList<>();
		Map<String,PaperTransition> paperTransitionMap = new HashMap<>();
		paperTransitions.stream()
						.forEach(paperTransition -> paperTransitionMap.put(paperTransition.getId(), paperTransition));
		paperTransitions.stream().forEach(paperTransition -> {
			//won't re-process the transition to improve the performance
			if (paperTransition.isProcessed()) {
				return;
			}

			//back-ward to get the previous paper transition
			PaperTransition paperTransTmp4Prev = null;
			String prevActonIdTmp = paperTransition.getPreviousActionId();
			if (!StringUtils.isEmpty(prevActonIdTmp)) {
				paperTransTmp4Prev = paperTransitionMap.get(prevActonIdTmp);
			}

			//add to result if matched the criteria
			if (paperTransition.getParticipantIds().contains(currentUserId) ||
				currentUserId.equalsIgnoreCase(paperTransition.getCreatorId()) ||
				(paperTransTmp4Prev != null && currentUserId.equalsIgnoreCase(paperTransTmp4Prev.getCreatorId()))) {
				if (paperTransition.getPaperActionType() != PaperActionType.START) {
					matchedPaperActionIds.add(paperTransition.getCurrentActionId());
				}

				//handle the link in backward direction
				PaperTransition paperTransitionIterator = paperTransition;
				while (paperTransitionIterator.getPreviousActionId() != null) {
					String previousActionId = paperTransitionIterator.getPreviousActionId();
					PaperTransition previousPaperTransition = paperTransitionMap.get(previousActionId);

					//update the iterator to previous paper transition
					paperTransitionIterator = previousPaperTransition;

					// if the previous transition is processed, continue the backward logic
					if (previousPaperTransition.isProcessed()) {
						continue;
					}

					//add to result because it's on the backward branch (which is matched at the leaf node)
					if (previousPaperTransition.getPaperActionType() != PaperActionType.START) {
						matchedPaperActionIds.add(previousPaperTransition.getCurrentActionId());
					}
					previousPaperTransition.setProcessed(true);
				}
			}
			paperTransition.setProcessed(true);
		});

		return paperActionRepository.findListByIdInOrderByCreatedAtDesc(matchedPaperActionIds.toArray(new String[]{}));
	}

	@Override
	public boolean isRead(Paper paper, User user) {
		return paperActionRepository.findFirstByPaperAndTypeAndCreatedBy(paper, PaperActionType.READ, user) != null;
	}

	@Override
	public boolean isFavorite(Paper paper, User user) {
		Message message = messageRepository.findByBizRefIdAndReceiver(paper.getId(), user);
		if (message == null) {
			return false;
		}

		return favoriteMessageRepository.findByMessageAndCreatedBy(message, user) != null;
	}

	@Override
	public void deletePaperAttachment(String paperId, String fileId, User user) {
		Paper paper = paperRepository.findById(paperId);
		if (paper == null) {
			throw new PaperNotFoundException(paperId);
		}

		if (paper.getStatus() == null ||
			paper.getStatus() == PaperStatus.DRAFT ||
			paper.getStatus() == PaperStatus.REVOKED) {
			FileObject fileObject = fileObjectService.findById(fileId);
			String uploadedBy = fileObject.getUploadedBy();
			if (!user.getUsername().equals(uploadedBy) &&
				!user.getUsername().equals(paper.getCreatedBy().getUsername())) {
				throw new PaperAttachmentException("只能删除自己上传的附件.");
			}
			try {
				fileObjectService.deleteById(fileId);
			}
			catch (Exception e) {
			}
			return;
		}

		if (paper.getStatus() == PaperStatus.IN_PROGRESS) {
			if (!Paper.isEditAllowed(paper)) {
				throw new PaperAttachmentException("该快文禁止编辑,不能删除附件.");
			}
			try {
				fileObjectService.deleteById(fileId);
			}
			catch (Exception e) {
			}
			return;
		}

		try {
			logger.warn(String.format("User[username=%s] delete Paper[id=%s,status=%]#File[id=%s]",
									  user.getUsername(),
									  paperId,
									  paper.getStatus(),
									  fileId));
			fileObjectService.deleteById(fileId);
		}
		catch (Exception e) {
		}
	}

	@Override
	public void markPaperBusinessComplete(Paper paper) {
		if (paper == null) {
			return;
		}
		paperRepository.markPaperBusinessComplete(paper);
	}

	private void checkSavePaperRequest(SavePaperRequest request) {
		// 要求刚进入时可以自动保存,去掉验证
		// if (StringUtils.isEmpty(request.getTitle())) {
		// throw new PaperException("标题不能为空");
		// }
		//
		// if (StringUtils.isEmpty(request.getContent())) {
		// throw new PaperException("内容不能为空");
		// }
	}

	private void checkSavedPaper(Paper paper) {
		if (StringUtils.isEmpty(paper.getTitle())) {
			throw new PaperException("标题不能为空");
		}

		if (StringUtils.isEmpty(paper.getContent())) {
			throw new PaperException("内容不能为空");
		}
	}

	private void checkUser(User user) {
		if (user.getUserType() == UserType.SYSUSER) {
			throw new PaperException("系统用户不能操作快文.");
		}
	}

	private PaperAction resolvePaperAction(String messageId) {
		if (StringUtils.isEmpty(messageId)) {
			return null;
		}

		Message message = messageRepository.findById(messageId);
		if (message == null || StringUtils.isEmpty(message.getActionRefId())) {
			return null;
		}

		return paperActionRepository.findById(message.getActionRefId());
	}

}
