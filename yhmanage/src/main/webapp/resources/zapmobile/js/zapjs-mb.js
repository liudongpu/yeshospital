/*
 * zapjs
 * 基本功能及基本插件
 * 其中zapjs.f 表示扩展功能
 */


/*
* jQuery Form Plugin; v20130711
* http://jquery.malsup.com/form/
* Copyright (c) 2013 M. Alsup; Dual licensed: MIT/GPL
* https://github.com/malsup/form#copyright-and-license
*/

/*!
 * jQuery Cookie Plugin v1.3.1
 * https://github.com/carhartl/jquery-cookie
 *
 * Copyright 2013 Klaus Hartl
 * Released under the MIT license
 */
(function (factory) {
	if (typeof define === 'function' && define.amd&&1==2) {
		// AMD. Register as anonymous module.
		define(['jquery'], factory);
	} else {
		// Browser globals.
		factory($);
	}
}(function ($) {

	var pluses = /\+/g;

	function raw(s) {
		return s;
	}

	function decoded(s) {
		return decodeURIComponent(s.replace(pluses, ' '));
	}

	function converted(s) {
		if (s.indexOf('"') === 0) {
			// This is a quoted cookie as according to RFC2068, unescape
			s = s.slice(1, -1).replace(/\\"/g, '"').replace(/\\\\/g, '\\');
		}
		try {
			return config.json ? JSON.parse(s) : s;
		} catch(er) {}
	}

	var config = $.cookie = function (key, value, options) {

		// write
		if (value !== undefined) {
			options = $.extend({}, config.defaults, options);

			if (typeof options.expires === 'number') {
				var days = options.expires, t = options.expires = new Date();
				t.setDate(t.getDate() + days);
			}

			value = config.json ? JSON.stringify(value) : String(value);

			return (document.cookie = [
				config.raw ? key : encodeURIComponent(key),
				'=',
				config.raw ? value : encodeURIComponent(value),
				options.expires ? '; expires=' + options.expires.toUTCString() : '', // use expires attribute, max-age is not supported by IE
				options.path    ? '; path=' + options.path : '',
				options.domain  ? '; domain=' + options.domain : '',
				options.secure  ? '; secure' : ''
			].join(''));
		}

		// read
		var decode = config.raw ? raw : decoded;
		var cookies = document.cookie.split('; ');
		var result = key ? undefined : {};
		for (var i = 0, l = cookies.length; i < l; i++) {
			var parts = cookies[i].split('=');
			var name = decode(parts.shift());
			var cookie = decode(parts.join('='));

			if (key && key === name) {
				result = converted(cookie);
				break;
			}

			if (!key) {
				result[name] = converted(cookie);
			}
		}

		return result;
	};

	config.defaults = {};

	$.removeCookie = function (key, options) {
		if ($.cookie(key) !== undefined) {
			// Must not alter options, thus extending a fresh object...
			$.cookie(key, '', $.extend({}, options, { expires: -1 }));
			return true;
		}
		return false;
	};

}));




var zapjs = {
	// 配置
	c : {
		web_paginaion : 'zw_p_',
		web_field : 'zw_f_',
		web_extend : 'zw_e_',
		field_attr : 'zapweb_attr_',
		main_iframe : 'main_iframe',
		split : '|',
		path_resources : '../resources/',
		path_upload:'../upload/',
		cookie_base : 'cookie-zw-',
		cookie_user : 'userinfo',
		extend : {},
		// 是否调试模式
		debug : true
	},
	// 注册函数专用调用 注册该函数的方法需要返回true/false
	// 调用注册方法为zapjs.f.callextend(sId);该参数会返回true/false
	e : function(sId, fCall) {
		if (!zapjs.c.extend[sId]) {
			zapjs.c.extend[sId] = [];
		}

		zapjs.c.extend[sId].push(fCall);

	},
	// 调试模式的话则输出日志
	d : function(oLog) {
		if (zapjs.c.debug) {

			if (window.console && window.console.log) {
				console.log(zapjs.f.tojson(oLog));
			}

		}
	}
};

zapjs.fn = zapjs.prototype = {};

window.zapjs = zapjs;

/*
 * f
 */
zapjs.f = {

	// 提交参数
	ajaxsubmit : function(oElment, sAction, fSucceess, fError) {
		var bReturn=true;
		if (sAction == "") {

		}
		var options = {
			url : sAction,
			type : "post",
			success : function(o) {
				fSucceess(o);
			},
			error : function(o) {
				fError(o);
			}
		};

		if(zapjs.f.callextend("zapjs_e_zapjs_f_ajaxsubmit_submit"))
		{
			$(oElment).ajaxSubmit(options);
		}
		else
		{
			bReturn=false;
		}
		return bReturn;

	},
	ajaxjson : function(sTarget, data, fCallBack) {
		
		
		var options = {
			
			dataType: "json",
		  url: sTarget,
		  type: "POST",
		  data: data,
		  success: fCallBack,
		   error: function (msg) {
						zapjs.f.message('系统异步调用出现错误，请联系技术，谢谢！');
		            }
		};
		
		
				$.ajax(options);

		
		/*
		 * $.getJSON(sTarget, data, fCallBack).fail(function() {
		 * alert('系统异步调用出现错误，请联系技术，谢谢！'); });
		 */
	},

	setdomain : function() {

	},

	// Cookie操作
	cookie : function(key, value, options) {

		// 判断如果是写操作 写到根目录
		if (value !== undefined) {
			if (!options) {
				options = {};
			}

			options.path = "/";
			if (!options.expires)
				options.expires = 30;
		}

		return $.cookie(zapjs.c.cookie_base + key, value, options);
	},

	ready : function(f) {
		$(f);
	},

	require : function(aNeeds, f) {

		requirejs(aNeeds, f);

	},
	// 获取当前主域
	updomain : function(url) {

		var host = "null";
		if ( typeof url == "undefined" || null == url)
			url = window.location.href;
		var regex = /.*\:\/\/([^\/|:]*).*/;
		var match = url.match(regex);
		if ( typeof match != "undefined" && null != match) {
			host = match[1];
		}
		if ( typeof host != "undefined" && null != host) {
			var strAry = host.split(".");
			if (strAry.length > 1) {
				host = strAry[strAry.length - 2] + "." + strAry[strAry.length - 1];
			}
		}

		return host;

	},
	// 调用扩展
	callextend : function(sId) {
		var bReturn = true;

		if (zapjs.c.extend[sId]) {
			for (var p in zapjs.c.extend[sId]) {
				var bFlag = zapjs.c.extend[sId][p]();
				if (!bFlag) {
					bReturn = false;
				}
			}
		}
		;

		return bReturn;
	},

	// 转成json格式
	tojson : function(oObj) {
		return JSON.stringify(oObj);
	},
	// 执行json
	evaljson : function(sJson) {
		return JSON.parse(sJson);
	},
	logdebug : function(o) {
		console.log(o);
	},
	upurl : function() {
		return window.location.href;
	},

	/*
	 * 自动刷新 如果有子页面 则刷新iframe
	 */
	autorefresh : function() {
		if (zapjs.f.exist('main_iframe')) {
			document.getElementById("main_iframe").contentWindow.zapjs.f.tourl();
		} else {
			zapjs.f.tourl();
		}
	},
	/*
	 * 格式化字符串
	 */
	formatsplit:function(sSource)
	{
	return zapjs.f.replace(sSource,zapjs.c.split,'-');	
	},

	replace : function(sSource, sOld, sNew) {

		sSource = sSource.replace(sOld, sNew);

		return sSource;

	},

	stringformat : function(sInput) {

		var args = arguments;

		if (args && args.length >= 2) {
			for (var i in args) {
				if (i > 0) {
					sInput = zapjs.f.replace(sInput, '{' + (i - 1) + '}', args[i]);
				}
			}
		}

		return sInput;

	},

	tourl : function(sUrl) {

		if (!sUrl) {
			sUrl = location.href;
		}

		location.href = sUrl;
	},
	// 判断是否存在元素
	exist : function(sId) {
		return document.getElementById(sId) ? true : false;

	},
	
	create_position:function(options)
	{
		
		var defaults = {
			id : 'zapjs_f_id_window_position',
			content : '',
			source:null
		};

		var s = $.extend({}, defaults, options || {});
		
		
		var offset_x = $(s.source).offset().top;
		var offset_y=$(s.source).offset().left;
		
		
		if(zapjs.f.exist(s.id))
		{
			$('#'+s.id).css('top',offset_x);
			$('#'+s.id).css('left',offset_y);
			$('#'+s.id).show();
			return ;
		}
		
		
		
		var sHtml='<div id="'+s.id+'" class="w_position" style="top:'+offset_x+'px;left:'+offset_y+'px;">'+s.content+'</div>';
		
		$('body').append(sHtml);
		
	},
	// 浏览器信息
	browser : function(sBrowser) {
		var userAgent = navigator.userAgent.toLowerCase();
		var bs = {
			version : (userAgent.match(/.+(?:rv|it|ra|ie)[\/: ]([\d.]+)/) || [
			0, '0' ])[1],
			safari : /webkit/.test(userAgent),
			opera : /opera/.test(userAgent),
			msie : /msie/.test(userAgent) && !/opera/.test(userAgent),
			mozilla : /mozilla/.test(userAgent) && !/(compatible|webkit)/.test(userAgent)
		};

		if (sBrowser) {

			if (sBrowser == "ie6") {
				try {
					return bs.msie && bs.version == "6.0";
				} catch (ex) {

				}

			}

		}

		return bs;

	},
	window_close : function(sId) {

		zapjs.f.window_box({
			id : sId,
			close : true
		});
		
		
		
		

	},
	// 弹出窗口
	window_box : function(options) {
		var defaults = {
			id : 'zapjs_f_id_window_box',
			content : '',
			width : 600,
			height : 400,
			type:'text',
			title : '&nbsp;',
			close : false
		};

		var s = $.extend({}, defaults, options || {});
		
		// s.onClose(function(){alert('2');});
		if (s.close) {

			$('#' + s.id).window('close');

			return;
		}

		if (!zapjs.f.exist(s.id)) {

			var sText = '<div id="' + s.id + '"  class="easyui-window" title="' + s.title + '"  data-options="iconCls:\'icon-save\',modal:true"></div>';

			$(document.body).append(sText);
		}
		
		if(s.url&&s.type=="iframe")
		{
			s.content='<iframe src="'+s.url+'" frameborder="0" style="width:100%;height:98%;"></iframe>';
		}
		

		$('#' + s.id).html(s.content);

		$('#' + s.id).window({
			width : s.width,
			height : s.height,
			modal : true,
			closed : false,
			onClose:function()
			{
				// 修正 关闭时强制删除所有元素
				var oParent=$('#'+s.id).parent('.panel');
				oParent.next('.window-shadow').remove();
				oParent.next('.window-mask').remove();
				oParent.remove();
			}
		});

		if (s.url&&s.type!='iframe') {
			$('#' + s.id).window('refresh', s.url);
		}

	},

	// 提示消息
	message : function(sContent, okfunc) {
		zapjs.f.modal({
			content : sContent,
			okfunc : okfunc,
			id:'zapjs_f_id_modal_message'
		});
	},

	// 关闭模态窗口
	modal_close:function(sId)
	{
		zapjs.f.modal(
			{
			id:sId,
			close:true}
			
		);
	},
	// 打开模态窗口
	modal:function(options) {

		zmapi.m.alert(false,options);

	},

	upset : function(sSet, sKey) {
		return zapjs.f.urlget(sKey, '?' + sSet);
	},

	/*
	 * zapjs 基本功能及基本插件 其中zapjs.f 表示扩展功能 @param sKey Url参数 @param sUrl Url链接
	 * 如果传空或者不传时为当前链接 @return 参数的值
	 */
	urlget : function(sKey, sUrl) {

		var sReturn = "";

		if (!sUrl) {
			sUrl = zapjs.f.upurl();
			if (sUrl.indexOf('?') < 1) {
				sUrl = sUrl + "?";
			}
		}

		var sParams = sUrl.split('?')[1].split('&');

		for (var i = 0, j = sParams.length; i < j; i++) {

			var sKv = sParams[i].split("=");
			if (sKv[0] == sKey) {
				sReturn = sKv[1];
				break;
			}
		}

		return sReturn;

	},
	//
	split:function(sValue,sSplit){
		
		return sValue==""?[]:sValue.split(sSplit);
		
	},
	

	// url替换 如果没有的话会自动添加 如果值为空则自动删除
	urlreplace : function(sUrl, sKey, sValue) {
		if (sUrl == "") {
			sUrl = zapjs.f.upurl();
		}
		if (sUrl.indexOf('?') < 1) {
			sUrl = sUrl + "?";
		}

		var sParams = sUrl.split('?')[1].split('&');
		var bFlagCon = -1;

		var sAddStr = sKey + "=" + sValue;

		for (var i = 0, j = sParams.length; i < j; i++) {

			var sKv = sParams[i].split("=");
			if (sKv[0] == sKey) {
				bFlagCon = i;
				sParams[i] = sAddStr;
				break;
			}
		}

		if (bFlagCon == -1 && sValue != "") {
			sParams.push(sAddStr);
		} else if (bFlagCon > -1 && sValue == "") {
			sParams.splice(bFlagCon, 1);
		}

		return sUrl.split('?')[0] + "?" + sParams.join("&");
	}
};

if ( typeof define === "function" && define.amd) {
	define("zapjs/zapjs", [], function() {
		return zapjs;
	});
}
