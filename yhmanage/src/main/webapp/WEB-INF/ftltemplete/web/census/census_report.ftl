 <#include "../../macro/macro_census.ftl" />
<@m_wx_weui/>
<@m_wx_init_dbcall />
<@m_wx_html_begin p_title="调查评估表" />
<@m_wx_body_begin />
<#assign a_web_helper=b_method.upClass("com.srnpr.zapweb.helper.WebSessionHelper") />


<#assign a_census_code=a_web_helper.upRequest("u_census_code") />

<#assign a_census_report=a_macro_wx_dbcall.upOne("yh_census_report","census_report_code",a_census_code)>

<#assign a_census_detail=a_macro_wx_dbcall.queryAll("yh_census_detail","","sort_code","","census_report_code",a_census_report["census_report_code"])>


<div >
	<div class="wxcss_page_title">${a_census_report["census_report_name"]}</div>

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


<div class="weui-progress">
	<div class="census_h_30"></div>
            <div class="weui-progress__bar">
                <div class="weui-progress__inner-bar js_progress" style="width: 100%;"></div>
            </div>
    <div class="census_h_30"></div>       
</div>



	<div class="weui-panel">

            <div class="weui-panel__hd">评分结论</div>

            <div class="weui-panel__bd">

                <div class="weui-media-box weui-media-box_text">

                    <h4 class="weui-media-box__title">标题一</h4>

                    <p class="weui-media-box__desc">由各种物质组成的巨型球状天体，叫做星球。星球有一定的形状，有自己的运行轨道。</p>

                    <ul class="weui-media-box__info">

                        <li class="weui-media-box__info__meta">文字来源</li>

                        <li class="weui-media-box__info__meta">时间</li>

                        <li class="weui-media-box__info__meta weui-media-box__info__meta_extra">其它信息</li>

                    </ul>

                </div>

            </div>

        </div>



<div>
${a_census_report["census_report_remark"]}
</div>



<#macro m_text_detail a_detail>
${a_detail["sort_code"]}.&nbsp;&nbsp;${a_detail["census_detail_name"]}
</#macro>
<#macro m_text_option  a_option>
${a_option["sort_code"]}.&nbsp;&nbsp;${a_option["census_option_name"]}&nbsp;&nbsp;&nbsp;&nbsp;(${a_option["census_option_score"]}分)
</#macro>


<#macro m_radio_select  a_detail>
	<#local a_census_option=a_macro_wx_dbcall.queryAll("yh_census_option","","sort_code","","census_detail_code",a_detail["census_detail_code"])>
	<div class="weui-cells__title"><@m_text_detail a_detail/></div>
	<div class="weui-cells weui-cells_radio">
	<#list a_census_option as a_option>
            <label class="weui-cell weui-check__label" for="census_o_${a_option['census_option_code']}">
                <div class="weui-cell__bd">
                    <p><@m_text_option a_option/></p>
                </div>
                <div class="weui-cell__ft">
                    <input type="radio" class="weui-check" name="census_d_${a_detail['census_detail_code']}" id="census_o_${a_option['census_option_code']}">
                    <span class="weui-icon-checked"></span>
                </div>
            </label>
	</#list>
	</div>
</#macro>



<#macro m_checkbox_select  a_detail>
	<#local a_census_option=a_macro_wx_dbcall.queryAll("yh_census_option","","sort_code","","census_detail_code",a_detail["census_detail_code"])>
	<div class="weui-cells__title"><@m_text_detail a_detail/></div>
	<div class="weui-cells weui-cells_checkbox">
	<#list a_census_option as a_option>
            <label class="weui-cell weui-check__label" for="census_o_${a_option['census_option_code']}">
                
                <div class="weui-cell__hd">
                    <input type="checkbox" class="weui-check" name="census_d_${a_detail['census_detail_code']}" id="census_o_${a_option['census_option_code']}">
                    <i class="weui-icon-checked"></i>
                </div>
                <div class="weui-cell__bd">
                    <p><@m_text_option a_option/></p>
                </div>
            </label>
	</#list>
	</div>
</#macro>




    
<@m_wx_body_end />
 
 
<@m_wx_html_end />