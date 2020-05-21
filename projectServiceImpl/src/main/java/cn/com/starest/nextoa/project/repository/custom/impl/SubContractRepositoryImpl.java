package cn.com.starest.nextoa.project.repository.custom.impl;

import cn.com.starest.nextoa.project.domain.model.SubContract;
import cn.com.starest.nextoa.project.domain.request.SubContractQueryRequest;
import cn.com.starest.nextoa.project.repository.custom.SubContractRepositoryCustom;
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
public class SubContractRepositoryImpl extends AbstractCustomRepositoryImpl implements SubContractRepositoryCustom {

	@Override
	public Page<SubContract> queryPage(SubContractQueryRequest queryRequest) {
		int start = queryRequest.getStart();
		int limit = queryRequest.getLimit();
		Query query = buildQuery(queryRequest);

		PageRequest pageable = new PageRequest(start, limit, new Sort(Sort.Direction.DESC, "createdAt"));
		query.with(pageable);
		long count = mongoTemplate.count(query, SubContract.class);
		List<SubContract> list = mongoTemplate.find(query, SubContract.class);

		return new PageImpl<>(list, pageable, count);
	}

	private Query buildQuery(SubContractQueryRequest request) {
		Query query = new Query();

		if (!StringUtils.isEmpty(request.getCode())) {
			query.addCriteria(Criteria.where("code").regex(request.getCode()));
		}

		if (!StringUtils.isEmpty(request.getName())) {
			query.addCriteria(Criteria.where("name").regex(request.getName()));
		}

		if (!StringUtils.isEmpty(request.getProjectId())) {
			query.addCriteria(Criteria.where("project.$id").is(new ObjectId(request.getProjectId())));
		}

		if (!StringUtils.isEmpty(request.getContractId())) {
			query.addCriteria(Criteria.where("contract.$id").is(new ObjectId(request.getContractId())));
		}

		if (!StringUtils.isEmpty(request.getSubContractorId())) {
			query.addCriteria(Criteria.where("subContractor.$id").is(new ObjectId(request.getSubContractorId())));
		}

		if (null != request.getPublished()) {
			query.addCriteria(Criteria.where("published").is(request.getPublished().booleanValue()));
		}

		return query;
	}

}
