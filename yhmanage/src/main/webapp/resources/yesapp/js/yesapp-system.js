var yesapp_system = {

	init_system_debug : function() {

		var sDebug = zmapi.f.readstorage(zmapi.c.name_storage_debug);
		var oElm = JSON.parse(sDebug);
		var aHtml = [];

		var oItems = oElm.items.reverse();

		for ( var i in oItems) {

			var o = oItems[i];

			aHtml.push('<div>' + JSON.stringify(oItems[i]) + '</div>');

		}

		$('#system_debug_box').html(aHtml.join('<br/>'));

	},
	system_debug_clear : function() {

		zmapi.f.savestorage(zmapi.c.name_storage_debug, '');
		location.href=location.href; 
	}

};
