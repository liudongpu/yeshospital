package com.srnpr.yeshospital.support;

import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.helper.WebHelper;

public class AdviceSupport {

	public void createAdvice(String sOutCode, String sMemberCode,
			String sAdviceInfo, String sCreateUser) {

		if (DbUp.upTable("yh_count_advice").count("out_code", sOutCode) == 0) {

			DbUp.upTable("yh_count_advice").insert("advice_code",
					WebHelper.upCode("AC"), "out_code", sOutCode,
					"advice_info", sAdviceInfo, "member_code", sMemberCode,
					"create_time", FormatHelper.upDateTime(), "create_user",
					sCreateUser);

		}

	}

}
