package com.srnpr.yeshospital.component;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.helper.WebSessionHelper;
import com.srnpr.zapweb.webcomponent.ComponentWindowSingle;
import com.srnpr.zapweb.webmodel.MWebField;

/*
 * 养老院老人单选控件
 */
public class GeracomiumMemberSelect extends ComponentWindowSingle {

	public String upText(MWebField mWebField, MDataMap mDataMap, int iType) {

		
		WebSessionHelper webSessionHelper=WebSessionHelper.create();
		 String sMemberCode= webSessionHelper.upRequest("u_member_code");
		
		
		mWebField
				.setSourceParam("zw_s_max_select=1&zw_s_source_tableinfo=yh_member_extend_geracomium|member_code|member_name&zw_s_source_page=page_chart_g_window_yh_member_extend_geracomium");

		return upShowText(mWebField, mDataMap, iType);
	}
}
