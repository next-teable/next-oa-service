package cn.com.starest.nextoa.dashboard.mvc.controller;

import cn.com.starest.nextoa.openapi.dto.SystemSettingSummary;
import cn.com.starest.nextoa.openapi.support.SystemSettingRestSupport;
import cn.com.starest.nextoa.shared.util.UserAgentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DefaultController {

	@Autowired
	private SystemSettingRestSupport systemSettingRestSupport;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(HttpServletRequest request) {
		String userAgent = request.getHeader("User-Agent");
		if (UserAgentUtils.getBrowserFamily(userAgent).contains("IE")) {
			return "redirect:/browsers";
		}

		return "redirect:/portal";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, HttpServletRequest request) {
		String userAgent = request.getHeader("User-Agent");
		if (UserAgentUtils.getBrowserFamily(userAgent).contains("IE")) {
			return "redirect:/browsers";
		}

		SystemSettingSummary systemSetting = systemSettingRestSupport.getSystemSetting();
		model.addAttribute("systemSetting", systemSetting);
		return "login";
	}

	@RequestMapping(value = "/portal", method = RequestMethod.GET)
	public String portal(Model model) {
		SystemSettingSummary systemSetting = systemSettingRestSupport.getSystemSetting();
		model.addAttribute("systemSetting", systemSetting);
		return "portal";
	}

	@RequestMapping(value = "/browsers", method = RequestMethod.GET)
	public String browserDownload(Model model) {
		SystemSettingSummary systemSetting = systemSettingRestSupport.getSystemSetting();
		model.addAttribute("systemSetting", systemSetting);
		return "browsers";
	}

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String dashboard() {
		return "dashboard";
	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String e403() {
		return "403";
	}

	@RequestMapping(value = "/404", method = RequestMethod.GET)
	public String e404() {
		return "404";
	}

	@RequestMapping(value = "/500", method = RequestMethod.GET)
	public String e500() {
		return "500";
	}

}
