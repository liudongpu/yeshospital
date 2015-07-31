package com.srnpr.yeshospital.func;

import com.srnpr.yeshospital.face.IPostDataInput;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.helper.WebHelper;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webfunc.FuncAdd;
import com.srnpr.zapweb.webfunc.RootFunc;
import com.srnpr.zapweb.webmodel.MWebResult;

public abstract class FuncPostBase extends RootFunc {

	public MWebResult funcDo(String sOperateUid, MDataMap mDataMap) {
		MWebResult mWebResult = new MWebResult();

		
		mDataMap.put(WebConst.CONST_WEB_FIELD_NAME+"post_code", WebHelper.upCode("PC"));
		
		if (mWebResult.upFlagTrue()) {
			mWebResult = new FuncAdd().funcDo(sOperateUid, mDataMap);

		}

		if (mWebResult.upFlagTrue()) {
			if (mWebResult.upFlagTrue()) {
				MDataMap mAddMaps = mDataMap
						.upSubMap(WebConst.CONST_WEB_FIELD_NAME);

				mWebResult.inOtherResult(toCheck(mAddMaps));

			}
		}

		return mWebResult;
	}

	public abstract MWebResult toCheck(MDataMap mDataMap);

}
