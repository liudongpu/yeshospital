package com.srnpr.yeshospital.func;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webdo.WebConst;
import com.srnpr.zapweb.webfunc.FuncEdit;
import com.srnpr.zapweb.webfunc.RootFunc;
import com.srnpr.zapweb.webmodel.MWebResult;

public class FuncEditOrderDrug extends RootFunc {

	public MWebResult funcDo(String sOperateUid, MDataMap mDataMap) {

		MWebResult mWebResult = new MWebResult();

		if (mWebResult.upFlagTrue()) {
			mWebResult = new FuncEdit().funcDo(sOperateUid, mDataMap);
		}

		if (mWebResult.upFlagTrue()) {
			MDataMap mAddMaps = mDataMap.upSubMap(WebConst.CONST_WEB_FIELD_NAME);

			MDataMap map = DbUp.upTable("yh_tour_order_drug").one("uid", mAddMaps.get("uid"));
			if (map != null && map.size() > 0) {

				int iCount = DbUp.upTable("yh_tour_order_drug").count("member_code", map.get("member_code"),
						"tour_code", map.get("tour_code"),"flag_buy","1");
				
				DbUp.upTable("yh_tour_order_detail")
						.dataUpdate(new MDataMap("tour_code", map.get("tour_code"), "member_code",
								map.get("member_code"), "flag_buy", iCount > 0 ? "1" : "0"), "flag_buy",
						"tour_code,member_code")

				;
			}

		}

		return mWebResult;
	}

}
