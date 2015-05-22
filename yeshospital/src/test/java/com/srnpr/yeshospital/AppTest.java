package com.srnpr.yeshospital;

import org.junit.Test;

import com.srnpr.yeshospital.et.rsync.*;
import com.srnpr.yeshospital.helper.SpellHelper;
import com.srnpr.zapcom.basehelper.TestHelper;
import com.srnpr.zapcom.basemodel.MDataMap;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestHelper

{

	@Test
	public void TestPY() {
		bLogTest(new EtRsyncGetEntInfo().upHttp(new MDataMap()));

		// 绑定设备
		// bLogTest(new EtRsyncAddDev().upHttp(new
		// MDataMap("devSerial","05200123")));
		// bLogTest(new EtRsyncGetEntInfo().upHttp(new MDataMap()));
		// 更新企业信息
		/*
		String sEntInfo = "{ \"IsUnexpired\" : false, \"EntAccount\" : \"testEnterprise\", \"Password\" : \"\", \"EntName\" : \"SunPaTest\", \"Contact\" : \"xiang\", \"Telephone\" : \"13940353015\", \"DataPushUrl\" : \"http://etpush.jk.chifaer.com/yhmanage/web/push/etpush\", \"Comment\" : \"testaccount\" }";
		bLogTest(new EtRsyncUpdateEnt()
				.upHttp(new MDataMap("entInfo", sEntInfo)));
		*/
		// bLogTest(new EtRsyncGetData().upHttp(new
		// MDataMap("appType","","onlyUnsync", "","PageMax","100")));

	}
}
