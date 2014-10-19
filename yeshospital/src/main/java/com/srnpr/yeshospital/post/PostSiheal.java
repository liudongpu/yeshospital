package com.srnpr.yeshospital.post;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.helper.WebHelper;
import com.srnpr.zapweb.helper.WebSessionHelper;

public class PostSiheal extends BaseClass {

	/**
	 * 处理
	 * 
	 * @return
	 */
	public String process() {
		HttpServletRequest hRequest = WebSessionHelper.create().upHttpRequest();

		StringBuffer info = new java.lang.StringBuffer();
		try {
			InputStream in = hRequest.getInputStream();
			BufferedInputStream buf = new BufferedInputStream(in);
			byte[] buffer = new byte[1024];
			int iRead;

			while ((iRead = buf.read(buffer)) != -1) {
				info.append(new String(buffer, 0, iRead, "UTF-8"));
			}
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		String sInfo = info.toString();

		String sReturn = "0";
		if (StringUtils.isEmpty(sInfo)) {

		} else {
			String sLogCodeString = WebHelper.upCode("LC");

			DbUp.upTable("yh_log_post").insert("log_code", sLogCodeString,
					"post_info", sInfo, "create_time",
					FormatHelper.upDateTime());

			sReturn = "1";

		}

		return sReturn;
	}

}
