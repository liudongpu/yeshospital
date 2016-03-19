var yesapp_visit = {

	temp : {
		// 费用编号
		mcode : ''
	},

	init_visit_order : function() {
		yesapp_visit.refresh_visit_detail(3, '', 0);
	},

	visit_dialog : function() {

		zmapi.m.setprefs(zmapi.c.event.sub_func,
				"visit-order:yesapp_visit.visit_select()");

		zmjs.page.open_page('sub-visit?u_hospital_code='
				+ $('#yesapp_vo_hospital_code').val());

	},
	visit_select : function() {
		zmapi.m.getprefs(zmapi.c.event.sub_select, function(sSelect) {

			yesapp_visit.refresh_visit_detail(1, sSelect, 1);

		});
	},

	visit_add : function(sCode) {

		$('#popup_nested').popup("close");

		yesapp_visit.refresh_visit_detail(1, sCode, 1);

	},
	visit_edit : function() {
		yesapp_visit.refresh_visit_detail(5, yesapp_visit.temp.mcode, $(
				'#yesapp_vo_number').val());
		$('#popup_money').popup("close");
	},

	visit_del : function() {
		yesapp_visit.refresh_visit_detail(4, yesapp_visit.temp.mcode, $(
				'#yesapp_vo_number').val());
		$('#popup_money').popup("close");
	},

	change_number : function(sCode, sNumber) {

		/*
		 * $('#popup_money').popup("open", { positionTo : "window" });
		 */

		yesapp_visit.temp.mcode = sCode;

		$('#yesapp_vo_number').val(sNumber);
		$('#popup_money').popup("open", {
			positionTo : "window"
		});

		// yesapp_visit.refresh_visit_detail(1, sCode, 1);

	},

	change_visit_status : function() {
		yesapp.api_call('visit_status', {
			visitOrderCode : $('#yesapp_vo_visit_code').val(),
			visitProcess : $('#yesapp_vo_process').val(),
			tourInfo : $('#yesapp_vo_tour').val(),
			agreeInfo : $('#yesapp_vo_agree').val()
		}, yesapp_visit.change_visit_status_success);
	},
	change_visit_status_success : function(oData) {
		zmapi.m.execjs("root.frame-main:yesapp_frame.init_frame_main()");
		zmjs.page.refresh_page();
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
					.push('<li><a href="javascript:yesapp_visit.change_number(\''
							+ o['visit_detail_code']
							+ '\',\''
							+ o["money_number"]
							+ '\')"><h2>'

							+ o['money_name']
							+ '</h2><p>'
							+ o["visit_money"]
							+ '/'
							+ o["visit_unit"]
							+ '&nbsp;&nbsp;数量:'
							+ o["money_number"]
							+ '<br/>'

							+ '</p></a><a href="javascript:yesapp_visit.change_number(\''
							+ o['visit_detail_code']
							+ '\',\''
							+ o["money_number"] + '\')" >操作</a></li>');

		}

		$('#yesapp_vo_table').html(aHtml);
		$('#yesapp_vo_table').listview('refresh');
	}

};
