package in.clouthink.nextoa.security.auth.support;

import in.clouthink.nextoa.security.auth.model.AuthEvent;
import org.springframework.data.domain.Page;

/**
 */
public interface AuthEventRestSupport {

	Page<AuthEvent> listAuthEventPage(UserAuthQueryParameter queryRequest);

	AuthEvent getAuthEventDetail(String id);
}
