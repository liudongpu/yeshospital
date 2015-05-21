package com.srnpr.yeshospital;

import org.junit.Test;

import com.srnpr.yeshospital.et.rsync.EtRsyncAddDev;
import com.srnpr.yeshospital.et.rsync.EtRsyncAuth;
import com.srnpr.yeshospital.et.rsync.EtRsyncGetData;
import com.srnpr.yeshospital.et.rsync.EtRsyncGetDevs;
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

		// 绑定设备
		// bLogTest(new EtRsyncAddDev().upHttp(new MDataMap("devSerial","05200123")));

		bLogTest(new EtRsyncGetData().upHttp(new MDataMap("appType","","onlyUnsync", "","PageMax","100")));

	}
}
