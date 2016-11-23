package com.srnpr.yeshospital.helper;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.helper.WebHelper;
import com.srnpr.zapweb.webdo.WebTemp;

public class YesHospitalHelper extends BaseClass {

	public static String upDefineName(String sDefineCode) {
		return WebTemp.upTempDataOne("yh_define", "define_name", "define_code",
				sDefineCode);
	}
	
	
	public static String upCheckReplace(String sText) {
		return WebHelper.recheckReplace(sText, new MDataMap());
	}
	

}
