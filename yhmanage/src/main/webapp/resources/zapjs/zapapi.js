/**
 * API调用相关
 * 
 * 
 */

var zapapi = {

	c : {

		api_key : 'jsapi',
		api_url : '../jsonapi/'
	},

	/*
	 * APi调用类
	 */
	api_call : function(sTarget, oData, fCallBack) {

		// 判断如果传入了oData则自动拼接 否则无所只传入key认证
		var defaults = oData ? {
			api_target : sTarget,
			api_input : zapapi.f.tojson(oData),
			api_key : zapapi.api_key
		} : {
			api_key : zapapi.api_key,
			api_input : ''
		};

		// oData = $.extend({}, defaults, oData || {});

		zapjs.f.ajaxjson(zapjs.c.api_url + sTarget, defaults, function(data) {

			if (data.resultCode == "1") {

				fCallBack(data);

			} else {

				zapapi.f.message(data.resultMessage);

			}

		});

	},
	f : {
		tojson : function(oObj) {
			return JSON.stringify(oObj);
		},
		ajaxjson : function(sTarget, data, fCallBack) {

			var options = {

				dataType : "json",
				url : sTarget,
				type : "POST",
				data : data,
				success : fCallBack,
				error : function(msg) {
					zapapi.f.message('系统异步调用出现错误，请联系技术，谢谢！');
				}
			};

			$.ajax(options);

		}
	},
	message : function(sMessage) {
		alert(sMessage);
	}

};
