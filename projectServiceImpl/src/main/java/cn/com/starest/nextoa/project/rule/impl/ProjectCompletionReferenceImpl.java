package cn.com.starest.nextoa.project.rule.impl;

import cn.com.starest.nextoa.project.domain.model.ProjectCompletion;
import cn.com.starest.nextoa.project.domain.rule.ProjectCompletionReference;
import cn.com.starest.nextoa.project.repository.ProjectSettlementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author dz
 */
@Component
public class ProjectCompletionReferenceImpl implements ProjectCompletionReference {

	@Autowired
	private ProjectSettlementRepository projectSettlementRepository;

	@Override
	public boolean hasReference(ProjectCompletion target) {
		return projectSettlementRepository.findFirstByProjectCompletion(target) != null;
	}

}
