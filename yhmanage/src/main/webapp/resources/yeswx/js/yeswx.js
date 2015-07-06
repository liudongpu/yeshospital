var yeswx = {

	c : {

		cookie_user : 'yeswx_cookie_user'

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
		zapapi.api_call('com_srnpr_yeshospital_api_wx_VerifyApi', {
			mobilePhone : $('#wx_bind_sib_hone').val()
		}, yeswx.wx_bind_verify_success);
	},
	wx_bind_verify_success : function() {

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
	wx_bind_submit_success:function(oResult)
	{
		location.href="member_info?code="+oResult.linkCode;
	}

};

yeswx.init_base();

$(function() {

	yeswx.init_check();

});