package com.srnpr.yeshospital.component;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.webcomponent.RootSimpleComponent;
import com.srnpr.zapweb.webmodel.MWebField;
import com.srnpr.zapweb.webmodel.MWebResult;

public class PrintQrcode extends RootSimpleComponent {

	@Override
	public String upText(MWebField mWebField, MDataMap mDataMap, int iType) {
		// TODO Auto-generated method stub
		return "<script type=\"text/javascript\" src=\"../resources/yhmanage/js/printqrcode.js\"></script>";
	}

	@Override
	public MWebResult inDo(MWebField mWebField, MDataMap mDataMap, int iType) {
		// TODO Auto-generated method stub
		return new MWebResult();
	}

}
