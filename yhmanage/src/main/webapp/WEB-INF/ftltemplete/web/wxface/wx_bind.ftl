 <#include "../../macro/macro_wx.ftl" />
<@m_wx_weui/>
<@m_wx_init_dbcall />
<@m_wx_html_begin p_title="绑定信息" />
<@m_wx_body_begin />
<#assign a_web_helper=b_method.upClass("com.srnpr.zapweb.helper.WebSessionHelper") />


<#assign a_relationdeep=a_macro_wx_dbcall.queryAll("yh_define","define_code as v,define_name as t ",""," parent_code='4658000100020005' ")>


<div >


	<@m_wx_html_hidden p_id="wx_bind_bind_token" p_value=a_web_helper.upRequest("bind_token") />

	<@m_wx_html_hidden p_id="wx_bind_member_uid"  />
	<div class="wxcss_height_1"></div>
	<div class="wxcss_page_title">绑定老人信息</div>

	<div class="weui_cells_title">老人信息</div>
	<div class="weui_cells weui_cells_form">
	
		<@m_visit_order_text p_label="老人姓名" p_name="wx_bind_user_name"   />
		<@m_visit_order_text p_label="身份证号" p_name="wx_bind_card_code"   />
			            
	</div>
	<div class="weui_cells_title">我的信息</div>
	<div class="weui_cells weui_cells_form">
		<@m_visit_order_text p_label="家属关系" p_name="wx_bind_sib_relation"   p_type="t_relationdeep" p_css="weui_cell_select weui_select_after" />
		<@m_visit_order_text p_label="本人姓名" p_name="wx_bind_sib_name"   />
		<@m_visit_order_text p_label="手机号码" p_name="wx_bind_sib_hone"   />
		
			<div class="weui_cell ">
                <div class="weui_cell_hd">验证码：</div>
                <div class="weui_cell_bd weui_cell_primary">
                    <input class="weui_input" placeholder="请输入验证码" type="number" id="wx_bind_sib_number" name="wx_bind_sib_number" />
                </div>
                <div class="weui_cell_ft">
                    <a href="javascript:yeswx.wx_bind_verify();" class="weui_btn weui_btn_mini weui_btn_primary">发送验证码</a>
                </div>
            </div>
			            
	</div>
	<div class="weui_cells_tips">提示：目前只支持已入住养老院的老人信息的绑定</div>
	<div class="weui_btn_area">
		  		<a href="javascript:yeswx.wx_bind_submit()" class="weui_btn weui_btn_primary">确定</a>
		  	</div>


<#macro m_visit_order_text p_label="" p_name="" p_text="" p_type="text" p_css="" >


	<div class="weui_cell  ${p_css}">
		<div class="weui_cell_hd">
			${p_label}：
		</div>
		<div class="weui_cell_bd weui_cell_primary">
		
			<#if p_type=="tarea">
				<@m_wx_html_tarea p_id=p_name  p_value=p_text  />
			<#elseif p_type=="t_relationdeep">
				<@m_wx_html_select p_id=p_name  p_value=p_text p_source=a_relationdeep p_label="请选择" />
			<#elseif p_type=="t_visittime">
				<@m_wx_html_select p_id=p_name  p_value=p_text p_source=a_visittime p_label="" />
			<#elseif p_type=="t_membersex">
				<@m_wx_html_select p_id=p_name  p_value=p_text p_source=a_membersex p_label="" />
			<#elseif p_type=="t_hospitalcode">
				<@m_wx_html_select p_id=p_name  p_value=p_text p_source=a_hospitalcode p_label="" />
			<#else>
				<@m_wx_html_text p_id=p_name  p_value=p_text  />
			</#if>
			
		</div>
		<div class="wxcss_form_split"></div>
	</div>
</#macro>





    
<@m_wx_body_end />
 
 
<@m_wx_html_end />