package cn.com.starest.nextoa.project.repository.custom.impl;

import cn.com.starest.nextoa.project.domain.model.Company;
import cn.com.starest.nextoa.project.domain.model.PendingPayment;
import cn.com.starest.nextoa.project.domain.model.Project;
import cn.com.starest.nextoa.project.domain.request.PendingPaymentQueryRequest;
import cn.com.starest.nextoa.project.repository.custom.PendingPaymentRepositoryCustom;
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
public class PendingPaymentRepositoryImpl extends AbstractCustomRepositoryImpl implements PendingPaymentRepositoryCustom {

	@Override
	public Page<PendingPayment> queryPage(PendingPaymentQueryRequest queryRequest) {
		int start = queryRequest.getStart();
		int limit = queryRequest.getLimit();
		Query query = buildQuery(queryRequest);

		PageRequest pageable = new PageRequest(start,
											   limit,
											   new Sort(new Sort.Order(Sort.Direction.ASC, "payed"),
														new Sort.Order(Sort.Direction.DESC, "createdAt")));
		query.with(pageable);
		long count = mongoTemplate.count(query, PendingPayment.class);
		List<PendingPayment> list = mongoTemplate.find(query, PendingPayment.class);

		return new PageImpl<>(list, pageable, count);
	}

	@Override
	public BigDecimal calculateTotalAmount(Company company, int year) {
		Aggregation aggregation = newAggregation(match(Criteria.where("company.$id")
															   .is(new ObjectId(company.getId()))
															   .and("payed")
															   .ne(true)
															   .and("year")
															   .is(year)),
												 group("company").sum("amount").as("amount"),
												 project("amount"));

		AggregationResults<AggregationAmount> aggregationResults = mongoTemplate.aggregate(aggregation,
																						   PendingPayment.class,
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
		//		Aggregation aggregation = newAggregation(match(Criteria.where("company.$id")
		//															   .is(new ObjectId(company.getId()))
		//															   .and("year")
		//															   .lte(year)
		//															   .and("pendingAt")
		//															   .lte(DateTimeUtils.endOfMonth(year, month))),
		//												 group("company").sum("amount").as("amount"),
		//												 project("amount"));

		//@since 2017.9.13 不管待支付日期
		Aggregation aggregation = newAggregation(match(Criteria.where("company.$id")
															   .is(new ObjectId(company.getId()))
															   .and("payed")
															   .ne(true)
															   .and("year")
															   .lte(year)),
												 group("company").sum("amount").as("amount"),
												 project("amount"));

		AggregationResults<AggregationAmount> aggregationResults = mongoTemplate.aggregate(aggregation,
																						   PendingPayment.class,
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
		Aggregation aggregation = newAggregation(match(Criteria.where("project.$id")
															   .is(new ObjectId(project.getId()))
															   .and("payed")
															   .ne(true)),
												 group("project").sum("amount").as("amount"),
												 project("amount"));

		AggregationResults<AggregationAmount> aggregationResults = mongoTemplate.aggregate(aggregation,
																						   PendingPayment.class,
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
															   .and("payed")
															   .ne(true)
															   .and("year")
															   .is(year)),
												 group("project").sum("amount").as("amount"),
												 project("amount"));

		AggregationResults<AggregationAmount> aggregationResults = mongoTemplate.aggregate(aggregation,
																						   PendingPayment.class,
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
	public BigDecimal calculateTotalAmount(PendingPaymentQueryRequest request) {
		Aggregation aggregation = newAggregation(match(buildCriteria(request)),
												 project("amount").and("amount").multiply(0).as("groupAmount"),
												 group("groupAmount").sum("amount").as("amount"),
												 project("amount"));

		AggregationResults<AggregationAmount> aggregationResults = mongoTemplate.aggregate(aggregation,
																						   PendingPayment.class,
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
	public BigDecimal calculateTotalPayedAmount(PendingPaymentQueryRequest request) {
		Aggregation aggregation = newAggregation(match(buildCriteria(request).and("payed").is(true)),
												 project("amount").and("amount").multiply(0).as("groupAmount"),
												 group("groupAmount").sum("amount").as("amount"),
												 project("amount"));

		AggregationResults<AggregationAmount> aggregationResults = mongoTemplate.aggregate(aggregation,
																						   PendingPayment.class,
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
	public BigDecimal calculateTotalPendingAmount(PendingPaymentQueryRequest request) {
		Aggregation aggregation = newAggregation(match(buildCriteria(request).and("payed").ne(true)),
												 project("amount").and("amount").multiply(0).as("groupAmount"),
												 group("groupAmount").sum("amount").as("amount"),
												 project("amount"));

		AggregationResults<AggregationAmount> aggregationResults = mongoTemplate.aggregate(aggregation,
																						   PendingPayment.class,
																						   AggregationAmount.class);
		if (aggregationResults != null) {
			AggregationAmount result = aggregationResults.getUniqueMappedResult();
			if (result != null) {
				return result.getAmount();
			}
		}
		return BigDecimal.ZERO;
	}

	private Criteria buildCriteria(PendingPaymentQueryRequest request) {
		Criteria result = Criteria.where("id").ne(null);

		if (!StringUtils.isEmpty(request.getReceivedPaymentCode())) {
			result = result.and("receivedPaymentCode").regex(request.getReceivedPaymentCode());
		}

		if (!StringUtils.isEmpty(request.getSubContractorId())) {
			result = result.and("subContractor.$id").is(new ObjectId(request.getSubContractorId()));
		}

		if (!StringUtils.isEmpty(request.getCompanyId())) {
			result = result.and("company.$id").is(new ObjectId(request.getCompanyId()));
		}

		if (!StringUtils.isEmpty(request.getProjectId())) {
			result = result.and("project.$id").is(new ObjectId(request.getProjectId()));
		}

		if (!StringUtils.isEmpty(request.getBizDepartmentId())) {
			result = result.and("bizDepartment.$id").is(new ObjectId(request.getBizDepartmentId()));
		}

		return result;
	}

	private Query buildQuery(PendingPaymentQueryRequest request) {
		Query query = new Query();

		if (!StringUtils.isEmpty(request.getReceivedPaymentCode())) {
			query.addCriteria(Criteria.where("receivedPaymentCode").regex(request.getReceivedPaymentCode()));
		}

		if (!StringUtils.isEmpty(request.getSubContractorId())) {
			query.addCriteria(Criteria.where("subContractor.$id").is(new ObjectId(request.getSubContractorId())));
		}

		if (!StringUtils.isEmpty(request.getCompanyId())) {
			query.addCriteria(Criteria.where("company.$id").is(new ObjectId(request.getCompanyId())));
		}

		if (!StringUtils.isEmpty(request.getProjectId())) {
			query.addCriteria(Criteria.where("project.$id").is(new ObjectId(request.getProjectId())));
		}

		if (!StringUtils.isEmpty(request.getBizDepartmentId())) {
			query.addCriteria(Criteria.where("bizDepartment.$id").is(new ObjectId(request.getBizDepartmentId())));
		}

		return query;
	}

}
