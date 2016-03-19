package com.srnpr.yeshospital.api.visit;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basehelper.DateHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webapi.RootApiForToken;
import com.srnpr.zapweb.webmodel.MWebResult;
import com.srnpr.zapweb.websupport.FlowSupport;

public class VisitStatus extends RootApiForToken<MWebResult, VisitStatusInput> {

	public MWebResult Process(VisitStatusInput inputParam, MDataMap mRequestMap) {

		MWebResult result = new MWebResult();

		if (result.upFlagTrue()) {
			new FlowSupport().changeStatus("ZF0004", inputParam.getVisitOrderCode(), "46580001000200110003",
					getUserCode(), "");

		}

		if (result.upFlagTrue()) {

			if (StringUtils.isNotBlank(inputParam.getVisitProcess())) {

				MDataMap mUpdateMap = new MDataMap();
				mUpdateMap.inAllValues("visit_order_code", inputParam.getVisitOrderCode(), "process_remark",
						inputParam.getVisitProcess(), "tour_info", inputParam.getTourInfo(), "agree_info",
						inputParam.getAgreeInfo(), "process_time", DateHelper.upNow());

				DbUp.upTable("yh_visit_order_info").dataUpdate(mUpdateMap,
						"process_remark,tour_info,agree_info,process_time", "visit_order_code");

			}
		}

		return result;

	}

}
