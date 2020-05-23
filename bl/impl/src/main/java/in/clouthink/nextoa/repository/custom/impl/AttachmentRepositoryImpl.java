package in.clouthink.nextoa.repository.custom.impl;

import in.clouthink.nextoa.model.Attachment;
import in.clouthink.nextoa.model.dtr.AttachmentQueryRequest;
import in.clouthink.nextoa.repository.QueryBuilder;
import in.clouthink.nextoa.repository.custom.AttachmentRepositoryCustom;
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

@Repository
public class AttachmentRepositoryImpl extends AbstractCustomRepositoryImpl implements AttachmentRepositoryCustom {

	@Override
	public Page<Attachment> queryPage(AttachmentQueryRequest queryRequest) {
		int start = queryRequest.getStart();
		int limit = queryRequest.getLimit();
		Query query = buildQuery(queryRequest);

		PageRequest pageable = new PageRequest(start, limit, new Sort(Sort.Direction.DESC, "createdAt"));
		query.with(pageable);
		long count = mongoTemplate.count(query, Attachment.class);
		List<Attachment> list = mongoTemplate.find(query, Attachment.class);

		return new PageImpl<>(list, pageable, count);
	}

	private Query buildQuery(AttachmentQueryRequest request) {
		QueryBuilder queryBuilder = new QueryBuilder();
		if (!StringUtils.isEmpty(request.getTitle())) {
			queryBuilder.add(Criteria.where("title").regex(request.getTitle()));
		}
		if (!StringUtils.isEmpty(request.getCategory())) {
			queryBuilder.add(Criteria.where("category").regex(request.getCategory()));
		}

		if (request.getPublished() != null) {
			queryBuilder.add(Criteria.where("published").is(request.getPublished().booleanValue()));
		}

		if (request.getCreatedAtBegin() != null && request.getCreatedAtEnd() != null) {
			queryBuilder.and(Criteria.where("createdAt").gte(request.getCreatedAtBegin()))
						.and(Criteria.where("createdAt").lte(request.getCreatedAtEnd()));
		}
		else if (request.getCreatedAtBegin() != null) {
			queryBuilder.add(Criteria.where("createdAt").gte(request.getCreatedAtBegin()));
		}
		else if (request.getCreatedAtEnd() != null) {
			queryBuilder.add(Criteria.where("createdAt").lte(request.getCreatedAtEnd()));
		}

		if (request.getBeginDate() != null && request.getEndDate() != null) {
			queryBuilder.and(Criteria.where("publishedAt").gte(request.getBeginDate()))
						.and(Criteria.where("publishedAt").lte(request.getEndDate()));
		}
		else if (request.getBeginDate() != null) {
			queryBuilder.add(Criteria.where("publishedAt").gte(request.getBeginDate()));
		}
		else if (request.getEndDate() != null) {
			queryBuilder.add(Criteria.where("publishedAt").lte(request.getEndDate()));
		}
		return queryBuilder.build();
	}

	@Override
	public void updateDownloadCounter(String id, int downloadCounter) {
		mongoTemplate.updateFirst(query(where("id").is(id)), update("downloadCounter", downloadCounter), Attachment.class);
	}

}
