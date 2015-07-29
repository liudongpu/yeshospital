package com.srnpr.yeshospital.component;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.basesupport.MapSupport;
import com.srnpr.zapweb.webcomponent.RootSimpleComponent;
import com.srnpr.zapweb.webmodel.MWebField;
import com.srnpr.zapweb.webmodel.MWebHtml;
import com.srnpr.zapweb.webmodel.MWebResult;

public class DoctorSelectMember extends RootSimpleComponent {

	@Override
	public String upText(MWebField mWebField, MDataMap mDataMap, int iType) {
		MWebHtml mDivHtml = new MWebHtml("div");
		
		mDivHtml.addChild("script",
				"zapjs.f.require(['yhmanage/js/doctorselectmember'],function(a){});");
		mDivHtml.addChild("span", "id","span_doctor_select_member");
		
		return mDivHtml.upString();
		
		
	}

	@Override
	public MWebResult inDo(MWebField mWebField, MDataMap mDataMap, int iType) {
		// TODO Auto-generated method stub
		return null;
	}

}
