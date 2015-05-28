package com.srnpr.yeshospital.post;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.srnpr.yeshospital.api.model.OxygenInput;
import com.srnpr.yeshospital.api.model.PressureInput;
import com.srnpr.yeshospital.api.model.WeightInput;
import com.srnpr.yeshospital.api.postdata.ApiOxygen;
import com.srnpr.yeshospital.api.postdata.ApiPressure;
import com.srnpr.yeshospital.api.postdata.ApiWeight;
import com.srnpr.yeshospital.face.IPostDataInput;
import com.srnpr.yeshospital.helper.LogHttpHelper;
import com.srnpr.yeshospital.model.PostDataResult;
import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.basehelper.EncodeHelper;
import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topdo.TopConst;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.helper.WebHelper;
import com.srnpr.zapweb.helper.WebSessionHelper;
import com.srnpr.zapweb.webmodel.MWebResult;

public class PostEt extends BaseClass {

	public String process() {
		HttpServletRequest hRequest = WebSessionHelper.create().upHttpRequest();

		String sRequestString = hRequest.getParameter("datas");

		MWebResult mWebResult = doProcess(EncodeHelper
				.urlDecode(sRequestString));

		return String.valueOf(mWebResult.getResultCode());
	}

	public MWebResult doProcess(String sData) {

		MWebResult mWebResult = new MWebResult();

		if (mWebResult.upFlagTrue()) {

			JSONArray jsonArray = JSON.parseArray(sData);

			String sCode = LogHttpHelper.AddLog("46580001000200040002", "",
					sData);

			int iLength = jsonArray.size();

			int iProcess = 0;

			for (int i = 0; i < iLength; i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);

				String sDataType = jsonObject.getString("apptype");

				String sDataKey = jsonObject.getString("datakey");

				String sDataId = jsonObject.getString("DataId");

				String sAddTime = jsonObject.getString("adddate");

				String sCollectTime = jsonObject.getString("collectdate");

				// 如果该主键的数据不存在时 则开始处理
				if (DbUp.upTable("yh_log_etpull").count("log_dataid", sDataId) == 0) {

					String sLogCode = WebHelper.upCode("LET");
					DbUp.upTable("yh_log_etpull").insert("log_code", sLogCode,
							"log_type", sDataType, "log_dataid", sDataId,
							"log_datakey", sDataKey, "log_info",
							jsonArray.getString(i), "create_time",
							FormatHelper.upDateTime(), "add_time", sAddTime,
							"collect_time", sCollectTime);

					String sCardCode = StringUtils.right(sDataKey, 20);

					PostDataResult postDataResult = postData(sLogCode,
							sDataType, sAddTime, sCollectTime, sCardCode,
							jsonObject);

					if (postDataResult.upFlagTrue()) {
						iProcess++;
					}

					MWebResult mPostResult = new MWebResult();
					mPostResult.inOtherResult(postDataResult);
					MDataMap mUpdateMap = new MDataMap();
					mUpdateMap.inAllValues("log_code", sLogCode,
							"flag_process", "1", "process_result",
							mPostResult.upJson());
					DbUp.upTable("yh_log_etpull").dataUpdate(mUpdateMap,
							"flag_process,process_result", "log_code");

				}

			}

			LogHttpHelper.UpdateLog(sCode, "", 1, iLength, iProcess);

		}

		return mWebResult;
	}

	/**
	 * @param sLogCode
	 * @param sType
	 * @param sPostClientTime
	 * @param sPostProcessTime
	 * @param sCardCode
	 * @param jsonObject
	 * @return
	 */
	public PostDataResult postData(String sLogCode, String sType,
			String sPostClientTime, String sPostProcessTime, String sCardCode,
			JSONObject jsonObject) {

		PostDataResult postDataResult = new PostDataResult();

		if (postDataResult.upFlagTrue()) {

			// 体重
			if (sType.equals("WeightDataV1")) {

				WeightInput iPostDataInput = new WeightInput();
				initDate(iPostDataInput, sPostClientTime, sCardCode,
						sPostProcessTime);

				iPostDataInput.setWeight(jsonObject.getBigDecimal("weight"));

				postDataResult = new ApiWeight().toPost(iPostDataInput,
						sLogCode, "");

			}
			// 血氧
			else if (sType.equals("Spo2DataV1")) {

				OxygenInput iPostDataInput = new OxygenInput();
				initDate(iPostDataInput, sPostClientTime, sCardCode,
						sPostProcessTime);

				iPostDataInput.setDataHeart(jsonObject.getBigDecimal("pulse"));
				iPostDataInput.setDataOxygen(jsonObject.getBigDecimal("oxygen"));
				postDataResult = new ApiOxygen().toPost(iPostDataInput,
						sLogCode, "");

			} else if (sType.equals("BloodPressureV1")) {

				PressureInput iPostDataInput = new PressureInput();
				initDate(iPostDataInput, sPostClientTime, sCardCode,
						sPostProcessTime);

				iPostDataInput.setDataHeart(jsonObject.getBigDecimal("pulse"));
				iPostDataInput.setDataLower(jsonObject.getBigDecimal("diastolicpressure"));
				iPostDataInput.setDataUpper(jsonObject.getBigDecimal("systolicpressure"));
				postDataResult = new ApiPressure().toPost(iPostDataInput,
						sLogCode, "");

			}else {

				postDataResult.inErrorMessage(965805110, sType);

			}

		}

		return postDataResult;

	}

	private void initDate(IPostDataInput iPostDataInput,
			String sPostClientTime, String sPostDecviceSerial,
			String sPostProcessTime) {

		iPostDataInput.setPostClientTime(sPostClientTime);
		iPostDataInput.setPostDecviceSerial(sPostDecviceSerial);
		iPostDataInput.setPostProcessTime(sPostProcessTime);
		iPostDataInput.setPostType("card");
	}

}