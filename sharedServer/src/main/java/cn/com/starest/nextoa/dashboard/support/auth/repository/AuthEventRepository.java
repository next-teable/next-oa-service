package cn.com.starest.nextoa.dashboard.support.auth.repository;

import cn.com.starest.nextoa.dashboard.support.auth.model.AuthEvent;
import cn.com.starest.nextoa.dashboard.support.auth.repository.custom.AuthEventRepositoryCustom;
import cn.com.starest.nextoa.repository.shared.AbstractRepository;

public interface AuthEventRepository extends AbstractRepository<AuthEvent>, AuthEventRepositoryCustom {

}
