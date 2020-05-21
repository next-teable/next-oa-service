package cn.com.starest.nextoa.project.repository;

import cn.com.starest.nextoa.project.domain.model.Contract;
import cn.com.starest.nextoa.project.domain.model.Order;
import cn.com.starest.nextoa.project.domain.model.Project;
import cn.com.starest.nextoa.project.domain.model.ProjectCompletion;
import cn.com.starest.nextoa.project.repository.custom.ProjectCompletionRepositoryCustom;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;

import java.util.List;

/**
 * Order repo
 */
public interface ProjectCompletionRepository extends AbstractRepository<ProjectCompletion>,
													 ProjectCompletionRepositoryCustom {

	ProjectCompletion findFirstByProject(Project project);

	ProjectCompletion findFirstByOrder(Order order);

	ProjectCompletion findFirstByContract(Contract contract);

	List<ProjectCompletion> findListByProjectOrderByCreatedAtAsc(Project project);

}