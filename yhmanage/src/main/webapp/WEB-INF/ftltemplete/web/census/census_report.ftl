 <#include "../../macro/macro_census.ftl" />
<@m_wx_weui/>
<@m_wx_init_dbcall />
<@m_wx_html_begin p_title="调查评估表" />
<@m_wx_body_begin />
<#assign a_web_helper=b_method.upClass("com.srnpr.zapweb.helper.WebSessionHelper") />

<#assign a_info_code=a_web_helper.upRequest("u_info_code") />
<#assign a_census_code=a_web_helper.upRequest("u_census_code") />
<#assign a_member_code=a_web_helper.upRequest("u_member_code") />

<#assign a_option_code="" />


<#if a_info_code!="" >
	<#assign a_info_obj=a_macro_wx_dbcall.upOne("yh_census_info","census_info_code",a_info_code) />
	<#assign a_census_code=a_info_obj["census_report_code"] />
	<#assign a_member_code=a_info_obj["member_code"] />
	<#assign a_option_code=a_info_obj["option_select"] />
</#if>



<#assign a_census_report=a_macro_wx_dbcall.upOne("yh_census_report","census_report_code",a_census_code)>

<#assign a_census_detail=a_macro_wx_dbcall.queryAll("yh_census_detail","","length(sort_code),sort_code","","flag_use","1","census_report_code",a_census_report["census_report_code"])>

<#assign a_census_standard=a_macro_wx_dbcall.queryAll("yh_census_standard","","census_standard_min","","census_report_code",a_census_report["census_report_code"])>



<div id="census_page_main">
	
	<div >
	
		<#if a_info_code!="">
			<div class="census_title_history"><i class="weui-icon-waiting"></i>历史记录-${a_census_report["census_report_name"]}</div>
			<div></div>
		<#else>
			<div class="census_page_title">${a_census_report["census_report_name"]}</div>
		</#if>
		
	
		
	
	</div>
	
	
	<div>
	
	
		<#list a_census_detail as a_detail>
		
			<#if a_detail["census_detail_type"]=="46580001000700010001">
				<@m_radio_select a_detail />
			<#elseif a_detail["census_detail_type"]=="46580001000700010002">
				<@m_checkbox_select a_detail />
			
			</#if>
		</#list>
	
	
	
	</div>
	
	<script>
	
	
	
	var census_base_obj={
		report_code:'${a_census_code}',
		member_code:'${a_member_code}'
	};
	
	
	
	
	
	
	var census_detail_objs=[];
		<#list a_census_detail as a_detail>
		
		census_detail_objs.push({d_code:"${a_detail['census_detail_code']}",d_sort:"${a_detail['sort_code']}",d_name:"${a_detail['census_detail_name']}",d_type:"${a_detail['census_detail_type']}"});
		
		</#list>
		
		
	var census_standard_objs=[];
		<#list a_census_standard as a_standard>
		
		census_standard_objs.push({d_code:"${a_standard['census_standard_code']}",d_name:"${a_standard['census_standard_name']}",d_min:"${a_standard['census_standard_min']}"
		,d_max:"${a_standard['census_standard_max']}",d_desc:"${a_standard['census_standard_desc']}"});
		
		</#list>
	</script>
	
	
	
	
	<div class="census_h_30"></div>
	<div class="weui-progress">
		
	            <div class="weui-progress__bar">
	                <div class="weui-progress__inner-bar js_progress" style="width: 100%;"></div>
	            </div>
	    
	</div>
	
	
	
			<div class="weui-panel">
	
	            <div class="weui-panel__hd">评估结论</div>
	
	            <div class="weui-panel__bd">
	
	                <div class="weui-media-box weui-media-box_text">
	                	<p class="weui-media-box__desc">${a_census_report["census_report_remark"]}</p> 
	                	<h4 class="weui-media-box__title">评估分：<span class="census_score" ><span id="census_sum_score">0</span>分</span></h4>
						
						<div  id="census_sum_standard" class="census_sum_standard">
						</div>
						
	                </div>
	
	            </div>
	
	        </div>
	
	
	<#if a_info_code=="" >
	<div class="census_btn_box">
	<a href="javascript:census.submit_info();" class="weui-btn weui-btn_primary">提交结果</a>
	</div>
	<#else>
	<script>$(function(){census.sum_all();});</script>
	</#if>


</div>

<div  id="census_page_success"  style="display: none;">
    <div class="weui-msg">
        <div class="weui-msg__icon-area"><i class="weui-icon-success weui-icon_msg"></i></div>
        <div class="weui-msg__text-area">
            <h2 class="weui-msg__title">操作成功</h2>
            <p class="weui-msg__desc">你的评估信息已提交成功，谢谢！</p>
        </div>
        <div class="weui-msg__opr-area">
            <p class="weui-btn-area">
                <a href="census_list?u_member_code=${a_member_code}" class="weui-btn weui-btn_primary">返回列表</a>
                <a href="census_history?u_census_code=${a_census_code}&u_member_code=${a_member_code}" class="weui-btn weui-btn_default">历史记录</a>
            </p>
        </div>
        
    </div>
</div>


<#macro m_text_detail a_detail>
${a_detail["sort_code"]}.&nbsp;&nbsp;${a_detail["census_detail_name"]}
</#macro>
<#macro m_text_option  a_option>
${a_option["sort_code"]}.&nbsp;&nbsp;${a_option["census_option_name"]}&nbsp;&nbsp;&nbsp;&nbsp;(${a_option["census_option_score"]}分)
</#macro>


<#macro m_radio_select  a_detail>
	<#local a_census_option=a_macro_wx_dbcall.queryAll("yh_census_option","","sort_code","","flag_use","1","census_detail_code",a_detail["census_detail_code"])>
	<div class="weui-cells__title"><@m_text_detail a_detail/></div>
	<div class="weui-cells weui-cells_radio">
	<#list a_census_option as a_option>
            <label class="weui-cell weui-check__label" for="census_o_${a_option['census_option_code']}">
                <div class="weui-cell__bd">
                    <p><@m_text_option a_option/></p>
                </div>
                <div class="weui-cell__ft">
                    <input type="radio" onchange="census.sum_detail(this)" option-score="${a_option['census_option_score']}" class="weui-check" name="census_d_${a_detail['census_detail_code']}" id="census_o_${a_option['census_option_code']}" value="${a_option['census_option_code']}"  <#if (a_option_code?index_of(a_option['census_option_code'])>-1)> checked="checked" </#if>  />
                    <span class="weui-icon-checked"></span>
                </div>
            </label>
	</#list>
	</div>
</#macro>



<#macro m_checkbox_select  a_detail>
	<#local a_census_option=a_macro_wx_dbcall.queryAll("yh_census_option","","sort_code","","flag_use","1","census_detail_code",a_detail["census_detail_code"])>
	<div class="weui-cells__title"><@m_text_detail a_detail/></div>
	<div class="weui-cells weui-cells_checkbox">
	<#list a_census_option as a_option>
            <label class="weui-cell weui-check__label" for="census_o_${a_option['census_option_code']}">
                
                <div class="weui-cell__hd">
                    <input type="checkbox" onchange="census.sum_detail(this)" option-score="${a_option['census_option_score']}" class="weui-check" name="census_d_${a_detail['census_detail_code']}" id="census_o_${a_option['census_option_code']}"  value="${a_option['census_option_code']}" <#if (a_option_code?index_of(a_option['census_option_code'])>-1)> checked="checked" </#if> />
                    <i class="weui-icon-checked"></i>
                </div>
                <div class="weui-cell__bd">
                    <p><@m_text_option a_option/></p>
                </div>
            </label>
	</#list>
	</div>
</#macro>


		<div class="js_dialog" id="census_dialog_alert_box" style="display: none;">
            <div class="weui-mask"></div>
            <div class="weui-dialog">
                <div class="weui-dialog__bd" id="census_dialog_alert_info"></div>
                <div class="weui-dialog__ft">
                    <a href="javascript:$('#census_dialog_alert_box').fadeOut(200);" class="weui-dialog__btn weui-dialog__btn_primary">确定</a>
                </div>
            </div>
        </div>




    
<@m_wx_body_end />
 
 
<@m_wx_html_end />