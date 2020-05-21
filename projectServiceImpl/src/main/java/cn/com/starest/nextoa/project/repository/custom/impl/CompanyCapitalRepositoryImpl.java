package cn.com.starest.nextoa.project.repository.custom.impl;

import cn.com.starest.nextoa.project.domain.model.CompanyCapital;
import cn.com.starest.nextoa.project.domain.request.CompanyCapitalQueryRequest;
import cn.com.starest.nextoa.project.repository.custom.CompanyCapitalRepositoryCustom;
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
public class CompanyCapitalRepositoryImpl extends AbstractCustomRepositoryImpl implements CompanyCapitalRepositoryCustom {

	@Override
	public Page<CompanyCapital> queryPage(CompanyCapitalQueryRequest queryRequest) {
		int start = queryRequest.getStart();
		int limit = queryRequest.getLimit();
		Query query = buildQuery(queryRequest);

		PageRequest pageable = new PageRequest(start, limit, new Sort(Sort.Direction.DESC, "year", "month"));
		query.with(pageable);
		long count = mongoTemplate.count(query, CompanyCapital.class);
		List<CompanyCapital> list = mongoTemplate.find(query, CompanyCapital.class);

		return new PageImpl<>(list, pageable, count);
	}

	private Query buildQuery(CompanyCapitalQueryRequest request) {
		Query query = new Query();

		if (!StringUtils.isEmpty(request.getCompanyId())) {
			query.addCriteria(Criteria.where("company.$id").is(new ObjectId(request.getCompanyId())));
		}

		if (request.getYear() != null) {
			query.addCriteria(Criteria.where("year").is(request.getYear()));
		}

		return query;
	}

}
