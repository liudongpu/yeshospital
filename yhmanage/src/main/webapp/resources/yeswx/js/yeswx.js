var yeswx = {

	c : {

		cookie_user : 'yeswx_cookie_user'

	},
	
	
	

	init_check : function() {

		var sCookie = zapfunc.f.cookie(yeswx.c.cookie_user);

		zapfunc.d(sCookie);

	},
	
	
	
	
	wx_bind_submit:function()
	{
		zapapi.api_call('',);
	}
	

};

$(function() {

	yeswx.init_check();

});