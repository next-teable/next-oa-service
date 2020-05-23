package in.clouthink.nextoa.dashboard.support.audit.service;

import in.clouthink.nextoa.model.User;
import org.springframework.scheduling.annotation.Async;

import java.util.Date;

/**
 */
public interface AdvancedAuditEventService {

	/**
	 * 以天为单位统计数据
	 *
	 * @param day
	 */
	void statisticByDay(Date day);

	/**
	 * 以月为单位统计数据
	 *
	 * @param day
	 */
	void statisticByMonth(Date day);

	/**
	 * 统计所有的审计数据
	 */
	@Async
	void scanAllAuditEventsAndDoStatistics(User byWho);

	void fixedPermissionForProject(User user);
}
