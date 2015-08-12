package com.srnpr.yeshospital.component;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.webcomponent.ComponentWindowSingle;
import com.srnpr.zapweb.webmodel.MWebField;

/**
 * 药品单选控件
 * 
 * @author srnpr
 *
 */
public class DrugSingleSelect extends ComponentWindowSingle {

	public String upText(MWebField mWebField, MDataMap mDataMap, int iType) {

		mWebField
				.setSourceParam("zw_s_max_select=1&zw_s_source_tableinfo=yh_drug_info|drug_code|drug_name&zw_s_source_page=page_chart_window_yh_drug_info&zw_s_add_js=zapjs.f.require(['yhmanage/js/drugsingleselect'],function(a){});");

		return upShowText(mWebField, mDataMap, iType);
	}
}
