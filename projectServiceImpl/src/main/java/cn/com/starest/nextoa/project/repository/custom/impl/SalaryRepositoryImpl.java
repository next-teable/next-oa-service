package cn.com.starest.nextoa.project.repository.custom.impl;

import cn.com.starest.nextoa.project.domain.model.BizDepartment;
import cn.com.starest.nextoa.project.domain.model.Company;
import cn.com.starest.nextoa.project.domain.model.Project;
import cn.com.starest.nextoa.project.domain.model.Salary;
import cn.com.starest.nextoa.project.domain.request.SalaryQueryRequest;
import cn.com.starest.nextoa.project.repository.custom.SalaryRepositoryCustom;
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
public class SalaryRepositoryImpl extends AbstractCustomRepositoryImpl implements SalaryRepositoryCustom {

	@Override
	public Page<Salary> queryPage(SalaryQueryRequest queryRequest) {
		int start = queryRequest.getStart();
		int limit = queryRequest.getLimit();
		Query query = buildQuery(queryRequest);

		PageRequest pageable = new PageRequest(start, limit, new Sort(Sort.Direction.DESC, "createdAt"));
		query.with(pageable);
		long count = mongoTemplate.count(query, Salary.class);
		List<Salary> list = mongoTemplate.find(query, Salary.class);

		return new PageImpl<>(list, pageable, count);
	}

	@Override
	public BigDecimal calculateTotalPay(Company company, int year) {
		Aggregation aggregation = newAggregation(match(Criteria.where("company.$id")
															   .is(new ObjectId(company.getId()))
															   .and("year")
															   .is(year)),
												 group("company").sum("totalPay").as("amount"),
												 project("amount"));

		AggregationResults<AggregationAmount> aggregationResults = mongoTemplate.aggregate(aggregation,
																						   Salary.class,
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
	public BigDecimal calculateTotalPay(Company company, int year, int month) {
		Aggregation aggregation = newAggregation(match(Criteria.where("company.$id")
															   .is(new ObjectId(company.getId()))
															   .and("year")
															   .is(year)
															   .and("month")
															   .is(month)),
												 group("company").sum("totalPay").as("amount"),
												 project("amount"));

		AggregationResults<AggregationAmount> aggregationResults = mongoTemplate.aggregate(aggregation,
																						   Salary.class,
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
	public BigDecimal calculateTotalPay(BizDepartment bizDepartment, int year, int month) {
		Aggregation aggregation = newAggregation(match(Criteria.where("bizDepartment.$id")
															   .is(new ObjectId(bizDepartment.getId()))
															   .and("year")
															   .is(year)
															   .and("month")
															   .is(month)),
												 group("bizDepartment").sum("totalPay").as("amount"),
												 project("amount"));

		AggregationResults<AggregationAmount> aggregationResults = mongoTemplate.aggregate(aggregation,
																						   Salary.class,
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
	public BigDecimal calculateTotalPay(Project project, int year, int month) {
		Aggregation aggregation = newAggregation(match(Criteria.where("project.$id")
															   .is(new ObjectId(project.getId()))
															   .and("year")
															   .is(year)
															   .and("month")
															   .is(month)),
												 group("project").sum("totalPay").as("amount"),
												 project("amount"));

		AggregationResults<AggregationAmount> aggregationResults = mongoTemplate.aggregate(aggregation,
																						   Salary.class,
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
	public BigDecimal calculateProjectTotalPay(Company company, int year) {
		Aggregation aggregation = newAggregation(match(Criteria.where("company.$id")
															   .is(new ObjectId(company.getId()))
															   .and("year")
															   .is(year)
															   .and("project")
															   .ne(null)),
												 group("company").sum("totalPay").as("amount"),
												 project("amount"));

		AggregationResults<AggregationAmount> aggregationResults = mongoTemplate.aggregate(aggregation,
																						   Salary.class,
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
	public BigDecimal calculateProjectTotalPay(Project project) {
		Aggregation aggregation = newAggregation(match(Criteria.where("project.$id").is(new ObjectId(project.getId()))),
												 group("project").sum("totalPay").as("amount"),
												 project("amount"));

		AggregationResults<AggregationAmount> aggregationResults = mongoTemplate.aggregate(aggregation,
																						   Salary.class,
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
	public BigDecimal calculateProjectTotalPay(Project project, int year) {
		Aggregation aggregation = newAggregation(match(Criteria.where("project.$id")
															   .is(new ObjectId(project.getId()))
															   .and("year")
															   .is(year)),
												 group("project").sum("totalPay").as("amount"),
												 project("amount"));

		AggregationResults<AggregationAmount> aggregationResults = mongoTemplate.aggregate(aggregation,
																						   Salary.class,
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
	public BigDecimal calculateBizDepartmentTotalPay(Company company, int year) {
		Aggregation aggregation = newAggregation(match(Criteria.where("company.$id")
															   .is(new ObjectId(company.getId()))
															   .and("year")
															   .is(year)
															   .and("bizDepartment")
															   .ne(null)),
												 group("company").sum("totalPay").as("amount"),
												 project("amount"));

		AggregationResults<AggregationAmount> aggregationResults = mongoTemplate.aggregate(aggregation,
																						   Salary.class,
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
	public BigDecimal calculateBizDepartmentTotalPay(BizDepartment bizDepartment, int year) {
		Aggregation aggregation = newAggregation(match(Criteria.where("bizDepartment.$id")
															   .is(new ObjectId(bizDepartment.getId()))
															   .and("year")
															   .is(year)),
												 group("bizDepartment").sum("totalPay").as("amount"),
												 project("amount"));

		AggregationResults<AggregationAmount> aggregationResults = mongoTemplate.aggregate(aggregation,
																						   Salary.class,
																						   AggregationAmount.class);
		if (aggregationResults != null) {
			AggregationAmount result = aggregationResults.getUniqueMappedResult();
			if (result != null) {
				return result.getAmount();
			}
		}
		return BigDecimal.ZERO;
	}

	private Query buildQuery(SalaryQueryRequest request) {
		Query query = new Query();

		if (!StringUtils.isEmpty(request.getCompanyId())) {
			query.addCriteria(Criteria.where("company.$id").is(new ObjectId(request.getCompanyId())));
		}

		if (!StringUtils.isEmpty(request.getProjectId())) {
			query.addCriteria(Criteria.where("project.$id").is(new ObjectId(request.getProjectId())));
		}

		if (!StringUtils.isEmpty(request.getBizDepartmentId())) {
			query.addCriteria(Criteria.where("bizDepartment.$id").is(new ObjectId(request.getBizDepartmentId())));
		}

		if (!StringUtils.isEmpty(request.getEmployee())) {
			query.addCriteria(Criteria.where("employee").regex(request.getEmployee()));
		}

		if (null != request.getYear()) {
			query.addCriteria(Criteria.where("year").is(request.getYear()));
		}

		if (null != request.getMonth()) {
			query.addCriteria(Criteria.where("month").is(request.getMonth()));
		}

		return query;
	}

}
