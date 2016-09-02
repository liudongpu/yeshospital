package com.srnpr.yeshospital.component;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.webcomponent.ComponentWindowSingle;
import com.srnpr.zapweb.webmodel.MWebField;

public class AreaSingleSelect  extends ComponentWindowSingle {

	
	public String upText(MWebField mWebField, MDataMap mDataMap, int iType) {

		mWebField
				.setSourceParam("zw_s_max_select=1&zw_s_source_tableinfo=ze_area_code|area_code|area_note&zw_s_source_page=page_chart_chart_v_ze_area_code");

		return upShowText(mWebField, mDataMap, iType);
	}
	
}
