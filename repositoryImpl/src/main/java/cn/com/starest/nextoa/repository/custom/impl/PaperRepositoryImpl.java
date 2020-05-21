package cn.com.starest.nextoa.repository.custom.impl;

import cn.com.starest.nextoa.model.Paper;
import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.model.dtr.PaperQueryRequest;
import cn.com.starest.nextoa.repository.custom.PaperRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

/**
 * @author dz
 */
@Repository
public class PaperRepositoryImpl extends AbstractCustomRepositoryImpl implements PaperRepositoryCustom {

	@Override
	public Page<Paper> queryPage(User creator,
								 PaperQueryRequest request,
								 PaperQueryRequest.IncludeOrExcludeStatus includeOrExcludeStatus) {
		Query query = buildQuery(request, includeOrExcludeStatus);
		if (creator != null) {
			query.addCriteria(Criteria.where("createdBy").is(creator));
		}

		int start = request.getStart();
		int limit = request.getLimit();
		PageRequest pageable = new PageRequest(start, limit, new Sort(Sort.Direction.DESC, "createdAt"));
		query.with(pageable);
		long count = mongoTemplate.count(query, Paper.class);
		List<Paper> list = mongoTemplate.find(query, Paper.class);

		return new PageImpl<>(list, pageable, count);
	}

	@Override
	public long queryCount(User creator,
						   PaperQueryRequest request,
						   PaperQueryRequest.IncludeOrExcludeStatus includeOrExcludeStatus) {
		Query query = buildQuery(request, includeOrExcludeStatus);
		if (creator != null) {
			query.addCriteria(Criteria.where("createdBy").is(creator));
		}

		return mongoTemplate.count(query, Paper.class);
	}

	@Override
	public void updateReadCounter(String id, int readCounter) {
		mongoTemplate.updateFirst(query(where("id").is(id)), update("readCounter", readCounter), Paper.class);
	}

	@Override
	public void markPaperBusinessComplete(Paper paper) {
		mongoTemplate.updateFirst(query(where("id").is(paper.getId())), update("businessComplete", true), Paper.class);
	}

	private Query buildQuery(PaperQueryRequest request,
							 PaperQueryRequest.IncludeOrExcludeStatus includeOrExcludeStatus) {
		Query query = new Query();

		if (!StringUtils.isEmpty(request.getCategory())) {
			query.addCriteria(Criteria.where("category").regex(request.getCategory()));
		}

		if (!StringUtils.isEmpty(request.getTitle())) {
			query.addCriteria(Criteria.where("title").regex(request.getTitle()));
		}

		if (request.getPaperStatus() != null) {
			if (PaperQueryRequest.IncludeOrExcludeStatus.INCLUDE == includeOrExcludeStatus) {
				query.addCriteria(Criteria.where("status").is(request.getPaperStatus()));
			}
			else if (PaperQueryRequest.IncludeOrExcludeStatus.EXCLUDE == includeOrExcludeStatus) {
				query.addCriteria(Criteria.where("status").ne(request.getPaperStatus()));
			}
		}

		if (request.getBeginDate() != null && request.getEndDate() != null) {
			Criteria criteria = new Criteria().andOperator(Criteria.where("createdAt").gte(request.getBeginDate()),
														   Criteria.where("createdAt").lte(request.getEndDate()));
			query.addCriteria(criteria);
		}
		else if (request.getBeginDate() != null) {
			query.addCriteria(Criteria.where("createdAt").gte(request.getBeginDate()));
		}
		else if (request.getEndDate() != null) {
			query.addCriteria(Criteria.where("createdAt").lte(request.getEndDate()));
		}

		return query;
	}

}
