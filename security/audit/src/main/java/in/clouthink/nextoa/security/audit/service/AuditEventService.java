package in.clouthink.nextoa.security.audit.service;

import in.clouthink.nextoa.security.audit.model.AuditEvent;
import in.clouthink.nextoa.security.audit.model.AuditEventQueryParameter;
import org.springframework.data.domain.Page;

import java.util.Date;

/**
 */
public interface AuditEventService {

	Page<AuditEvent> findAuditEventPage(AuditEventQueryParameter queryRequest);

	AuditEvent findById(String id);

	void deleteAuditEventsByDay(Date day);

	void deleteAuditEventsBeforeDay(Date day);
	
}
