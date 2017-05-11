package com.srnpr.yeshospital.func;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webfunc.FuncEdit;
import com.srnpr.zapweb.webfunc.RootFunc;
import com.srnpr.zapweb.webmodel.MWebResult;

public class FuncEditDoctor extends RootFunc {
	public MWebResult funcDo(String sOperateUid, MDataMap mDataMap) {

		MWebResult mWebResult = new MWebResult();

		if (mWebResult.upFlagTrue()) {
			mWebResult = new FuncEdit().funcDo(sOperateUid, mDataMap);
		}

		if (mWebResult.upFlagTrue()) {
			MDataMap mAddMaps = mDataMap.upSubMap(WebConst.CONST_WEB_FIELD_NAME);
			MDataMap map = DbUp.upTable("yh_doctor_info").one("uid", mAddMaps.get("uid"));
			if (map != null && map.size() > 0) {
				DbUp.upTable("fm_form_scope").delete("user_code", map.get("user_code"));
			}
		}

		return mWebResult;
	}
}
