package com.srnpr.yeshospital.api.app;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.RootApi;
import com.srnpr.zapweb.webapi.RootPageDataResult;
import com.srnpr.zapweb.websupport.DataApiSupport;

public class QueryIcd extends RootApi<RootPageDataResult, QueryIcdInput> {

	public RootPageDataResult Process(QueryIcdInput inputParam,
			MDataMap mRequestMap) {

		MDataMap mDataMap = new MDataMap();

		ArrayList<String> aWhere = new ArrayList<String>();

		 if (StringUtils.isNotBlank(inputParam.getKeyWord())) {
			mDataMap.put("keyword", "%" + inputParam.getKeyWord() + "%");

			aWhere.add("irritability_code like :keyword or irritability_title like :keyword ");
		}

		

		return new DataApiSupport()
				.upData("yh_irritability_info",
						"irritability_code,irritability_title",
						"irritability_code", aWhere, mDataMap, 0, 10);

	}

}
