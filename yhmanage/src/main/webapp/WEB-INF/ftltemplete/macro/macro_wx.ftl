<#-- 系统版本号 -->
<#assign a_macro_wx_system_version="2.0.0.07">
<#-- 资源附加后缀版本 -->
<#assign a_macro_wx_resources_version="?v="+a_macro_wx_system_version >
<#-- 资源文件路径 -->
<#assign a_macro_wx_resources_link="../../" >

<#-- 图片文件路径 -->
<#assign a_macro_wx_img_link="../../" >

<#-- 项目特殊样式 -->


<#assign a_macro_wx_resources_base_js=["mlib/jquery/jquery-2.1.4.min.js","mlib/jquery.mobile-1.4.5/jquery.mobile-1.4.5.min.js"] >

<#assign a_macro_wx_resources_thems_js=["zapjs/zapapi.js","zapjs/zapfunc.js","yeswx/js/yeswx.js"] >

<#-- ,"mlib/jquery.mobile.wx/themes/jquery.mobile.icons.min.css" -->
<#assign a_macro_wx_resources_thems_css=["mlib/jquery.mobile.wx/themes/jm-wx.min.css","mlib/jquery.mobile-1.4.5/jquery.mobile.structure-1.4.5.min.css","yeswx/css/wxcss.css"] >



<#macro m_wx_html_js e_list>
	<#list e_list as e>
	<script type="text/javascript" src="${a_macro_wx_resources_link}resources/${e}${a_macro_wx_resources_version}"></script>
	</#list>
</#macro>
<#macro m_wx_html_css e_list >
    <#list e_list as e>
	<link type="text/css" href="${a_macro_wx_resources_link}resources/${e}${a_macro_wx_resources_version}" rel="stylesheet">
	</#list>
</#macro>

<#macro m_wx_html_script  e_info >

	<script type="text/javascript">
		${e_info}
	</script>

</#macro>

<#-- 页面初始化时执行脚本 -->
<#macro m_wx_html_initjs  e_js >
	<@m_wx_html_script "$(function(){"+e_js+"}); " />

</#macro>


<#macro m_wx_html_begin  p_title="" p_type="" p_css=[]>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0"/>
    <title>${p_title}</title>
    
  
   
		<@m_wx_html_css a_macro_wx_resources_thems_css />
		<@m_wx_html_js a_macro_wx_resources_base_js />
	 <@m_wx_html_css    p_css />
	 
	 
	 
	
	
</#macro>



<#macro m_wx_body_begin>

</head>
<body>

</#macro>

<#macro m_wx_body_end  p_type="" p_js=[]>


	<@m_wx_html_js a_macro_wx_resources_thems_js />



	<@m_wx_html_js p_js />


</#macro>

<#macro m_wx_html_end  >

</body>
</html>
</#macro>


<#macro m_wx_header_begin p_title="" >
	
	
	<h1>${p_title}</h1>
</#macro>


<#macro m_wx_header_end  >
	
	</div>

</#macro>




<#-- 检查登陆  -->
<#macro m_wx_init_check_login>

	<#assign a_macro_wx_page_info=b_method.upClass("com.srnpr.yeshospital.wx.WxPageInfo") />

	<#assign a_macro_wx_member_info=a_macro_wx_page_info.upMemberInfo() />

	<#if a_macro_wx_member_info.getAccessToken()=="">

		<@m_wx_html_script "location.href='wx_bind?bind_token="+a_macro_wx_member_info.getBindToken()+"';" />
	<#else>

		
	</#if>

</#macro>



<#-- 初始化数据操作脚本  -->
<#macro m_wx_init_dbcall>
<#assign a_macro_wx_dbcall=b_method.upClass("com.srnpr.zapweb.websupport.DataCallSupport") >
</#macro>





<#macro m_wx_html_hidden p_id="" p_value="">
	<input type="hidden" name="${p_id}" id="${p_id}" value="${p_value}"/>
</#macro>
<#macro m_wx_html_text p_id="" p_value="">
	<input type="text" name="${p_id}" id="${p_id}" value="${p_value}"/>
</#macro>

