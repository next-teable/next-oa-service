package in.clouthink.nextoa.openapi;

import in.clouthink.nextoa.dashboard.support.audit.security.SecurityContextImpl;
import in.clouthink.nextoa.dashboard.support.audit.spiImpl.AuditEventPersisterImpl;
import in.clouthink.nextoa.event.EventModuleConfiguration;
import in.clouthink.nextoa.model.ModelConfiguration;
import in.clouthink.nextoa.rbac.RbacConfiguration;
import in.clouthink.nextoa.repository.RepositoryConfiguration;
import in.clouthink.nextoa.service.ServiceConfiguration;
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
@ComponentScan({"in.clouthink.nextoa.dashboard"})
@Import({OpenApiSecurityConfigurer.class, OpenApiWebMvcConfigurer.class})
@EnableMongoRepositories({"in.clouthink.nextoa.dashboard.support.audit.repository",
						  "in.clouthink.nextoa.dashboard.support.auth.repository"})
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
