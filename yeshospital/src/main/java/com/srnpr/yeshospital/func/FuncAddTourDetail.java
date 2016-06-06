package com.srnpr.yeshospital.func;

import org.apache.commons.lang.StringUtils;

import com.srnpr.yeshospital.support.ProcRefreshSupport;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.helper.WebHelper;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webfunc.FuncAdd;
import com.srnpr.zapweb.webfunc.RootFunc;
import com.srnpr.zapweb.webmodel.MWebResult;

public class FuncAddTourDetail extends RootFunc {

	public MWebResult funcDo(String sOperateUid, MDataMap mDataMap) {

		MWebResult mWebResult = new MWebResult();
		MDataMap mAddMaps = mDataMap.upSubMap(WebConst.CONST_WEB_FIELD_NAME);

		String sLockCode = WebHelper.addLock(100, mAddMaps.get("member_code"));
		if (StringUtils.isNotBlank(sLockCode)) {
			if (mWebResult.upFlagTrue()) {

				if (DbUp.upTable("yh_tour_order_detail").count("tour_code", mAddMaps.get("tour_code"), "member_code",
						mAddMaps.get("member_code")) > 0) {

					mWebResult.inErrorMessage(965806004);

				}

			}

			if (mWebResult.upFlagTrue()) {
				mWebResult = new FuncAdd().funcDo(sOperateUid, mDataMap);

				new ProcRefreshSupport().refreshCountAgree(mAddMaps);

			}
		} else {
			mWebResult.inErrorMessage(965806004);
		}

		return mWebResult;
	}

}
