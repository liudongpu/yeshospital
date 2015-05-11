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
					.push('<li class="table-view-cell media" onclick="zmjs.page.open_page(\'tour-select?tour_order='+o["tour_code"]+'\')" >');
			aHtml
					.push(' <a class="navigate-right"><div  class="media-object pull-left" >');

			if (o["order_status"] == "") {

				aHtml
						.push('<button class="btn btn-negative btn-block">&nbsp;进行中&nbsp;</button>');
			} else {
				aHtml
						.push('<button class="btn btn-primary btn-block btn-outlined">&nbsp;已完成&nbsp;</button>');
			}

			aHtml.push('</div><div class="media-body">');
			aHtml.push(o["tour_date"]
					+ '<p>'+o["geracomium_name"]+'<br/>已检查：11人 未检查：14人</p>');
			aHtml.push('</div></a></li>');

		}

		$('#yesapp_fm_main_list').html(aHtml.join(''));

	}

};
