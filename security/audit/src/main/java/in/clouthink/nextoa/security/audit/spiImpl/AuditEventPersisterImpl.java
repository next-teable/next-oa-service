package in.clouthink.nextoa.security.audit.spiImpl;

import in.clouthink.daas.audit.spi.AuditEventPersister;
import in.clouthink.nextoa.security.audit.model.AuditEvent;
import in.clouthink.nextoa.security.audit.repository.AuditEventRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

public class AuditEventPersisterImpl implements AuditEventPersister {

	@Autowired
	@Lazy
	private AuditEventRepository auditEventRepository;

	@Override
	public AuditEvent saveAuditEvent(in.clouthink.daas.audit.core.AuditEvent o) {
		AuditEvent auditEvent = new AuditEvent();
		BeanUtils.copyProperties(o, auditEvent);
		if (o.getRequestedBy() != null) {
			auditEvent.setRequestedBy(o.getRequestedBy().toString());
		}
		return auditEventRepository.save(auditEvent);
	}

}
