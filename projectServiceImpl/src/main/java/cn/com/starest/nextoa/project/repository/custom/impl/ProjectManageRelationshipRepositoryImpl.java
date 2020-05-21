package cn.com.starest.nextoa.project.repository.custom.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.Project;
import cn.com.starest.nextoa.project.domain.model.ProjectManageRelationship;
import cn.com.starest.nextoa.project.domain.request.ProjectQueryRequest;
import cn.com.starest.nextoa.project.repository.custom.ProjectManageRelationshipRepositoryCustom;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Created by cengruilin on 2017/12/31.
 */
@Repository
public class ProjectManageRelationshipRepositoryImpl extends AbstractCustomRepositoryImpl implements ProjectManageRelationshipRepositoryCustom {
    @Override
    public Page<ProjectManageRelationship> queryPage4SupervisedMonitorProjectManageRelationship(ProjectQueryRequest queryRequest) {
        int start = queryRequest.getStart();
        int limit = queryRequest.getLimit();
        Query query = buildQuery(queryRequest);
        query.with(buildSort(queryRequest));
        List<Project> projectList = mongoTemplate.find(query, Project.class);
//        List<ObjectId> projectIdList = projectList.stream().map(project -> new ObjectId(project.getId())).collect(Collectors.toList());

        Query queryRelationship = new Query();
        queryRelationship.addCriteria(Criteria.where("project").in(projectList));
        PageRequest pageable = new PageRequest(start, limit);
        queryRelationship.with(pageable);
        long count = mongoTemplate.count(queryRelationship, ProjectManageRelationship.class);
        List<ProjectManageRelationship> list = mongoTemplate.find(queryRelationship, ProjectManageRelationship.class);

        return new PageImpl<>(list, pageable, count);
    }

    @Override
    public Page<ProjectManageRelationship> queryPage4SupervisedMonitorProjectManageRelationship(ProjectQueryRequest queryRequest, User manger) {
        int start = queryRequest.getStart();
        int limit = queryRequest.getLimit();
        Query query = buildQuery(queryRequest);
        PageRequest pageable = new PageRequest(start, limit, buildSort(queryRequest));
        query.with(pageable);
        List<Project> projectList = mongoTemplate.find(query, Project.class);


        Query queryRelationship = new Query();
        queryRelationship.addCriteria(Criteria.where("project").in(projectList));
        queryRelationship.addCriteria(Criteria.where("managers").in(manger));
        long count = mongoTemplate.count(queryRelationship, ProjectManageRelationship.class);
        List<ProjectManageRelationship> list = mongoTemplate.find(queryRelationship, ProjectManageRelationship.class);

        return new PageImpl<>(list, pageable, count);
    }

    private Query buildQuery(ProjectQueryRequest request) {
        Query query = new Query();
        if (!StringUtils.isEmpty(request.getCompanyId())) {
            query.addCriteria(Criteria.where("company.$id").is(new ObjectId(request.getCompanyId())));
        }

        if (!StringUtils.isEmpty(request.getCode())) {
            query.addCriteria(Criteria.where("code").regex(request.getCode()));
        }

        if (!StringUtils.isEmpty(request.getName())) {
            query.addCriteria(Criteria.where("name").regex(request.getName()));
        }

        if (!StringUtils.isEmpty(request.getFrameworkContractId())) {
            query.addCriteria(Criteria.where("frameworkContract.$id")
                    .is(new ObjectId(request.getFrameworkContractId())));
        }

        if (null != request.getStatus()) {
            if (request.getStatusScope() == ProjectQueryRequest.ProjectStatusScope.EXCLUDE) {
                query.addCriteria(Criteria.where("status").ne(request.getStatus()));
            } else {
                query.addCriteria(Criteria.where("status").is(request.getStatus()));
            }
        }

        if (null != request.getProjectType()) {
            query.addCriteria(Criteria.where("projectType").is(request.getProjectType()));
        } else if (null != request.getProjectTypes() && request.getProjectTypes().length > 0) {
            query.addCriteria(Criteria.where("projectType").in(Arrays.asList(request.getProjectTypes())));
        }

        if (null != request.getDeliveryType()) {
            query.addCriteria(Criteria.where("deliveryType").is(request.getDeliveryType()));
        }

        if (!StringUtils.isEmpty(request.getFirstPartyId())) {
            query.addCriteria(Criteria.where("firstParty.$id").is(new ObjectId(request.getFirstPartyId())));
        }

        return query;
    }

    private Sort buildSort(ProjectQueryRequest queryRequest) {
        if ("AMOUNT_DESC".equals(queryRequest.getSortOption())) {
            return new Sort(Sort.Direction.DESC, "currentCollectProfitAmount");
        } else if ("AMOUNT_ASC".equals(queryRequest.getSortOption())) {
            return new Sort(Sort.Direction.ASC, "currentCollectProfitAmount");
        } else if ("RATE_DESC".equals(queryRequest.getSortOption())) {
            return new Sort(Sort.Direction.DESC, "currentCollectProfitRate");
        } else if ("RATE_ASC".equals(queryRequest.getSortOption())) {
            return new Sort(Sort.Direction.ASC, "currentCollectProfitRate");
        } else {
            return new Sort(Sort.Direction.ASC, "sort");
        }
    }
}
