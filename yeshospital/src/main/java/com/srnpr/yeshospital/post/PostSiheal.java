package com.srnpr.yeshospital.post;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.srnpr.yeshospital.api.model.GlucoseInput;
import com.srnpr.yeshospital.api.model.OxygenInput;
import com.srnpr.yeshospital.api.model.PressureInput;
import com.srnpr.yeshospital.api.model.TemperatureInput;
import com.srnpr.yeshospital.api.postdata.ApiGlucose;
import com.srnpr.yeshospital.api.postdata.ApiOxygen;
import com.srnpr.yeshospital.api.postdata.ApiPressure;
import com.srnpr.yeshospital.api.postdata.ApiTemperature;
import com.srnpr.yeshospital.face.IPostDataInput;
import com.srnpr.yeshospital.model.PostDataResult;
import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.baseface.IBaseResult;
import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.helper.WebHelper;
import com.srnpr.zapweb.helper.WebSessionHelper;

public class PostSiheal extends BaseClass {

	/**
	 * 处理
	 * 
	 * @return
	 */
	public String process() {
		HttpServletRequest hRequest = WebSessionHelper.create().upHttpRequest();

		StringBuffer info = new java.lang.StringBuffer();
		try {
			InputStream in = hRequest.getInputStream();
			BufferedInputStream buf = new BufferedInputStream(in);
			byte[] buffer = new byte[1024];
			int iRead;

			while ((iRead = buf.read(buffer)) != -1) {
				info.append(new String(buffer, 0, iRead, "UTF-8"));
			}
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		String sInfo = info.toString();

		String sReturn = "0";
		if (StringUtils.isEmpty(sInfo)) {

		} else {
			String sLogCodeString = WebHelper.upCode("LC");

			DbUp.upTable("yh_log_post").insert("log_code", sLogCodeString,
					"post_info", sInfo, "create_time",
					FormatHelper.upDateTime());

			process(sInfo, sLogCodeString);

			sReturn = "1";

		}

		return sReturn;
	}

	public PostDataResult process(String sInfo, String sLogCode) {
		PostDataResult iResult = new PostDataResult();

		String[] sFirst = StringUtils.split(
				StringUtils.substringBeforeLast(
						StringUtils.substringAfter(sInfo, "["), "]"), ",");

		int iType = -1;

		if (sFirst.length == 4) {
			iType = Integer.valueOf(sFirst[1]);

			String[] sData = StringUtils.split(sFirst[3], "@");

			String sPhone = sData[0];
			String sDeciveCode = sData[1];
			String sClientPostTime = formatTime(sFirst[0]);
			String sProcessTime = formatTime(sData[2]);

			switch (iType) {

			// 血压
			case 1:

				if (iResult.upFlagTrue()) {
					PressureInput tInput = new PressureInput();

					initDate(tInput, sClientPostTime, sDeciveCode, sProcessTime);

					tInput.setDataHeart(new BigDecimal(sData[3]));
					tInput.setDataLower(new BigDecimal(sData[5]));
					tInput.setDataUpper(new BigDecimal(sData[4]));

					ApiPressure api = new ApiPressure();
					iResult = api.upResult(tInput, sLogCode, "");
				}
				break;

			// 血糖
			case 2:
				if (iResult.upFlagTrue()) {
					GlucoseInput tInput = new GlucoseInput();
					initDate(tInput, sClientPostTime, sDeciveCode, sProcessTime);

					tInput.setDataGlucose(new BigDecimal(sData[3]));
					tInput.setDataCheckType(sData[4]);
					ApiGlucose api = new ApiGlucose();
					iResult = api.upResult(tInput, sLogCode, "");
				}
				break;
			// 血氧
			case 3:
				if (iResult.upFlagTrue()) {
					OxygenInput tInput = new OxygenInput();
					initDate(tInput, sClientPostTime, sDeciveCode, sProcessTime);

					tInput.setDataOxygen(new BigDecimal(sData[3]));
					tInput.setDataHeart(new BigDecimal(sData[4]));
					ApiOxygen api = new ApiOxygen();
					iResult = api.upResult(tInput, sLogCode, "");
				}
				break;
			// 体温
			case 5:
				if (iResult.upFlagTrue()) {
					TemperatureInput tInput = new TemperatureInput();
					initDate(tInput, sClientPostTime, sDeciveCode, sProcessTime);

					tInput.setDataTemperature(new BigDecimal(sData[3]));
					ApiTemperature api = new ApiTemperature();
					iResult = api.upResult(tInput, sLogCode, "");

				}
				break;

			default:
				break;
			}

		} else {
			iResult.inErrorMessage(965805105);
		}

		return iResult;

	}

	private void initDate(IPostDataInput iPostDataInput,
			String sPostClientTime, String sPostDecviceSerial,
			String sPostProcessTime) {

		iPostDataInput.setPostClientTime(sPostClientTime);
		iPostDataInput.setPostDecviceSerial(sPostDecviceSerial);
		iPostDataInput.setPostProcessTime(sPostProcessTime);
	}

	private String formatTime(String sTime) {

		return StringUtils.substring(sTime, 0, 4) + "-"
				+ StringUtils.substring(sTime, 4, 6) + "-"
				+ StringUtils.substring(sTime, 6, 7) + " "
				+ StringUtils.substring(sTime, 8, 10) + ":"
				+ StringUtils.substring(sTime, 10, 12) + ":"
				+ StringUtils.substring(sTime, 12, 14);
	}

}
