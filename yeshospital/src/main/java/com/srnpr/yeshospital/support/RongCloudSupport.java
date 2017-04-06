package com.srnpr.yeshospital.support;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basehelper.GsonHelper;
import com.srnpr.zapcom.basehelper.SecrurityHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.basesupport.WebClientSupport;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.helper.WebHelper;

public class RongCloudSupport {

	public MDataMap userGetToken(MDataMap mDataMap) {

		String sReturn = upRequest("user/getToken.json", mDataMap);

		return new GsonHelper().fromJson(sReturn, new MDataMap());

	}

	private String upRequest(String sTarget, MDataMap mDataMap) {

		String sReturn = "";

		WebClientSupport webClientSupport = new WebClientSupport();

		String sAppKey = "m7ua80gbml8sm";
		String sAppSecret = "wX9ee11ZjZvJhH";

		String sUrl = "http://api.cn.ronghub.com/" + sTarget;

		MDataMap headerDataMap = new MDataMap();

		String sNonce = WebHelper.upUuid();
		String sTimesTamp = String.valueOf(new Date().getTime());

		String sSignature = SecrurityHelper.sha1(sAppSecret + sNonce + sTimesTamp);

		headerDataMap.put("App-Key", sAppKey);
		headerDataMap.put("Nonce", sNonce);
		headerDataMap.put("Timestamp", sTimesTamp);
		headerDataMap.put("Signature", sSignature);

		try {
			sReturn = webClientSupport.upPost(sUrl, mDataMap, headerDataMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sReturn;

	}

	public String upVideoToken(String sLoginName, String sUserCode) {

		String sReturn = "";

		MDataMap map = DbUp.upTable("ys_user_info").one("user_code", sUserCode);
		if (map != null && StringUtils.isNotBlank(map.get("video_token"))) {
			sReturn = map.get("video_token");

		} else {

			MDataMap mInputMap = new MDataMap();
			mInputMap.inAllValues("userId", sLoginName, "name", sLoginName);

			sReturn = userGetToken(mInputMap).get("token");
			
			
			if(map!=null){
				
				map.put("video_token", sReturn);
				DbUp.upTable("ys_user_info").dataUpdate(map, "video_token", "user_code");
				
			}
			

		}

		return sReturn;

	}

}
