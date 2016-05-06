package com.srnpr.yeshospital.api.app;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.RootApi;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webapi.RootApiForManage;
import com.srnpr.zapweb.webapi.RootApiForToken;
import com.srnpr.zapweb.webapi.RootPageDataResult;
import com.srnpr.zapweb.websupport.DataApiSupport;

public class QueryMember extends RootApiForToken<RootPageDataResult, QueryMemberInput> {

	public RootPageDataResult Process(QueryMemberInput inputParam, MDataMap mRequestMap) {

		MDataMap mDataMap = new MDataMap();

		ArrayList<String> aWhere = new ArrayList<String>();

		if (StringUtils.isNotBlank(inputParam.getKeyWord())) {
			mDataMap.put("keyword", "%" + inputParam.getKeyWord() + "%");

			aWhere.add("member_name like :keyword or spell_info like :keyword ");
		}

		// 如果没有传养老院编号 则自动设置为账号绑定的编号
		if (StringUtils.isBlank(inputParam.getGeracomiumCode())) {
			inputParam.setGeracomiumCode(getOauthInfo().getManageCode());
		}

		if (StringUtils.isNotBlank(inputParam.getGeracomiumCode())) {
			mDataMap.put("geracomium_code", inputParam.getGeracomiumCode());

			aWhere.add("geracomium_code=:geracomium_code ");
		}

		aWhere.add("member_status='46580001000500040001' ");

		RootPageDataResult result = new DataApiSupport().upData("yh_member_extend_geracomium",
				"member_code,member_name,room_name,spell_info", "room_name", aWhere, mDataMap, 0, 0);

		if (StringUtils.isNotBlank(inputParam.getTourCode())) {
			MDataMap mExist = new MDataMap();

			for (MDataMap map : DbUp.upTable("yh_tour_order_detail").queryByWhere("tour_code",
					inputParam.getTourCode())) {
				mExist.put(map.get("member_code"), map.get("member_code"));

			}

			for (int i = 0, j = result.getPageData().size(); i < j; i++) {

				result.getPageData().get(i).put("flag_check",
						mExist.containsKey(result.getPageData().get(i).get("member_code")) ? "1" : "0");

			}

		}

		return result;

	}

}
