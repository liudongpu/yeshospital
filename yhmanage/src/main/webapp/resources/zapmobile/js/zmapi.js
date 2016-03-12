var zmapi = {

};
window.zmapi = zmapi;
// 配置
zmapi.c = {

	// 标记是否加载的apicloud版
	flag_api : true,

	// 是否加载debug模式
	flag_debug : false,

	// 绑定推送编号
	push_token : '',

	name_storage_debug : 'zmapi_name_storage_debug',
	// 事件名称
	event : {
		// 退出事件
		login_out : 'zmapi_event_login_out',
		// 登陆事件
		login_success : 'zmapi_event_loin_success',
		// 异步执行事件
		exec_js : 'zmapi_event_exec_js',
		// 打开页面的事件
		open_page : 'zmapi_event_open_page',
		// 收到push消息的事件
		push_msg : 'zmapi_event_push_msg',

		temp_event : '',
		
		sub_select:'zmapi_event_sub_select'
	}
};

zmapi.d = function(oInfo) {
	// 如果启用了调试模式
	if (zmapi.c.flag_debug) {
		var oElm = zmapi.f.readstorage(zmapi.c.name_storage_debug);

		var sDebug = zmapi.f.readstorage(zmapi.c.name_storage_debug);

		var oElm = {
			items : []
		};

		if (sDebug) {
			oElm = JSON.parse(sDebug);
		}

		var oItem = {
			time : new Date(),
			info : oInfo
		};

		if (zmapi.c.flag_api) {
			oItem.page = api.winName;
		}

		oElm.items.push(oItem);
		// zmapi.m.toast(JSON.stringify(oItem));
		sDebug = JSON.stringify(oElm);

		zmapi.f.savestorage(zmapi.c.name_storage_debug, sDebug);

		console.log(sDebug);

	}
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

			zmapi.m.ready(function() {
				// 开始解决IOS7以上的工具条问题
				var systemType = api.systemType;

				if (systemType == "ios") {

					var sVersion = parseInt(api.systemVersion);

					// 如果是IOS7以上版本
					/*
					 * if (sVersion >= 7) {
					 * $('#zmcss_mm_page_header').addClass('zmcss_ios_t_20');
					 * $('#zmcss_mm_page_header').after( '<div
					 * class="zmcss_ios_nav"></div>'); }
					 */
				}
			});

		} else {
			// alert(navigator.userAgent);
			zmapi.c.flag_api = false;
			// 如果不是手机版 则默认开启调试模式
			zmapi.f.opendebug();
		}

	},

	opendebug : function() {

		zmapi.c.flag_debug = true;
		// zmapi.f.savestorage(zmapi.c.name_storage_debug, '');
	},

	// 本地存储 h5版
	savestorage : function(sName, oTarget) {
		localStorage.setItem(sName, oTarget);
	},
	// 读取本地存储 h5版
	readstorage : function(sName) {
		return localStorage.getItem(sName);
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

	open_frame : function(sName, sUrl) {
		api.openFrame({
			name : sName,
			url : sUrl,
			bounces : false

		});

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

			// zmapi.m.sendevent(zmapi.c.event.login_success, {});

			api.closeFrame({
				name : 'user-login'
			});

		} else {
			history.go(-1);
		}
	},
	login_out : function() {
		if (zmapi.c.flag_api) {

			var winName = api.winName;
			if (winName != 'root') {
				api.closeToWin({
					name : 'root'
				});
			}

			zmapi.m.sendevent(zmapi.c.event.login_out, {});

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

	confirm : function(f_ok, options) {

		var defaults = {
			title : '提示消息',
			msg : '确认要执行该操作?',

			oktext : '确认',
			canceltext : '取消',

			okfunc : null
		};
		var s = $.extend({}, defaults, options || {});

		if (zmapi.c.flag_api) {
			api.confirm({
				title : s.title,
				msg : s.msg,
				buttons : [ s.oktext, s.canceltext ]
			}, function(ret, err) {
				if (ret) {

					if (ret.buttonIndex == 1) {
						f_ok();
					}

				} else {
					// alert(JSON.stringify(err));
				}
			});
		} else {
			if (confirm(s.msg)) {
				f_ok();
			}
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

		zmapi.d({
			'target_name' : 'zmapi.m.execjs',
			'js' : sExecDo
		});

		if (zmapi.c.flag_api) {
			// api.execScript(oExec);
			var aTarget = sExecDo.split(':');

			var oSet = {
				name : aTarget[0],
				// frameName : aTarget[0],
				script : aTarget[1]
			};
			if (aTarget[0].indexOf('.') > -1) {
				var aName = aTarget[0].split('.');

				oSet.name = aName[0];
				oSet.frameName = aName[1];

			}
			// api.execScript(oSet);
			// zmapi.m.alert(sExecDo);
			zmapi.m.sendevent(zmapi.c.event.exec_js, oSet);

		}
	},
	setprefs : function(sKey, sVal) {
		api.setPrefs({
			key : sKey,
			value : sVal
		});
	},
	// 获取属性值
	getprefs : function(sKey, fCall) {
		api.getPrefs({
			key : sKey
		}, function(ret, err) {
			var v = ret.value;
			fCall(v);
		});
	},

	// 添加事件监听
	addevent : function(sName, fCall) {

		zmapi.d({
			'target_name' : 'zmapi.m.addevent',
			'event_name' : sName
		});

		if (zmapi.c.flag_api) {
			api.addEventListener({
				name : sName
			}, function(ret, err) {
				var v = ret.value;
				fCall(v);
			});
		}
	},
	// 发送事件
	sendevent : function(sName, oJson) {

		zmapi.d({
			'target_name' : 'zmapi.m.sendevent',
			'event_name' : sName,
			'event_data' : oJson
		});

		if (zmapi.c.flag_api) {
			api.sendEvent({
				name : sName,
				extra : oJson
			});
		}

	},

	scrolltobottom : function(f) {
		if (zmapi.c.flag_api) {
			api.addEventListener({
				name : 'scrolltobottom'
			}, function(ret, err) {
				f();
			});
		}
	},
	/*
	 * 封装ready操作
	 */
	ready : function(f) {
		apiready = function() {
			f();
		};
	}

};

zmapi.f.init();
