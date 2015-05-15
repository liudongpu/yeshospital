var yesapp_frame = {

	qrcode : function() {

	},

	shake : function() {
		var obj = api.require('shakeView');
		obj.open();

	},

	init_frame_main : function() {
		yesapp.api_call('main_page', {

		}, yesapp_frame.init_success_main);
	},
	init_success_main : function(oData) {
		var aHtml = [];

		for ( var i in oData.pageData) {

			var o = oData.pageData[i];

			aHtml
					.push('<li  onclick="zmjs.page.open_page(\'tour-select?tour_order='+o["tour_code"]+'\')" ><a href="#">');
			
			
			if (o["order_status"] == "") {

				aHtml
						.push('<img src=""/>');
			} else {
				aHtml
						.push('<img src=""/>');
			}

			aHtml.push('<h2>'+o["geracomium_name"]+'</h2>'
					+ '<p>'+o["tour_date"]+'<br/>已检查：11人 未检查：14人</p>');
			aHtml.push('</a></li>');

		}

		$('#yesapp_fm_main_list').html(aHtml.join(''));
		
		$('#yesapp_fm_main_list').listview('refresh');

	}

};
