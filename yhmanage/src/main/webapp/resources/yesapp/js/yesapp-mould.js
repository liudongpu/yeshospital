var yesapp_mould = {

	temp : {
		// 费用编号
		mcode : ''
	},

	init_mould_order : function() {
		yesapp_mould.refresh_mould_detail(3, '', 0);
	},

	mould_add : function() {

		yesapp_mould.refresh_mould_detail(1, yesapp_mould.temp.mcode, $(
				'#yesapp_vo_add').val());
		$('#popup_nested').popup("close");

	},
	mould_edit : function() {
		yesapp_mould.refresh_mould_detail(5, yesapp_mould.temp.mcode, $(
				'#yesapp_vo_number').val());
		$('#popup_money').popup("close");
	},

	mould_del : function() {
		yesapp_mould.refresh_mould_detail(4, yesapp_mould.temp.mcode, $(
				'#yesapp_vo_number').val());
		$('#popup_money').popup("close");
	},

	change_number : function(sCode, sNumber) {

		/*
		 * $('#popup_money').popup("open", { positionTo : "window" });
		 */

		yesapp_mould.temp.mcode = sCode;

		$('#yesapp_vo_number').val(sNumber);
		$('#popup_money').popup("open", {
			positionTo : "window"
		});

		// yesapp_mould.refresh_mould_detail(1, sCode, 1);

	},

	refresh_mould_detail : function(sType, sMoneyCode, iNumber) {
		yesapp.api_call('mould_list', {
			parentCode : $('#yesapp_vo_mould_code').val(),
			modleCode : sMoneyCode,
			optType : sType,
			mouldContent : iNumber
		}, yesapp_mould.refresh_mould_detail_success);
	},
	refresh_mould_detail_success : function(oData) {
		var aHtml = [];

		// var sOrderCode = $('#yesapp_ts_tour_code').val();

		for ( var i in oData.pageData) {

			var o = oData.pageData[i];

			// if (o["init_code"] == "") {
			var sJs = 'yesapp_mould.change_number(\'' + o['model_code']
					+ '\',\'' + o["mould_content"] + '\')';

			aHtml.push('<li><a href="javascript:' + sJs
					+ '"><p   class="yb_space">'

					+ o['mould_content'] + '</p>'

					+ '</a></li>');
			// } else {
			// aHtml.push('<li><p class="yb_space yb_color_focus">'

			// + o['mould_content'] + ' </p>'

			// + '');
			// }

		}

		$('#yesapp_vo_table').html(aHtml);
		$('#yesapp_vo_table').listview('refresh');
	}

};
