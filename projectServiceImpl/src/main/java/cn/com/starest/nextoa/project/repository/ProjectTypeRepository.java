package cn.com.starest.nextoa.project.repository;

import cn.com.starest.nextoa.project.domain.model.ProjectType;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;

/**
 *
 */
public interface ProjectTypeRepository extends AbstractRepository<ProjectType> {

	ProjectType findFirstByName(String name);
}