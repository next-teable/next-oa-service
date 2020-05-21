package cn.com.starest.nextoa.project.web.support.impl;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.License;
import cn.com.starest.nextoa.project.domain.request.SaveLicenseRequest;
import cn.com.starest.nextoa.project.service.LicenseService;
import cn.com.starest.nextoa.project.web.dto.LicenseDetail;
import cn.com.starest.nextoa.project.web.dto.LicenseQueryParameter;
import cn.com.starest.nextoa.project.web.dto.LicenseSummary;
import cn.com.starest.nextoa.project.web.dto.PermissionAwarePageImpl;
import cn.com.starest.nextoa.project.web.support.LicenseRestSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * @author dz
 */
@Component
public class LicenseRestSupportImpl implements LicenseRestSupport {

	@Autowired
	private LicenseService licenseService;

	@Override
	public LicenseSummary createLicense(SaveLicenseRequest request, User byWho) {
		License license = licenseService.createLicense(request, byWho);
		LicenseSummary summary = LicenseSummary.from(license);
		summary.setGrantedActions(licenseService.resolveGrantedActions(license, byWho));
		return summary;
	}

	@Override
	public LicenseSummary updateLicense(String id, SaveLicenseRequest request, User byWho) {
		License license = licenseService.updateLicense(id, request, byWho);
		LicenseSummary summary = LicenseSummary.from(license);
		summary.setGrantedActions(licenseService.resolveGrantedActions(license, byWho));
		return summary;
	}

	@Override
	public LicenseDetail findLicenseById(String id, User byWho) {
		License license = licenseService.findLicenseById(id, byWho);
		LicenseDetail summary = LicenseDetail.from(license);
		summary.setGrantedActions(licenseService.resolveGrantedActions(license, byWho));
		return summary;
	}

	@Override
	public Page<LicenseSummary> listLicenses(LicenseQueryParameter request, User byWho) {
		Page<License> licensePage = licenseService.listLicenses(request, byWho);
		return new PermissionAwarePageImpl<>(licensePage.getContent().stream().map(item -> {
			LicenseSummary summary = LicenseSummary.from(item);
			summary.setGrantedActions(licenseService.resolveGrantedActions(item, byWho));
			return summary;
		}).collect(Collectors.toList()),
											 new PageRequest(request.getStart(), request.getLimit()),
											 licensePage.getTotalElements(),
											 licenseService.resolveGrantedActions(byWho));
	}

	@Override
	public void deleteLicenseById(String id, User user) {
		licenseService.deleteLicenseById(id, user);
	}

}
