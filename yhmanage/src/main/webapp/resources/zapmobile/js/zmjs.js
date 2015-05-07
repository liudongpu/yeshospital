
















var zmjs = {};
window.zmjs = zmjs;

zmjs.temp = {

};

zmjs.config = {

};

zmjs.a = {

	log : function(o) {
		console.log(o);
	}
};

zmjs.func = {

	convert_url : function(sUrl) {
		return sUrl.replace('../', '').replace('/', '-');
	}

};

zmjs.page = {

	to_link : function() {
	},
	open_page : function(sUrl) {

		zmjs.a.log(zmjs.func.convert_url(sUrl));

		zmapi.p.open_page(sUrl, zmjs.func.convert_url(sUrl));
	},
	
	
	back_page : function(sName) {
		zmapi.p.close_window(sName);
	},
	back_root : function() {
		zmapi.p.close_goto("root");
	}

};

zmjs.ui = {

};
