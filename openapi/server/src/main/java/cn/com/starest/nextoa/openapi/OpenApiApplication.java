package cn.com.starest.nextoa.openapi;

import cn.com.starest.nextoa.dashboard.support.audit.security.SecurityContextImpl;
import cn.com.starest.nextoa.dashboard.support.audit.spiImpl.AuditEventPersisterImpl;
import cn.com.starest.nextoa.event.EventModuleConfiguration;
import cn.com.starest.nextoa.model.ModelConfiguration;
import cn.com.starest.nextoa.rbac.RbacConfiguration;
import cn.com.starest.nextoa.repository.RepositoryConfiguration;
import cn.com.starest.nextoa.service.ServiceConfiguration;
import in.clouthink.daas.audit.annotation.EnableAudit;
import in.clouthink.daas.audit.configure.AuditConfigurer;
import in.clouthink.daas.audit.spi.AuditEventPersister;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan({"cn.com.starest.nextoa.dashboard"})
@Import({OpenApiSecurityConfigurer.class, OpenApiWebMvcConfigurer.class})
@EnableMongoRepositories({"cn.com.starest.nextoa.dashboard.support.audit.repository",
						  "cn.com.starest.nextoa.dashboard.support.auth.repository"})
@EnableAudit
public class OpenApiApplication  {

	@Bean
	public AuditEventPersister auditEventPersisterImpl() {
		return new AuditEventPersisterImpl();
	}

	@Bean
	public AuditConfigurer auditConfigurer() {
		return result -> {
			result.setSecurityContext(new SecurityContextImpl());
			result.setAuditEventPersister(auditEventPersisterImpl());
			result.setErrorDetailRequired(true);
		};
	}
//
//	@Override
//	protected WebApplicationContext run(SpringApplication application) {
//		application.getSources().remove(ErrorPageFilter.class);
//		return super.run(application);
//	}

	public static void main(String[] args) {
		SpringApplication.run(new Object[]{ModelConfiguration.class,
										   RepositoryConfiguration.class,
										   ServiceConfiguration.class,
										   EventModuleConfiguration.class,
										   OpenApiModuleConfigure.class,
										   RbacConfiguration.class,
										   OpenApiApplication.class}, args);
	}

}
