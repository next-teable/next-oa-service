package cn.com.starest.nextoa.project.web.support;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.License;
import cn.com.starest.nextoa.project.domain.request.SaveLicenseRequest;
import cn.com.starest.nextoa.project.web.dto.LicenseDetail;
import cn.com.starest.nextoa.project.web.dto.LicenseQueryParameter;
import cn.com.starest.nextoa.project.web.dto.LicenseSummary;
import org.springframework.data.domain.Page;

/**
 * @author dz
 */
public interface LicenseRestSupport {

	LicenseSummary createLicense(SaveLicenseRequest request, User byWho);

	LicenseSummary updateLicense(String id, SaveLicenseRequest request, User byWho);

	LicenseDetail findLicenseById(String id, User byWho);

	Page<LicenseSummary> listLicenses(LicenseQueryParameter request, User byWho);

	void deleteLicenseById(String id, User user);
}
