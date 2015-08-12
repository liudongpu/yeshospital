package com.srnpr.yeshospital.func;

import com.srnpr.yeshospital.support.EtSupport;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.webfunc.RootFunc;
import com.srnpr.zapweb.webmodel.MWebResult;

public class FuncBindEtGateway extends RootFunc {

	public MWebResult funcDo(String sOperateUid, MDataMap mDataMap) {
		new EtSupport().bindDevice();
		return new MWebResult();
	}

}
