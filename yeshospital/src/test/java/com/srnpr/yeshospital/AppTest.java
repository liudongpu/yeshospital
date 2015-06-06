package com.srnpr.yeshospital;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.srnpr.yeshospital.et.rsync.*;
import com.srnpr.yeshospital.helper.SpellHelper;
import com.srnpr.zapcom.basehelper.EncodeHelper;
import com.srnpr.zapcom.basehelper.TestHelper;
import com.srnpr.zapcom.basemodel.MDataMap;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestHelper

{
	
	public void TestImage() {

		String sInfoString ="";
		
		sInfoString=EncodeHelper.urlEncode(sInfoString);
		
		decodeBase64ToImage(sInfoString, "D:\\x\\", "1.png");
	}

	public static void decodeBase64ToImage(String base64, String path,
			String imgName) {

		try {
			FileOutputStream write = new FileOutputStream(new File(path
					+ imgName));
			byte[] bImages = Base64.decodeBase64(base64);
			write.write(bImages);
			write.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void TestPY() {
		// bLogTest(new EtRsyncGetEntInfo().upHttp(new MDataMap()));

		// 绑定设备
		// bLogTest(new EtRsyncAddDev().upHttp(new
		// MDataMap("devSerial","05200123")));
		// bLogTest(new EtRsyncGetEntInfo().upHttp(new MDataMap()));
		// 更新企业信息
		/*
		 * String sEntInfo =
		 * "{ \"IsUnexpired\" : false, \"EntAccount\" : \"testEnterprise\", \"Password\" : \"\", \"EntName\" : \"SunPaTest\", \"Contact\" : \"xiang\", \"Telephone\" : \"13940353015\", \"DataPushUrl\" : \"http://etpush.jk.chifaer.com/yhmanage/web/push/etpush\", \"Comment\" : \"testaccount\" }"
		 * ; bLogTest(new EtRsyncUpdateEnt() .upHttp(new MDataMap("entInfo",
		 * sEntInfo)));
		 */
		// bLogTest(new EtRsyncGetData().upHttp(new
		// MDataMap("appType","","onlyUnsync", "","PageMax","100")));

		String sData = "[{\"apptype\":\"WeightDataV1\",\"datakey\":\"XXXX\",\"collectdate\":\"xxxx\",\"adddate\":\"xxxx\",\"EntAccount\":\"xxxx\",\"DataId\":\"XXXX\",\"weight\":\"xxxx\"}]";

		JSONArray jsonArray = JSON.parseArray(sData);

		for (int i = 0, j = jsonArray.size(); i < j; i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			String sDataType = jsonObject.getString("apptype");

			bLogTest(sDataType);

		}

	}
}
