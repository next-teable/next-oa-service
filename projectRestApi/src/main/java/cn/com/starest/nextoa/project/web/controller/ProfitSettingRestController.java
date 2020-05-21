package cn.com.starest.nextoa.project.web.controller;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.security.SecurityContexts;
import cn.com.starest.nextoa.project.web.dto.ProjectSettlementSummary;
import cn.com.starest.nextoa.project.web.dto.SaveProjectSettlementParameter;
import cn.com.starest.nextoa.project.web.support.ProfitSettingRestSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("预算管理")
@RestController
@RequestMapping("/api/profits")
public class ProfitSettingRestController {

	@Autowired
	private ProfitSettingRestSupport profitSettingRestSupport;

	@ApiOperation(value = "修改项目利润分配")
	@RequestMapping(value = "/projects/{id}", method = RequestMethod.POST)
	public void updateProjectProfitSetting(@PathVariable String id,
										   @Validated @RequestBody SaveProjectSettlementParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		profitSettingRestSupport.updateProjectProfitSetting(id, request, user);
	}

	@ApiOperation(value = "查询项目利润分配详情")
	@RequestMapping(value = {"/projects/{id}", "/shared/projects/{id}"}, method = RequestMethod.GET)
	public ProjectSettlementSummary findProjectProfitSettingById(@PathVariable String id) {
		return profitSettingRestSupport.findProjectProfitSettingById(id);
	}

	@ApiOperation(value = "查询项目利润分配列表")
	@RequestMapping(value = {"/projects", "/shared/projects"}, method = RequestMethod.GET)
	public List<ProjectSettlementSummary> listProjectProfitSettings() {
		return profitSettingRestSupport.listProjectProfitSettings();
	}

}
