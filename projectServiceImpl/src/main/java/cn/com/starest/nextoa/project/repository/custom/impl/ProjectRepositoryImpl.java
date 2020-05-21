package cn.com.starest.nextoa.project.repository.custom.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.Project;
import cn.com.starest.nextoa.project.domain.request.ProjectQueryRequest;
import cn.com.starest.nextoa.project.repository.custom.ProjectRepositoryCustom;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

@Repository
public class ProjectRepositoryImpl extends AbstractCustomRepositoryImpl implements ProjectRepositoryCustom {

	@Override
	public void clearSort(Project project) {
		mongoTemplate.updateFirst(query(where("id").is(project.getId())), update("sort", null), Project.class);
	}

	@Override
	public void sortProject(Project project, String sort) {
		mongoTemplate.updateFirst(query(where("id").is(project.getId())), update("sort", sort), Project.class);
	}

	@Override
	public Page<Project> queryPage(ProjectQueryRequest queryRequest) {
		int start = queryRequest.getStart();
		int limit = queryRequest.getLimit();
		Query query = buildQuery(queryRequest);

		PageRequest pageable = new PageRequest(start, limit, new Sort(Sort.Direction.DESC, "createdAt"));
		query.with(pageable);
		long count = mongoTemplate.count(query, Project.class);
		List<Project> list = mongoTemplate.find(query, Project.class);

		return new PageImpl<>(list, pageable, count);
	}

	@Override
	public Page<Project> queryPageByManager(ProjectQueryRequest queryRequest, User manager) {
		int start = queryRequest.getStart();
		int limit = queryRequest.getLimit();
		Query query = buildQuery(queryRequest);
		query.addCriteria(Criteria.where("managers").in(manager));

		PageRequest pageable = new PageRequest(start, limit, new Sort(Sort.Direction.DESC, "createdAt"));
		query.with(pageable);
		long count = mongoTemplate.count(query, Project.class);
		List<Project> list = mongoTemplate.find(query, Project.class);

		return new PageImpl<>(list, pageable, count);
	}

	@Override
	public Page<Project> queryPage4SupervisedMonitorProject(ProjectQueryRequest queryRequest) {
		int start = queryRequest.getStart();
		int limit = queryRequest.getLimit();
		Query query = buildQuery(queryRequest);

		PageRequest pageable = new PageRequest(start, limit, buildSort(queryRequest));
		query.with(pageable);
		long count = mongoTemplate.count(query, Project.class);
		List<Project> list = mongoTemplate.find(query, Project.class);

		return new PageImpl<>(list, pageable, count);
	}

	@Override
	public Page<Project> queryPage4ManagedMonitorProject(ProjectQueryRequest queryRequest, User manager) {
		int start = queryRequest.getStart();
		int limit = queryRequest.getLimit();
		Query query = buildQuery(queryRequest);
		query.addCriteria(Criteria.where("managers").in(manager));

		PageRequest pageable = new PageRequest(start, limit, buildSort(queryRequest));
		query.with(pageable);
		long count = mongoTemplate.count(query, Project.class);
		List<Project> list = mongoTemplate.find(query, Project.class);

		return new PageImpl<>(list, pageable, count);
	}

	private Sort buildSort(ProjectQueryRequest queryRequest) {
		if ("AMOUNT_DESC".equals(queryRequest.getSortOption())) {
			return new Sort(Sort.Direction.DESC, "currentCollectProfitAmount");
		}
		else if ("AMOUNT_ASC".equals(queryRequest.getSortOption())) {
			return new Sort(Sort.Direction.ASC, "currentCollectProfitAmount");
		}
		else if ("RATE_DESC".equals(queryRequest.getSortOption())) {
			return new Sort(Sort.Direction.DESC, "currentCollectProfitRate");
		}
		else if ("RATE_ASC".equals(queryRequest.getSortOption())) {
			return new Sort(Sort.Direction.ASC, "currentCollectProfitRate");
		}
		else {
			return new Sort(Sort.Direction.ASC, "sort");
		}
	}

	@Override
	public void setProjectManagers(String projectId, List<User> managers) {
		mongoTemplate.updateFirst(query(where("id").is(projectId)), update("managers", managers), Project.class);
	}

	@Override
	public void setReducedPercent(String projectId, BigDecimal reducedPercent) {
		mongoTemplate.updateFirst(query(where("id").is(projectId)),
								  update("reducedPercent", reducedPercent),
								  Project.class);
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
			}
			else {
				query.addCriteria(Criteria.where("status").is(request.getStatus()));
			}
		}

		if (null != request.getProjectType()) {
			query.addCriteria(Criteria.where("projectType").is(request.getProjectType()));
		}
		else if (null != request.getProjectTypes() && request.getProjectTypes().length > 0) {
			query.addCriteria(Criteria.where("projectType").in(Arrays.asList(request.getProjectTypes())));
		}

		if (null != request.getDeliveryType()) {
			query.addCriteria(Criteria.where("deliveryType").is(request.getDeliveryType()));
		}

		if (!StringUtils.isEmpty(request.getFirstPartyId())) {
			query.addCriteria(Criteria.where("firstParty.$id").is(new ObjectId(request.getFirstPartyId())));
		}

		if (!StringUtils.isEmpty(request.getManagerId())) {
			query.addCriteria(Criteria.where("managers.$id").in(new ObjectId(request.getManagerId())));
		}

		return query;
	}

}
