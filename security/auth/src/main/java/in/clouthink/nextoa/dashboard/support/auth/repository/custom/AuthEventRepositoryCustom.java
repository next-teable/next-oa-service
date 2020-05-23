package in.clouthink.nextoa.dashboard.support.auth.repository.custom;

import in.clouthink.nextoa.dashboard.support.auth.model.AuthEvent;
import in.clouthink.nextoa.dashboard.support.auth.model.AuthEventQueryRequest;
import org.springframework.data.domain.Page;

/**
 * Created by dz on 16/5/27.
 */
public interface AuthEventRepositoryCustom {

	Page<AuthEvent> queryPage(AuthEventQueryRequest queryRequest);

}
