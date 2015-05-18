var zmapi = {

};
window.zmapi = zmapi;
zmapi.c = {
	flag_api : true
};

zmapi.f = {

	init : function() {

		var browser = {
			versions : function() {
				var u = navigator.userAgent;
				return {// 移动终端浏览器版本信息
					trident : u.indexOf('Trident') > -1, // IE内核
					presto : u.indexOf('Presto') > -1, // opera内核
					webKit : u.indexOf('AppleWebKit') > -1, // 苹果、谷歌内核
					gecko : u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, // 火狐内核
					mobile : !!u.match(/AppleWebKit.*Mobile.*/), // 是否为移动终端
					ios : !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), // ios终端
					android : u.indexOf('Android') > -1
							|| u.indexOf('Linux') > -1, // android终端或者uc浏览器
					iPhone : u.indexOf('iPhone') > -1, // 是否为iPhone或者QQHD浏览器
					iPad : u.indexOf('iPad') > -1, // 是否iPad
					webApp : u.indexOf('Safari') == -1
				// 是否web应该程序，没有头部与底部
				};
			}(),
			language : (navigator.browserLanguage || navigator.language)
					.toLowerCase()
		}

		if (browser.versions.mobile || browser.versions.ios
				|| browser.versions.android || browser.versions.iPhone
				|| browser.versions.iPad) {
			
			// 开始解决IOS7以上的工具条问题
			var systemType = api.systemType;
			
			if (systemType == "ios") {

				var sVersion = parseInt(api.systemVersion);
				
				// 如果是IOS7以上版本
				if (sVersion >= 7) {
					$('#zmcss_mm_page_header').addClass('zmcss_ios_t_20');
					$('#zmcss_mm_page_header').after('<div class="zmcss_ios_nav"></div>');
				}

			}

		} else {
			// alert(navigator.userAgent);
			zmapi.c.flag_api = false;
		}

	}

};

zmapi.p = {
	open_page : function(sUrl, sName) {

		if (zmapi.c.flag_api) {

			api.openWin({
				name : sName,
				url : sUrl,
				bgColor : "#ffffff"
			});
		} else {
			location.href = sUrl;
		}
	},
	close_window : function(sName) {
		if (zmapi.c.flag_api) {

			api.closeWin();

		} else {
			history.go(-1);
		}
	},
	close_goto : function(sName) {
		if (zmapi.c.flag_api) {

			if (sName) {
				api.closeToWin({
					name : sName
				});
			} else {
				api.closeWin();
			}

		} else {
			history.go(-1);
		}
	},
	user_login : function() {

		api.openFrame({
			name : 'user-login',
			url : 'user-login',
			bounces : false

		});

	},
	login_success : function() {

		if (zmapi.c.flag_api) {
			api.closeFrame({
				name : 'user-login'
			});
		} else {
			history.go(-1);
		}
	},
	login_out : function() {
		if (zmapi.c.flag_api) {
			
			//zmapi.m.execjs('root:zmapi.p.user_login()');
			
			api.closeToWin({
				name : 'root'
			});
			
			
			
		} else {
			history.go(-1);
		}
	}

};

zmapi.m = {

	alert : function(sContent, options) {

		var defaults = {
			title : '提示消息',
			content : '',
			flagbutton : true,
			oktext : '确认',
			canceltext : '取消',
			close : false,
			okfunc : '',
			width : 400,
			id : 'zapjs_f_id_modal_box',
			cancelfunc : ''
		};
		var s = $.extend({}, defaults, options || {});
		if (sContent)
			s.content = sContent;
		if (zmapi.c.flag_api) {
			api.alert({
				title : s.title,
				msg : s.content,
				buttons : [ s.oktext ]
			}, function(ret, err) {
				if (ret.buttonIndex == 1) {

				}
			});
		} else {
			alert(s.content);
		}
	},
	toast : function(sContent) {
		if (zmapi.c.flag_api) {
			api.toast({
				msg : sContent,
				duration : 2000,
				location : 'bottom'
			});
		} else {
			alert(sContent);
		}
	},

	execjs : function(sExecDo) {
		if (zmapi.c.flag_api) {
			// api.execScript(oExec);
			var aTarget = sExecDo.split(':');

			var oSet = {
				name : aTarget[0],
				script : aTarget[1]
			};
			if (aTarget[0].indexOf('.') > -1) {
				var aName = aTarget[0].split('.');

				oSet.name = aName[0];
				oSet.frameName = aName[1];

			}
			api.execScript(oSet);

		}
	},
	setprefs : function(sKey, sVal) {
		api.setPrefs({
			key : sKey,
			value : sVal
		});
	},
	getprefs : function(sKey, fCall) {
		api.getPrefs({
			key : sKey
		}, function(ret, err) {
			var v = ret.value;
			fCall(v);
		});
	}

};

zmapi.f.init();
