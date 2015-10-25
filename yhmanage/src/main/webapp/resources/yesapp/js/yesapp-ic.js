var yesapp_ic = {
	
	init:function()
	{
		
		//$('#yc_icd_code').html('<input id="yesapp_ic_search" type="text" data-type="search" placeholder="请输入老人姓名" onkeyup="yesapp_ic.search(this)" onkeypress="yesapp_ic.search(this)">');
		
	},
	search:function(oEl)
	{
		var sText = zapjs.f.trim($(oEl).val());

		if (sText != "" ) {

			yesapp.api_call('query_icd', {
				keyWord : sText
			}, yesapp_ic.search_success);

		}
	},
	search_success:function(oData)
	{
		var aHtml = [];

		// var sOrderCode = $('#yesapp_ts_tour_code').val();

		
		for ( var i in oData.pageData) {

			var o = oData.pageData[i];

			var sText=o['irritability_code'] +':'+ o['irritability_title'];
			aHtml
					.push('<li><a href="javascript:yesapp_ic.icd_select(\''
							+ sText + '\')">' +sText + '</a></li>');

		}

		$('#yesapp_ts_table').html(aHtml);
		$('#yesapp_ts_table').listview('refresh');
		
		
	},
	icd_select:function(sText)
	{
		
		$('#zw_f_diagnose_info').val($('#zw_f_diagnose_info').val()+sText+';');
	}

};


$(
function(){yesapp_ic.init();}		
);