package cn.com.starest.nextoa.project.repository.custom.impl;

import cn.com.starest.nextoa.project.domain.model.Company;
import cn.com.starest.nextoa.project.domain.model.CompanyReceivedPayment;
import cn.com.starest.nextoa.project.domain.request.CompanyReceivedPaymentQueryRequest;
import cn.com.starest.nextoa.project.repository.custom.CompanyReceivedPaymentRepositoryCustom;
import cn.com.starest.nextoa.shared.util.DateTimeUtils;
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
public class CompanyReceivedPaymentRepositoryImpl extends AbstractCustomRepositoryImpl implements
																					   CompanyReceivedPaymentRepositoryCustom {

	@Override
	public BigDecimal calculateTotalAmount(Company company, int year) {
		Aggregation aggregation = newAggregation(match(Criteria.where("company.$id")
															   .is(new ObjectId(company.getId()))
															   .and("year")
															   .is(year)),
												 group("company").sum("amount").as("amount"),
												 project("amount"));

		AggregationResults<AggregationAmount> aggregationResults = mongoTemplate.aggregate(aggregation,
																						   CompanyReceivedPayment.class,
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
	public BigDecimal calculateTotalAmount(Company company, int year, int month) {
		Aggregation aggregation = newAggregation(match(Criteria.where("company.$id")
															   .is(new ObjectId(company.getId()))
//															   .and("year")
//															   .is(year)
															   .andOperator(Criteria.where("receivedAt")
																					.gte(DateTimeUtils.startOfMonth(year,
																													month)),
																			Criteria.where("receivedAt")
																					.lte(DateTimeUtils.endOfMonth(year,
																												  month)))),
												 group("company").sum("amount").as("amount"),
												 project("amount"));

		AggregationResults<AggregationAmount> aggregationResults = mongoTemplate.aggregate(aggregation,
																						   CompanyReceivedPayment.class,
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
	public Page<CompanyReceivedPayment> queryPage(CompanyReceivedPaymentQueryRequest queryRequest) {
		int start = queryRequest.getStart();
		int limit = queryRequest.getLimit();
		Query query = buildQuery(queryRequest);

		PageRequest pageable = new PageRequest(start, limit, new Sort(Sort.Direction.DESC, "createdAt"));
		query.with(pageable);
		long count = mongoTemplate.count(query, CompanyReceivedPayment.class);
		List<CompanyReceivedPayment> list = mongoTemplate.find(query, CompanyReceivedPayment.class);

		return new PageImpl<>(list, pageable, count);
	}

	private Query buildQuery(CompanyReceivedPaymentQueryRequest request) {
		Query query = new Query();

		if (!StringUtils.isEmpty(request.getCode())) {
			query.addCriteria(Criteria.where("code").regex(request.getCode()));
		}

		if (null != request.getType()) {
			query.addCriteria(Criteria.where("type").is(request.getType()));
		}

		if (!StringUtils.isEmpty(request.getCompanyId())) {
			query.addCriteria(Criteria.where("company.$id").is(new ObjectId(request.getCompanyId())));
		}

		if (!StringUtils.isEmpty(request.getAccountSubjectId())) {
			query.addCriteria(Criteria.where("accountSubject.$id").is(new ObjectId(request.getAccountSubjectId())));
		}

		if (!StringUtils.isEmpty(request.getSubAccountSubjectId())) {
			query.addCriteria(Criteria.where("subAccountSubject.$id")
									  .is(new ObjectId(request.getSubAccountSubjectId())));
		}

		if (!StringUtils.isEmpty(request.getReceivedFrom())) {
			query.addCriteria(Criteria.where("receivedFrom").regex(request.getReceivedFrom()));
		}

		buildDateRangePart(query, request, "receivedAt");

		return query;
	}

}
