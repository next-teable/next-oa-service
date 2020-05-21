package cn.com.starest.nextoa.project.service;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.project.domain.model.License;
import cn.com.starest.nextoa.project.domain.model.ModuleActions;
import cn.com.starest.nextoa.project.domain.model.Project;
import cn.com.starest.nextoa.project.domain.request.LicenseQueryRequest;
import cn.com.starest.nextoa.project.domain.request.SaveLicenseRequest;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author dz
 */
public interface LicenseService {

	ModuleActions.ModuleAction[] resolveGrantedActions(User byWho);

	ModuleActions.ModuleAction[] resolveGrantedActions(License license, User byWho);

	License createLicense(SaveLicenseRequest request, User byWho);

	License updateLicense(String id, SaveLicenseRequest request, User byWho);

	License findLicenseById(String id, User byWho);

	void deleteLicenseById(String id, User byWho);

	Page<License> listLicenses(LicenseQueryRequest request, User byWho);

	List<License> listLicensesByProject(String projectId, User byWho);

	List<License> listLicensesByProject(Project project, User byWho);

}
