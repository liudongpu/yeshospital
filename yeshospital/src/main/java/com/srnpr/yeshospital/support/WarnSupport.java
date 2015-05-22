package com.srnpr.yeshospital.support;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.aspectj.bridge.MessageHandler;

import com.srnpr.yeshospital.helper.MessageHelper;
import com.srnpr.yeshospital.model.WarnCheckInfo;
import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.helper.WebHelper;
import com.srnpr.zapweb.webdo.WebTemp;
import com.srnpr.zapweb.webmodel.MWebResult;
import com.srnpr.zapweb.webmodel.MWebUpload;

/**
 * 报警检测
 * 
 * @author srnpr
 *
 */
public class WarnSupport extends BaseClass {

	public MWebResult warnCheck(String sMemberCode, WarnCheckInfo... checkInfos) {

		MWebResult mWebResult = new MWebResult();

		String sNoticeWarn = "";

		for (WarnCheckInfo warnCheckInfo : checkInfos) {

			MDataMap mDataMap = DbUp.upTable("yh_warn_value").oneWhere(
					"",
					"",
					"device_type='" + warnCheckInfo.getDeviceType()
							+ "' and warn_set='" + warnCheckInfo.getWarnSet()
							+ "' and min_value<="
							+ warnCheckInfo.getDeviceValue().toString()
							+ " and max_value>"
							+ warnCheckInfo.getDeviceValue().toString() + " ");

			if (mDataMap != null && mDataMap.size() > 0) {

				String sWarnLevel = mDataMap.get("warn_type");

				if (StringUtils.isNotEmpty(sWarnLevel)) {

					// 判断是否更新警报级别
					if (sWarnLevel.compareTo(sNoticeWarn) > -1) {
						sNoticeWarn = sWarnLevel;
					}

					// 如果不是正常值
					if (!StringUtils.equals(sWarnLevel, "46580001000300020001")) {

						String sTypeText = WebTemp.upTempDataOne("yh_define",
								"define_name", "define_code", sWarnLevel);

						String sSetText = WebTemp.upTempDataOne("yh_define",
								"define_name", "define_code",
								mDataMap.get("warn_set"));

						// 报警信息
						String sWarnInfo = bInfo(965805104, sSetText,
								warnCheckInfo.getDeviceValue().toString(),
								sTypeText);

						DbUp.upTable("yh_warn_info").insert("warn_code",
								WebHelper.upCode("WAC"), "member_code",
								sMemberCode, "warn_info", sWarnInfo,
								"create_time", FormatHelper.upDateTime(),
								"warn_type", warnCheckInfo.getDeviceType(),
								"warn_level", sWarnLevel);

					}
				}

			}

		}

		noticeAll(sMemberCode, sNoticeWarn, checkInfos);

		return mWebResult;

	}

	/**
	 * 通知所有人
	 * 
	 * @param sMemberCode
	 * @param sWarnLevel
	 * @param checkInfos
	 * @return
	 */
	public MWebResult noticeAll(String sMemberCode, String sWarnLevel,
			WarnCheckInfo... checkInfos) {

		MWebResult mWebResult = new MWebResult();
		if (StringUtils.isNotBlank(sWarnLevel)) {

			String sTypeText = WebTemp
					.upTempDataOne("yh_define", "define_name", "define_code",
							checkInfos[0].getDeviceType());

			String sValueText = "";

			for (WarnCheckInfo warnCheckInfo : checkInfos) {
				sValueText = sValueText
						+ WebTemp.upTempDataOne("yh_define", "define_name",
								"define_code", warnCheckInfo.getWarnSet())
						+ ":" + warnCheckInfo.getDeviceValue().toString();
			}

			String sLevelText = WebTemp.upTempDataOne("yh_define",
					"define_name", "define_code", sWarnLevel);

			String sMemberMobile = "";

			if (mWebResult.upFlagTrue()) {

				if (DbUp.upTable("yh_member_info").count("member_code",
						sMemberCode) <= 0) {
					mWebResult.inErrorMessage(965805805, sMemberCode);
				}

			}

			/*
			 * 中度异常：发短信给老人，家属，社区医院。
			 * 内容：老人：您的【检测项目】检测结果为【XXX】（根据各个检测设备显示不同的检测值），数据中度异常
			 * ，请保持手机畅通，会有医护人员联系您。
			 * 家属：您的家人【姓名】的【检测项目】检测结果为【XXX】（根据各个检测设备显示不同的检测值），数据中度异常。
			 * 社区医院医生：您所负责的
			 * 【姓名】【检测项目】检测结果为【XXX】（根据各个检测设备显示不同的检测值），数据中度异常，请尽快与患者【手机】联系。
			 * ----------------------- 重度异常：发短信给老人，家属，三甲医院。
			 * 老人：您的【检测项目】检测结果为【XXX】（根据各个检测设备显示不同的检测值），数据重度异常，请保持手机畅通，会有医护人员联系您。
			 * 家属
			 * ：您的家人【姓名】的【检测项目】检测结果为【XXX】（根据各个检测设备显示不同的检测值），数据重度异常，医生会尽快联系您的家人。
			 * 三甲医院医生
			 * ：您所负责的【姓名】【检测项目】检测结果为【XXX】（根据各个检测设备显示不同的检测值），数据重度异常，请尽快与患者【手机】联系。
			 * -------------------------- 正常：发短信给老人。
			 * 老人：您的【检测项目】检测结果为【XXX】（根据各个检测设备显示不同的检测值），数据正常。
			 */

			// 开始通知老人
			if (mWebResult.upFlagTrue()) {

				sMemberMobile = DbUp.upTable("yh_member_info")
						.one("member_code", sMemberCode)
						.get("i_home_mobilephone");

				if (StringUtils.isNotBlank(sMemberMobile)) {

					String sContent = "";

					if (sWarnLevel.equals("46580001000300020001")) {

						sContent = bInfo(965805801, sTypeText, sValueText);

					} else {
						sContent = bInfo(965805802, sTypeText, sValueText,
								sLevelText);
					}

					MessageHelper.SendSms(sMemberMobile, sContent);

				}

			}

			// 开始通知家属
			if (mWebResult.upFlagTrue()) {

				if (sWarnLevel.equals("46580001000300020003")
						|| sWarnLevel.equals("46580001000300020004")) {

					String sMemberName = DbUp
							.upTable("yh_member_info")
							.dataGet("member_name", "",
									new MDataMap("member_code", sMemberCode))
							.toString();

					String sContent = bInfo(965805803, sMemberName, sTypeText,
							sValueText, sLevelText);

					for (MDataMap mDataMap : DbUp.upTable("yh_sib_info")
							.queryByWhere("member_code", sMemberCode)) {

						String sMobile = mDataMap.get("mobile_phone");

						if (StringUtils.isNotBlank(sMobile)) {
							MessageHelper.SendSms(sMobile, sContent);
						}

					}
				}

			}

			// 开始通知医生
			if (mWebResult.upFlagTrue()) {
				if (sWarnLevel.equals("46580001000300020003")
						|| sWarnLevel.equals("46580001000300020004")) {

					String sMemberName = DbUp
							.upTable("yh_member_info")
							.dataGet("member_name", "",
									new MDataMap("member_code", sMemberCode))
							.toString();
					String sContent = bInfo(965805804, sMemberName, sTypeText,
							sValueText, sLevelText, sMemberMobile);

					// 循环关联医院
					for (MDataMap mHospitalMap : DbUp.upTable(
							"yh_member_hospital").queryByWhere("member_code",
							sMemberCode, "flag_enable", "1")) {

						// 循环科室信息
						for (MDataMap mOfficeMap : DbUp.upTable(
								"yh_office_info").queryByWhere("hospital_code",
								mHospitalMap.get("hospital_code")))

						{

							for (MDataMap mDoctorMap : DbUp.upTable(
									"yh_doctor_info").queryByWhere(
									"office_code",
									mOfficeMap.get("office_code"))) {

								String sMobile = mDoctorMap.get("mobile_phone");

								if (StringUtils.isNotBlank(sMobile)) {
									MessageHelper.SendSms(sMobile, sContent);
								}

							}

						}

					}

				}
			}

		}

		return mWebResult;

	}

}
