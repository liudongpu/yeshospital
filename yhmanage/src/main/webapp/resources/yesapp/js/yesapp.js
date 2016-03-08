var yesapp = {

	config : {
		// 二维码扫描开始域名
		scanner_start : 'http://q.yxl9.cn/',
		api_key : 'appfordoctor',
		flag_open : 'yesapp_hidden_flag_open_check'
	},

	y_define : {
		frams : [ {
			name : 'frame-main',
			url : '../mobile/frame-main'
		}, {
			name : 'frame-daily',
			url : '../mobile/frame-daily'
		}, {
			name : 'frame-see',
			url : '../mobile/frame-see'
		}, {
			name : 'frame-my',
			url : '../mobile/frame-my'
		} ],

		base_init : 'root.frame-main:yesapp_frame.refresh_frame_main()',
		// 渠道类型 默认为医生版 可以为gen版
		channel : 'doctor',

		qrcode_link : 'tour-member'

	},

	temp : {
		// 是否加载滑动处理的方法
		scroll_process : 1,
		// 滑动加载时的页码
		scroll_page_index : 0,
		// 最近一次查询参数
		last_search : '-1'
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
			api_name : 'com_srnpr_yeshospital_api_app_QueryMember',
			flag_token : true
		},

		query_drug : {
			api_name : 'com_srnpr_yeshospital_api_app_QueryDrug'
		},
		query_icd : {
			api_name : 'com_srnpr_yeshospital_api_app_QueryIcd'
		},
		query_tour_drug : {
			api_name : 'com_srnpr_yeshospital_api_app_QueryTourDrug'
		},

		member_last : {
			api_name : 'com_srnpr_yeshospital_api_app_MemberLast',
			flag_token : true
		},
		member_pic : {
			api_name : 'com_srnpr_yeshospital_api_app_MemberPic',
			flag_token : true
		},
		delete_tour_drug : {
			api_name : 'com_srnpr_yeshospital_api_app_DeleteTourDrug'
		},

		check_tour_drug : {
			api_name : 'com_srnpr_yeshospital_api_app_CheckTourDrug',
			flag_token : true
		},

		query_tour_detail : {
			api_name : 'com_srnpr_yeshospital_api_app_QueryTourDetail'
		},
		finish_tour_order : {
			api_name : 'com_srnpr_yeshospital_api_app_FinishTourOrder',
			flag_token : true
		},
		my_info : {
			api_name : 'com_srnpr_yeshospital_api_app_MyInfo',
			flag_token : true
		},
		my_config : {
			api_name : 'com_srnpr_yeshospital_api_app_MyConfig',
			flag_token : true
		},
		my_password : {
			api_name : 'com_srnpr_yeshospital_api_member_ChangeUserPassword',
			flag_token : true
		},

		my_suggestion : {
			api_name : 'com_srnpr_yeshospital_api_member_SuggestionInfo',
			flag_token : true
		},

		main_page : {
			api_name : 'com_srnpr_yeshospital_api_app_FrameMain',
			flag_token : true
		},
		daily_page : {
			api_name : 'com_srnpr_yeshospital_api_app_FrameDaily',
			flag_token : true
		},
		visit_detail : {
			api_name : 'com_srnpr_yeshospital_api_visit_VisitDetail',
			flag_token : true
		},
		visit_status : {
			api_name : 'com_srnpr_yeshospital_api_visit_VisitStatus',
			flag_token : true
		},
		mould_list : {
			api_name : 'com_srnpr_yeshospital_api_app_MouldList',
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
			}, function(o) {

				zmjs.page.open_page(yesapp.y_define.qrcode_link
						+ '?u_order_code=' + o["orderCode"] + '&u_member_code='
						+ o["memberCode"]);

			});

		} else {

			zmapi.m.alert('暂不支持该内容，请重新扫描！');

		}

	},

	/*
	 * 判断是否审核流程
	 */
	check_open : function() {
		var bFlag = false;

		if ($('#' + yesapp.config.flag_open).val() == "1") {
			bFlag = true;
		}

		return bFlag;
	},

	/*
	 * 开始滚动前或者刷新滚动前调用 执行
	 */
	scroll_begin : function() {

		yesapp.temp.scroll_process = 1;
		yesapp.temp.scroll_page_index = 0;
	},

	scroll_input : function(oInput) {
		oInput.pageIndex = yesapp.temp.scroll_page_index + 1;
		return oInput;
	},

	scroll_check : function() {

		var bFlagCheck = (yesapp.temp.scroll_process == 1);
		if (bFlagCheck) {
			yesapp.temp.scroll_process == 0;
		}

		return bFlagCheck;
	},
	scroll_end : function(oData) {

		yesapp.temp.scroll_process == 1;
		yesapp.temp.scroll_page_index = oData.pageInfo.pageIndex;
		if (oData.pageInfo.flagNext == 0) {
			yesapp.temp.scroll_process == 2;
		}

	}

};
