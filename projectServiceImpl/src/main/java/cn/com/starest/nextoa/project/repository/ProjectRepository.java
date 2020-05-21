package cn.com.starest.nextoa.project.repository;

import cn.com.starest.nextoa.project.domain.model.*;
import cn.com.starest.nextoa.project.repository.custom.ProjectRepositoryCustom;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;

import java.util.List;

/**
 *
 */
public interface ProjectRepository extends AbstractRepository<Project>, ProjectRepositoryCustom {

	Project findFirstByName(String name);

	Project findFirstByCode(String code);

	Project findFirstByProjectType(ProjectType projectType);

	Project findFirstByFirstParty(FirstParty firstParty);

	Project findFirstByDeliveryType(ProjectDeliveryType projectDeliveryType);

	Project findFirstByCompany(Company target);

	List<Project> findListByStatusOrderByCloseAtDesc(ProjectStatus closed);

	List<Project> findListByCity(String city);

    List<Project> findListByProvinceAndCity(String province, String city);
}