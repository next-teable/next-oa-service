package cn.com.starest.nextoa.project.repository.custom.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.Company;
import cn.com.starest.nextoa.project.domain.model.Lending;
import cn.com.starest.nextoa.project.domain.model.LendingType;
import cn.com.starest.nextoa.project.domain.request.LendingQueryRequest;
import cn.com.starest.nextoa.project.repository.custom.LendingRepositoryCustom;
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
public class LendingRepositoryImpl extends AbstractCustomRepositoryImpl implements LendingRepositoryCustom {

	@Override
	public BigDecimal calculateLendingAmount(User lendingBy) {
		Aggregation aggregation = newAggregation(match(Criteria.where("lendingBy.$id")
															   .is(new ObjectId(lendingBy.getId()))
															   .and("lendingType")
															   .is(LendingType.LENDING.name())),
												 group("lendingBy").sum("amount").as("amount"),
												 project("amount"));

		AggregationResults<AggregationAmount> aggregationResults = mongoTemplate.aggregate(aggregation,
																						   Lending.class,
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
	public BigDecimal calculateRepaymentAmount(User lendingBy) {
		Aggregation aggregation = newAggregation(match(Criteria.where("lendingBy.$id")
															   .is(new ObjectId(lendingBy.getId()))
															   .and("lendingType")
															   .is(LendingType.REPAYMENT.name())),
												 group("lendingBy").sum("amount").as("amount"),
												 project("amount"));

		AggregationResults<AggregationAmount> aggregationResults = mongoTemplate.aggregate(aggregation,
																						   Lending.class,
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
	public BigDecimal calculateLendingAmount(Company lendingTo) {
		Aggregation aggregation = newAggregation(match(Criteria.where("lendingTo.$id")
															   .is(new ObjectId(lendingTo.getId()))
															   .and("lendingType")
															   .is(LendingType.LENDING.name())),
												 group("lendingTo").sum("amount").as("amount"),
												 project("amount"));

		AggregationResults<AggregationAmount> aggregationResults = mongoTemplate.aggregate(aggregation,
																						   Lending.class,
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
	public BigDecimal calculateRepaymentAmount(Company lendingTo) {
		Aggregation aggregation = newAggregation(match(Criteria.where("lendingTo.$id")
															   .is(new ObjectId(lendingTo.getId()))
															   .and("lendingType")
															   .is(LendingType.REPAYMENT.name())),
												 group("lendingTo").sum("amount").as("amount"),
												 project("amount"));

		AggregationResults<AggregationAmount> aggregationResults = mongoTemplate.aggregate(aggregation,
																						   Lending.class,
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
	public BigDecimal calculateLendingAmountByFrom(Company lendingFrom) {
		Aggregation aggregation = newAggregation(match(Criteria.where("company.$id")
															   .is(new ObjectId(lendingFrom.getId()))
															   .and("lendingType")
															   .is(LendingType.LENDING.name())),
												 group("company").sum("amount").as("amount"),
												 project("amount"));

		AggregationResults<AggregationAmount> aggregationResults = mongoTemplate.aggregate(aggregation,
																						   Lending.class,
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
	public BigDecimal calculateLendingAmountByFrom(Company lendingFrom, int year, int month) {
		Aggregation aggregation = newAggregation(match(Criteria.where("company.$id")
															   .is(new ObjectId(lendingFrom.getId()))
															   .and("lendingType")
															   .is(LendingType.LENDING.name())
															   .and("handleAt")
															   .lte(DateTimeUtils.endOfMonth(year, month))),
												 //															   .andOperator(Criteria.where("handleAt")
												 //																					.gte(DateTimeUtils.startOfMonth(year,
												 //																													month)),
												 //																			Criteria.where("handleAt")
												 //																					.lte(DateTimeUtils.endOfMonth(year,
												 //																												  month)))),
												 group("company").sum("amount").as("amount"), project("amount"));

		AggregationResults<AggregationAmount> aggregationResults = mongoTemplate.aggregate(aggregation,
																						   Lending.class,
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
	public BigDecimal calculateRepaymentAmountByFrom(Company lendingFrom) {
		Aggregation aggregation = newAggregation(match(Criteria.where("company.$id")
															   .is(new ObjectId(lendingFrom.getId()))
															   .and("lendingType")
															   .is(LendingType.REPAYMENT.name())),
												 group("company").sum("amount").as("amount"),
												 project("amount"));

		AggregationResults<AggregationAmount> aggregationResults = mongoTemplate.aggregate(aggregation,
																						   Lending.class,
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
	public BigDecimal calculateRepaymentAmountByFrom(Company lendingFrom, int year, int month) {
		Aggregation aggregation = newAggregation(match(Criteria.where("company.$id")
															   .is(new ObjectId(lendingFrom.getId()))
															   .and("lendingType")
															   .is(LendingType.REPAYMENT.name())
															   .and("handleAt")
															   .lte(DateTimeUtils.endOfMonth(year, month))),
				//															   .andOperator(Criteria.where("handleAt")
				//																					.gte(DateTimeUtils.startOfMonth(year,
				//																													month)),
				//																			Criteria.where("handleAt")
				//																					.lte(DateTimeUtils.endOfMonth(year,
				//																												  month)))),
												 group("company").sum("amount").as("amount"), project("amount"));

		AggregationResults<AggregationAmount> aggregationResults = mongoTemplate.aggregate(aggregation,
																						   Lending.class,
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
	public Page<Lending> queryPage(LendingQueryRequest queryRequest) {
		int start = queryRequest.getStart();
		int limit = queryRequest.getLimit();
		Query query = buildQuery(queryRequest);

		PageRequest pageable = new PageRequest(start, limit, new Sort(Sort.Direction.DESC, "createdAt"));
		query.with(pageable);
		long count = mongoTemplate.count(query, Lending.class);
		List<Lending> list = mongoTemplate.find(query, Lending.class);

		return new PageImpl<>(list, pageable, count);
	}

	private Query buildQuery(LendingQueryRequest request) {
		Query query = new Query();

		if (request.getLendingObject() != null) {
			query.addCriteria(Criteria.where("lendingObject").is(request.getLendingObject()));
		}
		if (request.getLendingType() != null) {
			query.addCriteria(Criteria.where("lendingType").is(request.getLendingType()));
		}
		if (!StringUtils.isEmpty(request.getCompanyId())) {
			query.addCriteria(Criteria.where("company.$id").is(new ObjectId(request.getCompanyId())));
		}
		if (!StringUtils.isEmpty(request.getAccountSubjectId())) {
			query.addCriteria(Criteria.where("accountSubject.$id").is(new ObjectId(request.getAccountSubjectId())));
		}
		if (!StringUtils.isEmpty(request.getLendingToId())) {
			query.addCriteria(Criteria.where("lendingTo.$id").is(new ObjectId(request.getLendingToId())));
		}
		if (!StringUtils.isEmpty(request.getLendingById())) {
			query.addCriteria(Criteria.where("lendingBy.$id").is(new ObjectId(request.getLendingById())));
		}

		return query;
	}

}
