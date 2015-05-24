package com.srnpr.yeshospital.api.app;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.RootApi;
import com.srnpr.zapweb.webapi.RootPageDataResult;
import com.srnpr.zapweb.websupport.DataApiSupport;

public class QueryDrug extends RootApi<RootPageDataResult, QueryDrugInput> {

	public RootPageDataResult Process(QueryDrugInput inputParam,
			MDataMap mRequestMap) {
		
		
		MDataMap mDataMap = new MDataMap();

		ArrayList<String> aWhere = new ArrayList<String>();

		if (StringUtils.isNotBlank(inputParam.getKeyWord())) {
			mDataMap.put("keyword", "%" + inputParam.getKeyWord() + "%");

			aWhere.add("drug_name like :keyword or spell_info like :keyword ");
		}
		
		aWhere.add(" flag_enable=1 ");
		
		
		

		return new DataApiSupport().upData("yh_drug_info",
				"drug_code,drug_name,manufacturer,drug_usage", "spell_info",
				aWhere, mDataMap, 0, 10);
		
		
	}

}
