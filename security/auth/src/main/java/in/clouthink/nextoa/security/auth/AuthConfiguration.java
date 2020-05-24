package in.clouthink.nextoa.security.auth;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan({"in.clouthink.nextoa.security.auth"})
@EnableMongoRepositories({"in.clouthink.nextoa.security.auth.repository"})
public class AuthConfiguration {

}

