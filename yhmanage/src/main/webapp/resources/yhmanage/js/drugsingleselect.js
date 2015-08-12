zapadmin_single.result = function(sId, sValues, sTexts) {

	// alert(sValues);
	$('#' + sId).val(sValues);
	$('#' + sId + "_show_text").val(sTexts);

	zapjs.f.window_close(sId + 'zapadmin_single_showbox');

	zapadmin_single.show_text(sId);

	if (sValues.substring(0, 2) == "DD") {
		zapjs.zw.api_call('com_srnpr_yeshospital_api_app_QueryDrug', {
			keyWord : sValues,
			drugCode : sValues
		}, drugsingleselect);
	}

};

function drugsingleselect(oResult) {

	if (oResult.pageData.length > 0) {

		var oThis = oResult.pageData[0];
		// console.log(oResult);
		$('#zw_f_drug_name').val(oThis.drug_name);
		$('#zw_f_drug_unit').val(oThis.drug_unit);
		$('#zw_f_drug_usage').val(oThis.drug_usage);
		$('#zw_f_manufacturer').val(oThis.manufacturer);
		$('#zw_f_drug_dose').val(oThis.drug_dose);
		$('#zw_f_drug_single').val(oThis.drug_single);
		$('#zw_f_drug_source').val(oThis.drug_source);

	}

};