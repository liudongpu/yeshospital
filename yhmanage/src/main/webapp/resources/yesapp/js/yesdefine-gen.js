yesapp.y_define = {

	frams : [ {
		name : 'frame-people',
		url : '../mobile/frame-people'
	}, {
		name : 'frame-daily',
		url : '../mobile/frame-daily'
	}, {
		name : 'frame-discover',
		url : '../mobile/frame-discover'
	}, {
		name : 'frame-my',
		url : '../mobile/frame-my'
	} ],

	base_init : 'root.frame-people:yesapp_frame.init_frame_people()',
	channel:'gen',
	qrcode_link : 'people-link'

};
