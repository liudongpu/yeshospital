package com.srnpr.yeshospital.api.app;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.RootApi;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webapi.RootApiForToken;
import com.srnpr.zapweb.webapi.RootPageDataResult;
import com.srnpr.zapweb.websupport.DataApiSupport;

public class MemberPic extends RootApiForToken<RootPageDataResult, MemberPicInput> {

	public RootPageDataResult Process(MemberPicInput inputParam, MDataMap mRequestMap) {

		if (StringUtils.isNotBlank(inputParam.getPicImg())) {

			DbUp.upTable("yh_member_pic").insert("member_code", inputParam.getMemberCode(), "create_user",
					getUserCode(), "create_time", FormatHelper.upDateTime(), "pic_url", inputParam.getPicImg());
		}

		if (StringUtils.isNotBlank(inputParam.getDeleteUid())) {
			DbUp.upTable("yh_member_pic").delete("uid", inputParam.getDeleteUid());
		}

		RootPageDataResult result = new DataApiSupport().upData("yh_member_pic", "uid,member_code,pic_url",
				"create_time", "", new MDataMap("member_code", inputParam.getMemberCode()), 0, 0);

		return result;
	}

}
