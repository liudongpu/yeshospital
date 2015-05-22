package com.srnpr.yeshospital.api.et;

import com.srnpr.yeshospital.et.rsync.EtRsyncUpdateEnt;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.RootApi;
import com.srnpr.zapcom.topapi.RootInput;
import com.srnpr.zapweb.webapi.RootResultWeb;

public class UpdateEtInfo extends RootApi<RootResultWeb, RootInput> {

	public RootResultWeb Process(RootInput inputParam, MDataMap mRequestMap) {

		String sEntInfo = "{ \"IsUnexpired\" : false, \"EntAccount\" : \""
				+ bConfig("yeshospital.et_account")
				+ "\", \"Password\" : \"\", \"EntName\" : \""
				+ bConfig("yeshospital.et_entname") + "\", \"Contact\" : \""
				+ bConfig("yeshospital.et_entconcat")
				+ "\", \"Telephone\" : \"" + bConfig("yeshospital.et_entphone")
				+ "\", \"DataPushUrl\" : \""
				+ bConfig("yeshospital.et_callback")
				+ "\", \"Comment\" : \"testaccount\" }";
		String sRString = new EtRsyncUpdateEnt().upHttp(new MDataMap("entInfo",
				sEntInfo));

		RootResultWeb resultWeb = new RootResultWeb();
		resultWeb.setResultMessage(sRString);
		return resultWeb;

	}

}
