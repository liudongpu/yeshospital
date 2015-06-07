var yesapp_tour = {

	temp : {
		// 最近一次搜索词
		last_search : '',
		// 临时保存交换变量
		obj_temp : null,
		template_code : '',
		template_id : ''
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
					.push('<li><a href="javascript:zmjs.page.open_page(\'tour-member?u_order_code='
							+ sOrderCode
							+ '&u_member_code='
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
		zmjs.form.set('drug_unit', o['drug_unit']);
		zmjs.form.set('drug_dose', o['drug_dose']);
		zmjs.form.set('drug_single', o['drug_single']);
		zmjs.form.set('drug_source', o['drug_source']);

	},
	init_tour_drug : function() {
		zmjs.form.set('member_code', $('#yesapp_td_member_code').val());
		zmjs.form.set('tour_code', $('#yesapp_td_order_code').val());
		zmjs.form.set('account_type', $('#yesapp_td_account_type').val());
	},

	init_tour_member : function() {

		zmjs.form.set('member_code', $('#yesapp_tm_member_code').val());
		zmjs.form.set('tour_code', $('#yesapp_tm_order_code').val());

		$('#zw_f_tour_info')
				.prev('label')
				.append(
						'<a href="javascript:yesapp_tour.tour_member_template(\'yesapp_tm_dialog_t1\',\'zw_f_tour_info\')">模板</a>');
		$('#zw_f_agree_info')
				.prev('label')
				.append(
						'<a href="javascript:yesapp_tour.tour_member_template(\'yesapp_tm_dialog_t2\',\'zw_f_agree_info\')">模板</a>');
		$('#zw_f_check_info')
				.prev('label')
				.append(
						'<a href="javascript:yesapp_tour.tour_member_template(\'yesapp_tm_dialog_t3\',\'zw_f_check_info\')">模板</a>');

		yesapp_tour.refresh_tour_member();
	},

	tour_member_template : function(sCode, sId) {
		zmjs.ui.dialog_open(sCode);
		yesapp_tour.temp.template_code = sCode;
		yesapp_tour.temp.template_id = sId;

	},

	tour_member_tmp_click : function(oEl) {

		var sText = $('#' + yesapp_tour.temp.template_id).val();
		

		$('#' + yesapp_tour.temp.template_id).val(sText + $(oEl).text()+";");
		zmjs.ui.dialog_close(yesapp_tour.temp.template_code);

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
							+ ((o["flag_check"] == "0") ? '<b class="yb_color_red">●</b>'
									: '')
							+ o['drug_name']
							+ '</h2><p>是否代购：'
							+ (o["flag_buy"] == "1" ? "是" : "否")
							+ '&nbsp;&nbsp;&nbsp;购买数量：'
							+ o["number_buy"]
							+ o["drug_unit"]
							+ '<br/>'
							+ o['drug_dose']
							+ o['drug_single']
							+ '&nbsp;&nbsp;&nbsp;'
							+ o['drug_source']
							+ '&nbsp;&nbsp;&nbsp;'
							+ o['drug_usage']
							+ '</p></a><a href="javascript:yesapp_tour.tour_member_option(\''
							+ i + '\')" >操作</a></li>');

		}

		$('#yesapp_tm_ul_drug').html(aHtml);
		$('#yesapp_tm_ul_drug').listview('refresh');
	},

	tour_member_option : function(iIndex) {

		var o = yesapp_tour.temp.obj_temp[iIndex];

		var aHtml = [];
		aHtml
				.push('<a href="javascript:yesapp_tour.tour_member_edit(\''
						+ iIndex
						+ '\')"  class="ui-btn">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;修改&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>');

		aHtml
				.push('<a href="javascript:yesapp_tour.tour_member_delete(\''
						+ iIndex
						+ '\')" class="ui-btn">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;删除&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>');

		if (o["flag_check"] == "0") {
			aHtml
					.push('<a href="javascript:yesapp_tour.tour_member_check(\''
							+ iIndex
							+ '\')"  class="ui-btn">&nbsp;&nbsp;&nbsp;确认服用&nbsp;&nbsp;&nbsp;</a>');
		}

		$('#yesapp_tm_dialog_option_info').html(aHtml.join(''));

		zmjs.ui.dialog_open('yesapp_tm_dialog_option');

	},

	tour_member_edit : function(iIndex) {
		var o = yesapp_tour.temp.obj_temp[iIndex];

		zmjs.page.open_page('../mb/page_edit_m_yh_tour_order_drug?zw_f_uid='
				+ o['uid']);

		zmjs.ui.dialog_close('yesapp_tm_dialog_option');
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
		zmjs.ui.dialog_close('yesapp_tm_dialog_option');
	},

	tour_member_check : function(iIndex) {

		var o = yesapp_tour.temp.obj_temp[iIndex];

		yesapp.api_call('check_tour_drug', {
			recordCode : o["record_code"]
		}, yesapp_tour.tour_member_check_success);
	},
	tour_member_check_success : function(oData) {
		zmapi.m.toast('操作成功');
		yesapp_tour.refresh_tour_member();
		zmjs.ui.dialog_close('yesapp_tm_dialog_option');
	},

	tour_member_done : function() {
		zmapi.m.execjs("tour-select:yesapp_tour.refresh_tour_select()");
		zmapi.m.execjs("root.frame-main:yesapp_frame.refresh_frame_main()");

	}

};
