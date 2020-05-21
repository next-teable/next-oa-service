package cn.com.starest.nextoa.project.repository.custom;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.ProjectManageRelationship;
import cn.com.starest.nextoa.project.domain.request.ProjectQueryRequest;
import org.springframework.data.domain.Page;

/**
 * Created by cengruilin on 2017/12/31.
 */
public interface ProjectManageRelationshipRepositoryCustom {
    Page<ProjectManageRelationship> queryPage4SupervisedMonitorProjectManageRelationship(ProjectQueryRequest queryRequest);

    Page<ProjectManageRelationship> queryPage4SupervisedMonitorProjectManageRelationship(ProjectQueryRequest queryRequest, User manger);
}
