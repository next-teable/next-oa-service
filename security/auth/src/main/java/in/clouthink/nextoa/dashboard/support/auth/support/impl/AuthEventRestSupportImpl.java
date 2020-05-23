package in.clouthink.nextoa.dashboard.support.auth.support.impl;

import in.clouthink.nextoa.dashboard.support.auth.model.AuthEvent;
import in.clouthink.nextoa.dashboard.support.auth.service.AuthEventService;
import in.clouthink.nextoa.dashboard.support.auth.support.AuthEventRestSupport;
import in.clouthink.nextoa.dashboard.support.auth.support.UserAuthQueryParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 */
@Service
public class AuthEventRestSupportImpl implements AuthEventRestSupport {

	@Autowired
	private AuthEventService auditEventService;

	@Override
	public Page<AuthEvent> listAuthEventPage(UserAuthQueryParameter queryRequest) {
		return auditEventService.listUserAuthEvents(queryRequest);
	}

	@Override
	public AuthEvent getAuthEventDetail(String id) {
		return auditEventService.findUserAuthEventById(id);
	}

}
