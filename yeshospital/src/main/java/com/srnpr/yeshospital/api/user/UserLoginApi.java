package com.srnpr.yeshospital.api.user;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.RootApi;
import com.srnpr.zapweb.usermodel.MUserInfo;
import com.srnpr.zapweb.webapi.UserLoginInput;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webfactory.UserFactory;
import com.srnpr.zapweb.webfunc.FuncManageLogin;
import com.srnpr.zapweb.webmodel.MWebResult;
import com.srnpr.zapweb.websupport.OauthSupport;

public class UserLoginApi extends RootApi<UserLoginApiResult, UserLoginInput> {

	public UserLoginApiResult Process(UserLoginInput inputParam,
			MDataMap mRequestMap) {

		UserLoginApiResult mResult = new UserLoginApiResult();

		if (mResult.upFlagTrue()) {
			if (StringUtils.isNotEmpty(inputParam.getLoginName())
					&& StringUtils.isNotEmpty(inputParam.getLoginPass())) {

				MWebResult mLoginResult = UserFactory.INSTANCE.userLogin(
						inputParam.getLoginName(), inputParam.getLoginPass());

				mResult.inOtherResult(mLoginResult);

			} else {
				mResult.inErrorMessage(969905013);
			}
		}

		if (mResult.upFlagTrue()) {
			MUserInfo mUserInfo = UserFactory.INSTANCE.create();

			OauthSupport oauthSupport = new OauthSupport();

			// 判断 只有指定的医生用户才能登陆app
			if (mUserInfo.getUserRole().contains("467703180003")) {

				// 插入授权登陆表
				oauthSupport.dbInsertOauth(mUserInfo.getCookieUser(),
						mUserInfo.getUserCode(), mUserInfo.getManageCode(),
						mUserInfo.getLoginName(), "3600d", "");

				mResult.setUserToken(mUserInfo.getCookieUser());
			} else {
				mResult.inErrorMessage(965805202);
			}

		}

		return mResult;

	}
}