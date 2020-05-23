package in.clouthink.nextoa.dashboard.support.audit.repository.custom;

import in.clouthink.nextoa.dashboard.support.audit.model.AuditEvent;
import in.clouthink.nextoa.dashboard.support.audit.model.AuditEventQueryParameter;
import org.springframework.data.domain.Page;

public interface AuditEventRepositoryCustom {

	Page<AuditEvent> queryPage(AuditEventQueryParameter queryRequest);

}
