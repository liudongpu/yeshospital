zapadmin_single.result = function(sId, sValues, sTexts) {

	// alert(sValues);
	$('#' + sId).val(sValues);
	$('#' + sId + "_show_text").val(sTexts);

	zapjs.f.window_close(sId + 'zapadmin_single_showbox');

	zapadmin_single.show_text(sId);

	$('#span_doctor_select_member').html(
			'<a href="page_book_v_yh_member_extend_geracomium?zw_f_member_code='
					+ sValues + '" target="_blank">查看' + sTexts + '信息</a>');

	zapjs.zw.api_call('com_srnpr_yeshospital_api_member_MemberAgreeAuto', {
		memberCode : sValues
	}, doctorselectmember);

};

function doctorselectmember(oResult) {

	// console.log(oResult);
	$('#zw_f_f_hd_geracomium_code').val(oResult.geracomiumCode);
	$('#zw_f_agree_info').val(oResult.agreeInfo);

};
