var yesapp_my = {
	my_password_change : function() {

		yesapp.api_call('my_password', {
			oldPassword : $('#mp_id_oldp').val(),
			newPassword : $('#mp_id_newp').val(),
			rePassword : $('#mp_id_rep').val()
		}, yesapp_my.my_password_change_success);

	},
	my_password_change_success : function(oData) {
		zmapi.m.toast('操作成功');
	},
	my_suggestion_submit : function() {

		yesapp.api_call('my_suggestion', {
			suggestionInfo : $('#ms_id_suggestion').val()
		}, yesapp_my.my_password_change_success);

	},
	my_suggestion_submit_success : function(oData) {
		zmapi.m.toast('操作成功');
	}

};
