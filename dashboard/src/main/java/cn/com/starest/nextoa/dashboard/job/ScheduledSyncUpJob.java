package cn.com.starest.nextoa.dashboard.job;

import cn.com.starest.nextoa.dashboard.support.audit.service.AdvancedAuditEventService;
import cn.com.starest.nextoa.model.DomainConstants;
import cn.com.starest.nextoa.model.dtr.NamedPageQueryRequest;
import cn.com.starest.nextoa.project.service.BaseDataService;
import cn.com.starest.nextoa.project.service.CompanyCapitalService;
import cn.com.starest.nextoa.project.service.ProjectSortService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduledSyncUpJob {

	private static final Log logger = LogFactory.getLog(ScheduledSyncUpJob.class);

	@Autowired
	private AdvancedAuditEventService advancedAuditEventService;

	@Autowired
	private ProjectSortService projectSortService;

	@Autowired
	private BaseDataService baseDataService;

	@Autowired
	private CompanyCapitalService companyCapitalService;

	@Scheduled(cron = "0 5 0 * * ?")
	public void auditEventStatistics() {
		logger.debug("auditEventStatistics start...");
		try {
			Date targetDay = new Date(System.currentTimeMillis() - DomainConstants.HOW_LONG_OF_ONE_DAY);
			advancedAuditEventService.statisticByDay(targetDay);
			advancedAuditEventService.statisticByMonth(targetDay);
		}
		catch (Throwable e) {
			logger.error("auditEventStatistics failed", e);
		}
		logger.debug("auditEventStatistics end...");
	}

	@Scheduled(cron = "0 10 0 * * ?")
	public void sortProjectMonitor() {
		logger.debug("sortProjectMonitor start...");
		try {
			long currentTimeMillis = System.currentTimeMillis();
			projectSortService.sortProjects();
			logger.debug(String.format("resort project takes %d mills",
									   System.currentTimeMillis() - currentTimeMillis));
		}
		catch (Throwable e) {
			logger.error("auditEventStatistics failed", e);
		}
		logger.debug("sortProjectMonitor end...");
	}

	@Scheduled(cron = "0 15 0 * * ?")
	public void calculateMonthInitialFund() {
		logger.debug("calculateMonthInitialFund start...");
		try {
			long currentTimeMillis = System.currentTimeMillis();
			baseDataService.listCompanies(NamedPageQueryRequest.DUMMY)
						   .getContent()
						   .stream()
						   .forEach(company -> companyCapitalService.autoCalculateInitialCapital(company));
			logger.debug(String.format("calculate month initial fund takes %d mills",
									   System.currentTimeMillis() - currentTimeMillis));
		}
		catch (Throwable e) {
			logger.error("calculateMonthInitialFund failed", e);
		}
		logger.debug("calculateMonthInitialFund end...");
	}

}
