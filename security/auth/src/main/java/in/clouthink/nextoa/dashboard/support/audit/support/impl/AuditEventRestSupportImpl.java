package in.clouthink.nextoa.dashboard.support.audit.support.impl;

import in.clouthink.nextoa.dashboard.support.audit.model.AuditEvent;
import in.clouthink.nextoa.dashboard.support.audit.model.AuditEventQueryParameter;
import in.clouthink.nextoa.dashboard.support.audit.service.AuditEventService;
import in.clouthink.nextoa.dashboard.support.audit.support.AuditEventRestSupport;
import in.clouthink.nextoa.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 */
@Service
public class AuditEventRestSupportImpl implements AuditEventRestSupport {

	@Autowired
	private AuditEventService auditEventService;

	@Override
	public Page<AuditEvent> listAuditEventPage(AuditEventQueryParameter queryRequest) {
		return auditEventService.findAuditEventPage(queryRequest);
	}

	@Override
	public AuditEvent getAuditEventDetail(String id) {
		return auditEventService.findById(id);
	}

	@Override
	public void deleteAuditEventsByDay(Date day, User byWho) {
		if (!"administrator".equals(byWho.getUsername())) {
			throw new RuntimeException("只有超级管理员能删除用户操作审计数据.");
		}

		auditEventService.deleteAuditEventsByDay(day);
	}

	@Override
	public void deleteAuditEventsBeforeDay(Date day, User byWho) {
		if (!"administrator".equals(byWho.getUsername())) {
			throw new RuntimeException("只有超级管理员能删除用户操作审计数据.");
		}

		auditEventService.deleteAuditEventsBeforeDay(day);
	}
}
