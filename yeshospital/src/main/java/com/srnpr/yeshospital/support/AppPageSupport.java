package com.srnpr.yeshospital.support;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;

public class AppPageSupport extends BaseClass {

	public MDataMap upTourOrderInfo(String sTourOrderCode) {

		return DbUp.upTable("yh_tour_order_info").one("tour_code",
				sTourOrderCode);

	}

}
