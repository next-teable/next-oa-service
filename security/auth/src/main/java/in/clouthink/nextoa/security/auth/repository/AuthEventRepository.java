package in.clouthink.nextoa.security.auth.repository;

import in.clouthink.nextoa.security.auth.model.AuthEvent;
import in.clouthink.nextoa.security.auth.repository.custom.AuthEventRepositoryCustom;
import in.clouthink.nextoa.shared.repository.AbstractRepository;

public interface AuthEventRepository extends AbstractRepository<AuthEvent>, AuthEventRepositoryCustom {

}
