var zmjs = {};
window.zmjs = zmjs;

zmjs.temp = {

};

zmjs.config = {
	form_add : 'zw_f_'
};

zmjs.a = {

	log : function(o) {
		console.log(o);
	}
};

zmjs.data = {

	save : function(sKey, sVal) {
		zapjs.f.cookie(sKey, sVal);
	},
	read : function(sKey) {
		return zapjs.f.cookie(sKey);
	}

};

zmjs.func = {

	convert_url : function(sUrl) {
		
		while ((sUrl.indexOf('/') > -1)) {
			
			sUrl = sUrl.substring(sUrl.indexOf('/')+1);
			
		}
		if (sUrl.indexOf('.') > -1) {
			sUrl = sUrl.substr(0, sUrl.indexOf('.'));
		}

		if (sUrl.indexOf('?') > -1) {
			sUrl = sUrl.substr(0, sUrl.indexOf('?'));
		}

		return sUrl;

		// return sUrl.replace('../', '').replace('/', '-');
	}

};

zmjs.page = {

	to_link : function() {
	},
	open_page : function(sUrl) {

		zmapi.d({
			target : 'zmjs.page.open_page',
			name : zmjs.func.convert_url(sUrl)
		});

		// zmapi.m.alert(zmjs.func.convert_url(sUrl));

		zmapi.p.open_page(sUrl, zmjs.func.convert_url(sUrl));
	},

	back_page : function(sName) {
		zmapi.p.close_window(sName);
	},
	refresh_page : function(sName) {
		location.href = location.href;
	},

	back_root : function() {
		zmapi.p.close_goto("root");
	}

};

zmjs.ui = {

	dialog_open : function(sId) {

		$("#" + sId).popup("open", {});

	},
	dialog_close : function(sId) {

		$("#" + sId).popup("close");

	}

};

zmjs.form = {

	set : function(sId, sVal) {
		$('#' + zmjs.config.form_add + sId).val(sVal);
	},
	get : function(sId) {
		return $('#' + zmjs.config.form_add + sId).val();
		2
	}

};
