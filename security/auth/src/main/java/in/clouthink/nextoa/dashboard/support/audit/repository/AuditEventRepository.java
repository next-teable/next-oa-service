package in.clouthink.nextoa.dashboard.support.audit.repository;


import in.clouthink.nextoa.dashboard.support.audit.model.AuditEvent;
import in.clouthink.nextoa.dashboard.support.audit.repository.custom.AuditEventRepositoryCustom;
import in.clouthink.nextoa.repository.shared.AbstractRepository;

import java.util.Date;
import java.util.List;

public interface AuditEventRepository extends AbstractRepository<AuditEvent>, AuditEventRepositoryCustom {

	List<AuditEvent> findListByRequestedAtBetween(Date from, Date to);

	long deleteByRequestedAtBefore(Date day);

}
