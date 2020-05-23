package cn.com.starest.nextoa.service.impl;

import cn.com.starest.nextoa.exception.PaperNotFoundException;
import cn.com.starest.nextoa.model.*;
import cn.com.starest.nextoa.repository.PaperActionRepository;
import cn.com.starest.nextoa.repository.PaperRepository;
import cn.com.starest.nextoa.repository.PaperTransitionRepository;
import cn.com.starest.nextoa.service.PaperIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dz
 */
@Service
public class PaperIssueServiceImpl implements PaperIssueService {

	@Autowired
	private PaperRepository paperRepository;

	@Autowired
	private PaperActionRepository paperActionRepository;

	@Autowired
	private PaperTransitionRepository paperTransitionRepository;

	@Override
	public void fixPaperTransitionIssues(String id, User byWho) {
		if (!"administrator".equals(byWho.getUsername())) {
			throw new RuntimeException("Only the administrator is allowed to access.");
		}

		Paper paper = paperRepository.findById(id);
		if (paper == null) {
			throw new PaperNotFoundException(id);
		}

		List<PaperAction> paperActionList = paperActionRepository.findListByPaper(paper);

		paperActionList.stream().forEach(action -> {
			PaperTransition paperTransition = paperTransitionRepository.findById(action.getId());
			if (paperTransition == null) {
				paperTransition = new PaperTransition();
				paperTransition.setId(action.getId());
				paperTransition.setPaper(paper);
				paperTransition.setPaperActionType(action.getType());
				paperTransition.setCreatorId(action.getCreatedBy().getId());
				paperTransition.setCreatedBy(action.getCreatedBy());
				paperTransition.setCreatedAt(action.getCreatedAt());
				paperTransition.setCurrentActionId(action.getId());
				paperTransition.setPreviousActionId(action.getPreviousActionId());
				paperTransition.setParticipantIds(resolveParticipants(action));
				paperTransitionRepository.save(paperTransition);
			}
		});

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
