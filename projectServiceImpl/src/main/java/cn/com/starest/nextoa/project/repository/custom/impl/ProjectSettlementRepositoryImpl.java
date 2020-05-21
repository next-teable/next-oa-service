package cn.com.starest.nextoa.project.repository.custom.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.ProjectSettlement;
import cn.com.starest.nextoa.project.domain.request.ProjectSettlementQueryRequest;
import cn.com.starest.nextoa.project.repository.custom.ProjectSettlementRepositoryCustom;
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
public class ProjectSettlementRepositoryImpl extends AbstractCustomRepositoryImpl implements
																				  ProjectSettlementRepositoryCustom {

	@Override
	public Page<ProjectSettlement> queryPage(ProjectSettlementQueryRequest queryRequest) {
		return queryPage(queryRequest, null);
	}

	@Override
	public Page<ProjectSettlement> queryPage(ProjectSettlementQueryRequest queryRequest, User byWho) {
		int start = queryRequest.getStart();
		int limit = queryRequest.getLimit();
		Query query = buildQuery(queryRequest, byWho);

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
		long count = mongoTemplate.count(query, ProjectSettlement.class);
		List<ProjectSettlement> list = mongoTemplate.find(query, ProjectSettlement.class);

		return new PageImpl<>(list, pageable, count);
	}

	private Query buildQuery(ProjectSettlementQueryRequest request, User byWho) {
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

		if (byWho != null) {
			Criteria criteria = new Criteria().orOperator(Criteria.where("user1").is(byWho),
														  Criteria.where("user2").is(byWho),
														  Criteria.where("user3").is(byWho),
														  Criteria.where("user4").is(byWho),
														  Criteria.where("user5").is(byWho));
			query.addCriteria(criteria);
		}

		return query;
	}

}
