 <#include "../macro/macro_mobile.ftl" />




<@m_mobile_html_begin  p_css=["yesapp/css/yes-frame.css"]/>
<@m_mobile_body_begin  />
<div class=""  data-role="page">
	<div class="yh_my_img"><img src="${a_macro_mobile_img_link}resources/yesapp/demo/my-bg.jpg"/></div>
	
	<div class="yb_list_box" >
	<ul data-role="listview" data-inset="true">
    <li><a <@m_mobile_a_href p_page="my-info"/>><img src="${a_macro_mobile_img_link}resources/yesapp/icons/i-1.png" alt="" class="ui-li-icon ui-corner-none">基本信息 </a></li>
    <li><a href="#"><img src="${a_macro_mobile_img_link}resources/yesapp/icons/i-2.png" alt="Germany" class="ui-li-icon">设置</a></li>
    </ul>
	</div>
    <div class="zmcss_h_10"></div>
    <div class="yb_list_box" >
    <ul data-role="listview" data-inset="true">
    <li><a href="#"><img src="${a_macro_mobile_img_link}resources/yesapp/icons/i-3.png" alt="Great Britain" class="ui-li-icon">修改密码</a></li>
    <li><a href="#"><img src="${a_macro_mobile_img_link}resources/yesapp/icons/i-4.png" alt="Finland" class="ui-li-icon">意见反馈</a></li>
    <li><a href="#"><img src="${a_macro_mobile_img_link}resources/yesapp/icons/i-5.png" alt="United States" class="ui-li-icon ui-corner-none">关于</a></li>
	</ul>
	</div>
	<div class="zmcss_h_10"></div>
	
</div>
 
 

    
 <@m_mobile_body_end/>
 

 
 
 <@m_mobile_html_end />