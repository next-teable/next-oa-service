package cn.com.starest.nextoa.project.repository;

import cn.com.starest.nextoa.project.domain.model.ProjectDeliveryType;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;

/**
 * ProjectDeliveryType repo
 */
public interface ProjectDeliveryTypeRepository extends AbstractRepository<ProjectDeliveryType> {

	ProjectDeliveryType findFirstByName(String name);
}