var yesapp_frame = {

	shake : function() {
		/*
		 * zmapi.m.alert('shake'); var obj = api.require('shakeView');
		 * obj.open();
		 */
		var obj = api.require('scanner');
		obj.openView({
			x : 40,
			y : 160,
			w : 200,
			h : 240
		}, function(ret, err) {
			ret.msg
		});

	},

	// 二维码扫描结果解析
	frame_see_scanner : function(sText) {

	},

	init_frame_main : function() {

		// zmapi.p.user_login();
		yesapp_frame.refresh_frame_main();

	},

	refresh_frame_main : function() {

		yesapp.api_call('main_page', {

		}, yesapp_frame.refresh_frame_main_success);

	},

	refresh_frame_main_success : function(oData) {
		var aHtml = [];

		for ( var i in oData.pageData) {

			var o = oData.pageData[i];

			aHtml.push('<li  onclick="zmjs.page.open_page(\'' + o["a_link"]
					+ '\')" ><a href="#">');

			if (o["a_status"] == "0") {

				aHtml
						.push('<div class="zmcss_f_left yb_show_tag zmcss_c_red">进行中</div>');
			} else {
				aHtml
						.push('<div class="zmcss_f_left yb_show_tag zmcss_c_green">已完成</div>');
			}

			aHtml.push('<p>' + o["a_name"]+'<br/>'+ o["a_date"]
					+ '<br/>' + o["a_text"] + '</p>');
			aHtml.push('</a></li>');

		}

		$('#yesapp_fm_main_list').html(aHtml.join(''));

		$('#yesapp_fm_main_list').listview('refresh');

	},
	begin_frame_daily : function() {

		yesapp_frame.init_frame_daily();

		zmapi.m.ready(function() {
			zmapi.m.scrolltobottom(function() {
				yesapp_frame.refresh_frame_daily();
			});

		});

	},

	init_frame_daily : function() {

		$('#yh_frame_daily_box').html('');
		yesapp.scroll_begin();
		yesapp_frame.refresh_frame_daily();

	},

	refresh_frame_daily : function() {

		if (yesapp.scroll_check()) {
			yesapp.api_call('daily_page', yesapp.scroll_input({}),
					yesapp_frame.refresh_frame_daily_success);
		}
	},

	refresh_frame_daily_success : function(oData) {

		yesapp.scroll_end(oData);

		var aHtml = [];

		for ( var i in oData.pageData) {

			var o = oData.pageData[i];

			aHtml.push('<div class="yh_frame_daily_item '
					+ (o["process_status"] == "46580001000200070001" ? ""
							: "yh_frame_daily_over") + '"  ');
			if (o["msg_link"] != "") {
				aHtml.push(' onclick="zmjs.page.open_page(\'' + o["msg_link"]
						+ '\') " ');
			}

			aHtml
					.push(' ><div class="zmcss_h_10"></div><div class="yh_frame_daily_left"><div class="yh_frame_daily_date">');
			aHtml.push(o["create_time"].substring(5, 10));
			aHtml
					.push('</div></div><div class="yh_frame_daily_right"><div class="yh_frame_daily_msg"><h3>');
			aHtml.push(o["msg_title"]);
			aHtml.push('</h3><p>');

			aHtml.push(o["msg_info"]);
			aHtml
					.push('</p></div></div><div class="yh_frame_daily_clear zmcss_h_10"></div></div>');

		}

		$('#yh_frame_daily_box').append(aHtml.join(''));

	},
	init_frame_people : function() {

		// zmapi.p.user_login();
		yesapp_frame.search_frame_people('#yesapp_ts_search');

	},
	search_frame_people : function(oEl) {

		var sText = zapjs.f.trim($(oEl).val());

		if (sText != yesapp.temp.last_search) {
			yesapp.temp.last_search = sText;
			yesapp.api_call('query_member', {
				keyWord : sText
			}, yesapp_frame.search_frame_people_success);

		}

	},
	search_frame_people_success : function(oData) {
		// yesapp.temp.last_search=zapjs.f.trim($('#yesapp_ts_search').val());
		var aHtml = [];

		for ( var i in oData.pageData) {

			var o = oData.pageData[i];

			var sLinkUrl = ('people-link?u_member_code=' + o["member_code"]);

			aHtml.push('<li><a href="javascript:zmjs.page.open_page(\''
					+ sLinkUrl

					+ '\')"><span class="yb_span_width_5em">'
					+ o['member_name'] + '</span>(房间：' + o['room_name']
					+ ')</a></li>');

		}

		$('#yesapp_ts_table').html(aHtml.join(''));
		$('#yesapp_ts_table').listview('refresh');

	}

};
