var yesapp = {

	config : {
		// 二维码扫描开始域名
		scanner_start : 'http://q.yxl.co/',
		api_key : 'appfordoctor'
	},

	// API列表
	api_list : {
		// 用户登陆
		user_login : {
			api_name : "com_cmall_membercenter_group_api_ApiGroupLogin"
		},
		page_data : {
			api_name : "com_srnpr_zapweb_webapi_RootPageDataApi"
		},
		query_member : {
			api_name : 'com_srnpr_yeshospital_api_app_QueryMember'
		},

		query_drug : {
			api_name : 'com_srnpr_yeshospital_api_app_QueryDrug'
		},
		query_tour_drug : {
			api_name : 'com_srnpr_yeshospital_api_app_QueryTourDrug'
		},

		member_last : {
			api_name : 'com_srnpr_yeshospital_api_app_MemberLast',
			flag_token : true
		},

		delete_tour_drug : {
			api_name : 'com_srnpr_yeshospital_api_app_DeleteTourDrug'
		},
		query_tour_detail : {
			api_name : 'com_srnpr_yeshospital_api_app_QueryTourDetail'
		},
		finish_tour_order : {
			api_name : 'com_srnpr_yeshospital_api_app_FinishTourOrder',
			flag_token : true
		},
		main_page : {
			api_name : 'com_srnpr_yeshospital_api_app_FrameMain',
			flag_token : true
		}
	},

	api_call : function(sName, oData, fCallBack) {

		zapjs.c.api_key = yesapp.config.api_key;

		var o_config = yesapp.api_list[sName];

		if (o_config == null) {
			zapjs.f.message('api not exist!');
			return false;
		}

		if (o_config.flag_token) {

			zapjs.c.api_token = zapjs.f.cookie(zapjs.c.cookie_user);
			if (!zapjs.c.api_token) {
				return false;
			}

		}

		zapjs.zw.api_call(o_config['api_name'], oData, fCallBack);

	},

	api_page : function(sPageCode, oQuery, fCallBack) {

		var nQuery = {};
		for ( var p in oQuery) {
			nQuery[zapjs.c.web_field + p] = oQuery[p];
		}

		yesapp.api_call('page_data', {
			pageCode : sPageCode,
			queryMap : nQuery
		}, fCallBack);

	},

	scanner_code : function() {
		var obj = api.require('scanner');

		obj.open(function(ret, err) {

			yesapp.exec_scanner(ret.msg);

		});
	},

	exec_scanner : function(sText) {

		var iIndex = sText.indexOf(yesapp.config.scanner_start);

		if (iIndex == 0) {

			var sCode = sText.substr(yesapp.config.scanner_start.length);
			// zmapi.m.alert(sCode);
			// zmjs.page.open_page('');

			var sTourCode = $('#yesapp_ts_tour_code').val();

			yesapp.api_call('member_last', {
				postCard : sCode,
				orderCode : sTourCode
			},
					function(o) {

						zmjs.page.open_page('tour-member?u_order_code='
								+ o["orderCode"] + '&u_member_code='
								+ o["memberCode"]);

					});

		} else {

			zmapi.m.alert('暂不支持该内容，请重新扫描！');

		}

	}

};
