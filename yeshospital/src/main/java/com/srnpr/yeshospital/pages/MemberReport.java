package com.srnpr.yeshospital.pages;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.helper.WebSessionHelper;
import com.srnpr.zapweb.webdo.WebConst;

public class MemberReport extends BaseClass {

	public String upMemberCode() {

		String sReturn = "";

		String sMemberCode = WebSessionHelper.create().upRequest(
				WebConst.CONST_WEB_FIELD_NAME + "member_code");

		if (StringUtils.isNotEmpty(sMemberCode)) {
			if (DbUp.upTable("yh_report_info")
					.count("member_code", sMemberCode) > 0) {
				sReturn = sMemberCode;
			}
		}

		return sReturn;

	}

}
