package cn.com.starest.nextoa.dashboard.support.auth.repository.custom;

import cn.com.starest.nextoa.dashboard.support.auth.model.AuthEvent;
import cn.com.starest.nextoa.dashboard.support.auth.model.AuthEventQueryRequest;
import org.springframework.data.domain.Page;

/**
 * Created by dz on 16/5/27.
 */
public interface AuthEventRepositoryCustom {

	Page<AuthEvent> queryPage(AuthEventQueryRequest queryRequest);

}
