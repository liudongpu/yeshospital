package com.srnpr.yeshospital.api.member;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapweb.webapi.RootApiForToken;
import com.srnpr.zapweb.webapi.RootResultWeb;
import com.srnpr.zapweb.webfactory.UserFactory;

public class ChangeUserPassword extends
		RootApiForToken<RootResultWeb, ChangeUserPasswordInput> {

	public RootResultWeb Process(ChangeUserPasswordInput inputParam,
			MDataMap mRequestMap) {

		RootResultWeb rootResultWeb = new RootResultWeb();

		if (rootResultWeb.upFlagTrue()) {

			if (!inputParam.getNewPassword().equals(inputParam.getRePassword())) {

				rootResultWeb.inErrorMessage(965806003);
			}
		}

		if (rootResultWeb.upFlagTrue()) {

			rootResultWeb.inOtherResult(UserFactory.INSTANCE.changePassword(
					getUserCode(), inputParam.getOldPassword(),
					inputParam.getNewPassword()));

		}

		return rootResultWeb;

	}

}
