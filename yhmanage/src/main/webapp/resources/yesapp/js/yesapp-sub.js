var yesapp_sub = {

	temp : {
		page_name : '',
		text_id : ''

	},

	init_sub_select : function() {

	},

	sub_select_click : function(sText) {

		zmapi.m.setprefs(zmapi.c.event.sub_select, sText);

		zmapi.m.getprefs(zmapi.c.event.sub_func, function(sText) {

			zmapi.m.execjs(sText);

			zmapi.p.close_window();
		});
	},

	init_sub_mould : function(sPage, sType, sId) {

		$('#' + sId).prev('label').append(
				'<a href="javascript:yesapp_sub.sub_mould_click(\'' + sPage
						+ '\',\'' + sType + '\',\'' + sId + '\')">模板</a>');

	},
	sub_mould_click : function(sPage, sType, sId) {

		yesapp_sub.temp.text_id = sId;

		zmapi.m.setprefs(zmapi.c.event.sub_func, sPage
				+ ":yesapp_sub.sub_mould_select()");

		zmjs.page.open_page('sub-select?u_mould_type=' + sType);

	},
	sub_mould_select : function() {

		zmapi.m.getprefs(zmapi.c.event.sub_select, function(sSelect) {

			var sText = $('#' + yesapp_sub.temp.text_id).val();
			autosize($('#' + yesapp_sub.temp.text_id));
			$('#' + yesapp_sub.temp.text_id).val(sText + sSelect);
			autosize.update($('#' + yesapp_sub.temp.text_id));
		});
	}

};
