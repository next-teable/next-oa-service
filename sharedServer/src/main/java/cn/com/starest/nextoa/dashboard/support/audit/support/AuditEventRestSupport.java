package cn.com.starest.nextoa.dashboard.support.audit.support;

import cn.com.starest.nextoa.dashboard.support.audit.model.AuditEvent;
import cn.com.starest.nextoa.dashboard.support.audit.model.AuditEventQueryParameter;
import cn.com.starest.nextoa.model.User;
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
