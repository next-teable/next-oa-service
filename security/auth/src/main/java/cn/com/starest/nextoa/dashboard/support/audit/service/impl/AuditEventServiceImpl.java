package cn.com.starest.nextoa.dashboard.support.audit.service.impl;

import cn.com.starest.nextoa.dashboard.support.audit.model.AuditEvent;
import cn.com.starest.nextoa.dashboard.support.audit.model.AuditEventQueryParameter;
import cn.com.starest.nextoa.dashboard.support.audit.repository.AuditEventRepository;
import cn.com.starest.nextoa.dashboard.support.audit.service.AuditEventException;
import cn.com.starest.nextoa.dashboard.support.audit.service.AuditEventService;
import cn.com.starest.nextoa.model.DomainConstants;
import cn.com.starest.nextoa.shared.util.DateTimeUtils;
import in.clouthink.daas.audit.spi.AuditEventDispatcher;
import in.clouthink.daas.edm.Edms;
import in.clouthink.daas.edm.EventListener;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 */
@Service
public class AuditEventServiceImpl implements AuditEventService, EventListener<Date>, InitializingBean {

	@Autowired
	private AuditEventRepository auditEventRepository;

	@Override
	public Page<AuditEvent> findAuditEventPage(AuditEventQueryParameter queryRequest) {
		return auditEventRepository.queryPage(queryRequest);
	}

	@Override
	public AuditEvent findById(String id) {
		return auditEventRepository.findById(id);
	}

	@Override
	public void deleteAuditEventsByDay(Date day) {
		if (System.currentTimeMillis() - day.getTime() < 15 * DomainConstants.HOW_LONG_OF_ONE_DAY) {
			throw new AuditEventException("只能删除15天前的数据");
		}

		Date from = DateTimeUtils.startOfDay(day);
		Date to = DateTimeUtils.endOfDay(day);
		List<AuditEvent> auditEvents = auditEventRepository.findListByRequestedAtBetween(from, to);
		auditEventRepository.delete(auditEvents);
	}

	@Override
	public void deleteAuditEventsBeforeDay(Date day) {
		if (System.currentTimeMillis() - day.getTime() < 15 * DomainConstants.HOW_LONG_OF_ONE_DAY) {
			throw new AuditEventException("只能删除15天前的数据");
		}

		Edms.getEdm(AuditEventDispatcher.QUEUE_NAME).dispatch("DELETE_AUDIT_EVENT_BEFORE_DAY", day);
	}

	@Override
	public void onEvent(Date day) {
		Date startOfDay = DateTimeUtils.startOfDay(day);
		auditEventRepository.deleteByRequestedAtBefore(startOfDay);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Edms.getEdm(AuditEventDispatcher.QUEUE_NAME).register("DELETE_AUDIT_EVENT_BEFORE_DAY", this);
	}

}
