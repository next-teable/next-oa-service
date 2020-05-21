package cn.com.starest.nextoa.dashboard.support.audit.service;

import cn.com.starest.nextoa.dashboard.support.audit.model.AuditEvent;
import cn.com.starest.nextoa.dashboard.support.audit.model.AuditEventQueryParameter;
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
