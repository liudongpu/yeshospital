package com.srnpr.yeshospital.api.member;

import com.srnpr.yeshospital.api.input.ChangeMemberInfoInput;
import com.srnpr.yeshospital.api.model.MemberInfo;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.webapi.RootApiForToken;
import com.srnpr.zapweb.webapi.RootResultWeb;

public class ChangeMemebrInfo extends RootApiForToken<RootResultWeb, ChangeMemberInfoInput> {

	public RootResultWeb Process(ChangeMemberInfoInput inputParam,
			MDataMap mRequestMap) {
		// TODO Auto-generated method stub
		return new RootResultWeb();
	}

}
