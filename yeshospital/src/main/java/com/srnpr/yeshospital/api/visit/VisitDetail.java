package com.srnpr.yeshospital.api.visit;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basehelper.DateHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.helper.WebHelper;
import com.srnpr.zapweb.webapi.RootApiForToken;
import com.srnpr.zapweb.webapi.RootPageDataResult;
import com.srnpr.zapweb.websupport.DataApiSupport;

public class VisitDetail extends RootApiForToken<RootPageDataResult, VisitDetailInput> {

	public RootPageDataResult Process(VisitDetailInput inputParam, MDataMap mRequestMap) {

		MDataMap mDataMap = new MDataMap("visit_order_code", inputParam.getVisitOrderCode());

		if (inputParam.getOptCode().equals("1")) {

			mDataMap.inAllValues("visit_detail_code", WebHelper.upCode("VD"), "create_time", DateHelper.upNow(),
					"create_user", getUserCode(), "money_code", inputParam.getMoneyCode(), "money_number",
					String.valueOf(inputParam.getMoneyNumber()));

			if (StringUtils.isNotBlank(inputParam.getMoneyCode())) {

				MDataMap mMoneyMap = DbUp.upTable("yh_visit_money").one("define_code", inputParam.getMoneyCode());

				mDataMap.inAllValues("money_name", mMoneyMap.get("define_name"), "visit_unit",
						mMoneyMap.get("visit_unit"), "visit_money", mMoneyMap.get("visit_money"));

				DbUp.upTable("yh_visit_order_detail").dataInsert(mDataMap);
			}
		}

		return new DataApiSupport().upData("yh_visit_order_detail",
				"money_code,money_name,visit_unit,visit_money,money_number", "-create_time", "",
				new MDataMap("visit_order_code", inputParam.getVisitOrderCode(), "flag_enable", "1"), 0, 0);
	}

}
