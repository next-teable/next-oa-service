package cn.com.starest.nextoa.project.service.impl;

import cn.com.starest.nextoa.project.domain.model.Contract;
import cn.com.starest.nextoa.project.domain.model.ProjectStatus;
import cn.com.starest.nextoa.project.repository.ContractRepository;
import cn.com.starest.nextoa.project.repository.ProjectRepository;
import in.clouthink.daas.edm.Edms;
import in.clouthink.daas.edm.EventListener;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author dz
 */
@Component
public class ProjectCompletionByContractListener implements EventListener<Contract>, InitializingBean {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private ContractRepository contractRepository;

	@Override
	public void onEvent(Contract contract) {
		if (contract == null) {
			return;
		}
		if (contract.getProjects() == null || contract.getProjects().isEmpty()) {
			return;
		}

		if (!contract.isCompleted()) {
			contract.getProjects().forEach(project -> project.setStatus(ProjectStatus.RUNNING));
			projectRepository.save(contract.getProjects());
			return;
		}

		contract.getProjects().forEach(project -> {
			if (contractRepository.findListByProjectsIn(project)
								  .stream()
								  .filter(item -> !item.isCompleted())
								  .count() == 0) {
				project.setStatus(ProjectStatus.COMPLETED);
				projectRepository.save(project);
			}
		});
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Edms.getEdm("project").register("completeByContract", this);
	}

}
