package com.srnpr.yeshospital.api.postdata;

import com.srnpr.yeshospital.api.result.PostSetResult;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.RootApi;
import com.srnpr.zapcom.topapi.RootInput;

/**
 * 上传设置
 * 
 * @author srnpr
 *
 */
public class ApiPostSet extends RootApi<PostSetResult, RootInput> {

	public PostSetResult Process(RootInput inputParam, MDataMap mRequestMap) {

		return new PostSetResult();
	}

}
