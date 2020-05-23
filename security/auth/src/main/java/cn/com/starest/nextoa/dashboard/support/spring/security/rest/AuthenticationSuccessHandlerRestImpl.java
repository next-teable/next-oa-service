package cn.com.starest.nextoa.dashboard.support.spring.security.rest;

import cn.com.starest.nextoa.dashboard.support.auth.service.AuthEventService;
import cn.com.starest.nextoa.dashboard.support.spring.security.AuthEventHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author dz
 */
public class AuthenticationSuccessHandlerRestImpl implements AuthenticationSuccessHandler {

	@Autowired
	private AuthEventService userAuditService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
										HttpServletResponse response,
										Authentication authentication) throws IOException, ServletException {
		try {
			userAuditService.saveUserAuthEvent(AuthEventHelper.buildSucceedAuthEvent(request));
		}
		catch (Throwable e) {
			//do nothing
		}
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().append("{\"succeed\":true}");
		response.flushBuffer();
	}

}