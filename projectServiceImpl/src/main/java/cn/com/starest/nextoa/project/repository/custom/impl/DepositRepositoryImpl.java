package cn.com.starest.nextoa.project.repository.custom.impl;

import cn.com.starest.nextoa.project.domain.model.Company;
import cn.com.starest.nextoa.project.domain.model.Deposit;
import cn.com.starest.nextoa.project.domain.model.Project;
import cn.com.starest.nextoa.project.domain.request.DepositQueryRequest;
import cn.com.starest.nextoa.project.repository.custom.DepositRepositoryCustom;
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
public class DepositRepositoryImpl extends AbstractCustomRepositoryImpl implements DepositRepositoryCustom {

	@Override
	public Page<Deposit> queryPage(DepositQueryRequest queryRequest) {
		int start = queryRequest.getStart();
		int limit = queryRequest.getLimit();
		Query query = buildQuery(queryRequest);

		PageRequest pageable = new PageRequest(start, limit, new Sort(Sort.Direction.DESC, "createdAt"));
		query.with(pageable);
		long count = mongoTemplate.count(query, Deposit.class);
		List<Deposit> list = mongoTemplate.find(query, Deposit.class);

		return new PageImpl<>(list, pageable, count);
	}

	@Override
	public BigDecimal calculateTotalTransferAmount(Company company) {
		Aggregation aggregation = newAggregation(match(Criteria.where("company.$id").is(new ObjectId(company.getId()))),
												 group("company").sum("amount").as("amount"),
												 project("amount"));

		AggregationResults<AggregationAmount> aggregationResults = mongoTemplate.aggregate(aggregation,
																						   Deposit.class,
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
	public BigDecimal calculateTotalTransferAmount(Company company, int year, int month) {
		Aggregation aggregation = newAggregation(match(Criteria.where("company.$id")
															   .is(new ObjectId(company.getId()))
															   .and("transferDate")
															   .lte(DateTimeUtils.endOfMonth(year, month))),
												 group("company").sum("amount").as("amount"),
												 project("amount"));

		AggregationResults<AggregationAmount> aggregationResults = mongoTemplate.aggregate(aggregation,
																						   Deposit.class,
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
	public BigDecimal calculateTotalReturnedAmount(Company company) {
		Aggregation aggregation = newAggregation(match(Criteria.where("company.$id").is(new ObjectId(company.getId()))),
												 group("company").sum("returnedAmount").as("amount"),
												 project("amount"));

		AggregationResults<AggregationAmount> aggregationResults = mongoTemplate.aggregate(aggregation,
																						   Deposit.class,
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
	public BigDecimal calculateTotalReturnedAmount(Company company, int year, int month) {
		Aggregation aggregation = newAggregation(match(Criteria.where("company.$id")
															   .is(new ObjectId(company.getId()))
															   .and("returnedDate")
															   .lte(DateTimeUtils.endOfMonth(year, month))),
												 group("company").sum("returnedAmount").as("amount"),
												 project("amount"));

		AggregationResults<AggregationAmount> aggregationResults = mongoTemplate.aggregate(aggregation,
																						   Deposit.class,
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
	public BigDecimal calculateTotalTransferAmount(Project project) {
		Aggregation aggregation = newAggregation(match(Criteria.where("project.$id").is(new ObjectId(project.getId()))),
												 group("project").sum("amount").as("amount"),
												 project("amount"));

		AggregationResults<AggregationAmount> aggregationResults = mongoTemplate.aggregate(aggregation,
																						   Deposit.class,
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
	public BigDecimal calculateTotalReturnedAmount(Project project) {
		Aggregation aggregation = newAggregation(match(Criteria.where("project.$id").is(new ObjectId(project.getId()))),
												 group("company").sum("returnedAmount").as("amount"),
												 project("amount"));

		AggregationResults<AggregationAmount> aggregationResults = mongoTemplate.aggregate(aggregation,
																						   Deposit.class,
																						   AggregationAmount.class);
		if (aggregationResults != null) {
			AggregationAmount result = aggregationResults.getUniqueMappedResult();
			if (result != null) {
				return result.getAmount();
			}
		}
		return BigDecimal.ZERO;
	}

	private Query buildQuery(DepositQueryRequest request) {
		Query query = new Query();

		if (!StringUtils.isEmpty(request.getDepositTypeId())) {
			//the deposit id is specified by manual
			query.addCriteria(Criteria.where("type.id").is(request.getDepositTypeId()));
		}

		if (!StringUtils.isEmpty(request.getCompanyId())) {
			query.addCriteria(Criteria.where("company.$id").is(new ObjectId(request.getCompanyId())));
		}

		if (!StringUtils.isEmpty(request.getProjectName())) {
			query.addCriteria(Criteria.where("projectName").regex(request.getProjectName()));
		}

		if (!StringUtils.isEmpty(request.getProjectId())) {
			query.addCriteria(Criteria.where("project.$id").is(new ObjectId(request.getProjectId())));
		}

		if (!StringUtils.isEmpty(request.getTransferTo())) {
			query.addCriteria(Criteria.where("transferTo").regex(request.getTransferTo()));
		}

		if (!StringUtils.isEmpty(request.getTransferOperator())) {
			query.addCriteria(Criteria.where("transferOperator").regex(request.getTransferOperator()));
		}

		return query;
	}

}
