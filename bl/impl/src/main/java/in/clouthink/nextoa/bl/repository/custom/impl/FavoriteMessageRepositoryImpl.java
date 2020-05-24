package in.clouthink.nextoa.bl.repository.custom.impl;

import in.clouthink.nextoa.bl.model.*;
import in.clouthink.nextoa.bl.repository.custom.FavoriteMessageRepositoryCustom;
import in.clouthink.nextoa.bl.request.MessageQueryRequest;
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
public class FavoriteMessageRepositoryImpl extends AbstractCustomRepositoryImpl implements
																				FavoriteMessageRepositoryCustom {

	@Override
	public Page<FavoriteMessage> queryPage(User createdBy, MessageQueryRequest queryRequest) {
		Query query = buildQuery(queryRequest);
		if (createdBy != null) {
			query.addCriteria(Criteria.where("createdBy").is(createdBy));
		}

		int start = queryRequest.getStart();
		int limit = queryRequest.getLimit();
		PageRequest pageable = new PageRequest(start, limit, new Sort(Sort.Direction.DESC, "createdAt"));
		query.with(pageable);
		long count = mongoTemplate.count(query, FavoriteMessage.class);
		List<FavoriteMessage> list = mongoTemplate.find(query, FavoriteMessage.class);

		return new PageImpl<>(list, pageable, count);
	}

	private Query buildQuery(MessageQueryRequest request) {
		Query query = new Query();
		if (!StringUtils.isEmpty(request.getCategory())) {
			List<Message> messages = mongoTemplate.find(new Query(Criteria.where("category")
																		  .regex(request.getCategory())
																		  .andOperator(Criteria.where("status")
																							   .ne(MessageStatus.TERMINATED))),
														Message.class);
			query.addCriteria(Criteria.where("message").in(messages));
		}

		if (!StringUtils.isEmpty(request.getTitle())) {
			List<Message> messages = mongoTemplate.find(new Query(Criteria.where("title")
																		  .regex(request.getTitle())
																		  .andOperator(Criteria.where("status")
																							   .ne(MessageStatus.TERMINATED))),
														Message.class);
			query.addCriteria(Criteria.where("message").in(messages));
		}

		if (!StringUtils.isEmpty(request.getInitiatorUsername())) {
			Query userQuery = new Query();
			userQuery.addCriteria(Criteria.where("userType").is(UserType.APPUSER));
			userQuery.addCriteria(Criteria.where("username").regex(request.getInitiatorUsername()));
			List<Message> messages = mongoTemplate.find(new Query(Criteria.where("initiator")
																		  .in(mongoTemplate.find(userQuery, User.class))
																		  .andOperator(Criteria.where("status")
																							   .ne(MessageStatus.TERMINATED))),
														Message.class);
			query.addCriteria(Criteria.where("message").in(messages));
		}
		return query;
	}
}
