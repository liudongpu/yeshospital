package com.srnpr.yeshospital.func;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webfactory.UserFactory;
import com.srnpr.zapweb.webfunc.FuncAdd;
import com.srnpr.zapweb.webfunc.RootFunc;
import com.srnpr.zapweb.webmodel.MWebResult;

public class FuncAddDoctor extends RootFunc {

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

			String sUserCode = UserFactory.INSTANCE.createUser(
					mAddMaps.get("mobile_phone"), mAddMaps.get("a_password"),
					mAddMaps.get("doctor_name"), mAddMaps.get("hospital_code"),
					"467721200003", "4677031800030001");

		}

		return mWebResult;

	}

}
