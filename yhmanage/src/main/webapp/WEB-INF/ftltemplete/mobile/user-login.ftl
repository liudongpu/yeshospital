<#include "../macro/macro_mobile.ftl" />




<@m_mobile_html_begin />
<@m_mobile_body_begin />




<@m_mobile_header_begin p_title="用户登陆" />
<@m_mobile_header_end />


<div class="zmcss_o_a zmcss_w_96">
<div class="zmcss_h_20"></div>
<form class="form-horizontal" method="POST" >

		
	

<div class="control-group">
	<label class="control-label" for="zw_f_tour_code">用户名：</label>
	<div class="controls">

		<input type="text"id="zw_f_login_name" name="zw_f_login_name"  value="">
	</div>
</div>
	  	
		
	

<div class="control-group">
	<label class="control-label" for="zw_f_create_time">密码：</label>
	<div class="controls">

		<input type="password"
										 id="zw_f_login_pass"
										name="zw_f_login_pass"   value="">
	</div>
</div>
	  	
		
	


	  	
	
</form>
<input type="hidden" id="zapjs_zw_login_sucess_target" value="mobile-index.html"/>
 <button class="btn btn-positive btn-block" onclick="zapjs.zw.login_post(this)">登陆</button>

 </div>




 <@m_mobile_body_end />
 

 
 <@m_mobile_html_end />