var yesapp_tour = {

	temp : {
		// 最近一次搜索词
		last_search : '',
		// 临时保存交换变量
		obj_temp : null
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
					.push('<li><a href="javascript:zmjs.page.open_page(\'tour-member?order_code='
							+ sOrderCode
							+ '&member_code='
							+ o["member_code"]
							+ '\')">'
							+ o['member_name']
							+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(房间：'
							+ o['room_name'] + ')</a></li>');

		}

		$('#yesapp_ts_table').html(aHtml.join(''));
		$('#yesapp_ts_table').listview('refresh');

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
							+ i
							+ '\')">'
							+ o['drug_name']
							+ '</a></li>');

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
		
		

	}

};
