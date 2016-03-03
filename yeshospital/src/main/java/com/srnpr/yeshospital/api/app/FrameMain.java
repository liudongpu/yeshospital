package com.srnpr.yeshospital.api.app;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.ietf.jgss.Oid;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.RootInput;
import com.srnpr.zapweb.webapi.RootApiForToken;
import com.srnpr.zapweb.webapi.RootPageDataResult;
import com.srnpr.zapweb.websupport.DataApiSupport;

public class FrameMain extends RootApiForToken<RootPageDataResult, RootInput> {

	public RootPageDataResult Process(RootInput inputParam, MDataMap mRequestMap) {

		RootPageDataResult result = new RootPageDataResult();

		MDataMap mDataMap = new MDataMap();

		ArrayList<String> aWhere = new ArrayList<String>();
		String sUserCode = getUserCode();
		aWhere.add("create_user='" + sUserCode + "' ");

		List<MDataMap> listMaps = new ArrayList<MDataMap>();

		listMaps.addAll(new DataApiSupport().upData("yh_tour_order_info",
				"concat('tour-select?u_tour_order=',tour_code) as a_link,left(tour_date,10) as a_date,case order_status when '46580001000500020001' then 0 else 1 end as a_status,(select geracomium_name from yh_geracomium_info where yh_geracomium_info.geracomium_code=yh_tour_order_info.geracomium_code) as a_name,concat('已检查：',(select CAST(count(1)as char) from yh_tour_order_detail where yh_tour_order_detail.tour_code=yh_tour_order_info.tour_code),'人 共计：',(select CAST(count(1)as char) from yh_member_extend_geracomium where yh_member_extend_geracomium.geracomium_code=yh_tour_order_info.geracomium_code and yh_member_extend_geracomium.member_status='46580001000500040001' ),'人' ) as a_text,tour_date as a_order",
				"-create_time", aWhere, mDataMap, 0, 10).getPageData());

		listMaps.addAll(new DataApiSupport().upData("yh_visit_order_info",
				"concat('visit-order?u_visit_order=',visit_order_code) as a_link,left(visit_time,16) as a_date,case visit_order_status when '46580001000200110002' then 0 else 1 end as a_status,(select concat('姓名：',member_name,'&nbsp;电话：',member_phone,  '<br/>地址：',room_name) from yh_member_extend_geracomium where yh_member_extend_geracomium.member_code=yh_visit_order_info.member_code) as a_name,visit_note as a_text,visit_time as a_order",
				"-visit_time", "process_user='" + sUserCode + "'", mDataMap, 0, 10).getPageData());

		Collections.sort(listMaps, new Comparator<MDataMap>() {

			public int compare(MDataMap o1, MDataMap o2) {

				return o2.get("a_order").compareTo(o1.get("a_order"));
			}
		});
		result.setPageData(listMaps);

		return result;

	}

}
