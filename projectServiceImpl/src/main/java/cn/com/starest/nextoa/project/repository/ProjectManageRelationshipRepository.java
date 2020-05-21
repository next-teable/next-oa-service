package cn.com.starest.nextoa.project.repository;

import cn.com.starest.nextoa.project.domain.model.BizDepartment;
import cn.com.starest.nextoa.project.domain.model.Project;
import cn.com.starest.nextoa.project.domain.model.ProjectManageRelationship;
import cn.com.starest.nextoa.project.repository.custom.ProjectManageRelationshipRepositoryCustom;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;

import java.util.List;

/**
 * Created by cengruilin on 2017/12/28.
 */
public interface ProjectManageRelationshipRepository extends AbstractRepository<ProjectManageRelationship>, ProjectManageRelationshipRepositoryCustom {

    List<ProjectManageRelationship> findByProject(Project project);

    ProjectManageRelationship findByProjectAndYear(Project project, int year);

    List<ProjectManageRelationship> findByBelongTo(BizDepartment bizDepartment);
}
