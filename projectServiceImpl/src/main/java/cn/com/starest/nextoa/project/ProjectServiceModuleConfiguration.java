package cn.com.starest.nextoa.project;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan({"cn.com.starest.nextoa.project.repository",
				"cn.com.starest.nextoa.project.rule",
				"cn.com.starest.nextoa.project.service"})
@EnableMongoRepositories({"cn.com.starest.nextoa.project.repository"})
public class ProjectServiceModuleConfiguration {

	public static final String CONFIGURATION_PREFIX = "cn.com.starest.nextoa.project";

}
