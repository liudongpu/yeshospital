package com.srnpr.yeshospital.api.member;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.RootApi;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webapi.RootApiForManage;

public class MemberAgreeAuto extends
		RootApi<MemberAgreeAutoResult, MemberAgreeAutoInput> {

	public MemberAgreeAutoResult Process(MemberAgreeAutoInput inputParam,
			MDataMap mRequestMap) {
		MemberAgreeAutoResult result = new MemberAgreeAutoResult();

		MDataMap mMeberMap = DbUp.upTable("yh_member_extend_geracomium").one(
				"member_code", inputParam.getMemberCode());

		result.setGeracomiumCode(mMeberMap.get("geracomium_code"));

		List<MDataMap> listMaps = DbUp.upTable("yh_tour_order_detail").query(
				"agree_info", "zid desc", "",
				new MDataMap("member_code", inputParam.getMemberCode()), 0, 10);

		List<String> lStrings = new ArrayList<String>();
		for (MDataMap map : listMaps) {

			lStrings.add(map.get("agree_info"));

		}

		if (lStrings.size() > 0) {
			result.setAgreeInfo(StringUtils.join(lStrings, "\r\n"));
		}

		return result;
	}
}
