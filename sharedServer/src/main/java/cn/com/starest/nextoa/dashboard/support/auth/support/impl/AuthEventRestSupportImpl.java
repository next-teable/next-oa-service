package cn.com.starest.nextoa.dashboard.support.auth.support.impl;

import cn.com.starest.nextoa.dashboard.support.auth.model.AuthEvent;
import cn.com.starest.nextoa.dashboard.support.auth.service.AuthEventService;
import cn.com.starest.nextoa.dashboard.support.auth.support.AuthEventRestSupport;
import cn.com.starest.nextoa.dashboard.support.auth.support.UserAuthQueryParameter;
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
