package in.clouthink.nextoa.security.audit.support.impl;

import in.clouthink.nextoa.security.audit.model.AggregationType;
import in.clouthink.nextoa.security.audit.model.AuditEventAggregation;
import in.clouthink.nextoa.security.audit.repository.AuditEventAggregationRepository;
import in.clouthink.nextoa.security.audit.support.AuditReportRestSupport;
import in.clouthink.nextoa.shared.domain.params.PageQueryParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

/**
 * @author dz
 */
@Component
public class AuditReportRestSupportImpl implements AuditReportRestSupport {

    @Autowired
    private AuditEventAggregationRepository auditEventAggregationRepository;

    @Override
    public Page<AuditEventAggregation> listAuditReportByMonth(PageQueryParameter queryRequest) {
        return auditEventAggregationRepository.findPageByAggregationTypeOrderByAggregationKeyDesc(AggregationType.MONTH,
                new PageRequest(
                        queryRequest.getStart(),
                        queryRequest.getLimit()));
    }

    @Override
    public Page<AuditEventAggregation> listAuditReportByDay(PageQueryParameter queryRequest) {
        return auditEventAggregationRepository.findPageByAggregationTypeOrderByAggregationKeyDesc(AggregationType.DAY,
                new PageRequest(
                        queryRequest.getStart(),
                        queryRequest.getLimit()));
    }

}
