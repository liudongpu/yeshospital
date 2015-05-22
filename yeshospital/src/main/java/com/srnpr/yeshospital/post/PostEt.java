package com.srnpr.yeshospital.post;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.topdo.TopConst;
import com.srnpr.zapweb.helper.WebSessionHelper;

public class PostEt extends BaseClass {

	public String process() {
		HttpServletRequest hRequest = WebSessionHelper.create().upHttpRequest();

		String sRequestString = hRequest.getParameter("datas");

		try {
			sRequestString = URLDecoder.decode(sRequestString,
					TopConst.CONST_BASE_ENCODING);
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}
		
		
		
		

		return "";
	}
}
