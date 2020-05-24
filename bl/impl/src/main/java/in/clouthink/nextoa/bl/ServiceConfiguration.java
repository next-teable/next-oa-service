package in.clouthink.nextoa.bl;

import in.clouthink.nextoa.shared.repository.converter.BigDecimalToDoubleConverter;
import in.clouthink.nextoa.shared.repository.converter.DoubleToBigDecimalConverter;
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
@ComponentScan({"in.clouthink.nextoa.bl.service",
        "in.clouthink.nextoa.bl.repository"})
@EnableMongoRepositories({"in.clouthink.nextoa.bl.repository"})
public class ServiceConfiguration {

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

