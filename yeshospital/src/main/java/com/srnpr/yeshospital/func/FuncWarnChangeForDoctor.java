package com.srnpr.yeshospital.func;

import com.srnpr.yeshospital.support.MemberMsgSupport;
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

public class FuncWarnChangeForDoctor extends RootFunc {

	public MWebResult funcDo(String sOperateUid, MDataMap mDataMap) {
		MWebResult mWebResult = new MWebResult();
		MDataMap mAddMaps = mDataMap.upSubMap(WebConst.CONST_WEB_FIELD_NAME);

		new MemberMsgSupport().finishMsgByOutCode(mAddMaps.get("warn_code"));

		String sUserCode = UserFactory.INSTANCE.create().getUserCode();
		if (mWebResult.upFlagTrue()) {

			

			DbUp.upTable("yh_warn_process_log").insert("order_code",
					mAddMaps.get("warn_code"), "remark",
					mAddMaps.get("process_plan"), "create_time",
					FormatHelper.upDateTime(), "create_user", sUserCode);

		}

		if (mWebResult.upFlagTrue()) {
			mWebResult = new FuncEdit().funcDo(sOperateUid, mDataMap);

			/*
			 * mWebResult.setResultType("116018010");
			 * mWebResult.setResultObject(FormatHelper.formatString(
			 * bConfig("zapweb.to_url_js"), mAddMaps.get("referer")));
			 */
		}

		return mWebResult;
	}

}
