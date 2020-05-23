package in.clouthink.nextoa.repository;

import in.clouthink.nextoa.repository.shared.converter.BigDecimalToDoubleConverter;
import in.clouthink.nextoa.repository.shared.converter.DoubleToBigDecimalConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.convert.*;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Arrays;

@Configuration
@ComponentScan({"in.clouthink.nextoa.repository"})
@EnableMongoRepositories({"in.clouthink.nextoa.repository"})
public class RepositoryConfiguration {

	public static final String CONFIGURATION_PREFIX = "in.clouthink.nextoa.repository";

	@Autowired
	@Bean
	public MongoConverter mongoConverter(MongoDbFactory factory) {
		DbRefResolver dbRefResolver = new DefaultDbRefResolver(factory);
		MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, new MongoMappingContext());
		converter.setCustomConversions(new CustomConversions(Arrays.asList(new BigDecimalToDoubleConverter(),
																		   new DoubleToBigDecimalConverter())));
		converter.afterPropertiesSet();
		return converter;
	}

}
