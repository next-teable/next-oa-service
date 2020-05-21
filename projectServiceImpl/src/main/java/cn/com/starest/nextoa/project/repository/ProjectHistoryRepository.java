package cn.com.starest.nextoa.project.repository;

import cn.com.starest.nextoa.project.domain.model.*;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 */
public interface ProjectHistoryRepository extends AbstractRepository<ProjectHistory> {

	Page<ProjectHistory> findPageByProject(Project project, Pageable pageable);

	void deleteByProject(Project project);

	ProjectHistory findFirstByProjectType(ProjectType projectType);

	ProjectHistory findFirstByFirstParty(FirstParty firstParty);

	ProjectHistory findFirstByDeliveryType(ProjectDeliveryType projectDeliveryType);

	ProjectHistory findFirstByCompany(Company target);
}
