package com.srnpr.yeshospital.api.app;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.RootApi;
import com.srnpr.zapweb.webapi.RootPageDataResult;
import com.srnpr.zapweb.websupport.DataApiSupport;

public class QueryDrug extends RootApi<RootPageDataResult, QueryDrugInput> {

	public RootPageDataResult Process(QueryDrugInput inputParam, MDataMap mRequestMap) {

		MDataMap mDataMap = new MDataMap();

		ArrayList<String> aWhere = new ArrayList<String>();

		// 如果传递有类型
		if (StringUtils.isNotBlank(inputParam.getDrugType())) {
			mDataMap.put("drug_type", inputParam.getDrugType());
			aWhere.add(" drug_type=:drug_type ");
		}

		// 如果传递有编号 则编号最优先处理
		if (StringUtils.isNotBlank(inputParam.getDrugCode())) {
			mDataMap.put("drug_code", inputParam.getDrugCode());
			aWhere.add(" drug_code=:drug_code ");
		}

		else if (StringUtils.isNotBlank(inputParam.getKeyWord())) {
			mDataMap.put("keyword", "%" + inputParam.getKeyWord() + "%");

			aWhere.add(" (drug_name like :keyword or spell_info like :keyword) ");
		}

		aWhere.add(" flag_enable=1 ");

		return new DataApiSupport().upData("yh_drug_info",
				"drug_code,drug_name,manufacturer,drug_usage,drug_unit,drug_dose,drug_single,drug_source,drug_price,product_name,drug_title",
				"spell_info", aWhere, mDataMap, 0, 10);

	}

}
