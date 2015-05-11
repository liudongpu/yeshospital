package com.srnpr.yeshospital.api.app;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.RootApi;
import com.srnpr.zapweb.webapi.RootPageDataResult;
import com.srnpr.zapweb.websupport.DataApiSupport;

public class QueryMember extends RootApi<RootPageDataResult, QueryMemberInput> {

	public RootPageDataResult Process(QueryMemberInput inputParam,
			MDataMap mRequestMap) {

		MDataMap mDataMap = new MDataMap();

		ArrayList<String> aWhere = new ArrayList<String>();

		if (StringUtils.isNotBlank(inputParam.getKeyWord())) {
			mDataMap.put("keyword", "%" + inputParam.getKeyWord() + "%");

			aWhere.add("member_name like :keyword or spell_info like :keyword ");
		}

		return new DataApiSupport().upData("yh_member_extend_geracomium",
				"member_code,member_name,room_name,spell_info", "spell_info",
				aWhere, mDataMap, 0, 10);

	}

}
