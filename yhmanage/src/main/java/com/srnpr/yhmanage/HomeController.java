package com.srnpr.yhmanage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.srnpr.yeshospital.pages.ExportQrcode;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.helper.WebSessionHelper;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webfactory.UserFactory;
import com.srnpr.zapweb.webmethod.RootControl;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController extends RootControl {

	public String mobileCheckLogin(String sInput) {

		// 开启多重校验模型

		boolean bFlagToken = UserFactory.INSTANCE.checkUserLogin();
		if (!bFlagToken) {
			String sCookieUser = WebSessionHelper.create().upCookie(
					WebConst.CONST_WEB_SESSION_USER);

			if (StringUtils.isNotEmpty(sCookieUser)) {

				Object oUserInfo = DbUp.upTable("za_oauth").dataGet(
						"user_code",
						"",
						new MDataMap("access_token", sCookieUser,
								"flag_enable", "1"));

				if (oUserInfo != null) {
					MDataMap mUserMap = DbUp.upTable("za_userinfo").one(
							"user_code", oUserInfo.toString());

					if (mUserMap != null) {
						UserFactory.INSTANCE.inUserInfo(mUserMap);
						bFlagToken = true;
					}
				}

			}
		}

		if (bFlagToken) {
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

	@RequestMapping(value = "/yhqrcode/{operateId}", produces = { "application/binary;charset=UTF-8" })
	@ResponseBody
	public String yhqrcode(@PathVariable("operateId") String sOperateId,
			HttpServletRequest request, HttpServletResponse response) {
		new ExportQrcode().export(sOperateId, request, response);

		return null;
	}

}
