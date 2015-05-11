var yesapp_tour = {

	temp : {
		// 最近一次搜索词
		last_search : ''
	},

	init_tour_select : function() {

	},

	tour_select_search : function(oEl) {

		var sText = zapjs.f.trim($(oEl).val());

		if (sText != "" && sText != yesapp_tour.temp.last_search) {

			yesapp.api_call('query_member', {
				keyWord : sText
			}, yesapp_tour.tour_select_search_success);

		}

	},
	tour_select_search_success : function(oData) {

		var aHtml = [];

		var sOrderCode = $('#yesapp_ts_tour_code').val();

		for ( var i in oData.pageData) {

			var o = oData.pageData[i];

			aHtml
					.push('<li class="table-view-cell" onclick="zmjs.page.open_page(\'tour-member?order_code='
							+ sOrderCode
							+ '&member_code='
							+ o["member_code"]
							+ '\')">'
							+ o['member_name']
							+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(房间：'
							+ o['room_name'] + ')</li>');

		}

		$('#yesapp_ts_table').html(aHtml);

	}

};
