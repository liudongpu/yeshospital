package com.srnpr.yeshospital.api.visit;

import com.srnpr.yeshospital.support.VisitSupport;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.RootApi;

public class VisitOrder extends RootApi<VisitOrderResult, VisitOrderInput> {

	public VisitOrderResult Process(VisitOrderInput inputParam, MDataMap mRequestMap) {
		VisitOrderResult result = new VisitOrderResult();

		result.inOtherResult(new VisitSupport().createVisitOrder(inputParam));

		return result;
	}

}
