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
						"tour_code,left(tour_date,10) as tour_date,order_status,create_name,(select geracomium_name from yh_geracomium_info where yh_geracomium_info.geracomium_code=yh_tour_order_info.geracomium_code) as geracomium_name",
						"-create_time", aWhere, mDataMap, 0, 0);

	}

}
