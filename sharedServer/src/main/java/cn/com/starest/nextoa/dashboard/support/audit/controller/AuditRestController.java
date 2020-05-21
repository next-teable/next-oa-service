package cn.com.starest.nextoa.dashboard.support.audit.controller;

import cn.com.starest.nextoa.dashboard.support.audit.model.AuditEvent;
import cn.com.starest.nextoa.dashboard.support.audit.model.AuditEventQueryParameter;
import cn.com.starest.nextoa.dashboard.support.audit.support.AuditEventRestSupport;
import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.security.SecurityContexts;
import in.clouthink.daas.audit.annotation.Ignored;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author dz
 */
@Ignored
@RestController
@RequestMapping("/api")
public class AuditRestController {

	@Autowired
	private AuditEventRestSupport auditEventRestSupport;

	@ApiOperation(value = "审计日志列表,支持分页,支持动态查询(按名称,分类查询)")
	@RequestMapping(value = "/auditEvents", method = RequestMethod.GET)
	public Page<AuditEvent> listAuditEventPage(AuditEventQueryParameter queryRequest) {
		return auditEventRestSupport.listAuditEventPage(queryRequest);
	}

	@ApiOperation(value = "审计日志明细")
	@RequestMapping(value = "/auditEvents/{id}", method = RequestMethod.GET)
	public AuditEvent getAuditEventDetail(@PathVariable String id) {
		return auditEventRestSupport.getAuditEventDetail(id);
	}

	@ApiOperation(value = "删除日志-以天为单位")
	@RequestMapping(value = "/auditEvents/byDay/{day}", method = RequestMethod.DELETE)
	public void deleteAuditEventsByDay(@PathVariable Date day) {
		User user = SecurityContexts.getContext().requireUser();
		auditEventRestSupport.deleteAuditEventsByDay(day, user);
	}

	@ApiOperation(value = "删除日志-删除指定日期（不包括）之前的所有数据")
	@RequestMapping(value = "/auditEvents/beforeDay/{day}", method = RequestMethod.DELETE)
	public void deleteAuditEventsBeforeDay(@PathVariable Date day) {
		User user = SecurityContexts.getContext().requireUser();
		auditEventRestSupport.deleteAuditEventsBeforeDay(day, user);
	}

}
