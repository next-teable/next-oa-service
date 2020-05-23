package cn.com.starest.nextoa.dashboard.support.auth.service;


import cn.com.starest.nextoa.dashboard.support.auth.model.AuthEvent;
import cn.com.starest.nextoa.dashboard.support.auth.model.AuthEventQueryRequest;
import org.springframework.data.domain.Page;

/**
 *
 */
public interface AuthEventService {

	Page<AuthEvent> listUserAuthEvents(AuthEventQueryRequest queryParameter);

	AuthEvent findUserAuthEventById(String id);

	AuthEvent saveUserAuthEvent(AuthEvent authEvent);

}
