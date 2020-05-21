package cn.com.starest.nextoa.project.repository.custom.impl;

import cn.com.starest.nextoa.project.domain.model.Project;
import cn.com.starest.nextoa.project.domain.model.SubOrder;
import cn.com.starest.nextoa.project.domain.request.SubOrderQueryRequest;
import cn.com.starest.nextoa.project.repository.custom.SubOrderRepositoryCustom;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Repository
public class SubOrderRepositoryImpl extends AbstractCustomRepositoryImpl implements SubOrderRepositoryCustom {

	@Override
	public BigDecimal calculateTotalAmount(Project project) {
		Aggregation aggregation = newAggregation(match(Criteria.where("project.$id")
															   .is(new ObjectId(project.getId()))
															   .and("published")
															   .is(true)),
												 group("project").sum("amount").as("amount"),
												 project("amount"));

		AggregationResults<AggregationAmount> aggregationResults = mongoTemplate.aggregate(aggregation,
																						   SubOrder.class,
																						   AggregationAmount.class);
		if (aggregationResults != null) {
			AggregationAmount result = aggregationResults.getUniqueMappedResult();
			if (result != null) {
				return result.getAmount();
			}
		}
		return BigDecimal.ZERO;
	}

	@Override
	public Page<SubOrder> queryPage(SubOrderQueryRequest queryRequest) {
		int start = queryRequest.getStart();
		int limit = queryRequest.getLimit();
		Query query = buildQuery(queryRequest);

		PageRequest pageable = new PageRequest(start, limit, new Sort(Sort.Direction.DESC, "createdAt"));
		query.with(pageable);
		long count = mongoTemplate.count(query, SubOrder.class);
		List<SubOrder> list = mongoTemplate.find(query, SubOrder.class);

		return new PageImpl<>(list, pageable, count);
	}

	private Query buildQuery(SubOrderQueryRequest request) {
		Query query = new Query();

		if (!StringUtils.isEmpty(request.getCode())) {
			query.addCriteria(Criteria.where("code").regex(request.getCode()));
		}

		if (!StringUtils.isEmpty(request.getName())) {
			query.addCriteria(Criteria.where("name").regex(request.getName()));
		}

		if (!StringUtils.isEmpty(request.getProjectId())) {
			query.addCriteria(Criteria.where("project.$id").is(new ObjectId(request.getProjectId())));
		}

		if (!StringUtils.isEmpty(request.getOrderId())) {
			query.addCriteria(Criteria.where("order.$id").is(new ObjectId(request.getOrderId())));
		}

		if (!StringUtils.isEmpty(request.getSubContractId())) {
			query.addCriteria(Criteria.where("subContract.$id").is(new ObjectId(request.getSubContractId())));
		}

		if (null != request.getPublished()) {
			query.addCriteria(Criteria.where("published").is(request.getPublished().booleanValue()));
		}


		if (request.getMinAmount() != null && request.getMaxAmount() != null) {
			Criteria criteria = new Criteria().andOperator(Criteria.where("amount").gte(request.getMinAmount()),
														   Criteria.where("amount").lte(request.getMaxAmount()));
			query.addCriteria(criteria);
		}
		else if (request.getMinAmount() != null) {
			query.addCriteria(Criteria.where("amount").gte(request.getMinAmount()));
		}
		else if (request.getMaxAmount() != null) {
			query.addCriteria(Criteria.where("amount").lte(request.getMaxAmount()));
		}

		return query;
	}

}
