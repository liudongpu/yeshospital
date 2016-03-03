var yesapp_visit = {

	init_visit_order : function() {
		yesapp_visit.refresh_visit_detail(3, '', 0);
	},

	change_number : function(sCode) {

		/*
		 * $('#popup_money').popup("open", { positionTo : "window" });
		 */
		$('#popup_nested').popup("close");

		yesapp_visit.refresh_visit_detail(1, sCode, 1);

	},

	refresh_visit_detail : function(sType, sMoneyCode, iNumber) {
		yesapp.api_call('visit_detail', {
			visitOrderCode : $('#yesapp_vo_visit_code').val(),
			moneyCode : sMoneyCode,
			optCode : sType,
			moneyNumber : iNumber
		}, yesapp_visit.refresh_visit_detail_success);
	},
	refresh_visit_detail_success : function(oData) {
		var aHtml = [];

		// var sOrderCode = $('#yesapp_ts_tour_code').val();

		
		for ( var i in oData.pageData) {

			var o = oData.pageData[i];

			aHtml
					.push('<li><a href="javascript:yesapp_visit.refresh_visit_detail(\''
							+ i
							+ '\')"><h2>'

							+ o['money_name']
							+ '</h2><p>'
							+ o["visit_money"]
							+ '/'
							+ o["visit_unit"]
							+ '&nbsp;&nbsp;数量:'
							+ o["money_number"]
							+ '<br/>'

							+ '</p></a><a href="javascript:yesapp_tour.tour_member_option(\''
							+ i + '\')" >操作</a></li>');

		}

		$('#yesapp_vo_table').html(aHtml);
		$('#yesapp_vo_table').listview('refresh');
	}

};
