package com.srnpr.yeshospital.api.app;

import java.util.ArrayList;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.RootInput;
import com.srnpr.zapweb.webapi.DataApiInput;
import com.srnpr.zapweb.webapi.RootApiForToken;
import com.srnpr.zapweb.webapi.RootPageDataResult;
import com.srnpr.zapweb.websupport.DataApiSupport;

public class FrameDaily extends
		RootApiForToken<RootPageDataResult, DataApiInput> {

	public RootPageDataResult Process(DataApiInput inputParam,
			MDataMap mRequestMap) {
		MDataMap mDataMap = new MDataMap();

		ArrayList<String> aWhere = new ArrayList<String>();

		aWhere.add("member_code='" + getUserCode() + "' ");

		return new DataApiSupport().upDataByInput("yh_member_msg",
				" msg_code,msg_title,msg_info,msg_link,create_time,process_status",
				"process_status,-create_time", aWhere, mDataMap, inputParam);

	}

}
