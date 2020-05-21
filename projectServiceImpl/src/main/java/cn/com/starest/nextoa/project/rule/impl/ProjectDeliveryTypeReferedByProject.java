package cn.com.starest.nextoa.project.rule.impl;

import cn.com.starest.nextoa.project.domain.model.ProjectDeliveryType;
import cn.com.starest.nextoa.project.domain.rule.ProjectDeliveryTypeReference;
import cn.com.starest.nextoa.project.repository.ProjectHistoryRepository;
import cn.com.starest.nextoa.project.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author dz
 */
@Component
public class ProjectDeliveryTypeReferedByProject implements ProjectDeliveryTypeReference {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private ProjectHistoryRepository projectHistoryRepository;

	@Override
	public boolean hasReference(ProjectDeliveryType target) {
		return (projectRepository.findFirstByDeliveryType(target) != null ||
				projectHistoryRepository.findFirstByDeliveryType(target) != null);
	}

}
