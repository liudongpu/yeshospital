/*
 * 滚动相关功能代码
 */

var zmscroll = {

	temp : {
		flag_process : 0
	},

	refresh_page : function(f) {
		myScroll = new IScroll('#mobile_base_scroll_wrap', {
			// probeType : 3,
			mouseWheel : true,
			// preventDefault:false,
			preventDefaultException : {
				tagName : /^(INPUT|TEXTAREA|BUTTON|SELECT|A|DIV|IMG)$/
			}

		});

		function updatePosition() {

			var iScreenHeight = parseInt(document
					.getElementById('mobile_base_scroll_wrap').clientHeight);
			var iMaxHeight = parseInt(document
					.getElementById('mobile_base_scroll_box').offsetHeight);

			if (iMaxHeight - iScreenHeight + this.y < 20) {
				// alert('end');

				// myScroll.refresh();
				if (zmscroll.temp.flag_process == 0) {
					zmscroll.temp.flag_process = 1;
					// zen.mobile.news_refresh();
					// console.log((zen.mobile.temp.refresh_func);
					f();
				}
			}

		}

		myScroll.on('scrollEnd', updatePosition);
		/*
		 * document.addEventListener('touchmove', function(e) {
		 * e.preventDefault(); }, false);
		 */

	},

	refresh_success : function(oResult) {
		zmscroll.temp.flag_process = 0;

		zen.mobile.temp.page_index = zen.mobile.temp.page_index + 1;
	}

};