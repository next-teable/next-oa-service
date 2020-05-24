package in.clouthink.nextoa.security.audit.support;

import in.clouthink.nextoa.bl.model.User;
import in.clouthink.nextoa.security.audit.model.AuditEvent;
import in.clouthink.nextoa.security.audit.model.AuditEventQueryParameter;
import org.springframework.data.domain.Page;

import java.util.Date;

/**
 */
public interface AuditEventRestSupport {

	Page<AuditEvent> listAuditEventPage(AuditEventQueryParameter queryRequest);

	AuditEvent getAuditEventDetail(String id);

	void deleteAuditEventsByDay(Date day, User user);

	void deleteAuditEventsBeforeDay(Date day, User user);
}
