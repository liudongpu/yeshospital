package com.srnpr.yeshospital.api.app;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.RootApi;
import com.srnpr.zapweb.webapi.RootPageDataResult;
import com.srnpr.zapweb.websupport.DataApiSupport;

public class QueryTourDetail extends
		RootApi<RootPageDataResult, QueryTourDetailInput> {

	public RootPageDataResult Process(QueryTourDetailInput inputParam,
			MDataMap mRequestMap) {
		MDataMap mDataMap = new MDataMap();

		ArrayList<String> aWhere = new ArrayList<String>();

		mDataMap.put("tour_code", inputParam.getOrderCode());

		aWhere.add("tour_code=:tour_code ");

		return new DataApiSupport()
				.upData("yh_tour_order_detail",
						"member_code,(select member_name from yh_member_extend_geracomium where yh_member_extend_geracomium.member_code=yh_tour_order_detail.member_code) as a_member_name,(select room_name from yh_member_extend_geracomium where yh_member_extend_geracomium.member_code=yh_tour_order_detail.member_code) as a_room_name",
						"-zid", aWhere, mDataMap, 0, 0);
	}

}
