package com.srnpr.yeshospital.support;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

import com.srnpr.yeshospital.model.WarnCheckInfo;
import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.helper.WebHelper;
import com.srnpr.zapweb.webdo.WebTemp;
import com.srnpr.zapweb.webmodel.MWebResult;

/**
 * 报警检测
 * 
 * @author srnpr
 *
 */
public class WarnSupport extends BaseClass {

	public MWebResult warnCheck(String sMemberCode, WarnCheckInfo... checkInfos) {

		MWebResult mWebResult = new MWebResult();

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
								"warn_type", warnCheckInfo.getDeviceType(),"warn_level",sWarnLevel);

					}
				}

			}

		}

		return mWebResult;

	}
}
