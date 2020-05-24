package in.clouthink.nextoa.security.auth.service;


import in.clouthink.nextoa.security.auth.model.AuthEvent;
import in.clouthink.nextoa.security.auth.model.AuthEventQueryRequest;
import org.springframework.data.domain.Page;

/**
 *
 */
public interface AuthEventService {

	Page<AuthEvent> listUserAuthEvents(AuthEventQueryRequest queryParameter);

	AuthEvent findUserAuthEventById(String id);

	AuthEvent saveUserAuthEvent(AuthEvent authEvent);

}
