var yesapp_pic = {

	upload : function(sId) {

		var sPickId = sId + "_filePicker";

		var $list = $('#' + sId + '_fileList'),
		// 优化retina, 在retina下这个值是2
		ratio = window.devicePixelRatio || 1,

		// 缩略图大小
		thumbnailWidth = 100 * ratio, thumbnailHeight = 100 * ratio,

		// Web Uploader实例
		uploader;

		// 初始化Web Uploader
		var uploader = WebUploader.create({

			// 选完文件后，是否自动上传。
			auto : true,

			// swf文件路径
			// swf: BASE_URL + '/js/Uploader.swf',

			// 文件接收服务端。
			server : '../yhupload/' + sPickId + "?zw_s_target=memberpic",

			// 选择文件的按钮。可选。
			// 内部根据当前运行是创建，可能是input元素，也可能是flash.
			pick : '#' + sPickId,

			compress : {
				width : 800,
				height : 800,

				// 图片质量，只有type为`image/jpeg`的时候才有效。
				quality : 80,

				// 是否允许放大，如果想要生成小图的时候不失真，此选项应该设置为false.
				allowMagnify : false,

				// 是否允许裁剪。
				crop : false,

				// 是否保留头部meta信息。
				preserveHeaders : true,

				// 如果发现压缩后文件大小比原来还大，则使用原来图片
				// 此属性可能会影响图片自动纠正功能
				noCompressIfLarger : false,

				// 单位字节，如果图片大小小于此值，不会采用压缩。
				compressSize : 0
			},

			// 只允许选择图片文件。
			accept : {
				title : 'Images',
				extensions : 'gif,jpg,jpeg,bmp,png',
				mimeTypes : 'image/*'
			}
		});

		// 文件上传过程中创建进度条实时显示。
		uploader.on('uploadProgress', function(file, percentage) {
			$('.webuploader-pick').text('正在上传中…… 请稍后');
		});

		// 文件上传成功，给item添加成功class, 用样式标记上传成功。
		uploader.on('uploadSuccess', function(file, response) {

			$('#' + file.id).addClass('upload-state-done');

			$('#' + sId).val(response.result.resultObject);

			yesapp.api_call('member_pic', {
				memberCode : $('#yesapp_pp_member_code').val(),
				picImg : response.result.resultObject
			}, yesapp_pic.show);

		});

		// 文件上传失败，显示上传出错。
		uploader.on('uploadError', function(file) {
			var $li = $('#' + file.id), $error = $li.find('div.error');

			// 避免重复创建
			if (!$error.length) {
				$error = $('<div class="error"></div>').appendTo($li);
			}

			$error.text('上传失败');
		});

		// 完成上传完了，成功或者失败，先删除进度条。
		uploader.on('uploadComplete', function(file) {
			// $('#' + file.id).find('.progress').remove();
		});

	},

	show : function(o_list) {
		var a_html = [];

		location.href = location.href;
	},

	del : function(s_uid) {

		zmapi.m.confirm(function() {
			yesapp.api_call('member_pic', {
				memberCode : $('#yesapp_pp_member_code').val(),
				deleteUid : s_uid
			}, yesapp_pic.show);
		});

	}

};
