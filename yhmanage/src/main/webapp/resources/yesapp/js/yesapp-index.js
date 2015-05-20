apiready = function() {
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

	/*
	 * var push = api.require('push'); push.bind({ userName:'testName',
	 * userId:'testId' },function(ret,err){ if(ret){
	 * //api.alert({msg:ret.status}); }else{ api.alert({msg:err.msg}); } });
	 * push.setListener( function(ret,err){ if(ret){ api.alert({msg:ret.data}); } } );
	 */

	// var user_token = $api.val($api.dom('#yesapp_mobile_index_user_token'));
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