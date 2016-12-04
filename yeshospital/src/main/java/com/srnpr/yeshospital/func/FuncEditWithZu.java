package com.srnpr.yeshospital.func;

import com.srnpr.zapcom.basehelper.DateHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webfactory.UserFactory;
import com.srnpr.zapweb.webfunc.FuncEdit;
import com.srnpr.zapweb.webfunc.RootFunc;
import com.srnpr.zapweb.webmodel.MWebResult;
import com.srnpr.zapweb.websupport.FlowSupport;

public class FuncEditWithZu  extends RootFunc{

	
	public MWebResult funcDo(String sOperateUid, MDataMap mDataMap) {

		MWebResult mResult = new MWebResult();

		
		
		mDataMap.put("zw_f_zu", DateHelper.upNow());

		if (mResult.upFlagTrue()) {
			mResult = new FuncEdit().funcDo(sOperateUid, mDataMap);
		}

		return mResult;

	}
	
	
}
