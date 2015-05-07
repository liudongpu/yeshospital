package com.srnpr.yeshospital.api.user;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.RootApi;
import com.srnpr.zapweb.webapi.UserLoginInput;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webfunc.FuncManageLogin;
import com.srnpr.zapweb.webmodel.MWebResult;

public class UserLoginApi extends RootApi<MWebResult, UserLoginInput> {

	
	public MWebResult Process(UserLoginInput inputParam, MDataMap mRequestMap) {
		// TODO Auto-generated method stub
		MDataMap mFuncMap = new MDataMap();
		mFuncMap.put(WebConst.CONST_WEB_FIELD_NAME + "login_name",
				inputParam.getLoginName());
		mFuncMap.put(WebConst.CONST_WEB_FIELD_NAME + "login_pass",
				inputParam.getLoginPass());

		return new FuncManageLogin().funcDo("", mFuncMap);

	}

}