package in.clouthink.nextoa.security.audit.repository;


import in.clouthink.nextoa.security.audit.model.AuditEvent;
import in.clouthink.nextoa.security.audit.repository.custom.AuditEventRepositoryCustom;
import in.clouthink.nextoa.shared.repository.AbstractRepository;

import java.util.Date;
import java.util.List;

public interface AuditEventRepository extends AbstractRepository<AuditEvent>, AuditEventRepositoryCustom {

	List<AuditEvent> findListByRequestedAtBetween(Date from, Date to);

	long deleteByRequestedAtBefore(Date day);

}
