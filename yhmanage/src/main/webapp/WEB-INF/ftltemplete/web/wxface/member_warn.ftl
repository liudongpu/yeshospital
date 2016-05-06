 <#include "../../macro/macro_wx.ftl" />
<@m_wx_weui/>
<@m_wx_html_begin p_title="报警信息" />

<@m_wx_body_begin />

<@m_wx_init_dbcall />
<#assign a_app_info=b_method.upClass("com.srnpr.yeshospital.support.AppInfoSupport") >

<#assign a_member_code=RequestParameters['u_member_code']?default("") >
<#assign a_visitlist=a_macro_wx_dbcall.queryAll("yh_count_warn_geracomium","create_time,warn_info,process_method,warn_code"
	+",(select define_name from yh_define where define_code=yh_count_warn_geracomium.warn_level ) as v_warn_level"
	+",(select hospital_name from yh_hospital_info where yh_hospital_info.hospital_code=yh_count_warn_geracomium.hospital_code ) as v_hospital_name"
	,"-zid","","member_code",a_member_code)>

	<div class="wxcss_height_1"></div>
	<#list a_visitlist as el>

		<div class="weui_panel">
            <div class="weui_panel_hd">${el["create_time"]?substring(0,10)}</div>
            <div class="weui_panel_bd">
                <div class="weui_media_box weui_media_text">
                    <h4 class="weui_media_title">${el["warn_info"]}</h4>
                    <p class="weui_media_desc">处理措施：${el["process_step"]}</p>
                    <ul class="weui_media_info">
                        <li class="weui_media_info_meta">${el["v_warn_level"]}</li>
                        <li class="weui_media_info_meta">${el["warn_code"]}</li>
                        <li class="weui_media_info_meta weui_media_info_meta_extra">${el["v_hospital_name"]}</li>
                    </ul>
                </div>
            </div>
        </div>


		
	</#list>

	<@m_wx_html_msg_empty p_data=a_visitlist />

<@m_wx_body_end />

<@m_wx_html_end />