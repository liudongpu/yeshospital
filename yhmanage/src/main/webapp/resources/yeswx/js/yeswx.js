var yeswx = {

	c : {

		cookie_user : 'yeswx_cookie_user'

	},
	temp : {
		report_width : 0,

		last_msg : 0
	},

	init_base : function() {
		zapapi.c.api_url = "../../jsonapi/";
	},

	init_check : function() {

		var sCookie = zapfunc.f.cookie(yeswx.c.cookie_user);

		zapfunc.d(sCookie);

	},
	tab_select : function(iIndex, sTab) {
		var iCount = parseInt($('#yeswx_tab_count_' + sTab).val());

		for (var i = 1; i < iCount + 1; i++) {
			if (i == iIndex) {
				$('#yeswx_tab_nav_' + sTab + '_' + i).addClass(
						'weui_bar_item_on');
				$('#yeswx_tab_item_' + sTab + '_' + i).show();
			} else {
				$('#yeswx_tab_nav_' + sTab + '_' + i).removeClass(
						'weui_bar_item_on');
				$('#yeswx_tab_item_' + sTab + '_' + i).hide();
			}
		}

	},

	wx_close : function() {

		WeixinJSBridge.invoke("closeWindow");
	},

	wx_bind_check : function() {
		zapapi.api_call('com_srnpr_yeshospital_api_wx_BindCheck', {
			cardCode : $('#wx_bind_card_code').val(),
			userName : $('#wx_bind_user_name').val(),
			bindToken : $('#wx_bind_bind_token').val()
		}, yeswx.wx_bind_check_success);
	},
	wx_bind_check_success : function(oResult) {

		$('#wx_bind_member').hide();
		$('#wx_bind_create').show();
		$('#wx_bind_member_uid').val(oResult.memberUid);

	},

	wx_bind_delete : function(sSibCode, sMemberCode) {

		if (confirm("确认要解除绑定关系么？")) {
			zapapi.api_call('com_srnpr_yeshospital_api_wx_BindDelete', {
				sibCode : sSibCode,
				memberCode : sMemberCode
			}, yeswx.wx_bind_delete_success);
		}
	},
	wx_bind_delete_success : function(oResult) {

		location.href = location.href;

	},

	wx_bind_verify : function() {
		// $('#wx_bind_send_link').button( "disable" );

		if (yeswx.temp.last_msg < 5) {
			zapapi.api_call('com_srnpr_yeshospital_api_wx_VerifyApi', {
				mobilePhone : $('#wx_bind_sib_hone').val()
			}, yeswx.wx_bind_verify_success);
		}
	},
	wx_bind_verify_success : function() {
		yeswx.temp.last_msg = 60;
		$('#wx_bind_send_link').addClass("weui_btn_disabled ");
		yeswx.wx_verify_check();
	},

	wx_verify_check : function() {
		if (yeswx.temp.last_msg > 0) {
			$('#wx_bind_send_link').text(yeswx.temp.last_msg + '秒后重试');
			yeswx.temp.last_msg = yeswx.temp.last_msg - 1;
			setTimeout(function() {
				yeswx.wx_verify_check();
			}, 1000);

		} else {
			$('#wx_bind_send_link').removeClass("weui_btn_disabled ");
			$('#wx_bind_send_link').text('发送验证码');
		}

	},

	wx_bind_submit : function() {
		zapapi.api_call('com_srnpr_yeshospital_api_wx_BindSubmit', {
			relationCode : $('#wx_bind_sib_relation').val(),
			cardCode : $('#wx_bind_card_code').val(),
			userName : $('#wx_bind_user_name').val(),
			myName : $('#wx_bind_sib_name').val(),
			mobilePhone : $('#wx_bind_sib_hone').val(),
			verifyCode : $('#wx_bind_sib_number').val(),
			memberUid : $('#wx_bind_member_uid').val(),
			bindToken : $('#wx_bind_bind_token').val()
		}, yeswx.wx_bind_submit_success);
	},
	wx_bind_submit_success : function(oResult) {
		location.href = "member_info?code=" + oResult.linkCode;
	},

	visit_order_submit : function(iIndex) {
		zapapi.api_call('com_srnpr_yeshospital_api_visit_VisitOrder', {
			memberName : $('#member_name_' + iIndex).val(),
			memberAge : $('#member_age_' + iIndex).val(),
			roomName : $('#room_name_' + iIndex).val(),
			memberPhone : $('#member_phone_' + iIndex).val(),
			cardCode : $('#card_code_' + iIndex).val(),
			visitTime : $('#visit_date_' + iIndex).val() + ' '
					+ $('#visit_time_' + iIndex).find("option:selected").text()
					+ ":00",
			visitNote : $('#visit_note_' + iIndex).val(),
			memberSex : $('#member_sex_' + iIndex).val(),
			sibName : $('#sib_name_' + iIndex).val(),
			relationDeep : $('#relation_deep_' + iIndex).val(),
			sibPhone : $('#sib_phone_' + iIndex).val(),
			// memberName : $('#member_name_' + iIndex).val(),
			hospitalCode : $('#hospital_code_' + iIndex).val(),
			sibCode : $('#sib_code_' + iIndex).val(),
			bindToken : $('#bind_token_' + iIndex).val(),
			sourceSystem : 'wx'
		}, yeswx.visit_order_submit_success);
	},

	visit_order_submit_success : function(oResult) {
		/*
		 * $('#visit_order_popup p').html('<p>您的预约已成功，如果有问题请与客服人员联系。</p>');
		 * $('#visit_order_popup').popup( "open", {positionTo: "window"} );
		 */
		$('#vo_main_page').hide();
		$('#vo_main_success').show();

	},

	report_show : function(sTarget, sMemberCode) {
		if (yeswx.temp.report_width == 0) {
			yeswx.temp.report_width = $('#report_' + sMemberCode).width();
		} else {
			$('#report_' + sMemberCode).width(yeswx.temp.report_width);
		}

		zapapi.api_call(sTarget, {
			memberCode : sMemberCode
		}, function(oResult) {

			// 去掉版权
			oResult.credits = {
				enabled : false
			};

			oResult.tooltip = {};
			oResult.tooltip.dateTimeLabelFormats = {
				millisecond : "%y-%m-%d %H:%M:%S",
				second : "%y-%m-%d %H:%M:%S",
				minute : "%Y-%m-%d %H:%M",
				hour : "%Y-%m-%d %H:%M",
				day : "%Y-%m-%d",
				week : "%Y-%m-%d",
				month : "%B %Y",
				year : "%Y"
			};

			oResult.xAxis.dateTimeLabelFormats = {
				millisecond : '%H:%M:%S',
				second : '%H:%M:%S',
				minute : '%H:%M',
				hour : '%m-%d',
				day : '%m-%d',
				week : '%m-%d',
				month : '%Y-%m',
				year : '%Y'
			};

			$('#report_' + sMemberCode).highcharts(oResult);
		});
	},
	report_elec : function(sTarget, sMemberCode) {

		zapapi
				.api_call(
						"com_srnpr_yeshospital_api_wx_ReportElectrocardiogram",
						{
							memberCode : sMemberCode
						},
						function(oResult) {
							// $('#report_' + sMemberCode).highcharts(oResult);

							if (oResult.items.length > 0) {

								var aHtml = [];

								for ( var i in oResult.items) {
									var ot = oResult.items[i];
									aHtml
											.push('<div class="wxcss_data_elec_date"><span class="wxcss_base_circle"><span class="wxcss_base_circle_small"></span></span>'
													+ ot["dateTime"] + '</div>');
									aHtml.push('<div>' + ot["dataMessage"]
											+ '<br/><br/></div>');
									aHtml.push('<div><a><img src="'
											+ ot["imageUrl"] + '"/></a></div>');
									aHtml
											.push('<div class="wxcss_show_split"></div>');
								}

								$('#report_' + sMemberCode)
										.html(aHtml.join(''));
							}

						});
	}
};

yeswx.init_base();

$(function() {

	yeswx.init_check();

});