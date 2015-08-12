package com.srnpr.yeshospital.func;

import com.srnpr.yeshospital.support.InvoiceSupport;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webfunc.FuncAdd;
import com.srnpr.zapweb.webfunc.RootFunc;
import com.srnpr.zapweb.webmodel.MWebResult;

public class FuncInvoiceAdd extends RootFunc {

	public MWebResult funcDo(String sOperateUid, MDataMap mDataMap) {

		MWebResult mWebResult = new MWebResult();

		MDataMap mAddMaps = mDataMap.upSubMap(WebConst.CONST_WEB_FIELD_NAME);

		String sOrderDetail = mAddMaps.get("order_detail_code");

		if (mWebResult.upFlagTrue()) {

			MDataMap mDetailMap = DbUp.upTable("yh_tour_order_detail").one(
					"detail_code", sOrderDetail);

			mDataMap.inAllValues(WebConst.CONST_WEB_FIELD_NAME + "tour_code",
					mDetailMap.get("tour_code"), WebConst.CONST_WEB_FIELD_NAME
							+ "member_code", mDetailMap.get("member_code"));

			mWebResult = new FuncAdd().funcDo(sOperateUid, mDataMap);

		}

		if (mWebResult.upFlagTrue()) {

			new InvoiceSupport().refreshInvoiceDetail(sOrderDetail);
		}

		return mWebResult;
	}

}
