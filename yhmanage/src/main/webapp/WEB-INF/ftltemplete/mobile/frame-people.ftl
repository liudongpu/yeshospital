 <#include "../macro/macro_mobile.ftl" />




<@m_mobile_html_begin   />
<@m_mobile_body_begin />
<div class=""  data-role="page">
	<div class="zmcss_h_10"></div>
	<div class="zmcss_w_96 ">
	  <div style="width:86%;" class="zmcss_f_left">
 	<input id="yesapp_ts_search" type="search" placeholder="请输入老人姓名" <@m_mobile_event_input "yesapp_frame.search_frame_people(this)"/> />
	</div>
	<div style="width:10%;" class="zmcss_f_right">
	
	<a href="javascript:yesapp.scanner_code()" class="ui-btn ui-shadow ui-corner-all ui-icon-camera ui-btn-icon-notext">Scan</a>
   		
	</div>
	<div class="zmcss_h_10"></div>
	<div class="yb_list_box">
		<ul data-role="listview" data-inset="true" id="yesapp_ts_table">
		</ul>
	</div>
	<div class="zmcss_h_20"></div>
	</div>
	<div class="zmcss_h_10"></div>
	
	
	
	
	
	
	
	

</div>
 
 

    
 <@m_mobile_body_end p_js=["yesapp/js/yesapp-frame.js","yesapp/js/yesdefine-gen.js"]/>
 
 <@m_mobile_html_initjs "yesapp_frame.init_frame_people() "/>
 
 
 <@m_mobile_html_end />