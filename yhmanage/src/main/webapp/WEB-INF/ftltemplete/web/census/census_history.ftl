 <#include "../../macro/macro_census.ftl" />
<@m_wx_weui/>
<@m_wx_init_dbcall />
<@m_wx_html_begin p_title="调查评估表" />
<@m_wx_body_begin />
<#assign a_web_helper=b_method.upClass("com.srnpr.zapweb.helper.WebSessionHelper") />


<#assign a_member_code=a_web_helper.upRequest("u_member_code") />
<#assign a_census_code=a_web_helper.upRequest("u_census_code") />



<#assign a_census_detail=a_macro_wx_dbcall.queryAll("yh_census_info","","-create_time","","census_report_code",a_census_code,"member_code",a_member_code)>

<div >
	<div class="census_page_title">历史记录</div>

</div>


		<div class="weui-cells__title"></div>
        <div class="weui-cells">

            <#list a_census_detail as a_detail>
		

				<a class="weui-cell weui-cell_access" href="census_report?u_info_code=${a_detail["census_info_code"]}">
	                <div class="weui-cell__bd">
	                    <p>评分：${a_detail["report_score"]}分</p>
	                </div>
	                <div class="weui-cell__ft">${a_detail["create_time"]}</div>
	            </a>
			</#list>
            

        </div>



<#if a_census_detail?size==0>
	<div class="weui-msg">
        <div class="weui-msg__icon-area"><i class="weui-icon-info weui-icon_msg"></i></div>
        <div class="weui-msg__text-area">
            <h2 class="weui-msg__title">暂无历史记录</h2>
            <p class="weui-msg__desc">请先开始相应评估</p>
        </div>
       
    </div>

</#if>





    
<@m_wx_body_end />
 
 
<@m_wx_html_end />