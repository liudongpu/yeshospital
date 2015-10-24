package com.srnpr.yeshospital.component;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.webcomponent.ComponentWindowSingle;
import com.srnpr.zapweb.webmodel.MWebField;

/**
 * 医生单选控件
 * 
 * @author srnpr
 *
 */
public class DoctorSingleSelect extends ComponentWindowSingle {

	
	public String upText(MWebField mWebField, MDataMap mDataMap, int iType) {

		mWebField
				.setSourceParam("zw_s_max_select=1&zw_s_source_tableinfo=yh_doctor_info|doctor_code|doctor_name&zw_s_source_page=page_chart_window_yh_doctor_info");

		return upShowText(mWebField, mDataMap, iType);
	}
}
