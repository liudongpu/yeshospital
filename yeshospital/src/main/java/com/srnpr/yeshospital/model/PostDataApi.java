package com.srnpr.yeshospital.model;

import org.apache.commons.lang.StringUtils;

import com.srnpr.yeshospital.api.model.PressureInput;
import com.srnpr.yeshospital.face.IPostDataApi;
import com.srnpr.yeshospital.face.IPostDataInput;
import com.srnpr.zapcom.baseface.IBaseInput;
import com.srnpr.zapcom.baseface.IBaseResult;
import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.RootApi;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.helper.WebHelper;
import com.srnpr.zapweb.webapi.RootApiForManage;
import com.srnpr.zapweb.webmodel.MWebResult;

public abstract class PostDataApi<TResult extends IBaseResult, TInput extends IBaseInput>
		extends RootApiForManage<TResult, TInput> implements
		IPostDataApi<TResult, TInput> {

	public TResult upResult(TInput tInput, String sLogCode, String sManageCode) {

		return toPost(tInput, sLogCode, sManageCode);

	}

	/**
	 * 检查并且初始化
	 * 
	 * @param iPostDataInput
	 * @return
	 */
	public MWebResult checkAndInit(IPostDataInput iPostDataInput) {
		MWebResult mWebResult = new MWebResult();

		// 判断如果操作类型时刷卡类
		if (iPostDataInput.getPostType().equals("card")) {

			MDataMap mMemberMap = DbUp.upTable("yh_member_extend_geracomium")
					.oneWhere("", "", "lpad(post_card,20,'0')=:post_card",
							"post_card", iPostDataInput.getPostDecviceSerial());

			if (mMemberMap != null && mMemberMap.size() > 0) {

				memberCode = mMemberMap.get("member_code");
			} else {
				mWebResult.inErrorMessage(965805103,
						iPostDataInput.getPostDecviceSerial());
			}

		} else {

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
		}

		return mWebResult;
	}

	/**
	 * 更新报告信息
	 * 
	 * @param sInfoField
	 * @param sInfoValue
	 * @param sUpdateTimeField
	 * @return
	 */
	public MWebResult updateReport(String sInfoField, String sInfoValue,
			String sUpdateTimeField) {

		MDataMap mUpdateMap = DbUp.upTable("yh_report_info").one("member_code",
				upMemberCode());

		if (mUpdateMap == null || mUpdateMap.size() == 0) {
			mUpdateMap = new MDataMap();
			mUpdateMap.inAllValues("report_code", WebHelper.upCode("RC"),
					"member_code", upMemberCode());

			DbUp.upTable("yh_report_info").dataInsert(mUpdateMap);
		} else {
			mUpdateMap.inAllValues("member_code", upMemberCode());
		}

		mUpdateMap.inAllValues(sInfoField, sInfoValue, sUpdateTimeField,
				FormatHelper.upDateTime(), "last_update_time",
				FormatHelper.upDateTime());

		DbUp.upTable("yh_report_info").dataUpdate(mUpdateMap,
				sInfoField + "," + sUpdateTimeField + ",last_update_time",
				"member_code");

		return new MWebResult();

	}

	private String memberCode = "";

	public String upMemberCode() {
		return memberCode;
	};

}
