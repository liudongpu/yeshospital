package com.srnpr.yeshospital.component;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.webcomponent.ComponentWindowSingle;
import com.srnpr.zapweb.webmodel.MWebField;

public class GroupSingleSelect  extends ComponentWindowSingle{

	
	public String upText(MWebField mWebField, MDataMap mDataMap, int iType) {

		mWebField
				.setSourceParam("zw_s_max_select=1&zw_s_source_tableinfo=yh_group_info|group_code|group_name&zw_s_source_page=page_chart_window_yh_group_info");

		return upShowText(mWebField, mDataMap, iType);
	}
}
