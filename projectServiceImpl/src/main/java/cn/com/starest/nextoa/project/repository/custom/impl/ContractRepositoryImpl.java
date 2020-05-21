package cn.com.starest.nextoa.project.repository.custom.impl;

import cn.com.starest.nextoa.project.domain.model.Contract;
import cn.com.starest.nextoa.project.domain.model.Project;
import cn.com.starest.nextoa.project.domain.request.ContractQueryRequest;
import cn.com.starest.nextoa.project.repository.custom.ContractRepositoryCustom;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Repository
public class ContractRepositoryImpl extends AbstractCustomRepositoryImpl implements ContractRepositoryCustom {

	@Override
	public BigDecimal calculateTotalAmount(Project project) {
		Aggregation aggregation = newAggregation(match(Criteria.where("projects.$id")
															   .in(new ObjectId(project.getId()))
															   .and("published")
															   .is(true)),
												 group("published").sum("amount").as("amount"),
												 project("amount"));

		AggregationResults<AggregationAmount> aggregationResults = mongoTemplate.aggregate(aggregation,
																						   Contract.class,
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
	public Page<Contract> queryPage(ContractQueryRequest queryRequest) {
		int start = queryRequest.getStart();
		int limit = queryRequest.getLimit();
		Query query = buildQuery(queryRequest);

		PageRequest pageable = new PageRequest(start, limit, new Sort(Sort.Direction.DESC, "createdAt"));
		query.with(pageable);
		long count = mongoTemplate.count(query, Contract.class);
		List<Contract> list = mongoTemplate.find(query, Contract.class);

		return new PageImpl<>(list, pageable, count);
	}

	private Query buildQuery(ContractQueryRequest request) {
		Query query = new Query();

		if (!StringUtils.isEmpty(request.getCode())) {
			query.addCriteria(Criteria.where("code").regex(request.getCode()));
		}

		if (!StringUtils.isEmpty(request.getName())) {
			query.addCriteria(Criteria.where("name").regex(request.getName()));
		}

		if (!StringUtils.isEmpty(request.getProjectId())) {
			query.addCriteria(Criteria.where("projects.$id").in(Arrays.asList(new ObjectId(request.getProjectId()))));
		}

		if (!StringUtils.isEmpty(request.getFirstPartyId())) {
			query.addCriteria(Criteria.where("firstParty.$id").is(new ObjectId(request.getFirstPartyId())));
		}

		if (null != request.getPublished()) {
			query.addCriteria(Criteria.where("published").is(request.getPublished().booleanValue()));
		}

		return query;
	}

}
