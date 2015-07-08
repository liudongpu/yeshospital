var yeswx = {

	c : {

		cookie_user : 'yeswx_cookie_user'

	},
	temp : {
		report_width : 0
	},

	init_base : function() {
		zapapi.c.api_url = "../../jsonapi/";
	},

	init_check : function() {

		var sCookie = zapfunc.f.cookie(yeswx.c.cookie_user);

		zapfunc.d(sCookie);

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
	wx_bind_verify : function() {
		// $('#wx_bind_send_link').button( "disable" );

		zapapi.api_call('com_srnpr_yeshospital_api_wx_VerifyApi', {
			mobilePhone : $('#wx_bind_sib_hone').val()
		}, yeswx.wx_bind_verify_success);
	},
	wx_bind_verify_success : function() {
		$('#wx_bind_send_link').button("disable");
	},
	wx_bind_submit : function() {
		zapapi.api_call('com_srnpr_yeshospital_api_wx_BindSubmit', {
			relationCode : $('#wx_bind_sib_relation').val(),
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

	report_show : function(sTarget, sMemberCode) {
		if (yeswx.temp.report_width == 0) {
			yeswx.temp.report_width = $('#report_' + sMemberCode).width();
		} else {
			$('#report_' + sMemberCode).width(yeswx.temp.report_width);
		}

		zapapi.api_call(sTarget, {
			memberCode : sMemberCode
		}, function(oResult) {
			$('#report_' + sMemberCode).highcharts(oResult);
		});
	},
	report_elec : function(sTarget, sMemberCode) {

		zapapi.api_call("com_srnpr_yeshospital_api_wx_ReportElectrocardiogram",
				{
					memberCode : sMemberCode
				}, function(oResult) {
					// $('#report_' + sMemberCode).highcharts(oResult);

					if (oResult.items.length > 0) {

						var aHtml = [];

						for ( var i in oResult.items) {
							var ot = oResult.items[i];
							aHtml.push('<div class="wxcss_data_elec_date"><span class="wxcss_base_circle"><span class="wxcss_base_circle_small"></span></span>'+ot["dateTime"]+'</div>');
							aHtml.push('<div><a><img src="'+ot["imageUrl"]+'"/></a></div>');
							aHtml.push('<div class="wxcss_show_split"></div>');
						}
						
						$('#report_' + sMemberCode).html(aHtml.join(''));
					}

				});
	}
};

yeswx.init_base();

$(function() {

	yeswx.init_check();

});