package com.srnpr.yeshospital.support;

import java.util.Date;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.basehelper.DateHelper;
import com.srnpr.zapcom.basehelper.SecrurityHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.basesupport.WebClientSupport;

public class ApicloudSupport extends BaseClass {

	public boolean pushMessage(String sTitle, String sContent, String sUserId) {

		MDataMap mDataMap = new MDataMap();
		mDataMap.inAllValues("title", sTitle, "content", sContent, "userIds",
				sUserId,"type","1");
		String sRes = call(bConfig("yeshospital.apicloud_url_push")  , mDataMap);

		//bLogInfo(0, sRes);
		return true;
	}
	
	
	
	
	public String createUser(String sUserName)
	{
		MDataMap mDataMap = new MDataMap();
		mDataMap.inAllValues("username", sUserName, "password", "ssssss");
		
		String sRes = call(bConfig("yeshospital.apicloud_url_user") , mDataMap);
		
		return sRes;
	}
	
	

	public String call(String sUrl, MDataMap mPost) {
		String sResponse = "";

		

		String sAppId = bConfig("yeshospital.apicloud_appid");
		String sKey = bConfig("yeshospital.apicloud_appkey");

		String sNow = String.valueOf(new Date().getTime());

		String sAppKey = SecrurityHelper.sha1(sAppId + "UZ" + sKey + "UZ"
				+ sNow)
				+ "." + sNow;

		try {
			sResponse = WebClientSupport.upPost(sUrl, mPost, new MDataMap(
					"X-APICloud-AppId", sAppId, "X-APICloud-AppKey", sAppKey));
		} catch (Exception e) {

			e.printStackTrace();
		}

		return sResponse;
	}

}
