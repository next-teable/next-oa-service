package in.clouthink.nextoa.openapi;

import in.clouthink.daas.audit.annotation.EnableAudit;
import in.clouthink.daas.audit.configure.AuditConfigurer;
import in.clouthink.daas.audit.spi.AuditEventPersister;
import in.clouthink.nextoa.bl.openapi.OpenApiModuleConfiguration;
import in.clouthink.nextoa.bl.ServiceConfiguration;
import in.clouthink.nextoa.event.EventModuleConfiguration;
import in.clouthink.nextoa.security.audit.security.SecurityContextImpl;
import in.clouthink.nextoa.security.audit.spiImpl.AuditEventPersisterImpl;
import in.clouthink.nextoa.security.rbac.RbacConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ComponentScan({"in.clouthink.nextoa.bl", "in.clouthink.daas.fss"})
@EnableMongoRepositories({"in.clouthink.nextoa.security.audit.repository",
        "in.clouthink.nextoa.security.auth.repository"})
@Import({OpenApiSecurityConfigurer.class, OpenApiWebMvcConfigurer.class})
@EnableAudit
@EnableAsync
public class OpenApiApplication {

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
        SpringApplication.run(new Object[]{OpenApiApplication.class}, args);
    }

}
