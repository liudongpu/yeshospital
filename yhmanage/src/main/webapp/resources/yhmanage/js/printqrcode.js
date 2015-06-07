
var printqrcode = {
	init : function() {
		
		
		$('label[for=zw_f_f_qrcode]').parent().hide();
		
		
		var sUrl = zapjs.f.upurl().replace("/page/", "/yhqrcode/");
		
		var sText = '&nbsp;&nbsp;&nbsp;&nbsp;<a href="'+sUrl+'" target="_blank">生成二维码图片</a>';
		
		
		$('input[zapweb_attr_operate_id=3d64819ff27511e4a992005056165049]').after(sText);
		
	}
};

$(

function() {
	printqrcode.init()

});
