package com.srnpr.yeshospital.api.member;

import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.helper.WebHelper;
import com.srnpr.zapweb.webapi.RootApiForToken;
import com.srnpr.zapweb.webapi.RootResultWeb;

public class SuggestionInfo extends
		RootApiForToken<RootResultWeb, SuggestionInfoInput> {

	public RootResultWeb Process(SuggestionInfoInput inputParam,
			MDataMap mRequestMap) {

		DbUp.upTable("yh_suggestion_info").insert("suggestion_code",
				WebHelper.upCode("SC"), "member_code", getUserCode(),
				"suggestion_info", inputParam.getSuggestionInfo(),
				"create_time", FormatHelper.upDateTime(), "suggestion_type",
				inputParam.getSuggestionType());

		return new RootResultWeb();
	}

}
