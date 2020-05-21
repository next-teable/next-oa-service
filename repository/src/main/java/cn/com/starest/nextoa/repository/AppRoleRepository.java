package cn.com.starest.nextoa.repository;

import cn.com.starest.nextoa.model.AppRole;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;

/**
 *
 */
public interface AppRoleRepository extends AbstractRepository<AppRole> {

	AppRole findByCode(String code);

	AppRole findByName(String name);

}