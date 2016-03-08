package com.srnpr.yeshospital.support;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.helper.WebHelper;
import com.srnpr.zapweb.usermodel.MUserInfo;
import com.srnpr.zapweb.webfactory.UserFactory;
import com.srnpr.zapweb.websupport.UserSupport;

import freemarker.core.LibraryLoad;

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

		sUid = FormatHelper.upStringFromObject(
				DbUp.upTable("yh_app_member").dataGet("uid", "", new MDataMap("member_code", sUserCode)));

		if (StringUtils.isBlank(sUid)) {
			sUid = WebHelper.upUuid();

			DbUp.upTable("yh_app_member").insert("uid", sUid, "member_code", sUserCode, "create_time",
					FormatHelper.upDateTime(), "member_name", mUserInfo.getRealName(), "nick_name",
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

		sUid = FormatHelper.upStringFromObject(
				DbUp.upTable("yh_app_uset").dataGet("uid", "", new MDataMap("member_code", sUserCode)));

		if (StringUtils.isBlank(sUid)) {
			sUid = WebHelper.upUuid();

			DbUp.upTable("yh_app_uset").insert("uid", sUid, "member_code", sUserCode, "create_time",
					FormatHelper.upDateTime());
		}

		return sUid;

	}

	public List<MDataMap> upMouldList(String sWhere, String... sParmas) {
		String sUserCode = UserFactory.INSTANCE.create().getUserCode();

		MDataMap mDataMap = new MDataMap("user_code", sUserCode);
		mDataMap.inAllValues(sParmas);

		return DbUp.upTable("yh_mould_info").queryAll("", "", sWhere + "  and  (user_code=:user_code or user_code='0' )", mDataMap);
	}

	public String upMouldInitCode() {
		String sUserCode = UserFactory.INSTANCE.create().getUserCode();
		/*
		 * if(DbUp.upTable("yh_mould_info").count("user_code",sUserCode)==0) {
		 * 
		 * for(MDataMap
		 * map:DbUp.upTable("yh_mould_info").queryByWhere("user_code","0")) {
		 * 
		 * map.inAllValues("zid",
		 * "0","uid",WebHelper.upUuid(),"user_code",sUserCode,"create_time",
		 * FormatHelper.upDateTime());
		 * DbUp.upTable("yh_mould_info").dataInsert(map); }
		 * 
		 * }
		 */

		return sUserCode;
	}

}
