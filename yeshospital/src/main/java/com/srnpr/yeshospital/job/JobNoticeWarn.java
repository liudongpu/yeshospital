package com.srnpr.yeshospital.job;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.quartz.JobExecutionContext;

import com.srnpr.yeshospital.helper.MessageHelper;
import com.srnpr.yeshospital.helper.YesHospitalHelper;
import com.srnpr.yeshospital.support.AdviceSupport;
import com.srnpr.yeshospital.support.MemberMsgSupport;
import com.srnpr.yeshospital.support.MsgSupport;
import com.srnpr.yeshospital.wx.WxConst;
import com.srnpr.yeshospital.wx.WxSendTemplate;
import com.srnpr.yeshospital.wx.model.WxTemplageValue;
import com.srnpr.yeshospital.wx.model.WxTemplateSend;
import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basehelper.RegexHelper;
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

		
		//开始执行短信通知对象
		for (MDataMap map : DbUp
				.upTable("yh_count_warn_geracomium")
				.queryAll(
						"",
						"",
						"flag_msg=0 and warn_level in('46580001000300020003','46580001000300020004')",
						new MDataMap())) {

			map.put("flag_msg", "1");
			DbUp.upTable("yh_count_warn_geracomium").dataUpdate(map,
					"flag_msg", "zid");

			// 找到对应养老院的处理医生
			MDataMap mTourMap = DbUp.upTable("yh_tour_order_info").oneWhere(
					"geracomium_code,create_user", "-create_time", "",
					"geracomium_code", map.get("geracomium_code"));
			
			
			//通知对象  结构为用户账户编号 手机号
			MDataMap mNoticeMap=new MDataMap();
			
			if(mTourMap != null)
			{
				mNoticeMap.put(mTourMap.get("create_user"), "");
				
				MDataMap mDoctorMap=DbUp.upTable("za_userinfo").one("user_code",mTourMap.get("create_user"));
				 //MessageHelper().SendSms(sPhone, sContent);
				if(mDoctorMap!=null)
				{
					mNoticeMap.put(mTourMap.get("create_user"), mDoctorMap.get("user_name"));
				}
			}
			
			
			
			for(MDataMap mDoctorMap:DbUp.upTable("yh_doctor_geracomium").queryByWhere("geracomium_code",map.get("geracomium_code"),"flag_enable","1"))
			{
				
				MDataMap mDocInfo=DbUp.upTable("yh_doctor_info").one("doctor_code",mDoctorMap.get("doctor_code"));
				mNoticeMap.put(mDocInfo.get("user_code"), mDocInfo.get("mobile_phone"));
				
			}
			
			
			
			
			
			if (mNoticeMap != null&&mNoticeMap.size()>0) {

				
				
				for(String sKey:mNoticeMap.keySet())
				{
					
					MDataMap mMsgMap = new MDataMap();

					String sMsgInfo = bInfo(965805809, map.get("member_name"),
							map.get("warn_info"));
					mMsgMap.inAllValues("member_code",sKey,
							"msg_type", "46580001000200060002", "msg_title",
							YesHospitalHelper.upDefineName("46580001000200060002"),
							"msg_info", sMsgInfo, "msg_link",
							"../mobile/daily-warn?zw_f_uid="
									+ map.get("uid"), "out_code",
							map.get("warn_code"));

					new MemberMsgSupport().createMsg(mMsgMap);

					
					
					 //MessageHelper().SendSms(sPhone, sContent);
					if(StringUtils.isNotBlank(mNoticeMap.get(sKey)))
					{
						String sPhoneString=mNoticeMap.get(sKey);
						
						if(RegexHelper.checkRegexField(sPhoneString, "base=mobile"))
						{
							new MessageHelper().SendSms(sPhoneString, sMsgInfo);
						}
						
						
					}
					
				}
				
				
				
				
				
				
			}

		}

		// 开始处理通知消息相关
		for (MDataMap map : DbUp.upTable("yh_count_warn_geracomium")
				.queryByWhere("process_status", "46580001000300050002")) {

			MWebResult mResult = flowSupport.changeStatus("ZF0003",
					map.get("warn_code"), "46580001000300050003", "timer", "");
			if (mResult.upFlagTrue()) {

				String sProcess = StringUtils.defaultIfBlank(
						map.get("process_step"), map.get("process_method"));

				// 插入健康建议表
				new AdviceSupport().createAdvice(map.get("warn_code"),
						map.get("member_code"), sProcess,
						map.get("process_user"));

				String[] sNotices = map.get("notice_type").split(",");

				if (sNotices.length > 0) {
					String sWarnInfo = map.get("warn_info");
					String sMemberCode = map.get("member_code");

					String sMemberName = map.get("member_name");

					String sWarnType = map.get("warn_type");
					String sPostCode = map.get("post_code");

					String sLevelName = WebTemp
							.upTempDataOne("yh_define", "define_name",
									"define_code", map.get("warn_level"));

					String sMessageinfo = bInfo(965805806, sMemberName,
							sWarnInfo, sProcess);

					for (String sType : sNotices) {

						if (sType.equals("46580001000300060001")) {

							// 开始微信通知

							for (MDataMap mSibInfo : DbUp
									.upTable("yh_sib_info").queryByWhere(
											"member_code", sMemberCode)) {

								for (MDataMap mBindInfo : DbUp.upTable(
										"yh_wx_bind").queryByWhere("sib_code",
										mSibInfo.get("sib_code"))) {

									WxSendTemplate wxSendTemplate = new WxSendTemplate();

									WxTemplateSend wxTemplateSend = new WxTemplateSend();

									wxTemplateSend.setTouser(mBindInfo
											.get("wx_openid"));

									wxTemplateSend.getData().put(
											"first",
											new WxTemplageValue(bInfo(
													965805807, sMemberName)));

									wxTemplateSend.getData().put(
											"remark",
											new WxTemplageValue(
													bInfo(965805808)));

									// 血压信息推送
									if (sWarnType
											.equals("46580001000300010002")) {
										wxTemplateSend
												.setTemplate_id(WxConst.TEMPLATE_FOR_02);

										MDataMap mValueMap = DbUp.upTable(
												"yh_post_pressure").one(
												"post_code", sPostCode);

										wxTemplateSend
												.getData()
												.put("keyword1",
														new WxTemplageValue(
																mValueMap
																		.get("upper_pressure")));
										wxTemplateSend
												.getData()
												.put("keyword2",
														new WxTemplageValue(
																mValueMap
																		.get("lower_pressure")));
										wxTemplateSend.getData().put(
												"keyword3",
												new WxTemplageValue(mValueMap
														.get("heart_rate")));
										wxTemplateSend.getData()
												.put("keyword4",
														new WxTemplageValue(
																sLevelName));

									} else if (sWarnType
											.equals("46580001000300010001")) {

										wxTemplateSend
												.setTemplate_id(WxConst.TEMPLATE_FOR_01);
										MDataMap mValueMap = DbUp.upTable(
												"yh_post_temperature").one(
												"post_code", sPostCode);

										wxTemplateSend.getData().put(
												"keyword1",
												new WxTemplageValue(mValueMap
														.get("temperature")));
										wxTemplateSend.getData().put(
												"keyword2",
												new WxTemplageValue(mValueMap
														.get("create_time")
														+ "    " + sLevelName));

									} else if (sWarnType
											.equals("46580001000300010006")) {

										wxTemplateSend
												.setTemplate_id(WxConst.TEMPLATE_FOR_06);
										MDataMap mValueMap = DbUp.upTable(
												"yh_post_oxygen").one(
												"post_code", sPostCode);
										wxTemplateSend.getData().put(
												"keyword1",
												new WxTemplageValue(mValueMap
														.get("oxygen")));
										wxTemplateSend.getData().put(
												"keyword2",
												new WxTemplageValue(mValueMap
														.get("heart_rate")));

										wxTemplateSend.getData()
												.put("keyword3",
														new WxTemplageValue(
																sLevelName));

									}

									// 开始发送微信并计入日志
									if (wxTemplateSend.getData().size() > 2) {

										if (DbUp.upTable("yh_log_wx_template")
												.count("post_code",
														sPostCode,
														"open_id",
														wxTemplateSend
																.getTouser()) == 0) {
											String sResult = wxSendTemplate
													.process(wxTemplateSend);

											DbUp.upTable("yh_log_wx_template")
													.insert("post_code",
															sPostCode,
															"create_time",
															FormatHelper
																	.upDateTime(),
															"wx_result",
															sResult,
															"open_id",
															wxTemplateSend
																	.getTouser());

										}
									}

								}

							}

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
