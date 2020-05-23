package cn.com.starest.nextoa.repository.custom.impl;

import cn.com.starest.nextoa.model.Message;
import cn.com.starest.nextoa.model.MessageStatus;
import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.model.UserType;
import cn.com.starest.nextoa.model.dtr.MessageQueryRequest;
import cn.com.starest.nextoa.repository.custom.MessageRepositoryCustom;
import org.springframework.data.domain.*;
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
public class MessageRepositoryImpl extends AbstractCustomRepositoryImpl implements MessageRepositoryCustom {

	@Override
	public Page<Message> queryPage(User receiver,
								   MessageQueryRequest queryRequest,
								   MessageQueryRequest.IncludeOrExcludeStatus includeOrExcludeStatus) {
		if (includeOrExcludeStatus == null) {
			includeOrExcludeStatus = MessageQueryRequest.IncludeOrExcludeStatus.INCLUDE;
		}
		Query query = buildQuery(queryRequest, includeOrExcludeStatus);
		if (receiver != null) {
			query.addCriteria(Criteria.where("receiver").is(receiver));
		}

		int start = queryRequest.getStart();
		int limit = queryRequest.getLimit();
		PageRequest pageable = new PageRequest(start, limit, new Sort(Sort.Direction.DESC, "modifiedAt"));
		query.with(pageable);
		long count = mongoTemplate.count(query, Message.class);
		List<Message> list = mongoTemplate.find(query, Message.class);

		return new PageImpl<>(list, pageable, count);
	}

	@Override
	public Page<Message> queryPageByPaperCreator(String username, User byWho, Pageable pageable) {
		Query userQuery = new Query();
		userQuery.addCriteria(Criteria.where("userType").is(UserType.APPUSER));
		userQuery.addCriteria(Criteria.where("username").regex(username));

		Query messageQuery = new Query();
		//排除所有已终止的状态
		messageQuery.addCriteria(Criteria.where("status").ne(MessageStatus.TERMINATED));

		messageQuery.addCriteria(Criteria.where("receiver").is(byWho));
		messageQuery.addCriteria(Criteria.where("initiator").in(mongoTemplate.find(userQuery, User.class)));

		messageQuery.with(pageable);
		long count = mongoTemplate.count(messageQuery, Message.class);
		List<Message> list = mongoTemplate.find(messageQuery, Message.class);

		return new PageImpl<>(list, pageable, count);
	}

	@Override
	public Page<Message> queryPageByReceiver(String username, User byWho, Pageable pageable) {
		Query userQuery = new Query();
		userQuery.addCriteria(Criteria.where("userType").is(UserType.APPUSER));
		userQuery.addCriteria(Criteria.where("username").regex(username));

		Query messageQuery = new Query();
		//排除所有已终止的状态
		messageQuery.addCriteria(Criteria.where("status").ne(MessageStatus.TERMINATED));

		messageQuery.addCriteria(new Criteria().orOperator(Criteria.where("initiator").is(byWho),
														   Criteria.where("sender").is(byWho)));
		messageQuery.addCriteria(Criteria.where("receiver").in(mongoTemplate.find(userQuery, User.class)));

		messageQuery.with(pageable);
		long count = mongoTemplate.count(messageQuery, Message.class);
		List<Message> list = mongoTemplate.find(messageQuery, Message.class);

		return new PageImpl<>(list, pageable, count);
	}

	@Override
	public long queryCount(User receiver,
						   MessageQueryRequest queryRequest,
						   MessageQueryRequest.IncludeOrExcludeStatus includeOrExcludeStatus) {
		if (includeOrExcludeStatus == null) {
			includeOrExcludeStatus = MessageQueryRequest.IncludeOrExcludeStatus.INCLUDE;
		}
		Query query = buildQuery(queryRequest, includeOrExcludeStatus);

		if (receiver != null) {
			query.addCriteria(Criteria.where("receiver").is(receiver));
		}

		return mongoTemplate.count(query, Message.class);
	}

	@Override
	public void markAsRead(String id) {
		mongoTemplate.updateFirst(query(where("id").is(id)), update("read", true), Message.class);
	}

	private Query buildQuery(MessageQueryRequest request,
							 MessageQueryRequest.IncludeOrExcludeStatus includeOrExcludeStatus) {
		Query query = new Query();
		//分类
		if (!StringUtils.isEmpty(request.getCategory())) {
			query.addCriteria(Criteria.where("category").regex(request.getCategory()));
		}
		//标题
		if (!StringUtils.isEmpty(request.getTitle())) {
			query.addCriteria(Criteria.where("title").regex(request.getTitle()));
		}
		//发起人
		if (!StringUtils.isEmpty(request.getInitiatorUsername())) {
			Query userQuery = new Query();
			userQuery.addCriteria(Criteria.where("userType").is(UserType.APPUSER));
			userQuery.addCriteria(Criteria.where("username").regex(request.getInitiatorUsername()));
			query.addCriteria(Criteria.where("initiator").in(mongoTemplate.find(userQuery, User.class)));
		}
		//状态
		if (request.getMessageStatus() != null) {
			if (MessageQueryRequest.IncludeOrExcludeStatus.INCLUDE == includeOrExcludeStatus) {
				query.addCriteria(new Criteria().andOperator(Criteria.where("status").is(request.getMessageStatus()),
															 Criteria.where("status").ne(MessageStatus.TERMINATED)));
			}
			else if (MessageQueryRequest.IncludeOrExcludeStatus.EXCLUDE == includeOrExcludeStatus) {
				query.addCriteria(new Criteria().andOperator(Criteria.where("status").ne(request.getMessageStatus()),
															 Criteria.where("status").ne(MessageStatus.TERMINATED)));
			}
		}
		else {
			//排除所有已终止的状态
			query.addCriteria(Criteria.where("status").ne(MessageStatus.TERMINATED));
		}
		//时间范围
		if (request.getBeginDate() != null && request.getEndDate() != null) {
			Criteria criteria = new Criteria().andOperator(Criteria.where("receivedAt").gte(request.getBeginDate()),
														   Criteria.where("receivedAt").lte(request.getEndDate()));
			query.addCriteria(criteria);
		}
		else if (request.getBeginDate() != null) {
			query.addCriteria(Criteria.where("receivedAt").gte(request.getBeginDate()));
		}
		else if (request.getEndDate() != null) {
			query.addCriteria(Criteria.where("receivedAt").lte(request.getEndDate()));
		}
		return query;
	}

}
