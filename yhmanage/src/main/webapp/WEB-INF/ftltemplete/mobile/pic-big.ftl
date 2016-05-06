 <#include "../zapmacro/zapmacro_mobile.ftl" />
 <#include "../macro/macro_mobile.ftl" />

<@m_mobile_html_begin  p_css=["yesapp/css/yes-gencustom.css"]/>
<@m_mobile_body_begin />

<#assign a_picCode=RequestParameters['u_pic_code']?default("") >
<@m_mobile_header_begin p_title="图片" />
	<@m_mobile_button_back />
	 
	
<@m_mobile_header_end />

<@m_mobile_init_dbcall />
<#assign a_memberlist=b_method.upDataOne("yh_member_pic","uid,pic_url,create_time,pic_remark","","","uid",a_picCode)>



<div class="zmcss_o_a zmcss_w_96">
	<div class="zmcss_h_20"></div>

		<@m_zapmacro_mobile_form_hidden e_id="yesapp_pp_member_code" e_value=a_memberCode />


			<div class="">
            	
            	
            	<img src="${a_memberlist["pic_url"]}" style="width:100%;" />
            	
		
			
            	
            	
            	
            	
            	
            	<div class="zmcss_h_20"></div>
            	
            	
            </div>
	</div>
</div>
	
	
	
	
	
 </div>
 
 
 	
	
 
 

 <@m_mobile_body_end  />
 

 <@m_mobile_html_end />






