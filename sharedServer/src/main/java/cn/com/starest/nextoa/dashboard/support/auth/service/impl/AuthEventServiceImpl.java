package cn.com.starest.nextoa.dashboard.support.auth.service.impl;

import cn.com.starest.nextoa.dashboard.support.auth.model.AuthEvent;
import cn.com.starest.nextoa.dashboard.support.auth.model.AuthEventQueryRequest;
import cn.com.starest.nextoa.dashboard.support.auth.repository.AuthEventRepository;
import cn.com.starest.nextoa.dashboard.support.auth.service.AuthEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class AuthEventServiceImpl implements AuthEventService {

	@Autowired
	private AuthEventRepository authEventRepository;

	@Override
	public Page<AuthEvent> listUserAuthEvents(AuthEventQueryRequest queryParameter) {
		return authEventRepository.queryPage(queryParameter);
	}

	@Override
	public AuthEvent findUserAuthEventById(String id) {
		return authEventRepository.findById(id);
	}

	@Override
	public AuthEvent saveUserAuthEvent(AuthEvent authEvent) {
		return authEventRepository.save(authEvent);
	}
}
