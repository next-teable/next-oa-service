package cn.com.starest.nextoa.repository.custom.impl;

import cn.com.starest.nextoa.repository.shared.custom.AbstractCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public class AbstractCustomRepositoryImpl implements AbstractCustomRepository {

	@Autowired
//	@Qualifier("nextoaMongoTemplate")
	protected MongoTemplate mongoTemplate;

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

}
