<#-- 设置页 -->

<#assign a_zapmacro_uset_index=0 >


<#macro m_zapmacro_uset_page_html p_code="" p_class="" p_target="">

	<#assign a_config=b_method.upClass(p_class).upConfig() >
	
	<#assign a_input=b_method.upClass("com.srnpr.zapweb.simpleapi.SimpleSetInput") >
	
	${a_input.setDataCode(p_code)}
	
	
	<#assign a_support=b_method.upClass("com.srnpr.zapweb.simpleapi.SimpleSetSupport") >
	
	<#assign a_result=a_support.upResult(a_input,a_config) >
	
	<@m_mobile_header_begin p_title=a_result.getPageTitle() />
	<@m_mobile_button_back />
	<@m_mobile_header_end />
	
	
	<@m_zapmacro_uset_hidden e_id="zapjs_st_id_post_code" e_value=p_code />
	<@m_zapmacro_uset_hidden e_id="zapjs_st_id_post_target" e_value=p_target />
	
	<div class="zmcss_h_20"></div>
	
	<div>
	
	<@m_zapmacro_uset_page_show a_result />
	
	</div>

</#macro>


<#macro m_zapmacro_uset_page_show e_result>


<div class="zmcss_set_box">

<#list e_result.getInfos() as e_item>
	
	
	<#assign a_zapmacro_uset_index=a_zapmacro_uset_index+1 >

	<#if e_item.getFiledType()=="104005009">
		<@m_zapmacro_uset_field_text e_item />

	<#elseif e_item.getFiledType()=="104005020">
		<@m_zapmacro_uset_field_text e_item />
	<#elseif e_item.getFiledType()=="104005008">
		<@m_zapmacro_uset_field_split e_item />
	<#elseif e_item.getFiledType()=="104005019">
		<@m_zapmacro_uset_field_flag e_item />

	</#if>	
	
	

	
</#list>
<div class="zmcss_w_clear"></div>
</div>




<div data-role="popup" id="zapjs_st_id_dialog" data-overlay-theme="b" data-theme="a" data-dismissible="false" style="width:100%">
    <div data-role="header">
    <h1>修改</h1>
    <a href="#" data-rel="back" class="ui-btn-right ui-btn ui-btn-inline ui-mini ui-corner-all ui-btn-icon-left ui-icon-delete  ui-btn-icon-notext">关闭</a>
    </div>
    <div role="main" class="ui-content" id="zapjs_st_id_show">
       <textarea id="zapjs_st_id_info"></textarea>
       <a href="javascript:zapjs.st.save_change()" class="ui-btn ">确认</a>
    </div>
</div>


</#macro>





<#macro m_zapmacro_uset_field_text e_item>
	<@compress single_line=true>
	<div class="<@m_zapmacro_uset_css />"  id="zw_st_item_${e_item.getFiledName()}" onclick="zapjs.st.change_item('${e_item.getFiledName()}')">
		</@compress>
		<div  class="zmcss_set_left">${e_item.getFieldNote()}</div>

		<div  class="zmcss_set_right"><span id="zw_st_span_${e_item.getFiledName()}">${e_item.getFieldValue()}</span></div>

	</div>

</#macro>


<#macro m_zapmacro_uset_field_flag e_item>
	<@compress single_line=true>
	<div class="<@m_zapmacro_uset_css />"  id="zw_st_item_${e_item.getFiledName()}" >
		</@compress>
		<div  class="zmcss_set_left">${e_item.getFieldNote()}</div>

		<div  class="zmcss_set_right">			
			<select id="zw_st_select_${e_item.getFiledName()}" name="zw_st_select_${e_item.getFiledName()}" onchange="zapjs.st.change_select('${e_item.getFiledName()}')"  data-role="slider">
		        <option value="0">否</option>
		        <option value="1" <#if e_item.getFieldValue()=="1"> selected="selected" </#if> >是</option>
		    </select>		
		</div>

	</div>

</#macro>





<#macro m_zapmacro_uset_css>
zmcss_set_item <#if (a_zapmacro_uset_index>1) >zmcss_set_border</#if>
</#macro>


<#macro m_zapmacro_uset_field_split e_item>
	<#assign a_zapmacro_uset_index=0 >
	</div>
	<div class="zmcss_h_20"></div>
	<div class="zmcss_set_box">
</#macro>




<#macro m_zapmacro_uset_hidden e_id="" e_value="">
	<input type="hidden" id="${e_id}" name="${e_id}" value="${e_value}" />
</#macro>

