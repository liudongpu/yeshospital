var yesapp_frame = {

	qrcode : function() {
		
		var obj = api.require('scanner');
		obj.open(function(ret,err) {
		    api.alert({
		        title: '扫描结果', 
		        msg: ret.msg
		    });
		});
	},

	shake : function() {
		zmapi.m.alert('shake');
		var obj = api.require('shakeView');
		obj.open();

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

			aHtml
					.push('<li  onclick="zmjs.page.open_page(\'tour-select?tour_order='
							+ o["tour_code"] + '\')" ><a href="#">');

			if (o["order_status"] == "46580001000500020001") {

				aHtml
						.push('<div class="zmcss_f_left yb_show_tag zmcss_c_red">进行中</div>');
			} else {
				aHtml
						.push('<div class="zmcss_f_left yb_show_tag zmcss_c_green">已完成</div>');
			}

			aHtml.push('<h2>' + o["a_geracomium_name"] + '</h2>' + '<p>'
					+ o["a_tour_date"] + '<br/>已检查：' + o["a_check_member"]
					+ '人 共计：' + o["a_all_member"] + '人</p>');
			aHtml.push('</a></li>');

		}

		$('#yesapp_fm_main_list').html(aHtml.join(''));

		$('#yesapp_fm_main_list').listview('refresh');

	}

};
