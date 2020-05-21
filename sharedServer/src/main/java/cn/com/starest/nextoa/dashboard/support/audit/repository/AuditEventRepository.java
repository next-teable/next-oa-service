package cn.com.starest.nextoa.dashboard.support.audit.repository;


import cn.com.starest.nextoa.dashboard.support.audit.model.AuditEvent;
import cn.com.starest.nextoa.dashboard.support.audit.repository.custom.AuditEventRepositoryCustom;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;

import java.util.Date;
import java.util.List;

public interface AuditEventRepository extends AbstractRepository<AuditEvent>, AuditEventRepositoryCustom {

	List<AuditEvent> findListByRequestedAtBetween(Date from, Date to);

	long deleteByRequestedAtBefore(Date day);

}
