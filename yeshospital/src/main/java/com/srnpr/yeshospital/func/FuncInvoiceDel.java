package com.srnpr.yeshospital.func;

import com.srnpr.yeshospital.support.InvoiceSupport;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webfunc.FuncAdd;
import com.srnpr.zapweb.webfunc.FuncDelete;
import com.srnpr.zapweb.webfunc.RootFunc;
import com.srnpr.zapweb.webmodel.MWebResult;

public class FuncInvoiceDel extends RootFunc {

	public MWebResult funcDo(String sOperateUid, MDataMap mDataMap) {
		MWebResult mWebResult = new MWebResult();

		MDataMap mAddMaps = mDataMap.upSubMap(WebConst.CONST_WEB_FIELD_NAME);

		MDataMap mDetailMap = DbUp.upTable("yh_tour_invoice").one("uid",
				mAddMaps.get("uid"));

		if (mWebResult.upFlagTrue()) {
			mWebResult = new FuncDelete().funcDo(sOperateUid, mDataMap);

		}

		if (mWebResult.upFlagTrue()) {

			new InvoiceSupport().refreshInvoiceDetail(mDetailMap
					.get("order_detail_code"));
		}

		return mWebResult;
	}

}
