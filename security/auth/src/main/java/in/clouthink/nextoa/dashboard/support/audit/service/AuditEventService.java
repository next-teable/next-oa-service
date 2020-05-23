package in.clouthink.nextoa.dashboard.support.audit.service;

import in.clouthink.nextoa.dashboard.support.audit.model.AuditEvent;
import in.clouthink.nextoa.dashboard.support.audit.model.AuditEventQueryParameter;
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
