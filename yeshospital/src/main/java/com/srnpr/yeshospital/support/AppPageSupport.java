package com.srnpr.yeshospital.support;

import org.apache.commons.lang.StringUtils;

import com.srnpr.yeshospital.topdo.YhConst;
import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.usermodel.MUserInfo;
import com.srnpr.zapweb.webfactory.UserFactory;
import com.srnpr.zapweb.webmodel.MWebHtml;

public class AppPageSupport extends BaseClass {

	public MDataMap upTourOrderInfo(String sTourOrderCode) {

		return DbUp.upTable("yh_tour_order_info").one("tour_code",
				sTourOrderCode);

	}

	public String upFlagOpen() {

		MUserInfo mUserInfo = UserFactory.INSTANCE.create();

		String sFlagOpen = "0";
		MWebHtml mWebHtml = new MWebHtml("hidden");

		mWebHtml.inAttributes("name", "hidden", "id", YhConst.OPEN_HIDDEN_ID,
				"name", YhConst.OPEN_HIDDEN_ID);

		if (mUserInfo != null
				&& StringUtils.contains(mUserInfo.getUserRole(),
						YhConst.OPEN_ROLE)) {
			mWebHtml.inAttributes("value", "1");
		} else {
			mWebHtml.inAttributes("value", "");
		}

		return mWebHtml.upString();
	}

}
