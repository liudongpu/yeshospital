 <#include "../zapmacro/zapmacro_mobile.ftl" />
 <#include "../macro/macro_mobile.ftl" />

<@m_mobile_html_begin  p_css=["yesapp/css/yes-gencustom.css"]/>
<@m_mobile_body_begin />

<#assign a_memberCode=RequestParameters['u_member_code']?default("") >
<@m_mobile_header_begin p_title="用户图片" />
	<@m_mobile_button_back />
	 
	
<@m_mobile_header_end />

<@m_mobile_init_dbcall />
<#assign a_memberlist=a_macro_mobile_dbcall.queryAll("yh_member_pic","uid,pic_url,create_time,pic_remark","-zid","","member_code",a_memberCode)>

<style>
.ui-listview>.ui-li-has-thumb>.ui-btn, .ui-listview>.ui-li-static.ui-li-has-thumb
{
	
	padding-left:7em;
	min-height:4.4em;
  
}

.ui-listview .ui-li-has-thumb>img:first-child, .ui-listview .ui-li-has-thumb>.ui-btn>img:first-child, .ui-listview .ui-li-has-thumb .ui-li-thumb
{
	top:0.2em;
	left:0.2em;
}


</style>

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
            	
            		<ul data-role="listview" data-split-icon="edit" data-split-theme="a" data-inset="true">
            	
            		<#list a_memberlist as el>
            	
            			
            			<li><a  <@m_mobile_a_href p_page="pic-big?u_pic_code="+el["uid"] /> >
					        
					    <@m_mobile_html_img p_img=el["pic_url"] />
					    <p>上传时间：<br/>${el["create_time"]}<br/>描述：<label id="pr_${el["uid"]}">${el["pic_remark"]}</label></p>
					    </a>
					        <a href="javascript:yesapp_pic.pic_dialog('${el["uid"]}')" >操作</a>
					    </li>
            			
            	
            		</#list>

					</ul>
            	
            	</div>
            	<div class="zmcss_h_20"></div>
            	<div class="zmcss_h_20"></div>
            	
            </div>
	</div>
</div>
	
	
	
	
	
 </div>
 
 
 	<div data-role="popup" id="popup_change" data-dismissible="false"  data-overlay-theme="a" data-theme="a" style="margin:0;min-width:300px; ">
		<div data-role="header" data-theme="a" data-icon="delete">
	    <h1>请输入描述信息</h1>
	    <a href="#" data-rel="back" class="ui-btn ui-shadow ui-corner-all ui-icon-delete ui-btn-icon-notext ui-btn-inline ">Delete</a>
		    
	    </div>
	    <div role="main" class="ui-content">
	        <h3 class="ui-title"></h3>
	    <p>
	    	<textarea data-clear-btn="false" name="yesapp_vo_text"  id="yesapp_vo_text" ></textarea>
	    </p>
	        <a href="javascript:yesapp_pic.pic_edit()" class="ui-btn ui-corner-all ui-shadow ui-btn-inline " >确认修改</a>
	        <a href="javascript:yesapp_pic.pic_del()" class="ui-btn ui-corner-all ui-shadow ui-btn-inline " >删除</a>
	    </div>
	</div>
	
 
 

 <@m_mobile_body_end  />
 

 <@m_mobile_html_end />






