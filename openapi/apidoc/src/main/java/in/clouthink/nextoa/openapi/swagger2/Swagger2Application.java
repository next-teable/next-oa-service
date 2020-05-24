package in.clouthink.nextoa.openapi.swagger2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@ComponentScan({"in.clouthink.nextoa.bl", "in.clouthink.daas.fss"})
public class Swagger2Application {

    public static void main(String[] args) {
        SpringApplication.run(new Object[]{Swagger2Application.class,
                SpringfoxConfiguration.class}, args);
    }

}
