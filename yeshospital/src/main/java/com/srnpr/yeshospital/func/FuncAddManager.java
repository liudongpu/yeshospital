package com.srnpr.yeshospital.func;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webfactory.UserFactory;
import com.srnpr.zapweb.webfunc.FuncAdd;
import com.srnpr.zapweb.webfunc.RootFunc;
import com.srnpr.zapweb.webmodel.MWebResult;

public class FuncAddManager extends RootFunc {

	public MWebResult funcDo(String sOperateUid, MDataMap mDataMap) {

		MWebResult mWebResult = new MWebResult();
		MDataMap mAddMaps = mDataMap.upSubMap(WebConst.CONST_WEB_FIELD_NAME);

		if (mWebResult.upFlagTrue()) {

			if (DbUp.upTable("za_userinfo").count("user_name",
					mAddMaps.get("mobile_phone")) > 0) {
				mWebResult.inErrorMessage(965805201,
						mAddMaps.get("mobile_phone"));
			}

		}

		if (mWebResult.upFlagTrue()) {
			mWebResult = new FuncAdd().funcDo(sOperateUid, mDataMap);

		}

		if (mWebResult.upFlagTrue()) {

			String sGenCode = mAddMaps.get("geracomium_code");

			String sUserRole = "4677031800040001";

			if (DbUp.upTable("yh_geracomium_info")
					.dataGet("geracomium_type", "",
							new MDataMap("geracomium_code", sGenCode))
					.toString().equals("46580001000500050002")) {
				sUserRole = "4677031800040002";
			}

			UserFactory.INSTANCE.createUser(mAddMaps.get("mobile_phone"),
					mAddMaps.get("a_password"), mAddMaps.get("manager_name"),
					sGenCode, "467721200004", sUserRole);

		}

		return mWebResult;

	}
}
