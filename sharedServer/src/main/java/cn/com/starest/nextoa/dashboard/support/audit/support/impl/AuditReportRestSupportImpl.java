package cn.com.starest.nextoa.dashboard.support.audit.support.impl;

import cn.com.starest.nextoa.dashboard.support.audit.model.AggregationType;
import cn.com.starest.nextoa.dashboard.support.audit.model.AuditEventAggregation;
import cn.com.starest.nextoa.dashboard.support.audit.repository.AuditEventAggregationRepository;
import cn.com.starest.nextoa.dashboard.support.audit.support.AuditReportRestSupport;
import cn.com.starest.nextoa.openapi.dto.PageQueryParameter;
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
