package cn.com.starest.nextoa.project.repository;

import cn.com.starest.nextoa.project.domain.model.Project;
import cn.com.starest.nextoa.project.domain.model.ProjectBudget;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;

import java.util.List;

/**
 *
 */
public interface ProjectBudgetRepository extends AbstractRepository<ProjectBudget> {

	List<ProjectBudget> findListByProjectOrderByYearAsc(Project project);

	ProjectBudget findFirstByProjectAndYear(Project project, int year);

}