package com.srnpr.yeshospital.func;

import com.srnpr.yeshospital.api.visit.VisitOrderInput;
import com.srnpr.yeshospital.support.VisitSupport;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.basesupport.SerializeSupport;
import com.srnpr.zapweb.usermodel.MUserInfo;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webfactory.UserFactory;
import com.srnpr.zapweb.webfunc.FuncEdit;
import com.srnpr.zapweb.webfunc.RootFunc;
import com.srnpr.zapweb.webmodel.MWebResult;
import com.srnpr.zapweb.websupport.FlowSupport;

public class FuncEditVisitOrder extends RootFunc {

	public MWebResult funcDo(String sOperateUid, MDataMap mDataMap) {

		MWebResult mResult = new MWebResult();

		MDataMap mAddMaps = mDataMap.upSubMap(WebConst.CONST_WEB_FIELD_NAME);
		if (mResult.upFlagTrue()) {

			mResult = new FlowSupport().changeStatus("ZF0004", mAddMaps.get("visit_order_code"),
					mAddMaps.get("visit_order_status"), UserFactory.INSTANCE.create().getUserCode(), "");

		}

		if (mResult.upFlagTrue()) {
			mResult = new FuncEdit().funcDo(sOperateUid, mDataMap);
		}

		return mResult;

	}

}
