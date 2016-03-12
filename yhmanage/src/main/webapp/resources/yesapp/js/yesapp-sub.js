var yesapp_sub = {

	init_sub_select : function() {

	},

	sub_select_click : function(sText) {

		zmapi.m.setprefs('yesapp-sub-select-text', sText);
		zmapi.m.execjs('tour-member:yesapp_tour.tour_sub_select_text()');
		zmapi.p.close_window();
	}

};
