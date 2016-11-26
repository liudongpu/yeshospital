package com.srnpr.yeshospital.api.census;

import com.srnpr.zapcom.basehelper.DateHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.RootApi;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.helper.WebHelper;

public class CensusSubmit extends RootApi<CensusSubmitResult, CensusSubmitInput> {

	public CensusSubmitResult Process(CensusSubmitInput inputParam, MDataMap mRequestMap) {
		CensusSubmitResult result = new CensusSubmitResult();

		MDataMap mDataMap = new MDataMap();

		mDataMap.inAllValues("census_info_code", WebHelper.upCode("CIC"), "census_report_code",
				inputParam.getCensusReportCode(), "member_code", inputParam.getMemberCode(), "option_select",
				inputParam.getOptionSelect(), "report_score", inputParam.getReportScore(), "standard_code",
				inputParam.getStandCode(), "create_time", DateHelper.upNow()

		);

		DbUp.upTable("yh_census_info").dataInsert(mDataMap);

		return result;
	}

}
