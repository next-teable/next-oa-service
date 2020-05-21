package cn.com.starest.nextoa.project.rule.impl;

import cn.com.starest.nextoa.project.domain.model.FirstParty;
import cn.com.starest.nextoa.project.domain.rule.FirstPartyReference;
import cn.com.starest.nextoa.project.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author dz
 */
@Component
public class FirstPartyReferenceImpl implements FirstPartyReference {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private ProjectHistoryRepository projectHistoryRepository;

	@Autowired
	private ContractRepository contractRepository;

	@Autowired
	private ContractHistoryRepository contractHistoryRepository;

	@Autowired
	private IssueInvoiceRepository issueInvoiceRepository;

	@Override
	public boolean hasReference(FirstParty target) {
		return contractRepository.findFirstByFirstParty(target) != null ||
			   contractHistoryRepository.findFirstByFirstParty(target) != null ||
			   projectRepository.findFirstByFirstParty(target) != null ||
			   projectHistoryRepository.findFirstByFirstParty(target) != null ||
			   issueInvoiceRepository.findFirstByFirstParty(target) != null;
	}

}
