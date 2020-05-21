package cn.com.starest.nextoa.project.repository;

import cn.com.starest.nextoa.project.domain.model.Contract;
import cn.com.starest.nextoa.project.domain.model.License;
import cn.com.starest.nextoa.project.domain.model.Order;
import cn.com.starest.nextoa.project.domain.model.Project;
import cn.com.starest.nextoa.project.repository.custom.LicenseRepositoryCustom;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;

import java.util.List;

/**
 * License repo
 */
public interface LicenseRepository extends AbstractRepository<License>, LicenseRepositoryCustom {

	License findFirstByCode(String code);

	List<License> findListByProjectOrderByCreatedAtAsc(Project project);

	License findFirstByProject(Project target);

	License findFirstByOrder(Order target);

	License findFirstByContract(Contract target);

}