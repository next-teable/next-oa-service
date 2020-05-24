package in.clouthink.nextoa.security.auth.repository.custom;

import in.clouthink.nextoa.security.auth.model.AuthEvent;
import in.clouthink.nextoa.security.auth.model.AuthEventQueryRequest;
import org.springframework.data.domain.Page;

/**
 * Created by dz on 16/5/27.
 */
public interface AuthEventRepositoryCustom {

	Page<AuthEvent> queryPage(AuthEventQueryRequest queryRequest);

}
