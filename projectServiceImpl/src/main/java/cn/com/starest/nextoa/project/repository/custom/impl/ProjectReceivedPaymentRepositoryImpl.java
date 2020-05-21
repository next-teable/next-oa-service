package cn.com.starest.nextoa.project.repository.custom.impl;

import cn.com.starest.nextoa.project.domain.model.*;
import cn.com.starest.nextoa.project.domain.request.ProjectReceivedPaymentQueryRequest;
import cn.com.starest.nextoa.project.repository.custom.ProjectReceivedPaymentRepositoryCustom;
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
public class ProjectReceivedPaymentRepositoryImpl extends AbstractCustomRepositoryImpl implements
																					   ProjectReceivedPaymentRepositoryCustom {

	@Override
	public BigDecimal calculateTotalAmount(Company company, int year) {
		Aggregation aggregation = newAggregation(match(Criteria.where("company.$id")
															   .is(new ObjectId(company.getId()))
															   .and("year")
															   .is(year)),
												 group("company").sum("amount").as("amount"),
												 project("amount"));

		AggregationResults<AggregationAmount> aggregationResults = mongoTemplate.aggregate(aggregation,
																						   ProjectReceivedPayment.class,
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
		Aggregation aggregation = newAggregation(match(Criteria.where("company.$id").is(new ObjectId(company.getId()))
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
																						   ProjectReceivedPayment.class,
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
	public BigDecimal calculateTotalAmount(Project project) {
		Aggregation aggregation = newAggregation(match(Criteria.where("project.$id").is(new ObjectId(project.getId()))),
												 group("project").sum("amount").as("amount"),
												 project("amount"));

		AggregationResults<AggregationAmount> aggregationResults = mongoTemplate.aggregate(aggregation,
																						   ProjectReceivedPayment.class,
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
	public BigDecimal calculateTotalAmount(Project project, int year) {
		Aggregation aggregation = newAggregation(match(Criteria.where("project.$id")
															   .is(new ObjectId(project.getId()))
															   .and("year")
															   .is(year)),
												 group("project").sum("amount").as("amount"),
												 project("amount"));

		AggregationResults<AggregationAmount> aggregationResults = mongoTemplate.aggregate(aggregation,
																						   ProjectReceivedPayment.class,
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
	public BigDecimal calculateTotalAmount(IssueInvoice issueInvoice) {
		Aggregation aggregation = newAggregation(match(Criteria.where("issueInvoice.$id")
															   .is(new ObjectId(issueInvoice.getId()))),
												 group("issueInvoice").sum("amount").as("amount"),
												 project("amount"));

		AggregationResults<AggregationAmount> aggregationResults = mongoTemplate.aggregate(aggregation,
																						   ProjectReceivedPayment.class,
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
	public BigDecimal calculateTotalAmount(Order order) {
		Aggregation aggregation = newAggregation(match(Criteria.where("order.$id").is(new ObjectId(order.getId()))),
												 group("order").sum("amount").as("amount"),
												 project("amount"));

		AggregationResults<AggregationAmount> aggregationResults = mongoTemplate.aggregate(aggregation,
																						   ProjectReceivedPayment.class,
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
	public BigDecimal calculateTotalAmount(Contract contract) {
		Aggregation aggregation = newAggregation(match(Criteria.where("contract.$id")
															   .is(new ObjectId(contract.getId()))),
												 group("contract").sum("amount").as("amount"),
												 project("amount"));

		AggregationResults<AggregationAmount> aggregationResults = mongoTemplate.aggregate(aggregation,
																						   ProjectReceivedPayment.class,
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
	public Page<ProjectReceivedPayment> queryPage(ProjectReceivedPaymentQueryRequest queryRequest) {
		int start = queryRequest.getStart();
		int limit = queryRequest.getLimit();
		Query query = buildQuery(queryRequest);

		PageRequest pageable = new PageRequest(start, limit, new Sort(Sort.Direction.DESC, "createdAt"));
		query.with(pageable);
		long count = mongoTemplate.count(query, ProjectReceivedPayment.class);
		List<ProjectReceivedPayment> list = mongoTemplate.find(query, ProjectReceivedPayment.class);

		return new PageImpl<>(list, pageable, count);
	}

	private Query buildQuery(ProjectReceivedPaymentQueryRequest request) {
		Query query = new Query();

		if (!StringUtils.isEmpty(request.getCode())) {
			query.addCriteria(Criteria.where("code").regex(request.getCode()));
		}

		if (null != request.getType()) {
			query.addCriteria(Criteria.where("type").is(request.getType()));
		}

		if (!StringUtils.isEmpty(request.getAccountSubjectId())) {
			query.addCriteria(Criteria.where("accountSubject.$id").is(new ObjectId(request.getAccountSubjectId())));
		}

		if (!StringUtils.isEmpty(request.getProjectId())) {
			query.addCriteria(Criteria.where("project.$id").is(new ObjectId(request.getProjectId())));
		}

		if (!StringUtils.isEmpty(request.getContractId())) {
			query.addCriteria(Criteria.where("contract.$id").is(new ObjectId(request.getContractId())));
		}

		if (!StringUtils.isEmpty(request.getOrderId())) {
			query.addCriteria(Criteria.where("order.$id").is(new ObjectId(request.getOrderId())));
		}

		buildDateRangePart(query, request, "receivedAt");

		return query;
	}

}
