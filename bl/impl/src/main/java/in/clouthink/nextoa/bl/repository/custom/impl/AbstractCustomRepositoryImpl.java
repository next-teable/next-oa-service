package in.clouthink.nextoa.bl.repository.custom.impl;

import in.clouthink.nextoa.shared.repository.custom.AbstractCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
