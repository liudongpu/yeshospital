package com.srnpr.yeshospital.job;

import org.apache.commons.lang.StringUtils;
import org.quartz.JobExecutionContext;

import com.srnpr.yeshospital.helper.MessageHelper;
import com.srnpr.yeshospital.support.AdviceSupport;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.rootweb.RootJobForLock;
import com.srnpr.zapweb.webdo.WebTemp;
import com.srnpr.zapweb.webmodel.MWebHtml;
import com.srnpr.zapweb.webmodel.MWebResult;
import com.srnpr.zapweb.websupport.FlowSupport;

public class JobNoticeWarn extends RootJobForLock {

	public void doExecute(JobExecutionContext context) {

		FlowSupport flowSupport = new FlowSupport();

		for (MDataMap map : DbUp.upTable("yh_count_warn_geracomium")
				.queryByWhere("process_status", "46580001000300050002")) {

			MWebResult mResult = flowSupport.changeStatus("ZF0003",
					map.get("warn_code"), "46580001000300050003", "timer", "");
			if (mResult.upFlagTrue()) {

				String sProcess = StringUtils.defaultIfBlank(
						map.get("process_step"), map.get("process_plan"));

				// 插入健康建议表
				new AdviceSupport().createAdvice(map.get("warn_code"),
						map.get("member_code"), sProcess,
						map.get("process_user"));

				String[] sNotices = map.get("notice_type").split(",");

				if (sNotices.length > 0) {
					String sWarnInfo = map.get("warn_info");
					String sMemberCode = map.get("member_code");

					String sMemberName = map.get("member_name");

					String sMessageinfo = bInfo(965805806, sMemberName,
							sWarnInfo, sProcess);

					for (String sType : sNotices) {

						if (sType.equals("46580001000300060001")) {

							// 开始微信通知

						} else if (sType.equals("46580001000300060003")) {

							// 开始短信通知

							for (MDataMap mDataMap : DbUp
									.upTable("yh_sib_info").queryByWhere(
											"member_code", sMemberCode)) {

								String sMobile = mDataMap.get("mobile_phone");

								if (StringUtils.isNotBlank(sMobile)) {
									MessageHelper
											.SendSms(sMobile, sMessageinfo);
								}

							}

						}

					}

				}

			}

		}

	}

}
