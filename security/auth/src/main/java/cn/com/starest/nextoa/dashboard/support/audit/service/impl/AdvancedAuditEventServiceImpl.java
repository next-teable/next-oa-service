package cn.com.starest.nextoa.dashboard.support.audit.service.impl;

import cn.com.starest.nextoa.dashboard.support.audit.model.AggregationType;
import cn.com.starest.nextoa.dashboard.support.audit.model.AuditEvent;
import cn.com.starest.nextoa.dashboard.support.audit.model.AuditEventAggregation;
import cn.com.starest.nextoa.dashboard.support.audit.repository.AuditEventAggregationRepository;
import cn.com.starest.nextoa.dashboard.support.audit.repository.AuditEventRepository;
import cn.com.starest.nextoa.dashboard.support.audit.service.AdvancedAuditEventService;
import cn.com.starest.nextoa.model.DomainConstants;
import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.rbac.impl.repository.ResourceRoleRelationshipRepository;
import cn.com.starest.nextoa.shared.util.DateTimeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author dz
 */
@Service
public class AdvancedAuditEventServiceImpl implements AdvancedAuditEventService {

	private static final Log logger = LogFactory.getLog(AdvancedAuditEventServiceImpl.class);

	@Autowired
	private AuditEventRepository auditEventRepository;

	@Autowired
	private AuditEventAggregationRepository auditEventAggregationRepository;

	@Autowired
	private ResourceRoleRelationshipRepository resourceRoleRelationshipRepository;

	@Override
	public void statisticByDay(Date day) {
		if (day == null) {
			return;
		}
		String aggregationKeyByDay = new SimpleDateFormat("yyyy-MM-dd").format(day);
		logger.debug(String.format("statisticByDay : %s", aggregationKeyByDay));
		Date startOfDay = DateTimeUtils.startOfDay(day);
		Date endOfDay = DateTimeUtils.endOfDay(day);
		List<AuditEvent> auditEventList = auditEventRepository.findListByRequestedAtBetween(startOfDay, endOfDay);
		AuditEventAggregation auditEventAggregationByDay = auditEventAggregationRepository.findByAggregationTypeAndAggregationKey(
				AggregationType.DAY,
				aggregationKeyByDay);
		if (auditEventAggregationByDay == null) {
			auditEventAggregationByDay = new AuditEventAggregation();
			auditEventAggregationByDay.setAggregationType(AggregationType.DAY);
			auditEventAggregationByDay.setAggregationKey(aggregationKeyByDay);
		}

		long totalCountByDay = 0;
		long errorCountByDay = 0;
		long totalTimeCostByDay = 0;
		for (AuditEvent auditEvent : auditEventList) {
			totalCountByDay += 1;
			if (auditEvent.isError()) {
				errorCountByDay += 1;
			}
			totalTimeCostByDay += auditEvent.getTimeCost();
		}

		auditEventAggregationByDay.setTotalCount(totalCountByDay);
		auditEventAggregationByDay.setErrorCount(errorCountByDay);
		auditEventAggregationByDay.setTotalTimeCost(totalTimeCostByDay);
		auditEventAggregationRepository.save(auditEventAggregationByDay);

	}

	@Override
	public void statisticByMonth(Date dayOfMonth) {
		if (dayOfMonth == null) {
			return;
		}

		String aggregationKeyByMonth = new SimpleDateFormat("yyyy-MM").format(dayOfMonth);
		logger.debug(String.format("statisticByMonth : %s", aggregationKeyByMonth));
		AuditEventAggregation auditEventAggregationByMonth = auditEventAggregationRepository.findByAggregationTypeAndAggregationKey(
				AggregationType.MONTH,
				aggregationKeyByMonth);
		if (auditEventAggregationByMonth == null) {
			auditEventAggregationByMonth = new AuditEventAggregation();
			auditEventAggregationByMonth.setAggregationType(AggregationType.MONTH);
			auditEventAggregationByMonth.setAggregationKey(aggregationKeyByMonth);
		}

		List<AuditEventAggregation> auditEventAggregationList = auditEventAggregationRepository.findByAggregationTypeAndAggregationKeyLike(
				AggregationType.DAY,
				aggregationKeyByMonth);

		long totalCount = 0;
		long errorCount = 0;
		long totalTimeCost = 0;
		for (AuditEventAggregation auditEventAggregation : auditEventAggregationList) {
			totalCount += auditEventAggregation.getTotalCount();
			errorCount += auditEventAggregation.getErrorCount();
			totalTimeCost += auditEventAggregation.getTotalTimeCost();
		}

		auditEventAggregationByMonth.setTotalCount(totalCount);
		auditEventAggregationByMonth.setErrorCount(errorCount);
		auditEventAggregationByMonth.setTotalTimeCost(totalTimeCost);
		auditEventAggregationRepository.save(auditEventAggregationByMonth);
	}

	@Override
	public void fixedPermissionForProject(User byWho) {
		if (!"administrator".equals(byWho.getUsername())) {
			throw new RuntimeException("Only the administrator is allowed to access.");
		}
		resourceRoleRelationshipRepository.findByResourceCode("projectmanage").stream().forEach(item -> {
			item.setResourceCode("project");
			resourceRoleRelationshipRepository.save(item);
		});
	}

	@Override
	public void scanAllAuditEventsAndDoStatistics(User byWho) {
		if (!"administrator".equals(byWho.getUsername())) {
			throw new RuntimeException("Only the administrator is allowed to access.");
		}
		long whenToStop = System.currentTimeMillis() - 100 * DomainConstants.HOW_LONG_OF_ONE_DAY;
		Date day = new Date();
		for (; day.getTime() > whenToStop; ) {
			statisticByDay(day);
			statisticByMonth(day);
			day = new Date(day.getTime() - DomainConstants.HOW_LONG_OF_ONE_DAY);
		}
	}

}
