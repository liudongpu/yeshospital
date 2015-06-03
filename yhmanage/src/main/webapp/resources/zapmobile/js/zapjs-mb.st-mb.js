/*
 * zapjs.st

 */

zapjs.st = {

	config : {

	},
	temp : {

		current : ''
	},

	change_item : function(sId) {
		zapjs.st.temp.current = sId;

		var sText = $('#zw_st_span_' + zapjs.st.temp.current).text();
		$('#zapjs_st_id_info').val(sText);

		zmjs.ui.dialog_open('zapjs_st_id_dialog');
	},
	save_change : function() {
		var sVal = $('#zapjs_st_id_info').val();

		$('#zw_st_span_' + zapjs.st.temp.current).text(sVal);

		zapjs.st.post_change(zapjs.st.temp.current, sVal);

		zmjs.ui.dialog_close('zapjs_st_id_dialog');

	},

	change_select : function(sId) {
		zapjs.st.temp.current = sId;

		var sText = $('#zw_st_select_' + zapjs.st.temp.current).val();
		zapjs.st.post_change(zapjs.st.temp.current, sText);
	},

	post_change : function(sField, sValue) {

		yesapp.api_call($('#zapjs_st_id_post_target').val(), {
			dataCode : $('#zapjs_st_id_post_code').val(),
			changeInfos : [ {
				"filedName" : sField,
				"fieldValue" : sValue
			} ]
		}, zapjs.st.post_success);

	},
	post_success : function(oData) {
		// zmapi.m.toast('操作成功');
	}

};

if (typeof define === "function" && define.amd) {
	define("zapjs/zapjs.st", function() {
		return zapjs.st;
	});
}
