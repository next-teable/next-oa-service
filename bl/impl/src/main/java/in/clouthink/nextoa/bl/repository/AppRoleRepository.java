package in.clouthink.nextoa.bl.repository;

import in.clouthink.nextoa.bl.model.AppRole;
import in.clouthink.nextoa.shared.repository.AbstractRepository;

/**
 *
 */
public interface AppRoleRepository extends AbstractRepository<AppRole> {

	AppRole findByCode(String code);

	AppRole findByName(String name);

}
