 <#include "../zapmacro/zapmacro_mobile.ftl" />
 <#include "../macro/macro_mobile.ftl" />

<@m_mobile_html_begin  p_css=["yesapp/css/yes-gencustom.css"]/>
<@m_mobile_body_begin />

<#assign a_memberCode=RequestParameters['u_member_code']?default("") >
<@m_mobile_header_begin p_title="用户图片" />
	<@m_mobile_button_back />
	 
	
<@m_mobile_header_end />

<@m_mobile_init_dbcall />
<#assign a_memberlist=a_macro_mobile_dbcall.queryAll("yh_member_pic","uid,pic_url,create_time","-zid","","member_code",a_memberCode)>



<div class="zmcss_o_a zmcss_w_96">
	<div class="zmcss_h_20"></div>

		<@m_zapmacro_mobile_form_hidden e_id="yesapp_pp_member_code" e_value=a_memberCode />


			<div class="">
            	
            	
            	
            	<input type="hidden"   id="memberpic" name="memberpic" value="">
		
			<div class="zapweb_webfile">
    		<!--用来存放item-->
    			<div id="memberpic_fileList" class="zapweb_webfile_list"></div>
    			<div id="memberpic_filePicker">上传用户图片</div>
			</div>
			
            	
            	
            	<script type="text/javascript" src="${a_macro_mobile_resources_link}resources/lib/webupload/webuploader.min.js"></script>
			<script type="text/javascript" src="${a_macro_mobile_resources_link}resources/yesapp/js/yesapp-pic.js"></script>
			
			<script>
			
			
			
			
			
			$(function(){yesapp_pic.upload('memberpic')});
			
			
			
			</script>
            	
            	
            	
            	<div class="zmcss_h_20"></div>
            	
            	
            	<div id="yeaspp_pp_show">
            	
            		<ul data-role="listview" data-split-icon="delete" data-split-theme="a" data-inset="true">
            	
            		<#list a_memberlist as el>
            	
            			
            			<li><a href="#">
					        
					    <@m_mobile_html_img p_img=el["pic_url"] p_width=100/>
					    <p>上传时间：<br/>${el["create_time"]}</p></a>
					        <a href="javascript:yesapp_pic.del('${el["uid"]}')" >删除</a>
					    </li>
            			
            	
            		</#list>

					</ul>
            	
            	</div>
            	
            	
            </div>
	</div>
</div>
	
	
	
	
	
 </div>

 <@m_mobile_body_end  />
 

 <@m_mobile_html_end />






