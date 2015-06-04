package com.srnpr.yhmanage;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.srnpr.zapweb.webfactory.UserFactory;
import com.srnpr.zapweb.webmethod.RootControl;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController extends RootControl {

	public String mobileCheckLogin(String sInput) {
		if (UserFactory.INSTANCE.checkUserLogin()) {
			return sInput;
		} else {
			return "mobile/system-noaccess";
		}
	}

	@RequestMapping(value = "/mb/{url}")
	public String mb(@PathVariable("url") String sUrl, Model model,
			HttpServletRequest request) {
		model.addAttribute("b_page", page_Process.process(sUrl, request));
		model.addAttribute("b_method", web_method);
		return mobileCheckLogin("page/mb");
	}

	@RequestMapping(value = "/mobile/{url}")
	public String mobile(@PathVariable("url") String sUrl, Model model,
			HttpServletRequest request) {

		model.addAttribute("b_method", web_method);

		// 如果是需要登录的页面
		if (StringUtils.startsWithAny(sUrl, new String[] { "my-" })) {
			return mobileCheckLogin("mobile/" + sUrl);
		}

		return "mobile/" + sUrl;
	}

}
