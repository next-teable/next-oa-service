package in.clouthink.nextoa.rbac;

import in.clouthink.nextoa.rbac.core.spi.ResourceService;
import in.clouthink.nextoa.rbac.impl.service.impl.ResourceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 *
 */
@Configuration
@ComponentScan({"in.clouthink.nextoa.rbac"})
@EnableMongoRepositories({"in.clouthink.nextoa.rbac"})
@EnableConfigurationProperties(RbacConfigurationProperties.class)
public class RbacConfiguration {

	@Bean
	@Autowired
	public ResourceService resourceServiceImpl(RbacConfigurationProperties rbacConfigurationProperties) {
		ResourceServiceImpl result = new ResourceServiceImpl();
		result.setResourceFile(rbacConfigurationProperties.getResourceFile());
		return result;
	}

}
