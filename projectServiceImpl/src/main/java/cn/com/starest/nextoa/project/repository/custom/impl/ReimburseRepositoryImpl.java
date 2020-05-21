package cn.com.starest.nextoa.project.repository.custom.impl;

import cn.com.starest.nextoa.model.Message;
import cn.com.starest.nextoa.project.domain.model.Reimburse;
import cn.com.starest.nextoa.project.domain.request.ReimburseQueryRequest;
import cn.com.starest.nextoa.project.domain.request.ReimburseRequestRefer;
import cn.com.starest.nextoa.project.repository.custom.ReimburseRepositoryCustom;
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
public class ReimburseRepositoryImpl extends AbstractCustomRepositoryImpl implements ReimburseRepositoryCustom {

	@Override
	public Page<Reimburse> queryPage(ReimburseQueryRequest queryRequest) {
		int start = queryRequest.getStart();
		int limit = queryRequest.getLimit();
		Query query = buildQuery(queryRequest);

		PageRequest pageable = new PageRequest(start, limit, new Sort(Sort.Direction.DESC, "createdAt"));
		query.with(pageable);
		long count = mongoTemplate.count(query, Reimburse.class);
		List<Reimburse> list = mongoTemplate.find(query, Reimburse.class);

		return new PageImpl<>(list, pageable, count);
	}

	private Query buildQuery(ReimburseQueryRequest request) {
		Query query = new Query();

		if (!StringUtils.isEmpty(request.getRequestReferId())) {
			ReimburseRequestRefer requestReferType = request.getRequestReferType();
			if (ReimburseRequestRefer.PAPER == requestReferType) {
				query.addCriteria(Criteria.where("paper.$id").is(new ObjectId(request.getRequestReferId())));
			}
			else if (ReimburseRequestRefer.MESSAGE == requestReferType) {
				Message message = mongoTemplate.findById(request.getRequestReferId(), Message.class);
				if (message != null) {
					query.addCriteria(Criteria.where("paper.$id").is(new ObjectId(message.getBizRefId())));
				}
			}
		}

		if (!StringUtils.isEmpty(request.getProjectId())) {
			query.addCriteria(Criteria.where("project.$id").is(new ObjectId(request.getProjectId())));
		}

		if (!StringUtils.isEmpty(request.getBizDepartmentId())) {
			query.addCriteria(Criteria.where("bizDepartment.$id").is(new ObjectId(request.getBizDepartmentId())));
		}

		if (null != request.getSettled()) {
			query.addCriteria(Criteria.where("settled").is(request.getSettled().booleanValue()));
		}

		return query;
	}

}
