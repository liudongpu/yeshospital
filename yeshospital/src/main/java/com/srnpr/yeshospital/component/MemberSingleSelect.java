package com.srnpr.yeshospital.component;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.webcomponent.ComponentWindowSingle;
import com.srnpr.zapweb.webmodel.MWebField;

/**
 * 老人单选控件
 * 
 * @author srnpr
 *
 */
public class MemberSingleSelect extends ComponentWindowSingle {

	
	public String upText(MWebField mWebField, MDataMap mDataMap, int iType) {

		mWebField
				.setSourceParam("zw_s_max_select=1&zw_s_source_tableinfo=yh_member_info|member_code|member_name&zw_s_source_page=page_chart_window_yh_member_info");

		return upShowText(mWebField, mDataMap, iType);
	}
}
