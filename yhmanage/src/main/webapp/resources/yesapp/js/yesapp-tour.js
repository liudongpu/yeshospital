var yesapp_tour = {

	temp : {
		// 最近一次搜索词
		last_search : '',
		// 临时保存交换变量
		obj_temp : null
	},

	init_tour_select : function() {
		yesapp_tour.refresh_tour_select();
	},

	refresh_tour_select : function() {

		yesapp.api_call('query_tour_detail', {
			orderCode : $('#yesapp_ts_tour_code').val()
		}, yesapp_tour.refresh_tour_select_success);
	},
	refresh_tour_select_success : function(oData) {
		var aHtml = [];

		// var sOrderCode = $('#yesapp_ts_tour_code').val();
		
		yesapp_tour.temp.obj_temp = oData.pageData;
		for ( var i in oData.pageData) {

			var o = oData.pageData[i];

			aHtml.push('<li><span class="yb_span_width_5em">'
					+ o["a_member_name"] + '</span>[房间：' + o["a_room_name"]
					+ ']</li>');

		}

		$('#yesapp_ts_has_count').html(oData.pageData.length);

		$('#yesapp_ts_has_check').html(aHtml);
		$('#yesapp_ts_has_check').listview('refresh');
	},

	tour_select_search : function(oEl) {

		var sText = zapjs.f.trim($(oEl).val());

		if (sText != "" && sText != yesapp_tour.temp.last_search) {

			yesapp.api_call('query_member', {
				keyWord : sText,
				geracomiumCode : $('#yesapp_ts_geracomium_code').val()
			}, yesapp_tour.tour_select_search_success);

		}

	},
	tour_select_search_success : function(oData) {

		var aHtml = [];

		var sOrderCode = $('#yesapp_ts_tour_code').val();

		for ( var i in oData.pageData) {

			var o = oData.pageData[i];

			aHtml
					.push('<li><a href="javascript:zmjs.page.open_page(\'tour-member?order_code='
							+ sOrderCode
							+ '&member_code='
							+ o["member_code"]
							+ '\')"><span class="yb_span_width_5em">'
							+ o['member_name']
							+ '</span>(房间：'
							+ o['room_name']
							+ ')</a></li>');

		}

		$('#yesapp_ts_table').html(aHtml.join(''));
		$('#yesapp_ts_table').listview('refresh');

	},

	tour_select_option : function() {

		$('#yesapp_ts_option').toggle();

	},

	tour_select_finish : function() {
		yesapp.api_call('finish_tour_order', {
			orderCode : $('#yesapp_ts_tour_code').val()
		}, yesapp_tour.tour_select_finish_success);

	},
	tour_select_finish_success : function(oData) {
		zmapi.m.execjs("root.frame-main:yesapp_frame.init_frame_main()");

		zmjs.page.back_page();
	},

	tour_drug_search : function(oEl) {

		var sText = zapjs.f.trim($(oEl).val());

		if (sText != "" && sText != yesapp_tour.temp.last_search) {

			yesapp.api_call('query_drug', {
				keyWord : sText
			}, yesapp_tour.tour_drug_search_success);

		}

	},
	tour_drug_search_success : function(oData) {

		var aHtml = [];

		// var sOrderCode = $('#yesapp_ts_tour_code').val();

		yesapp_tour.temp.obj_temp = oData.pageData;
		for ( var i in oData.pageData) {

			var o = oData.pageData[i];

			aHtml
					.push('<li><a href="javascript:yesapp_tour.tour_drug_select(\''
							+ i + '\')">' + o['drug_name'] + '</a></li>');

		}

		$('#yesapp_ts_table').html(aHtml);
		$('#yesapp_ts_table').listview('refresh');

	},
	tour_drug_search_clear : function() {

		$('#yesapp_ts_table').html('');
		$('#yesapp_ts_search').val('');
	},

	tour_drug_select : function(iIndex) {

		var o = yesapp_tour.temp.obj_temp[iIndex];

		yesapp_tour.tour_drug_search_clear();

		zmjs.form.set('drug_name', o['drug_name']);
		zmjs.form.set('drug_code', o['drug_code']);
		zmjs.form.set('manufacturer', o['manufacturer']);
		zmjs.form.set('drug_usage', o['drug_usage']);
		

	},
	init_tour_drug : function() {
		zmjs.form.set('member_code', $('#yesapp_td_member_code').val());
		zmjs.form.set('tour_code', $('#yesapp_td_order_code').val());
		zmjs.form.set('account_type', $('#yesapp_td_account_type').val());
	},

	init_tour_member : function() {

		zmjs.form.set('member_code', $('#yesapp_tm_member_code').val());
		zmjs.form.set('tour_code', $('#yesapp_tm_order_code').val());

		yesapp_tour.refresh_tour_member();
	},
	refresh_tour_member : function() {
		yesapp.api_call('query_tour_drug', {
			orderCode : $('#yesapp_tm_order_code').val(),
			memberCode : $('#yesapp_tm_member_code').val()
		}, yesapp_tour.refresh_tour_member_success);
	},
	refresh_tour_member_success : function(oData) {
		var aHtml = [];

		// var sOrderCode = $('#yesapp_ts_tour_code').val();

		yesapp_tour.temp.obj_temp = oData.pageData;
		for ( var i in oData.pageData) {

			var o = oData.pageData[i];

			aHtml
					.push('<li><a href="javascript:yesapp_tour.tour_member_select(\''
							+ i
							+ '\')"><h2>'
							+ o['drug_name']
							+ '</h2><p>是否代购：'
							+ (o["flag_buy"] == "1" ? "是" : "否")
							+ '&nbsp;&nbsp;&nbsp;购买数量：'
							+ o["number_buy"]
							+ '</p></a><a href="javascript:yesapp_tour.tour_member_delete(\''
							+ i + '\')" >删除</a></li>');

		}

		$('#yesapp_tm_ul_drug').html(aHtml);
		$('#yesapp_tm_ul_drug').listview('refresh');
	},
	tour_member_delete : function(iIndex) {

		var o = yesapp_tour.temp.obj_temp[iIndex];

		yesapp.api_call('delete_tour_drug', {
			recordCode : o["record_code"]
		}, yesapp_tour.tour_member_delete_success);
	},
	tour_member_delete_success : function(oData) {
		zmapi.m.toast('删除成功');
		yesapp_tour.refresh_tour_member();
	},
	tour_member_done : function() {
		zmapi.m.execjs("tour-select:yesapp_tour.refresh_tour_select()");
		zmapi.m.execjs("root.frame-main:yesapp_frame.refresh_frame_main()");

	}

};
