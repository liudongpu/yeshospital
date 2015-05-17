package com.srnpr.yeshospital.api.app;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.RootApi;
import com.srnpr.zapweb.webapi.RootApiForToken;
import com.srnpr.zapweb.webapi.RootResultWeb;
import com.srnpr.zapweb.websupport.FlowSupport;

public class FinishTourOrder extends
		RootApiForToken<RootResultWeb, FinishTourOrderInput> {

	public RootResultWeb Process(FinishTourOrderInput inputParam,
			MDataMap mRequestMap) {

		FlowSupport flowSupport = new FlowSupport();

		return flowSupport.changeStatus("ZF0001", inputParam.getOrderCode(),
				"46580001000500020002", getManageCode(), "");
	}

}
