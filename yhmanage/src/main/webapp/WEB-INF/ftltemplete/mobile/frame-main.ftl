 <#include "../macro/macro_mobile.ftl" />




<@m_mobile_html_begin  p_js=["yesapp/js/yesapp-frame.js"] />
<@m_mobile_body_begin />
<div class=""  data-role="page">
	<div class="zmcss_h_10"></div>
	<div class="zmcss_w_96 ">
	 <button id="fm-create" class="ui-btn ui-btn-inline zmcss_f_right" <@m_mobile_call_page p_page="../mb/page_add_m_yh_tour_order_info" /> >
  		创建一个巡视
  		
	</button>
	</div>
	<div class="zmcss_h_10"></div>
	
	<div class="yb_list_box">
	<ul data-role="listview" data-inset="true"  id="yesapp_fm_main_list" >
    
	</ul>
	</div>
	
	
	
	
	
	

</div>
 
 

    
 <@m_mobile_body_end />
 
 <@m_mobile_html_initjs "yesapp_frame.init_frame_main() "/>
 
 
 <@m_mobile_html_end />