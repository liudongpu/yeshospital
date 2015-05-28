package com.srnpr.yeshospital.api.app;

import org.apache.commons.lang.StringUtils;

import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topapi.RootApi;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webapi.RootApiForToken;

/**
 * 根据卡号获取用户信息
 * 
 * @author srnpr
 *
 */
public class MemberLast extends RootApiForToken<MemberLastResult, MemberLastInput> {

	public MemberLastResult Process(MemberLastInput inputParam,
			MDataMap mRequestMap) {

		MemberLastResult result = new MemberLastResult();

		// 取出卡号对应的用户编号
		if (result.upFlagTrue()) {

			Object object = DbUp
					.upTable("yh_member_extend_geracomium")
					.dataGet("member_code", "",
							new MDataMap("post_card", inputParam.getPostCard()));

			if (object != null&&StringUtils.isNotBlank(object.toString())) {
				result.setMemberCode(object.toString());
			} else {
				result.inErrorMessage(965806001, inputParam.getPostCard());
			}

		}

		if (result.upFlagTrue()) {

			if (StringUtils.isNotBlank(inputParam.getOrderCode())) {
				result.setOrderCode(inputParam.getOrderCode());
			} else {

				String sGCode = DbUp
						.upTable("yh_member_extend_geracomium")
						.dataGet("geracomium_code", "",
								new MDataMap("member_code", result.getMemberCode()))
						.toString();

				MDataMap mTourMap = DbUp
						.upTable("yh_tour_order_info")
						.oneWhere(
								"",
								"-zid",
								"order_status='46580001000500020001' and geracomium_code=:geracomium_code",
								"geracomium_code", sGCode);

				if (mTourMap != null && mTourMap.size() > 0) {
					result.setOrderCode(mTourMap.get("tour_code"));
				} else {
					result.inErrorMessage(965806002);
				}

			}
		}

		return result;
	}

}
