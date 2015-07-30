package com.srnpr.yeshospital.func;

import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.helper.WebSessionHelper;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webfactory.UserFactory;
import com.srnpr.zapweb.webfunc.FuncAdd;
import com.srnpr.zapweb.webfunc.FuncEdit;
import com.srnpr.zapweb.webfunc.RootFunc;
import com.srnpr.zapweb.webmodel.MWebResult;
import com.srnpr.zapweb.websupport.FlowSupport;

public class FuncWarnNotice extends RootFunc {

	public MWebResult funcDo(String sOperateUid, MDataMap mDataMap) {
		MWebResult mWebResult = new MWebResult();
		MDataMap mAddMaps = mDataMap.upSubMap(WebConst.CONST_WEB_FIELD_NAME);

		if (mWebResult.upFlagTrue()) {

			FlowSupport flowSupport = new FlowSupport();

			mWebResult.inOtherResult(flowSupport.changeStatus("ZF0003",
					mAddMaps.get("warn_code"), "46580001000300050002",
					UserFactory.INSTANCE.create().getUserCode(), ""));

		}

		if (mWebResult.upFlagTrue()) {

			MDataMap mUpDataMap = new MDataMap("warn_code",
					mAddMaps.get("warn_code"), "process_user",
					UserFactory.INSTANCE.create().getUserCode(),
					"process_time", FormatHelper.upDateTime());

			DbUp.upTable("yh_count_warn_geracomium").dataUpdate(mUpDataMap,
					"process_user,process_time", "warn_code");

		}

		if (mWebResult.upFlagTrue()) {
			mWebResult = new FuncEdit().funcDo(sOperateUid, mDataMap);
			
			/*
			mWebResult.setResultType("116018010");
			mWebResult.setResultObject(FormatHelper.formatString(
					bConfig("zapweb.to_url_js"), mAddMaps.get("referer")));
			*/
		}

		return mWebResult;
	}

}
