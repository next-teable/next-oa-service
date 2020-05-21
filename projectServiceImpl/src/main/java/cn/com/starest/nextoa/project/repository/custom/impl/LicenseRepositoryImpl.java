package cn.com.starest.nextoa.project.repository.custom.impl;

import cn.com.starest.nextoa.project.domain.model.License;
import cn.com.starest.nextoa.project.domain.request.LicenseQueryRequest;
import cn.com.starest.nextoa.project.repository.custom.LicenseRepositoryCustom;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LicenseRepositoryImpl extends AbstractCustomRepositoryImpl implements LicenseRepositoryCustom {

	@Override
	public Page<License> queryPage(LicenseQueryRequest queryRequest) {
		int start = queryRequest.getStart();
		int limit = queryRequest.getLimit();
		Query query = buildQuery(queryRequest);

		List<Sort.Order> sortOrders = new ArrayList<>();

		String[] attributesOrderByAsc = queryRequest.getAttributesOrderByAsc();
		if (attributesOrderByAsc != null && attributesOrderByAsc.length > 0) {
			for (String attr : attributesOrderByAsc) {
				sortOrders.add(new Sort.Order(Sort.Direction.ASC, attr));
			}
		}

		String[] attributesOrderByDesc = queryRequest.getAttributesOrderByDesc();
		if (attributesOrderByDesc != null && attributesOrderByDesc.length > 0) {
			for (String attr : attributesOrderByDesc) {
				sortOrders.add(new Sort.Order(Sort.Direction.DESC, attr));
			}
		}

		Sort sort = sortOrders.isEmpty() ? new Sort(Sort.Direction.DESC, "createdAt") : new Sort(sortOrders);

		PageRequest pageable = new PageRequest(start, limit, sort);
		query.with(pageable);
		long count = mongoTemplate.count(query, License.class);
		List<License> list = mongoTemplate.find(query, License.class);

		return new PageImpl<>(list, pageable, count);
	}

	private Query buildQuery(LicenseQueryRequest request) {
		Query query = new Query();

		if (!StringUtils.isEmpty(request.getCode())) {
			query.addCriteria(Criteria.where("code").regex(request.getCode()));
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

		if (null != request.getCancelled()) {
			query.addCriteria(Criteria.where("cancelled").is(request.getCancelled().booleanValue()));
		}

		return query;
	}

}
