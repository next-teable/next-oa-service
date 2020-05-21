package cn.com.starest.nextoa.project.repository.custom.impl;

import cn.com.starest.nextoa.model.dtr.DateRangedQueryRequest;
import cn.com.starest.nextoa.repository.shared.custom.AbstractCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.math.BigDecimal;

@NoRepositoryBean
public class AbstractCustomRepositoryImpl implements AbstractCustomRepository {

	@Autowired
	protected MongoTemplate mongoTemplate;

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void buildDateRangePart(Query query, DateRangedQueryRequest request, String fieldName) {
		if (request.getBeginDate() != null && request.getEndDate() != null) {
			Criteria criteria = new Criteria().andOperator(Criteria.where(fieldName).gte(request.getBeginDate()),
														   Criteria.where(fieldName).lte(request.getEndDate()));
			query.addCriteria(criteria);
		}
		else if (request.getBeginDate() != null) {
			query.addCriteria(Criteria.where(fieldName).gte(request.getBeginDate()));
		}
		else if (request.getEndDate() != null) {
			query.addCriteria(Criteria.where(fieldName).lte(request.getEndDate()));
		}
	}

}
