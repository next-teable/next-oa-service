package cn.com.starest.nextoa.dashboard.job;

import cn.com.starest.nextoa.dashboard.support.audit.service.AdvancedAuditEventService;
import cn.com.starest.nextoa.model.DomainConstants;
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


    @Scheduled(cron = "0 5 0 * * ?")
    public void auditEventStatistics() {
        logger.debug("auditEventStatistics start...");
        try {
            Date targetDay = new Date(System.currentTimeMillis() - DomainConstants.HOW_LONG_OF_ONE_DAY);
            advancedAuditEventService.statisticByDay(targetDay);
            advancedAuditEventService.statisticByMonth(targetDay);
        } catch (Throwable e) {
            logger.error("auditEventStatistics failed", e);
        }
        logger.debug("auditEventStatistics end...");
    }


}
