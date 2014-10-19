package com.srnpr.yeshospital.model;

import org.apache.commons.lang.StringUtils;

import com.srnpr.yeshospital.api.model.PressureInput;
import com.srnpr.yeshospital.face.IPostDataApi;
import com.srnpr.yeshospital.face.IPostDataInput;
import com.srnpr.zapcom.baseface.IBaseInput;
import com.srnpr.zapcom.baseface.IBaseResult;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.RootApi;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webapi.RootApiForManage;
import com.srnpr.zapweb.webmodel.MWebResult;

public abstract class PostDataApi<TResult extends IBaseResult, TInput extends IBaseInput>
		extends RootApiForManage<TResult, TInput> implements
		IPostDataApi<TResult, TInput> {

	public TResult Process(TInput inputParam, MDataMap mRequestMap) {

		return upResult(inputParam, "", getManageCode());
	}

	public TResult upResult(TInput tInput, String sLogCode, String sManageCode) {

		return toPost(tInput, "", getManageCode());

	}

	/**
	 * 检查并且初始化
	 * 
	 * @param iPostDataInput
	 * @return
	 */
	public MWebResult checkAndInit(IPostDataInput iPostDataInput) {
		MWebResult mWebResult = new MWebResult();

		MDataMap mDeviceMap = DbUp.upTable("yh_device_info").one(
				"decive_series", iPostDataInput.getPostDecviceSerial());

		if (mWebResult.upFlagTrue()) {
			if (!(mDeviceMap != null && mDeviceMap.size() > 0)) {
				mWebResult.inErrorMessage(965805101,
						iPostDataInput.getPostDecviceSerial());
			}
		}

		if (mWebResult.upFlagTrue()) {
			if (!StringUtils.equals(mDeviceMap.get("flag_active"), "1")) {
				mWebResult.inErrorMessage(965805102,
						iPostDataInput.getPostDecviceSerial());
			}
		}

		if (mWebResult.upFlagTrue()) {

			MDataMap mMemberMap = DbUp.upTable("yh_member_device").one(
					"decive_code", mDeviceMap.get("decive_code"));

			if (mMemberMap != null && mMemberMap.size() > 0) {

				memberCode = mMemberMap.get("member_code");
			} else {
				mWebResult.inErrorMessage(965805103,
						iPostDataInput.getPostDecviceSerial());
			}

		}

		return mWebResult;
	}

	private String memberCode = "";

	public String upMemberCode() {
		return memberCode;
	};

}
