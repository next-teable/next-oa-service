package cn.com.starest.nextoa.dashboard.support.audit.repository.custom;

import cn.com.starest.nextoa.dashboard.support.audit.model.AuditEvent;
import cn.com.starest.nextoa.dashboard.support.audit.model.AuditEventQueryParameter;
import org.springframework.data.domain.Page;

public interface AuditEventAggregationRepositoryCustom {

	Page<AuditEvent> queryPage(AuditEventQueryParameter queryRequest);

}
