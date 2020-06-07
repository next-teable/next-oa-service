package in.clouthink.nextoa.security.audit;

import in.clouthink.daas.audit.configure.AuditConfigurer;
import in.clouthink.daas.audit.spi.AuditEventPersister;
import in.clouthink.nextoa.security.audit.security.SecurityContextImpl;
import in.clouthink.nextoa.security.audit.spiImpl.AuditEventPersisterImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan({"in.clouthink.nextoa.security.audit"})
@EnableMongoRepositories({"in.clouthink.nextoa.security.audit.repository"})
public class AuditConfiguration {

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

}

