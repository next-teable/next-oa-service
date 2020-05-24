package in.clouthink.nextoa.security.audit.support;

import in.clouthink.nextoa.security.audit.model.AuditEventAggregation;
import in.clouthink.nextoa.shared.domain.params.PageQueryParameter;
import org.springframework.data.domain.Page;

/**
 * @author dz
 */
public interface AuditReportRestSupport {

	Page<AuditEventAggregation> listAuditReportByMonth(PageQueryParameter queryRequest);

	Page<AuditEventAggregation> listAuditReportByDay(PageQueryParameter queryRequest);

}
