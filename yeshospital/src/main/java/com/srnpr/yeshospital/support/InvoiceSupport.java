package com.srnpr.yeshospital.support;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;

public class InvoiceSupport extends BaseClass {

	public void refreshInvoiceDetail(String sDetailCode) {

		MDataMap mDataMap = DbUp
				.upTable("yh_tour_invoice")
				.oneWhere(
						" ifnull(sum(money_all),0) as money_all,ifnull(sum(money_person),0) as money_person,ifnull(replace(group_concat(img_invoice),',','|'),'') as img_invoice",
						"", "", "order_detail_code", sDetailCode);

		if (mDataMap != null && mDataMap.size() > 0) {

			mDataMap.put("detail_code", sDetailCode);

			DbUp.upTable("yh_tour_order_detail").dataUpdate(mDataMap,
					"money_all,money_person,img_invoice", "detail_code");

		}

	}
}
