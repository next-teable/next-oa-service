package cn.com.starest.nextoa.dashboard.support.audit.support;

import cn.com.starest.nextoa.dashboard.support.audit.model.AuditEventAggregation;
import cn.com.starest.nextoa.openapi.dto.PageQueryParameter;
import org.springframework.data.domain.Page;

/**
 * @author dz
 */
public interface AuditReportRestSupport {

	Page<AuditEventAggregation> listAuditReportByMonth(PageQueryParameter queryRequest);

	Page<AuditEventAggregation> listAuditReportByDay(PageQueryParameter queryRequest);

}
