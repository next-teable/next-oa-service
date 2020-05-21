package cn.com.starest.nextoa.project.rule.impl;

import cn.com.starest.nextoa.project.domain.model.ProjectType;
import cn.com.starest.nextoa.project.domain.rule.ProjectTypeReference;
import cn.com.starest.nextoa.project.repository.ProjectHistoryRepository;
import cn.com.starest.nextoa.project.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author dz
 */
@Component
public class ProjectTypeReferedByProject implements ProjectTypeReference {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private ProjectHistoryRepository projectHistoryRepository;

	@Override
	public boolean hasReference(ProjectType target) {
		return (projectRepository.findFirstByProjectType(target) != null ||
				projectHistoryRepository.findFirstByProjectType(target) != null);
	}

}
