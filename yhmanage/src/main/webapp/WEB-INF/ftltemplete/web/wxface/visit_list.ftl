 <#include "../../macro/macro_wx.ftl" />
<@m_wx_weui/>
<@m_wx_html_begin p_title="历史信息" />

<@m_wx_body_begin />

<@m_wx_init_dbcall />
<#assign a_app_info=b_method.upClass("com.srnpr.yeshospital.support.AppInfoSupport") >

<#assign a_member_code=RequestParameters['u_member_code']?default("") >
<#assign a_visitlist=a_macro_wx_dbcall.queryAll("yh_visit_order_info","visit_order_code,visit_time,visit_note,tour_info,agree_info,process_time,visit_order_status"
	+",(select define_name from yh_define where define_code=visit_order_status ) as v_status_name"
	+",(select hospital_name from yh_hospital_info where yh_hospital_info.hospital_code=yh_visit_order_info.hospital_code ) as v_hospital_name"
	,"-zid","","member_code",a_member_code)>

	<div class="wxcss_height_1"></div>
	<#list a_visitlist as el>

		<div class="weui_panel">
            <div class="weui_panel_hd wxcss_color_1">${el["visit_time"]}</div>
            <div class="weui_panel_bd">
                <div class="weui_media_box weui_media_text">
                    
                    <ul class="wxcss_list_ul">
                    		<li>处理状态:${el["v_status_name"]}</li>
							<#if (el["visit_note"]!="")><li>预约备注:${el["visit_note"]}</li></#if>
							<#if (el["tour_info"]!="")><li>查房记录:${el["tour_info"]}</li></#if>
							<#if (el["agree_info"]!="")><li>查房建议:${el["agree_info"]}</li></#if>
					</ul>
					<#if (el["visit_order_status"]=="46580001000200110003")>
						<div class="weui_cells_title">费用记录</div>
						<div class="weui_cells">
							<#list a_app_info.upVisitDetail(el["visit_order_code"]) as ed>
				            <div class="weui_cell">
				                <div class="weui_cell_bd weui_cell_primary">
				                    <p>${ed["v_name"]}</p>
				                </div>
				                <div class="weui_cell_ft wxcss_color_2">${ed["v_sm"]?string.currency}</div>
				            </div>
				            </#list>
				        </div>
					</#if>
                    <ul class="weui_media_info">
                        <li class="weui_media_info_meta">${el["v_hospital_name"]}</li>
                        
                        <li class="weui_media_info_meta weui_media_info_meta_extra">${el["process_time"]}</li>
                    </ul>
                </div>
            </div>
        </div>
	</#list>



<@m_wx_body_end />

<@m_wx_html_end />