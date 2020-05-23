package in.clouthink.nextoa.dashboard.support.audit.support;

import in.clouthink.nextoa.dashboard.support.audit.model.AuditEventAggregation;
import in.clouthink.nextoa.openapi.dto.PageQueryParameter;
import org.springframework.data.domain.Page;

/**
 * @author dz
 */
public interface AuditReportRestSupport {

	Page<AuditEventAggregation> listAuditReportByMonth(PageQueryParameter queryRequest);

	Page<AuditEventAggregation> listAuditReportByDay(PageQueryParameter queryRequest);

}
