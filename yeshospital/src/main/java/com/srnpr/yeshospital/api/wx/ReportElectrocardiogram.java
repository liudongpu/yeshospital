package com.srnpr.yeshospital.api.wx;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.RootApi;
import com.srnpr.zapdata.dbdo.DbUp;

public class ReportElectrocardiogram extends
		RootApi<ReportElectrocardiogramResult, ReportQueryInput> {

	public ReportElectrocardiogramResult Process(ReportQueryInput inputParam,
			MDataMap mRequestMap) {
		ReportElectrocardiogramResult result = new ReportElectrocardiogramResult();

		MDataMap mQueryMap = new MDataMap();
		mQueryMap.inAllValues("member_code", inputParam.getMemberCode());
		for (MDataMap map : DbUp.upTable("yh_post_electrocardiogram").queryAll(
				"create_time,image_url,test_result", "-create_time",
				"member_code=:member_code and image_url!=''", mQueryMap)) {
			ReportElectrocardiogramItem item = new ReportElectrocardiogramItem();
			item.setDateTime(map.get("create_time"));
			item.setImageUrl(map.get("image_url"));
			item.setDataMessage(map.get("test_result"));
			result.getItems().add(item);
		}

		return result;
	}

}
