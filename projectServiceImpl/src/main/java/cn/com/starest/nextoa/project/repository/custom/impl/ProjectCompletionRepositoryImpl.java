package cn.com.starest.nextoa.project.repository.custom.impl;

import cn.com.starest.nextoa.project.domain.model.ProjectCompletion;
import cn.com.starest.nextoa.project.domain.request.ProjectCompletionQueryRequest;
import cn.com.starest.nextoa.project.repository.custom.ProjectCompletionRepositoryCustom;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProjectCompletionRepositoryImpl extends AbstractCustomRepositoryImpl implements
																				  ProjectCompletionRepositoryCustom {

	@Override
	public Page<ProjectCompletion> queryPage(ProjectCompletionQueryRequest queryRequest) {
		int start = queryRequest.getStart();
		int limit = queryRequest.getLimit();
		Query query = buildQuery(queryRequest);

		List<Sort.Order> sortOrders = new ArrayList<>();

		String[] attributesOrderByAsc = queryRequest.getAttributesOrderByAsc();
		if (attributesOrderByAsc != null && attributesOrderByAsc.length > 0) {
			for (String attr : attributesOrderByAsc) {
				sortOrders.add(new Sort.Order(Sort.Direction.ASC, attr));
			}
		}

		String[] attributesOrderByDesc = queryRequest.getAttributesOrderByDesc();
		if (attributesOrderByDesc != null && attributesOrderByDesc.length > 0) {
			for (String attr : attributesOrderByDesc) {
				sortOrders.add(new Sort.Order(Sort.Direction.DESC, attr));
			}
		}

		Sort sort = sortOrders.isEmpty() ? new Sort(Sort.Direction.DESC, "createdAt") : new Sort(sortOrders);

		PageRequest pageable = new PageRequest(start, limit, sort);
		query.with(pageable);
		long count = mongoTemplate.count(query, ProjectCompletion.class);
		List<ProjectCompletion> list = mongoTemplate.find(query, ProjectCompletion.class);

		return new PageImpl<>(list, pageable, count);
	}

	private Query buildQuery(ProjectCompletionQueryRequest request) {
		Query query = new Query();

		if (!StringUtils.isEmpty(request.getProjectId())) {
			query.addCriteria(Criteria.where("project.$id").is(new ObjectId(request.getProjectId())));
		}

		if (!StringUtils.isEmpty(request.getContractId())) {
			query.addCriteria(Criteria.where("contract.$id").is(new ObjectId(request.getContractId())));
		}

		if (!StringUtils.isEmpty(request.getOrderId())) {
			query.addCriteria(Criteria.where("order.$id").is(new ObjectId(request.getOrderId())));
		}
		return query;
	}

}
