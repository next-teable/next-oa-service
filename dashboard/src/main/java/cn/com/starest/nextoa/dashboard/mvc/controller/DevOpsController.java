package cn.com.starest.nextoa.dashboard.mvc.controller;

import cn.com.starest.nextoa.dashboard.support.audit.service.AdvancedAuditEventService;
import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.security.SecurityContexts;
import cn.com.starest.nextoa.project.service.ProjectDevOpsService;
import cn.com.starest.nextoa.service.MigrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * The debug back door for administrator(only the administrator is allowed)
 */
@Controller
@RequestMapping(value = "/api/_devops_")
public class DevOpsController {

	@Autowired
	private MigrationService migrationService;

	@Autowired
	private AdvancedAuditEventService advancedAuditEventService;

	@Autowired
	private ProjectDevOpsService projectDevOpsService;

	@RequestMapping(value = "/migrateUserOrganizationRelationship", method = RequestMethod.GET)
	@ResponseBody
	public boolean migrateUserOrganizationRelationship(HttpServletRequest request) {
		User user = SecurityContexts.getContext().requireUser();
		migrationService.migrateUserOrganizationRelationships(user);
		return true;
	}

	@RequestMapping(value = "/fixAppUserNullOrganization", method = RequestMethod.GET)
	@ResponseBody
	public boolean fixAppUserNullOrganization(HttpServletRequest request) {
		User user = SecurityContexts.getContext().requireUser();
		migrationService.fixAppUserNullOrganization(user);
		return true;
	}

	@RequestMapping(value = "/fixNoticeReadCounter", method = RequestMethod.GET)
	@ResponseBody
	public boolean fixNoticeReadCounter(HttpServletRequest request) {
		User user = SecurityContexts.getContext().requireUser();
		migrationService.fixNoticeReadCounter(user);
		return true;
	}

	@RequestMapping(value = "/updateOrganizationLeafStatus", method = RequestMethod.GET)
	@ResponseBody
	public boolean updateOrganizationLeafStatus(HttpServletRequest request) {
		User user = SecurityContexts.getContext().requireUser();
		migrationService.updateOrganizationLeafStatus(user);
		return true;
	}

	@RequestMapping(value = "/updatePaperVersion", method = RequestMethod.GET)
	@ResponseBody
	public boolean updatePaperVersion(HttpServletRequest request) {
		User user = SecurityContexts.getContext().requireUser();
		migrationService.updatePaperVersion(user);
		return true;
	}

	@RequestMapping(value = "/updatePaperTransitionCreator", method = RequestMethod.GET)
	@ResponseBody
	public boolean updatePaperTransitionCreator(HttpServletRequest request) {
		User user = SecurityContexts.getContext().requireUser();
		migrationService.updatePaperTransitionCreator(user);
		return true;
	}

	@RequestMapping(value = "/updateMessageCategory", method = RequestMethod.GET)
	@ResponseBody
	public boolean updateMessageCategory(HttpServletRequest request) {
		User user = SecurityContexts.getContext().requireUser();
		migrationService.updateMessageCategory(user);
		return true;
	}

	@RequestMapping(value = "/scanAllAuditEventsAndDoStatistics", method = RequestMethod.GET)
	@ResponseBody
	public boolean doAuditEventStatistics(HttpServletRequest request) {
		User user = SecurityContexts.getContext().requireUser();
		advancedAuditEventService.scanAllAuditEventsAndDoStatistics(user);
		return true;
	}

	@RequestMapping(value = "/fixPermissionForProject", method = RequestMethod.GET)
	@ResponseBody
	public boolean fixedPermissionForProject(HttpServletRequest request) {
		User user = SecurityContexts.getContext().requireUser();
		advancedAuditEventService.fixedPermissionForProject(user);
		return true;
	}

	@RequestMapping(value = "/fixPaymentTimeOfReimburse", method = RequestMethod.GET)
	@ResponseBody
	public boolean fixedPaymentTimeOfReimburse(HttpServletRequest request) {
		User user = SecurityContexts.getContext().requireUser();
		projectDevOpsService.fixedPaymentTimeOfReimburse(user);
		return true;
	}

	@RequestMapping(value = "/fixLendingObject", method = RequestMethod.GET)
	@ResponseBody
	public boolean fixedLendingObject(HttpServletRequest request) {
		User user = SecurityContexts.getContext().requireUser();
		projectDevOpsService.fixedLendingObject(user);
		return true;
	}

	@RequestMapping(value = "/fixDetachedSalary", method = RequestMethod.GET)
	@ResponseBody
	public boolean fixedDetachedSalary(HttpServletRequest request) {
		User user = SecurityContexts.getContext().requireUser();
		projectDevOpsService.fixedDetachedSalary(user);
		return true;
	}


	@RequestMapping(value = "/fixSalaryAggregationByYear", method = RequestMethod.GET)
	@ResponseBody
	public boolean fixSalaryAggregationByYear(HttpServletRequest request) {
		User user = SecurityContexts.getContext().requireUser();
		projectDevOpsService.fixSalaryAggregationByYear(user);
		return true;
	}

	@RequestMapping(value = "/fixPendingPaymentDate", method = RequestMethod.GET)
	@ResponseBody
	public boolean fixPendingPaymentDate(HttpServletRequest request) {
		User user = SecurityContexts.getContext().requireUser();
		projectDevOpsService.fixPendingPaymentDate(user);
		return true;
	}


	@RequestMapping(value = "/calculateCompanyInitialCapital", method = RequestMethod.GET)
	@ResponseBody
	public boolean calculateCompanyInitialCapital(HttpServletRequest request) {
		User user = SecurityContexts.getContext().requireUser();
		projectDevOpsService.calculateCompanyInitialCapital(user);
		return true;
	}
}
