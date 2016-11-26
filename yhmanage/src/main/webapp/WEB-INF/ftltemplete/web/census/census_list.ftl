 <#include "../../macro/macro_census.ftl" />
<@m_wx_weui/>
<@m_wx_init_dbcall />
<@m_wx_html_begin p_title="调查评估表" />
<@m_wx_body_begin />
<#assign a_web_helper=b_method.upClass("com.srnpr.zapweb.helper.WebSessionHelper") />


<#assign a_member_code=a_web_helper.upRequest("u_member_code") />



<#assign a_census_detail=a_macro_wx_dbcall.queryAll("yh_census_report","","","","flag_use","1")>

<div >
	<div class="census_page_title">调查评估表</div>

</div>

<#list a_census_detail as a_detail>
		<div class="weui-panel weui-panel_access">
            
            <div class="weui-panel__bd">
                <div class="weui-media-box weui-media-box_text">
                    <h4 class="weui-media-box__title">${a_detail["census_report_name"]}</h4>
                    <#if a_detail["census_report_remark"]!="">
                    	<p class="weui-media-box__desc">${a_detail["census_report_remark"]}</p>
                    </#if>
                </div>
                
            </div>
            <div class="weui-panel__ft">
                <a href="census_report?u_census_code=${a_detail["census_report_code"]}&u_member_code=${a_member_code}" class="weui-cell weui-cell_access weui-cell_link">
                    <div class="weui-cell__bd">开始评估</div>
                    <span class="weui-cell__ft"></span>
                </a>    
            </div>
            <div class="weui-panel__ft">
                <a href="census_history?u_census_code=${a_detail["census_report_code"]}&u_member_code=${a_member_code}" class="weui-cell weui-cell_access weui-cell_link">
                    <div class="weui-cell__bd">历史记录</div>
                    <span class="weui-cell__ft"></span>
                </a>    
            </div>
        </div>


</#list>






    
<@m_wx_body_end />
 
 
<@m_wx_html_end />