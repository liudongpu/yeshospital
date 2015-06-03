package com.srnpr.yeshospital.support;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.helper.WebHelper;
import com.srnpr.zapweb.usermodel.MUserInfo;
import com.srnpr.zapweb.webfactory.UserFactory;
import com.srnpr.zapweb.websupport.UserSupport;

public class AppInfoSupport extends BaseClass {

	/**
	 * 获取用户信息表的唯一编号
	 * 
	 * @return
	 */
	public String upInfoCode() {
		String sUid = "";

		MUserInfo mUserInfo = UserFactory.INSTANCE.create();

		String sUserCode = mUserInfo.getUserCode();

		sUid = FormatHelper.upStringFromObject(DbUp.upTable("yh_app_member")
				.dataGet("uid", "", new MDataMap("member_code", sUserCode)));

		if (StringUtils.isBlank(sUid)) {
			sUid = WebHelper.upUuid();

			DbUp.upTable("yh_app_member").insert("uid", sUid, "member_code",
					sUserCode, "create_time", FormatHelper.upDateTime(),
					"member_name", mUserInfo.getRealName(), "nick_name",
					mUserInfo.getRealName());
		}

		return sUid;

	}

	/**
	 * 获取设置表信息
	 * 
	 * @return
	 */
	public String upConfigCode() {
		String sUid = "";

		MUserInfo mUserInfo = UserFactory.INSTANCE.create();

		String sUserCode = mUserInfo.getUserCode();

		sUid = FormatHelper.upStringFromObject(DbUp.upTable("yh_app_uset")
				.dataGet("uid", "", new MDataMap("member_code", sUserCode)));

		if (StringUtils.isBlank(sUid)) {
			sUid = WebHelper.upUuid();

			DbUp.upTable("yh_app_uset").insert("uid", sUid, "member_code",
					sUserCode, "create_time", FormatHelper.upDateTime());
		}

		return sUid;

	}

}
