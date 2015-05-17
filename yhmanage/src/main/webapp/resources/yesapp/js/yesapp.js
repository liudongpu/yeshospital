var yesapp = {

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
		delete_tour_drug : {
			api_name : 'com_srnpr_yeshospital_api_app_DeleteTourDrug'
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

		zapjs.c.api_key = 'appfordoctor';

		var o_config = yesapp.api_list[sName];

		if (o_config == null) {
			zapjs.f.message('api not exist!');
			return false;
		}

		if (o_config.flag_token) {

			zapjs.c.api_token = zapjs.f.cookie(zapjs.c.cookie_user);

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

	}

};