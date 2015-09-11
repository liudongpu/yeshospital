package com.srnpr.yeshospital.api.app;

import java.util.ArrayList;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.RootInput;
import com.srnpr.zapweb.webapi.RootApiForToken;
import com.srnpr.zapweb.webapi.RootPageDataResult;
import com.srnpr.zapweb.websupport.DataApiSupport;

public class FrameMain extends RootApiForToken<RootPageDataResult, RootInput> {

	public RootPageDataResult Process(RootInput inputParam, MDataMap mRequestMap) {
		MDataMap mDataMap = new MDataMap();

		ArrayList<String> aWhere = new ArrayList<String>();

		aWhere.add("create_user='" + getUserCode() + "' ");

		return new DataApiSupport()
				.upData("yh_tour_order_info",
						"tour_code,left(tour_date,10) as a_tour_date,order_status,create_name,(select geracomium_name from yh_geracomium_info where yh_geracomium_info.geracomium_code=yh_tour_order_info.geracomium_code) as a_geracomium_name,(select count(1) from yh_member_extend_geracomium where yh_member_extend_geracomium.geracomium_code=yh_tour_order_info.geracomium_code and yh_member_extend_geracomium.member_status='46580001000500040001' ) as a_all_member,(select count(1) from yh_tour_order_detail where yh_tour_order_detail.tour_code=yh_tour_order_info.tour_code) as a_check_member",
						"-create_time", aWhere, mDataMap, 0, 0);

	}

}
