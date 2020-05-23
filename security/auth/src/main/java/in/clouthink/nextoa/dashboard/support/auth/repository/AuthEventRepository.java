package in.clouthink.nextoa.dashboard.support.auth.repository;

import in.clouthink.nextoa.dashboard.support.auth.model.AuthEvent;
import in.clouthink.nextoa.dashboard.support.auth.repository.custom.AuthEventRepositoryCustom;
import in.clouthink.nextoa.repository.shared.AbstractRepository;

public interface AuthEventRepository extends AbstractRepository<AuthEvent>, AuthEventRepositoryCustom {

}
