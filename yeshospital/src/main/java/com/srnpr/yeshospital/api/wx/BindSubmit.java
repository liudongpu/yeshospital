package com.srnpr.yeshospital.api.wx;

import org.apache.commons.lang.StringUtils;

import com.srnpr.yeshospital.support.VerifySupport;
import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.RootApi;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.helper.WebHelper;
import com.srnpr.zapweb.websupport.OauthSupport;

public class BindSubmit extends RootApi<BindSubmitResult, BindSubmitInput> {

	public BindSubmitResult Process(BindSubmitInput inputParam, MDataMap mRequestMap) {
		BindSubmitResult result = new BindSubmitResult();

		if (result.upFlagTrue()) {

			if (StringUtils.isBlank(inputParam.getMemberUid())) {
				MDataMap map = DbUp.upTable("yh_member_extend_geracomium").one("card_code", inputParam.getCardCode(),
						"member_name", inputParam.getUserName());

				if (map != null && map.size() > 0) {
					inputParam.setMemberUid(map.get("uid"));
				} else {
					result.inErrorMessage(965805281);
				}
			}
		}

		if (result.upFlagTrue()) {

			result.inOtherResult(
					new VerifySupport().verifyCheck("", inputParam.getMobilePhone(), inputParam.getVerifyCode()));

		}

		if (result.upFlagTrue()) {

			MDataMap mDataMap = DbUp.upTable("yh_wx_bind").one("bind_token", inputParam.getBindToken());

			if (mDataMap != null && mDataMap.size() > 0) {

				result.setLinkCode("yhbt_" + mDataMap.get("bind_token"));

				String sSibCode = mDataMap.get("sib_code");

				// 判断是否已创建了亲属的基本信息
				if (StringUtils.isBlank(sSibCode)) {
					sSibCode = WebHelper.upCode("SC");

					DbUp.upTable("yh_member_extend_sib").insert("member_code", sSibCode, "create_time",
							FormatHelper.upDateTime(), "member_name", inputParam.getMyName(), "mobile_phone",
							inputParam.getMobilePhone());

					OauthSupport oauthSupport = new OauthSupport();

					String sOauthToken = oauthSupport.insertOauth(sSibCode, "", inputParam.getMobilePhone(), "3600d",
							"");

					mDataMap.put("sib_code", sSibCode);
					mDataMap.put("access_token", sOauthToken);
					DbUp.upTable("yh_wx_bind").dataUpdate(mDataMap, "sib_code,access_token", "zid");
				}

				MDataMap mMemberMap = DbUp.upTable("yh_member_extend_geracomium").one("uid", inputParam.getMemberUid());
				String sUserCode = mMemberMap.get("member_code");

				if (DbUp.upTable("yh_sib_info").count("sib_code", sSibCode, "member_code", sUserCode) == 0) {

					MDataMap mSibMap = new MDataMap();

					mSibMap.inAllValues("sib_code", sSibCode, "member_code", sUserCode, "sib_name",
							inputParam.getMyName(), "mobile_phone", inputParam.getMobilePhone(), "relation_deep",
							inputParam.getRelationCode(), "create_time", FormatHelper.upDateTime());
					DbUp.upTable("yh_sib_info").dataInsert(mSibMap);

				} else {
					result.inErrorMessage(965805282);
				}

			}

		}

		return result;
	}
}
