package cn.com.starest.nextoa.project.repository.custom.impl;

import cn.com.starest.nextoa.project.domain.model.SalaryAggregation;
import cn.com.starest.nextoa.project.domain.request.SalaryAggregationQueryRequest;
import cn.com.starest.nextoa.project.repository.custom.SalaryAggregationRepositoryCustom;
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
public class SalaryAggregationRepositoryImpl extends AbstractCustomRepositoryImpl implements
																				  SalaryAggregationRepositoryCustom {

	@Override
	public Page<SalaryAggregation> queryPage(SalaryAggregationQueryRequest queryRequest,
											 SalaryAggregation.AggregationType aggregationType) {
		int start = queryRequest.getStart();
		int limit = queryRequest.getLimit();
		Query query = buildQuery(queryRequest, aggregationType);

		PageRequest pageable = new PageRequest(start, limit, new Sort(Sort.Direction.DESC, "year", "month", "company"));
		query.with(pageable);
		long count = mongoTemplate.count(query, SalaryAggregation.class);
		List<SalaryAggregation> list = mongoTemplate.find(query, SalaryAggregation.class);

		return new PageImpl<>(list, pageable, count);
	}

	private Query buildQuery(SalaryAggregationQueryRequest request, SalaryAggregation.AggregationType aggregationType) {
		Query query = new Query();

		if (request.getYear() != null) {
			query.addCriteria(Criteria.where("year").is(request.getYear()));
		}

		switch (aggregationType) {
			case BY_YEAR:
				query.addCriteria(Criteria.where("month").is(-1));
				break;
			case BY_MONTH:
				query.addCriteria(Criteria.where("month").ne(-1));
				break;
			default:
				if (request.getMonth() != null) {
					query.addCriteria(Criteria.where("month").is(request.getMonth()));
				}
		}

		if (!StringUtils.isEmpty(request.getCompanyId())) {
			query.addCriteria(Criteria.where("company.$id").is(new ObjectId(request.getCompanyId())));
		}

		return query;
	}

}
