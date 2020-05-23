package in.clouthink.nextoa.repository;

import in.clouthink.nextoa.model.AppRole;
import in.clouthink.nextoa.repository.shared.AbstractRepository;

/**
 *
 */
public interface AppRoleRepository extends AbstractRepository<AppRole> {

	AppRole findByCode(String code);

	AppRole findByName(String name);

}
