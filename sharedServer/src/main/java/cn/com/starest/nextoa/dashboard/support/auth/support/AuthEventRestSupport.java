package cn.com.starest.nextoa.dashboard.support.auth.support;

import cn.com.starest.nextoa.dashboard.support.auth.model.AuthEvent;
import org.springframework.data.domain.Page;

/**
 */
public interface AuthEventRestSupport {

	Page<AuthEvent> listAuthEventPage(UserAuthQueryParameter queryRequest);

	AuthEvent getAuthEventDetail(String id);
}
