package in.clouthink.nextoa.security.audit;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan({"in.clouthink.nextoa.security.audit"})
@EnableMongoRepositories({"in.clouthink.nextoa.security.audit.repository"})
public class AuditConfiguration {

}

