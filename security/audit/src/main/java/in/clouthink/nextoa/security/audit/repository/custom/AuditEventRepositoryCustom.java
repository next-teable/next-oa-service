package in.clouthink.nextoa.security.audit.repository.custom;

import in.clouthink.nextoa.security.audit.model.AuditEvent;
import in.clouthink.nextoa.security.audit.model.AuditEventQueryParameter;
import org.springframework.data.domain.Page;

public interface AuditEventRepositoryCustom {

	Page<AuditEvent> queryPage(AuditEventQueryParameter queryRequest);

}
