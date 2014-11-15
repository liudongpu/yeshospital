package com.srnpr.yeshospital.component;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.webcomponent.ComponentColorChange;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webdo.WebTemp;
import com.srnpr.zapweb.webmodel.MWebField;

public class WarnColor extends ComponentColorChange {

	@Override
	public String upText(MWebField mWebField, MDataMap mDataMap, int iType) {
		// TODO Auto-generated method stub

		return upShowText(mWebField, mDataMap, iType,
				upLeft("46580001000300020001")
				+ "&font-color=&back-color="+WebConst.CONST_SPLIT_LINE+upLeft("46580001000300020002")
						+ "&font-color=#fff&back-color=#e9d205"+WebConst.CONST_SPLIT_LINE+upLeft("46580001000300020003")
						+ "&font-color=#fff&back-color=#e45809"+WebConst.CONST_SPLIT_LINE+upLeft("46580001000300020004")
						+ "&font-color=#fff&back-color=#eb0515");
	}

	private String upLeft(String sKey) {

		return "key="
				+ sKey
				+ "&text="
				+ WebTemp.upTempDataOne("yh_define", "define_name",
						"define_code", sKey);

	}

}
