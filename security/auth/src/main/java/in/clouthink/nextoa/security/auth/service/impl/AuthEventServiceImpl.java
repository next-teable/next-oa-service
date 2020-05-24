package in.clouthink.nextoa.security.auth.service.impl;

import in.clouthink.nextoa.security.auth.model.AuthEvent;
import in.clouthink.nextoa.security.auth.model.AuthEventQueryRequest;
import in.clouthink.nextoa.security.auth.repository.AuthEventRepository;
import in.clouthink.nextoa.security.auth.service.AuthEventService;
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
