package in.clouthink.nextoa.openapi.support.impl;

import in.clouthink.nextoa.exception.PaperNotFoundException;
import cn.com.starest.nextoa.model.*;
import in.clouthink.nextoa.model.dtr.PaperQueryRequest;
import in.clouthink.nextoa.model.dtr.ReadPaperEvent;
import cn.com.starest.nextoa.openapi.dto.*;
import in.clouthink.nextoa.openapi.dto.*;
import in.clouthink.nextoa.openapi.support.PaperRestSupport;
import in.clouthink.nextoa.openapi.support.ReceiverBuilder;
import in.clouthink.nextoa.service.MessageService;
import in.clouthink.nextoa.service.OrganizationService;
import in.clouthink.nextoa.service.PaperService;
import in.clouthink.daas.edm.Edms;
import in.clouthink.nextoa.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@Service
public class PaperRestSupportImpl implements PaperRestSupport, ReceiverBuilder {

	@Autowired
	private PaperService paperService;

	@Autowired
	private MessageService messageService;

	@Autowired
	private OrganizationService organizationService;

	@Override
	public Page<PaperSummary> listAllPapers(PaperQueryParameter queryRequest, User user) {
		queryRequest.setPaperStatus(PaperStatus.TERMINATED);
		Page<Paper> paperPage = paperService.listPapers(queryRequest,
														PaperQueryRequest.IncludeOrExcludeStatus.EXCLUDE,
														user);
		return new PageImpl<>(paperPage.getContent()
									   .stream()
									   .map(paper -> convertToPaperSummary(paper, user))
									   .collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  paperPage.getTotalElements());
	}

	@Override
	public Page<PaperSummary> listDraftPapers(PaperQueryParameter queryRequest, User user) {
		queryRequest.setPaperStatus(PaperStatus.DRAFT);
		Page<Paper> paperPage = paperService.listPapers(queryRequest,
														PaperQueryRequest.IncludeOrExcludeStatus.INCLUDE,
														user);
		return new PageImpl<>(paperPage.getContent()
									   .stream()
									   .map(paper -> convertToPaperSummary(paper, user))
									   .collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  paperPage.getTotalElements());
	}

	@Override
	public Page<PaperSummary> listProcessingPapers(PaperQueryParameter queryRequest, User user) {
		queryRequest.setPaperStatus(PaperStatus.IN_PROGRESS);
		Page<Paper> paperPage = paperService.listPapers(queryRequest,
														PaperQueryRequest.IncludeOrExcludeStatus.INCLUDE,
														user);
		return new PageImpl<>(paperPage.getContent()
									   .stream()
									   .map(paper -> convertToPaperSummary(paper, user))
									   .collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  paperPage.getTotalElements());
	}

	@Override
	public Page<PaperSummary> listRevokedPapers(PaperQueryParameter queryRequest, User user) {
		queryRequest.setPaperStatus(PaperStatus.REVOKED);
		Page<Paper> paperPage = paperService.listPapers(queryRequest,
														PaperQueryRequest.IncludeOrExcludeStatus.INCLUDE,
														user);
		return new PageImpl<>(paperPage.getContent()
									   .stream()
									   .map(paper -> convertToPaperSummary(paper, user))
									   .collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  paperPage.getTotalElements());
	}

	@Override
	public long countOfAllPapers(PaperQueryParameter queryRequest, User user) {
		queryRequest.setPaperStatus(PaperStatus.TERMINATED);
		return paperService.countOfPapers(queryRequest, PaperQueryRequest.IncludeOrExcludeStatus.EXCLUDE, user);
	}

	@Override
	public long countOfDraftPapers(PaperQueryParameter queryRequest, User user) {
		queryRequest.setPaperStatus(PaperStatus.DRAFT);
		return paperService.countOfPapers(queryRequest, PaperQueryRequest.IncludeOrExcludeStatus.INCLUDE, user);
	}

	@Override
	public long countOfProcessingPapers(PaperQueryParameter queryRequest, User user) {
		queryRequest.setPaperStatus(PaperStatus.IN_PROGRESS);
		return paperService.countOfPapers(queryRequest, PaperQueryRequest.IncludeOrExcludeStatus.INCLUDE, user);
	}

	@Override
	public long countOfRevokedPapers(PaperQueryParameter queryRequest, User user) {
		queryRequest.setPaperStatus(PaperStatus.REVOKED);
		return paperService.countOfPapers(queryRequest, PaperQueryRequest.IncludeOrExcludeStatus.INCLUDE, user);
	}

	@Override
	public PaperDetail getPaperDetail(String id, User user) {
		Paper paper = paperService.findPaperById(id, user);
		if (paper == null) {
			return null;
		}
		PaperDetail result = PaperDetail.from(paper);
		result.setFavorite(paperService.isFavorite(paper, user));
		result.setRead(paperService.isRead(paper, user));
		return result;
	}

	@Override
	public PaperDetail copyPaperDetail(String id, User user) {
		return PaperDetail.from(paperService.copyPaper(id, user));
	}

	@Override
	public List<String> getPaperAllowedActions(String id, User user) {
		Paper paper = paperService.findPaperById(id, user);
		if (paper.getAllowedActions() == null) {
			return Collections.emptyList();
		}
		return paper.getAllowedActions().stream().map(actionType -> actionType.name()).collect(Collectors.toList());
	}

	@Override
	public String createPaper(SavePaperParameter request, User user) {
		Paper paper = paperService.createPaper(request, user);
		return paper.getId();
	}

	@Override
	public void updatePaper(String id, SavePaperParameter request, User user) {
		paperService.updatePaper(id, request, user);
	}

	@Override
	public void deletePaper(String id, User user) {
		paperService.deletePaper(id, user);
	}

	@Override
	public void revokePaper(String id, User user) {
		paperService.revokePaper(id, user);
	}

	@Override
	public void startPaper(String id, StartPaperParameter parameter, User user) {
		StartPaperRequestParameter startRequest = new StartPaperRequestParameter();
		startRequest.setDisabledActions(parameter.getDisabledActions());
		startRequest.setToReceivers(buildReceivers(parameter.getTo()));
		startRequest.setCcReceivers(buildReceivers(parameter.getCc()));

		paperService.startPaper(id, startRequest, user);
	}

	@Override
	public void replyPaper(String id, ReplyPaperParameter parameter, User user) {
		PaperActionRequestParameter request = new PaperActionRequestParameter();
		request.setMessageId(parameter.getMessageId());
		request.setContent(parameter.getContent());
		request.setPaperContent(parameter.getPaperContent());
		request.setToReceivers(buildReceivers(parameter.getTo()));
		request.setCcReceivers(buildReceivers(parameter.getCc()));


		paperService.replyPaper(id, request, user);
	}

	@Override
	public void forwardPaper(String id, ForwardPaperParameter parameter, User user) {
		PaperActionRequestParameter request = new PaperActionRequestParameter();
		request.setMessageId(parameter.getMessageId());
		request.setContent(parameter.getContent());
		request.setPaperContent(parameter.getPaperContent());
		request.setToReceivers(buildReceivers(parameter.getTo()));
		request.setCcReceivers(buildReceivers(parameter.getCc()));

		paperService.forwardPaper(id, request, user);
	}

	@Override
	public void printPaper(String id, User user) {
		paperService.printPaper(id, user);
	}

	public void markPaperAsRead(String id, User user) {
		Paper paper = paperService.findPaperById(id, user);
		if (paper == null) {
			throw new PaperNotFoundException(id);
		}
		Edms.getEdm().dispatch(ReadPaperEvent.EVENT_NAME, new ReadPaperEventObject(paper, user));
	}

	@Override
	public void markPaperAsDone(String id, User user) {
		paperService.endPaper(id, user);
	}

	@Override
	public Page<PaperReadSummary> getPaperReadHistory(String id, PaperActionQueryParameter queryRequest) {
		queryRequest.setPaperActionTypes(PaperActionType.READ);
		Page<PaperAction> paperActionPage = paperService.getPaperActionHistory(id, queryRequest);
		return new PageImpl<>(paperActionPage.getContent()
											 .stream()
											 .map(PaperReadSummary::from)
											 .collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  paperActionPage.getTotalElements());
	}

	@Override
	public Page<PaperPrintSummary> getPaperPrintHistory(String id, PaperActionQueryParameter queryRequest) {
		queryRequest.setPaperActionTypes(PaperActionType.PRINT);
		Page<PaperAction> paperActionPage = paperService.getPaperActionHistory(id, queryRequest);
		return new PageImpl<>(paperActionPage.getContent()
											 .stream()
											 .map(PaperPrintSummary::from)
											 .collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  paperActionPage.getTotalElements());
	}

	@Override
	public Page<PaperTransitionSummary> getPaperTransitionHistory(String id, PaperActionQueryParameter queryRequest) {
		queryRequest.setPaperActionTypes(PaperActionType.START, PaperActionType.REPLY, PaperActionType.FORWARD);
		Page<PaperAction> paperActionPage = paperService.getPaperActionHistory(id, queryRequest);
		return new PageImpl<>(paperActionPage.getContent()
											 .stream()
											 .map(PaperTransitionSummary::from)
											 .collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  paperActionPage.getTotalElements());
	}

	@Override
	public Page<PaperTransitionSummary> getPaperEndHistory(String id, PaperActionQueryParameter queryRequest) {
		queryRequest.setPaperActionTypes(PaperActionType.END);
		Page<PaperAction> paperActionPage = paperService.getPaperActionHistory(id, queryRequest);
		return new PageImpl<>(paperActionPage.getContent()
											 .stream()
											 .map(PaperTransitionSummary::from)
											 .collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  paperActionPage.getTotalElements());
	}

	@Override
	public Page<PaperProcessSummary> getPaperProcessHistory(String id, PaperActionQueryParameter queryRequest) {
		queryRequest.setPaperActionTypes(PaperActionType.REPLY, PaperActionType.FORWARD);
		Page<PaperAction> paperActionPage = paperService.getPaperActionHistory(id, queryRequest);
		return new PageImpl<>(paperActionPage.getContent()
											 .stream()
											 .map(PaperProcessSummary::from)
											 .collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  paperActionPage.getTotalElements());
	}

	@Override
	public List<PaperProcessSummary> getPaperProcessHistory(String id, User byWho) {
		Paper paper = paperService.findPaperById(id);
		if (paper == null) {
			throw new PaperNotFoundException(id);
		}

		if (paper.getVersion() == 1 || paper.getVersion() == 0) {
			PaperActionQueryParameter queryRequest = new PaperActionQueryParameter();
			queryRequest.setPaperActionTypes(PaperActionType.REPLY, PaperActionType.FORWARD);
			return paperService.getPaperActionHistoryList(id, queryRequest)
							   .stream()
							   .map(PaperProcessSummary::from)
							   .collect(Collectors.toList());
		}
		else {
			return paperService.getPaperProcessHistoryList(id, byWho)
							   .stream()
							   .map(PaperProcessSummary::from)
							   .collect(Collectors.toList());
		}

	}

	@Override
	public Receiver buildReceiver(ReceiverParameter receiverParameter) {
		Receiver receiver = new Receiver();
		receiver.setUser(organizationService.findAppUserById(receiverParameter.getUserId()));
		receiver.setNotifyEnabled(receiverParameter.isNotify());
		return receiver;
	}

	@Override
	public List<Receiver> buildReceivers(List<ReceiverParameter> parameters) {
		return parameters.stream()
						 .map(receiverParameter -> buildReceiver(receiverParameter))
						 .collect(Collectors.toList());
	}

	@Override
	public void deletePaperAttachment(String paperId, String fileId, User user) {
		paperService.deletePaperAttachment(paperId, fileId, user);
	}

	@Override
	public Page<PaperMessageSummary> getPaperMessages(String id, PageQueryParameter queryRequest) {
		Page<Message> messagePage = messageService.listPaperMessages(id, queryRequest);
		return new PageImpl<>(messagePage.getContent()
										 .stream()
										 .map(PaperMessageSummary::from)
										 .collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  messagePage.getTotalElements());
	}

	private PaperSummary convertToPaperSummary(Paper paper, User user) {
		PaperSummary result = PaperSummary.from(paper);
		result.setFavorite(paperService.isFavorite(paper, user));
		if (paper.getStatus() == PaperStatus.REVOKED || paper.getStatus() == PaperStatus.DRAFT) {
			result.setRead(true);
		}
		else {
			result.setRead(paperService.isRead(paper, user));
		}
		return result;
	}


}
