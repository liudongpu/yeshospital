package com.srnpr.yeshospital;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.entity.StringEntity;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.srnpr.yeshospital.et.rsync.*;
import com.srnpr.yeshospital.job.JobRecheckAge;
import com.srnpr.yeshospital.model.TemplateForMsg;
import com.srnpr.yeshospital.support.ApicloudSupport;
import com.srnpr.yeshospital.support.DocSupport;
import com.srnpr.yeshospital.support.MsgSupport;
import com.srnpr.yeshospital.support.QrcodeSupport;
import com.srnpr.yeshospital.wx.WxSendTemplate;
import com.srnpr.yeshospital.wx.model.WxTemplageValue;
import com.srnpr.yeshospital.wx.model.WxTemplateSend;
import com.srnpr.zapcom.basehelper.EncodeHelper;
import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basehelper.GsonHelper;
import com.srnpr.zapcom.basehelper.SecrurityHelper;
import com.srnpr.zapcom.basehelper.TestHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.basesupport.WebClientSupport;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.helper.WebHelper;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestHelper

{

	@Test
	public void testDoc() {

		DocSupport docSupport = new DocSupport();

		Map<String, Object> dataMap = upMemberInfo("MI150520100001");

		//docSupport.createWord("D:/x/doc/bj001.xml", dataMap);

	}

	public Map<String, Object> upMemberInfo(String sMemberCode) {

		MDataMap mMemberMap = DbUp.upTable("yh_member_extend_geracomium").oneWhere(
				"member_code as mcode,member_name as mname,member_age as mage,geracomium_code as gcode,room_name as mroom,card_code as mcard",
				"", "", "member_code", sMemberCode);

		Map<String, Object> result = new HashMap<String, Object>();
		result.putAll(mMemberMap);

		return result;

	}

	public void TeatSex() {
		bLogTest(new JobRecheckAge().upSexFromCard("110228193703271"));
	}

	public void TestPush() {

		bLogTest(new ApicloudSupport().pushMessage("测试通知", "xx", "UI100464"));

		// bLogTest(new ApicloudSupport().createUser("abcd"));
	}

	public void TestVerify() {

		bLogTest(new MsgSupport().SendMessageByYtx("17091033037", "1", "123476", "7"));

	}

	public void TestWx() {

		WxSendTemplate wxSendTemplate = new WxSendTemplate();

		WxTemplateSend wxTemplateSend = new WxTemplateSend();

		wxTemplateSend.setTouser("o26NLuAyrw0uVrsaX98K6RX5UvWs");
		wxTemplateSend.setTemplate_id("tA2fR-XFgoMsa0pHUZIW3aAUmaXwUZfgi2KG07880nQ");
		wxTemplateSend.getData().put("first", new WxTemplageValue("您关注的老人[某某某] "));
		wxTemplateSend.getData().put("keyword1", new WxTemplageValue("啊"));
		wxTemplateSend.getData().put("keyword2", new WxTemplageValue("2"));
		wxTemplateSend.getData().put("keyword3", new WxTemplageValue("3"));
		wxTemplateSend.getData().put("keyword4", new WxTemplageValue("4"));
		wxTemplateSend.getData().put("remark", new WxTemplageValue("remark"));

		bLogTest(wxSendTemplate.process(wxTemplateSend));

	}

	public void TestQrCode() {
		try {

			QrcodeSupport qrcodeSupport = new QrcodeSupport();

			List<MDataMap> listMaps = new ArrayList<MDataMap>();

			String sCard = "";
			sCard = StringUtils.substring(WebHelper.upUuid(), 0, 8);
			listMaps.add(new MDataMap("url", "http://q.yxl9.cn/" + sCard, "text", "姓名：测试;卡号：" + sCard));
			sCard = StringUtils.substring(WebHelper.upUuid(), 0, 8);
			listMaps.add(new MDataMap("url", "http://q.yxl9.cn/" + sCard, "text", "姓名：测试;卡号：" + sCard));
			sCard = StringUtils.substring(WebHelper.upUuid(), 0, 8);
			listMaps.add(new MDataMap("url", "http://q.yxl9.cn/" + sCard, "text", "姓名：测试;卡号：" + sCard));
			sCard = StringUtils.substring(WebHelper.upUuid(), 0, 8);
			listMaps.add(new MDataMap("url", "http://q.yxl9.cn/" + sCard, "text", "姓名：测试;卡号：" + sCard));
			sCard = StringUtils.substring(WebHelper.upUuid(), 0, 8);
			listMaps.add(new MDataMap("url", "http://q.yxl9.cn/" + sCard, "text", "姓名：测试;卡号：" + sCard));
			sCard = StringUtils.substring(WebHelper.upUuid(), 0, 8);
			listMaps.add(new MDataMap("url", "http://q.yxl9.cn/" + sCard, "text", "姓名：测试;卡号：" + sCard));
			sCard = StringUtils.substring(WebHelper.upUuid(), 0, 8);
			listMaps.add(new MDataMap("url", "http://q.yxl9.cn/" + sCard, "text", "姓名：测试;卡号：" + sCard));
			sCard = StringUtils.substring(WebHelper.upUuid(), 0, 8);
			listMaps.add(new MDataMap("url", "http://q.yxl9.cn/" + sCard, "text", "姓名：测试;卡号：" + sCard));
			sCard = StringUtils.substring(WebHelper.upUuid(), 0, 8);
			listMaps.add(new MDataMap("url", "http://q.yxl9.cn/" + sCard, "text", "姓名：测试;卡号：" + sCard));
			sCard = StringUtils.substring(WebHelper.upUuid(), 0, 8);
			listMaps.add(new MDataMap("url", "http://q.yxl9.cn/" + sCard, "text", "姓名：测试;卡号：" + sCard));

			qrcodeSupport.createImage("D:/x/", listMaps);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void TestImage() {

		String sInfoString = "";

		// sInfoString = EncodeHelper.urlEncode(sInfoString);

		decodeBase64ToImage(sInfoString, "D:\\x\\", "1.png");
	}

	public static void decodeBase64ToImage(String base64, String path, String imgName) {

		try {
			FileOutputStream write = new FileOutputStream(new File(path + imgName));
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

		bLogTest(new EtRsyncGetEntInfo().upHttp(new MDataMap()));

		bLogTest(new EtRsyncGetData().upHttp(new MDataMap("appType", "", "onlyUnsync", "", "PageMax", "100")));

		String sData = "[{\"apptype\":\"WeightDataV1\",\"datakey\":\"XXXX\",\"collectdate\":\"xxxx\",\"adddate\":\"xxxx\",\"EntAccount\":\"xxxx\",\"DataId\":\"XXXX\",\"weight\":\"xxxx\"}]";

		JSONArray jsonArray = JSON.parseArray(sData);

		for (int i = 0, j = jsonArray.size(); i < j; i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			String sDataType = jsonObject.getString("apptype");

			bLogTest(sDataType);

		}

	}
}
