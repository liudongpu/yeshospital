package com.srnpr.yeshospital.api.user;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.RootApi;
import com.srnpr.zapweb.usermodel.MUserInfo;
import com.srnpr.zapweb.webapi.UserLoginInput;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webfactory.UserFactory;
import com.srnpr.zapweb.webfunc.FuncManageLogin;
import com.srnpr.zapweb.webmodel.MWebResult;
import com.srnpr.zapweb.websupport.OauthSupport;

public class UserLoginApi extends RootApi<MWebResult, UserLoginInput> {

	public MWebResult Process(UserLoginInput inputParam, MDataMap mRequestMap) {
		// TODO Auto-generated method stub
		MDataMap mFuncMap = new MDataMap();
		mFuncMap.put(WebConst.CONST_WEB_FIELD_NAME + "login_name",
				inputParam.getLoginName());
		mFuncMap.put(WebConst.CONST_WEB_FIELD_NAME + "login_pass",
				inputParam.getLoginPass());

		MWebResult mResult = new FuncManageLogin().funcDo("", mFuncMap);

		if (mResult.upFlagTrue()) {
			MUserInfo mUserInfo = UserFactory.INSTANCE.create();

			OauthSupport oauthSupport = new OauthSupport();

			// 插入授权登陆表
			oauthSupport.dbInsertOauth(mUserInfo.getCookieUser(),
					mUserInfo.getUserCode(), mUserInfo.getManageCode(),
					mUserInfo.getLoginName(), "3600d", "");

		}

		return mResult;

	}
}