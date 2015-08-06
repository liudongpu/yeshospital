apiready = function() {

	var sFlagDebug = $('#yesapp_mobile_index_flag_debug').val();

	if (sFlagDebug == "1") {
		// zmapi.m.alert('调试模式开启');
		zmapi.f.opendebug();

		$('header').click(
				function() {
					zmapi.p.open_page('system-debug?r=' + Math.random(),
							'system-debug');
				});

		// 注册android下的菜单键
		api.addEventListener({
			name : 'keymenu'
		}, function(ret, err) {
			// zmapi.p.open_page('system-debug', 'system-debug');
		})

	}

	var $header = $api.dom('header');
	$api.fixIos7Bar($header);
	var $body = $api.dom('body');
	var $footer = $api.byId('footer');
	var header_h = $api.offset($header).h;
	var body_h = $api.offset($body).h;
	var footer_h = $api.offset($footer).h;
	var rect_h = body_h - header_h - footer_h;

	api.openFrameGroup({
		name : 'group',
		scrollEnabled : false,
		
		rect : {
			x : 0,
			y : header_h,
			w : 'auto',
			h : rect_h
		},
		index : 0,
		frames : [ {
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
		} ]
	}, function(ret, err) {
	});

	// 绑定异步执行事件
	zmapi.m.addevent(zmapi.c.event.exec_js, function(oSet) {

		// zmapi.m.execjs(sVal.execinfo);

		api.execScript(oSet);

	});

	/*
	 * var push = api.require('push'); push.bind({ userName:'testName',
	 * userId:'testId' },function(ret,err){ if(ret){
	 * //api.alert({msg:ret.status}); }else{ api.alert({msg:err.msg}); } });
	 * push.setListener( function(ret,err){ if(ret){ api.alert({msg:ret.data}); } } );
	 */

	// var user_token = $api.val($api.dom('#yesapp_mobile_index_user_token'));
	// 绑定登陆成功时执行操作
	zmapi.m.addevent(zmapi.c.event.login_success, function(oVal) {

		var s_push_token = oVal.push_token;

		if (s_push_token != zmapi.c.push_token) {

			s_push_token = s_push_token.substring(0, 32);
			zmapi.d({
				'target_name' : 'yesapp-index.push.begin',
				'token' : s_push_token
			});
			zmapi.c.push_token = s_push_token;

			var push = api.require('push');
			push.bind({
				userName : s_push_token,
				userId : s_push_token
			}, function(ret, err) {
				if (ret) {

					zmapi.d({
						'target_name' : 'yesapp-index.push.bind',
						'ret' : ret
					});

				} else {
					api.alert({
						msg : err.msg
					});
				}
			});

			push.setListener(function(rev, err2) {
				if (rev) {
					zmapi.d({
						'target_name' : 'yesapp-index.push.listener',
						'rev' : rev
					});
					zmapi.m.sendevent(zmapi.c.event.push_msg, rev.data);

				} else {
					api.alert({
						msg : err2.msg
					});
				}
			});

		}
		;

		zmapi.m.execjs('root.frame-main:yesapp_frame.refresh_frame_main()');
	});

	var user_token = "";

	if (user_token == "") {

		var iNumber = 0;

		zmapi.m.getprefs(

		zapjs.c.cookie_user, function(sValue) {

			if (!sValue) {
				zmapi.p.user_login();
			} else {
				if (iNumber == 0) {
					iNumber = 1;
					zapjs.zw.login_save(sValue);
				}
			}
		});

		// zapjs.zw.check_login();

	}
	;

	// 添加退出检测判断事件
	zmapi.m.addevent(zmapi.c.event.login_out, function(sVal) {
		zmapi.p.user_login();
	}

	);

	// 添加收到push消息时的处理事件
	zmapi.m.addevent(zmapi.c.event.push_msg, function(sVal) {
		// zmapi.p.user_login();

		

	}

	);

}
// 随意切换按钮
function randomSwitchBtn(obj, name, index) {
	var $header = $api.dom('header');
	var $titleBar = $api.domAll($header, '.topbar');
	for (var i = 0; i < $titleBar.length; i++) {
		$api.removeCls($titleBar[i], 'activebar');
	}
	$api.addCls($api.byId(name), 'activebar');
	var $footer = $api.byId('footer');
	var $footerBar = $api.domAll($footer, 'li');
	for (var j = 0; j < $footerBar.length; j++) {
		$api.removeCls($footerBar[j], 'active');
	}
	$api.addCls(obj, 'active');
	api.setFrameGroupIndex({
		name : 'group',
		index : index
	});

}