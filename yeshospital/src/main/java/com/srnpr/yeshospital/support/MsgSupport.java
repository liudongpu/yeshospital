package com.srnpr.yeshospital.support;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.entity.StringEntity;

import com.srnpr.yeshospital.model.TemplateForCall;
import com.srnpr.yeshospital.model.TemplateForMsg;
import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.basehelper.EncodeHelper;
import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basehelper.GsonHelper;
import com.srnpr.zapcom.basehelper.SecrurityHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.basesupport.WebClientSupport;

public class MsgSupport extends BaseClass {

	public boolean SendMessageByYtx(String sPhone, String sTemplateId,
			String... sParams) {

		boolean bFlagSuccess = false;

		String sUrl = bConfig("yeshospital.ytx_url");

		String sAccount = bConfig("yeshospital.ytx_account");
		String sToken = bConfig("yeshospital.ytx_auth");
		String sAppId = bConfig("yeshospital.ytx_appid");

		String sNow = StringUtils.replaceEach(FormatHelper.upDateTime(),
				new String[] { "-", ":", " " }, new String[] { "", "", "" });

		String sSign = SecrurityHelper.MD5(sAccount + sToken + sNow)
				.toUpperCase();

		sUrl = FormatHelper.formatString(sUrl, sAccount, sSign);

		String sAuthorization = EncodeHelper
				.base64Encode(sAccount + ":" + sNow);

		TemplateForMsg tMsg = new TemplateForMsg();
		tMsg.setTo(sPhone);
		tMsg.setAppId(sAppId);
		tMsg.setTemplateId(sTemplateId);
		tMsg.setDatas(sParams);
		
		String sPost = GsonHelper.toJson(tMsg);
		
		/*
		TemplateForCall tCall=new TemplateForCall();
		tCall.setTo(sPhone);
		tCall.setAppId(sAppId);
		tCall.setVerifyCode(sParams[0]);
		sPost = GsonHelper.toJson(tCall);
		*/
		

		HttpEntity httpEntity = null;

		try {
			httpEntity = new StringEntity(sPost);
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}

		try {

			String sResponse = WebClientSupport.poolRequest(sUrl, httpEntity,
					new MDataMap("Accept", "application/json", "Content-Type",
							"application/json;charset=utf-8", "Authorization",
							sAuthorization));

			if (StringUtils.contains(sResponse, "\"statusCode\":\"000000\"")) {
				bFlagSuccess = true;
			}
			else {
				bLogError(965805254, sResponse);
			}

		} catch (ClientProtocolException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return bFlagSuccess;
	}

}
