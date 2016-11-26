var census = {

	temp : {
		score : 0,
		option : '',
		standard : ''
	},

	sum_detail : function(o) {

		census.sum_all();

	},

	dialog_alert : function(sMsg) {
		$('#census_dialog_alert_info').html(sMsg);
		$('#census_dialog_alert_box').fadeIn(200);
	},

	submit_info : function() {

		for ( var i in census_detail_objs) {
			var oDetail = census_detail_objs[i];
			if (census.temp.option.indexOf(oDetail["d_code"]) < 0) {

				census.dialog_alert('[' + oDetail["d_sort"] + '.'
						+ oDetail["d_name"] + ']' + '尚未选择');
				return false;
			}

		}

		if (census_base_obj.member_code == "") {
			census.dialog_alert('没有找到用户信息');
			return false;
		}
		
		
		if (census.temp.standard == "") {
			census.dialog_alert('没有对应的评估结论，请联系系统管理人员，谢谢！');
			return false;
		}
		

		

		zapapi.api_call('com_srnpr_yeshospital_api_census_CensusSubmit', {
			memberCode : census_base_obj.member_code,
			censusReportCode : census_base_obj.report_code,
			optionSelect : census.temp.option,
			reportScore : census.temp.score,
			standCode : census.temp.standard
		}, census.submit_success);

	},

	submit_success : function() {
		$('#census_page_main').hide();
		$('#census_page_success').show();
		
	},

	sum_all : function() {

		var aSum = 0;

		var aOptions = [];

		for ( var i in census_detail_objs) {
			var oDetail = census_detail_objs[i];

			var aVal = [];
			$('input[name="census_d_' + oDetail["d_code"] + '"]:checked').each(

			function(n, el) {

				aVal.push($(el).val());

				aSum = aSum + parseInt($(el).attr("option-score"));

			});

			if (aVal.length > 0) {
				aOptions.push(oDetail["d_code"] + "=" + aVal.join(','));
			}

			// console.log(oDetail["d_code"]+':'+aVal.join(','));

		}

		census.temp.option = aOptions.join('&');
		census.temp.score = aSum;

		$('#census_sum_score').html(aSum);

		var sText = [];

		var aStandard = [];

		for ( var i in census_standard_objs) {
			var oStandard = census_standard_objs[i];

			if (aSum > parseInt(oStandard["d_min"])
					&& aSum <= parseInt(oStandard["d_max"])) {
				sText.push('<span class="census_standard_item" >'
						+ oStandard["d_name"] + '</span>&nbsp;&nbsp;'
						+ oStandard["d_desc"]);

				aStandard.push(oStandard["d_code"]);
			}

		}

		census.temp.standard = aStandard.join(',');

		$('#census_sum_standard').html(sText.join('<br/>'));

	}

};
