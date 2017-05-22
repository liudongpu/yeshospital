package com.srnpr.yeshospital.component;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.webcomponent.ComponentWindowSingle;
import com.srnpr.zapweb.webmodel.MWebField;

public class OfficeSingleSelect extends ComponentWindowSingle{

	
	public String upText(MWebField mWebField, MDataMap mDataMap, int iType) {

		mWebField
				.setSourceParam("zw_s_max_select=1&zw_s_source_tableinfo=yh_office_info|office_code|office_name&zw_s_source_page=page_chart_window_yh_office_info");

		return upShowText(mWebField, mDataMap, iType);
	}
}
