package cn.com.starest.nextoa.project.web.interceptor;

import cn.com.starest.nextoa.model.User;
import cn.com.starest.nextoa.openapi.security.SecurityContexts;
import cn.com.starest.nextoa.project.domain.model.Module;
import cn.com.starest.nextoa.project.service.ModulePermissionService;
import cn.com.starest.nextoa.project.web.ModuleAuthority;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截用户操作,判断是否有权限访问
 *
 * @author dz
 */
public class ProjectModulePermissionInterceptor extends HandlerInterceptorAdapter {

	public static final Log logger = LogFactory.getLog(ProjectModulePermissionInterceptor.class);

	@Autowired
	private ModulePermissionService modulePermissionService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		ModuleAuthority moduleAuthority = handlerMethod.getMethodAnnotation(ModuleAuthority.class);

		if (null == moduleAuthority) {
			//没有声明权限,放行
			return true;
		}

		User user = SecurityContexts.getContext().requireUser();
		Module feature = moduleAuthority.feature();
		String[] actions = moduleAuthority.actions();

		if (feature != null && actions != null && actions.length > 0) {
			boolean hasPermission = false;

			for (String action : actions) {
				if (modulePermissionService.hasPermission(feature, action, user)) {
					hasPermission = true;
				}
			}

			return hasPermission;
		}

		return true;
	}

}
