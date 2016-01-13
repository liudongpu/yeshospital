 <#include "../zapmacro/zapmacro_mobile.ftl" />
 <#include "../macro/macro_mobile.ftl" />

<@m_mobile_html_begin  p_css=["yesapp/css/yes-gencustom.css"]/>
<@m_mobile_body_begin />

<#assign a_memberCode=RequestParameters['u_member_code']?default("") >
<@m_mobile_header_begin p_title="用户图片" />
	<@m_mobile_button_back />
	 
	
<@m_mobile_header_end />





<div class="zmcss_o_a zmcss_w_96">
	<div class="zmcss_h_20"></div>

			<div class="">
            	
            	
            	
            	<input type="hidden"   id="people-pic" name="people-pic" value="">
		
			<div class="zapweb_webfile">
    		<!--用来存放item-->
    			<div id="people-pic_fileList" class="zapweb_webfile_list"></div>
    			<div id="people-pic_filePicker">上传用户图片</div>
			</div>
			
            	
            	
            	<script type="text/javascript" src="${a_macro_mobile_resources_link}resources/lib/webupload/webuploader.min.js"></script>
			<script type="text/javascript" src="${a_macro_mobile_resources_link}resources/yesapp/js/yesapp-pic.js"></script>
			
			<script>
			
			
			
			
			
			$(function(){yesapp_pic.upload('people-pic')});
			
			
			
			</script>
            	
            	
            	
            	<div class="zmcss_h_20"></div>
            </div>
	</div>
</div>
	
	
	
	
	
 </div>

 <@m_mobile_body_end  />
 

 <@m_mobile_html_end />






