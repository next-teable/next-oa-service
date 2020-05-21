package cn.com.starest.nextoa.project.web.controller;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.security.SecurityContexts;
import cn.com.starest.nextoa.project.web.dto.LicenseDetail;
import cn.com.starest.nextoa.project.web.dto.LicenseQueryParameter;
import cn.com.starest.nextoa.project.web.dto.LicenseSummary;
import cn.com.starest.nextoa.project.web.dto.SaveLicenseParameter;
import cn.com.starest.nextoa.project.web.support.LicenseRestSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api("证件管理（外管证）")
@RestController
@RequestMapping("/api")
public class LicenseRestController {

	@Autowired
	private LicenseRestSupport licenseRestSupport;

	@ApiOperation(value = "创建证件管理（外管证）")
	@RequestMapping(value = "/licenses", method = RequestMethod.POST)
	public LicenseSummary createLicense(@Validated @RequestBody SaveLicenseParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return licenseRestSupport.createLicense(request, user);
	}

	@ApiOperation(value = "修改证件管理（外管证）")
	@RequestMapping(value = "/licenses/{id}", method = RequestMethod.POST)
	public LicenseSummary updateLicense(@PathVariable String id, @Validated @RequestBody SaveLicenseParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return licenseRestSupport.updateLicense(id, request, user);
	}

	@ApiOperation(value = "查看证件管理（外管证）")
	@RequestMapping(value = "/licenses/{id}", method = RequestMethod.GET)
	public LicenseDetail findLicenseById(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		return licenseRestSupport.findLicenseById(id, user);
	}

	@ApiOperation(value = "删除证件管理（外管证）")
	@RequestMapping(value = "/licenses/{id}", method = RequestMethod.DELETE)
	public void deleteLicenseById(@PathVariable String id) {
		User user = SecurityContexts.getContext().requireUser();
		licenseRestSupport.deleteLicenseById(id, user);
	}

	@ApiOperation(value = "查看证件管理（外管证）列表")
	@RequestMapping(value = "/licenses", method = RequestMethod.GET)
	public Page<LicenseSummary> listLicenses(LicenseQueryParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		return licenseRestSupport.listLicenses(request, user);
	}

	@ApiOperation(value = "查看证件管理（外管证）列表 - 未核销")
	@RequestMapping(value = "/licenses/notCancelled", method = RequestMethod.GET)
	public Page<LicenseSummary> listNotCancelledLicenses(LicenseQueryParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		request.setCancelled(Boolean.FALSE);
		request.setAttributesOrderByAsc(new String[]{"expireDate"});
		return licenseRestSupport.listLicenses(request, user);
	}

	@ApiOperation(value = "查看证件管理（外管证）列表 - 已核销")
	@RequestMapping(value = "/licenses/cancelled", method = RequestMethod.GET)
	public Page<LicenseSummary> listCancelledLicenses(LicenseQueryParameter request) {
		User user = SecurityContexts.getContext().requireUser();
		request.setCancelled(Boolean.TRUE);
		request.setAttributesOrderByDesc(new String[]{"cancelledAt"});
		return licenseRestSupport.listLicenses(request, user);
	}

}
