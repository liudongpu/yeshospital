 <#include "../zapmacro/zapmacro_mobile.ftl" />
 <#include "../macro/macro_mobile.ftl" />

<@m_mobile_html_begin  p_css=["yesapp/css/yes-gencustom.css"]/>
<@m_mobile_body_begin />

<#assign a_memberCode=RequestParameters['u_member_code']?default("") >
<#assign a_optType=RequestParameters['u_opt_type']?default("") >

<@m_mobile_header_begin p_title="用户操作" />
	<@m_mobile_button_back />
	 
	
<@m_mobile_header_end />





<div class="zmcss_o_a zmcss_w_96">
	<div class="zmcss_h_20"></div>

			<div class="yh_people_link_box">
            	<div class="zmcss_h_20"></div>
            	<div class="zmcss_h_20"></div>
            	<#if (a_optType=="") >
            	<div class="yh_people_link_item">
            		<div  class="yh_people_link_left"><img src="${a_macro_mobile_img_link}resources/yesapp/iconpeople/4.png"/></div>
            		<div  class="yh_people_link_right">
						
						<span>个人信息  ：</span> <a <@m_mobile_a_href p_page="member-info?u_member_code=${a_memberCode}" /> class="ui-btn ui-btn-inline">查看</a>
						
            		</div>
            		
            	</div>
            	
            	<div class="yh_people_link_item">
            		<div  class="yh_people_link_left"><img src="${a_macro_mobile_img_link}resources/yesapp/iconpeople/6.jpg"/></div>
            		<div  class="yh_people_link_right">
						
						<span>相册信息  ：</span> <a <@m_mobile_a_href p_page="people-pic?u_member_code=${a_memberCode}" /> class="ui-btn ui-btn-inline">照片管理</a>
						
            		</div>
            		
            	</div>
            	</#if>
            	<div class="yh_people_link_item">
            		<div  class="yh_people_link_left"><img src="${a_macro_mobile_img_link}resources/yesapp/iconpeople/1.png"/></div>
            		<div  class="yh_people_link_right">
						<span>体温信息  ：</span> <a <@m_mobile_a_href p_page="../mb/page_add_m_yh_post_temperature?zw_f_member_code=${a_memberCode}" /> class="ui-btn ui-btn-inline">添加</a>
						<a <@m_mobile_a_href p_page="../mb/page_chart_m_yh_post_temperature?zw_f_member_code=${a_memberCode}" /> class="ui-btn ui-btn-inline">列表</a>
            		</div>
            		
            	</div>
            	<div class="yh_people_link_item">
            		<div  class="yh_people_link_left"><img src="${a_macro_mobile_img_link}resources/yesapp/iconpeople/3.png"/></div>
            		<div  class="yh_people_link_right">
						<span>血糖信息  ：</span> <a <@m_mobile_a_href p_page="../mb/page_add_m_yh_post_glucose?zw_f_member_code=${a_memberCode}" /> class="ui-btn ui-btn-inline">添加</a>
						<a <@m_mobile_a_href p_page="../mb/page_chart_m_yh_post_glucose?zw_f_member_code=${a_memberCode}" /> class="ui-btn ui-btn-inline">列表</a>
            		</div>
            		
            	</div>
            	<div class="yh_people_link_item">
            		<div  class="yh_people_link_left"><img src="${a_macro_mobile_img_link}resources/yesapp/iconpeople/2.png"/></div>
            		<div  class="yh_people_link_right">
						<span>血氧信息  ：</span> <a <@m_mobile_a_href p_page="../mb/page_add_m_yh_post_oxygen?zw_f_member_code=${a_memberCode}" /> class="ui-btn ui-btn-inline">添加</a>
						<a <@m_mobile_a_href p_page="../mb/page_chart_m_yh_post_oxygen?zw_f_member_code=${a_memberCode}" /> class="ui-btn ui-btn-inline">列表</a>
            		</div>
            		
            	</div>
            	<div class="yh_people_link_item">
            		<div  class="yh_people_link_left"><img src="${a_macro_mobile_img_link}resources/yesapp/iconpeople/5.png"/></div>
            		<div  class="yh_people_link_right">
						<span>血压信息  ： </span> <a <@m_mobile_a_href p_page="../mb/page_add_m_yh_post_pressure?zw_f_member_code=${a_memberCode}" /> class="ui-btn ui-btn-inline">添加</a>
						<a <@m_mobile_a_href p_page="../mb/page_chart_m_yh_post_pressure?zw_f_member_code=${a_memberCode}" /> class="ui-btn ui-btn-inline">列表</a>
            		</div>
            		
            	</div>
            	
            	
            	<div class="zmcss_h_20"></div>
            </div>
	</div>
</div>
	
	
	
	
	
 </div>

 <@m_mobile_body_end  />
 

 <@m_mobile_html_end />






