package cn.com.starest.nextoa.project.repository.custom.impl;

import cn.com.starest.nextoa.project.domain.model.LendingAggregation;
import cn.com.starest.nextoa.project.domain.request.LendingAggregationQueryRequest;
import cn.com.starest.nextoa.project.repository.custom.LendingAggregationRepositoryCustom;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
public class LendingAggregationRepositoryImpl extends AbstractCustomRepositoryImpl implements
																				   LendingAggregationRepositoryCustom {

	@Override
	public Page<LendingAggregation> queryPage(LendingAggregationQueryRequest queryRequest) {
		int start = queryRequest.getStart();
		int limit = queryRequest.getLimit();
		Query query = buildQuery(queryRequest);

		PageRequest pageable = new PageRequest(start, limit, new Sort(Sort.Direction.ASC, "owedAmount"));
		query.with(pageable);
		long count = mongoTemplate.count(query, LendingAggregation.class);
		List<LendingAggregation> list = mongoTemplate.find(query, LendingAggregation.class);

		return new PageImpl<>(list, pageable, count);
	}

	private Query buildQuery(LendingAggregationQueryRequest request) {
		Query query = new Query();

		if (request.getLendingObject() != null) {
			query.addCriteria(Criteria.where("lendingObject").is(request.getLendingObject()));
		}
		if (!StringUtils.isEmpty(request.getSubContractorId())) {
			query.addCriteria(Criteria.where("lendingTo.$id").is(new ObjectId(request.getSubContractorId())));
		}
		if (!StringUtils.isEmpty(request.getLendingById())) {
			query.addCriteria(Criteria.where("lendingBy.$id").is(new ObjectId(request.getLendingById())));
		}

		return query;
	}

}
