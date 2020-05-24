package in.clouthink.nextoa.bl.openapi.support.impl;

import in.clouthink.nextoa.bl.model.*;
import in.clouthink.nextoa.bl.openapi.dto.*;
import in.clouthink.nextoa.bl.openapi.support.SysPaperRestSupport;
import in.clouthink.nextoa.bl.service.MessageService;
import in.clouthink.nextoa.bl.service.OrganizationService;
import in.clouthink.nextoa.bl.service.PaperService;
import in.clouthink.nextoa.shared.domain.params.PageQueryParameter;
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
public class SysPaperRestSupportImpl implements SysPaperRestSupport {

	@Autowired
	private PaperService paperService;

	@Autowired
	private OrganizationService organizationService;

	@Autowired
	private MessageService messageService;

	@Override
	public Page<PaperSummary> listAllPapers(PaperQueryParameter queryRequest) {
		queryRequest.setPaperStatus(null);
		Page<Paper> paperPage = paperService.listPapers(queryRequest);
		return new PageImpl<>(paperPage.getContent().stream().map(PaperSummary::from).collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  paperPage.getTotalElements());
	}

	@Override
	public PaperDetail getPaperDetail(String id, User user) {
		Paper paper = paperService.findPaperById(id, user);
		if (paper == null) {
			return null;
		}
		return PaperDetail.from(paper);
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
	public List<PaperProcessSummary> getPaperProcessHistory(String id) {
		PaperActionQueryParameter queryRequest = new PaperActionQueryParameter();
		queryRequest.setPaperActionTypes(PaperActionType.REPLY, PaperActionType.FORWARD);
		return paperService.getPaperActionHistoryList(id, queryRequest)
						   .stream()
						   .map(PaperProcessSummary::from)
						   .collect(Collectors.toList());
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


	@Override
	public void terminatePaper(String id, User user) {
		paperService.terminatePaper(id, user);
	}

}
