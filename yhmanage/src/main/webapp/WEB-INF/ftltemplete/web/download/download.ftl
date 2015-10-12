 <#include "../../macro/macro_wx.ftl" />


<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0"/>
    <title>yinxl.com</title>
	<@m_wx_html_css ["lib/bootstrap-3.3.5-dist/css/bootstrap.min.css"] />
	<@m_wx_html_js ["mlib/jquery/jquery-2.1.4.min.js","lib/bootstrap-3.3.5-dist/js/bootstrap.min.js"] />

</head>
<body>

<div class="container" style="">
      <div class="header clearfix">
        
        <h3 class="text-muted">下载导航</h3>
      </div>

	<div class="progress">
	<div class="progress-bar progress-bar-warning progress-bar-striped" style="width: 25%">
    <span class="sr-only">25% Complete (warning)</span>
  </div>
	<div class="progress-bar progress-bar-success progress-bar-striped" style="width: 25%">
    <span class="sr-only">25% Complete (success)</span>
  </div>
  
  <div class="progress-bar progress-bar-danger progress-bar-striped" style="width: 25%">
    <span class="sr-only">25% Complete (danger)</span>
  </div>
  <div class="progress-bar progress-bar-info progress-bar-striped" style="width: 25%">
    <span class="sr-only">25% Complete (danger)</span>
  </div>
	</div>
	<div style="display:none;">
		<img src="../../resources/yesapp/demo/my-bg.jpg" alt="..." class="img-circle" style="width:100%"/>
	</div>
	<div style="height:50px;"></div>


      <div class="row">
        <div class="col-xs-6">
          <h4>银杏医助</h4>
          <p><a href="http://fir.im/yxyz" class="btn btn-success btn-lg" role="button">下载银杏医助</a></p>

         
        </div>

        <div class="col-xs-6">
          <h4>银杏养助 </h4>
          <p><a href="http://fir.im/yxlyz" class="btn btn-danger btn-lg" role="button">下载银杏养助</a></p>

         
        </div>
      </div>

      <footer class="footer">
        <p>&copy; Company 2015</p>
      </footer>



<script>

var d_proc_index=0;
function d_pro()
{
	$('#d_progress').width(Math.random()*100+"%");
	
	/*
	d_proc_index+=10;
	if(d_proc_index>100)
	{
		d_proc_index=0;
	}
	$('#d_progress').width(d_proc_index+"%");
	*/
};

function d_init()
{
	//setInterval(d_pro,300);
};

$(function(){d_init();});


</script>

</body>
</html>