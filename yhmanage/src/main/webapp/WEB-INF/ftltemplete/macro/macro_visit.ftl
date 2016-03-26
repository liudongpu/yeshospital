

<@m_wx_init_dbcall />

<#assign a_relationdeep=a_macro_wx_dbcall.queryAll("yh_define","define_code as v,define_name as t ",""," parent_code='4658000100020005' ")>
	
<#assign a_visittime=a_macro_wx_dbcall.queryAll("yh_define","define_code as v,define_name as t ",""," parent_code='4658000100020012' ")>


<#assign a_membersex=a_macro_wx_dbcall.queryAll("yh_define","define_code as v,define_name as t ",""," parent_code='4658000100020001' ")>

<#assign a_hospitalcode=a_macro_wx_dbcall.queryAll("yh_hospital_info","hospital_code as v,hospital_name as t ",""," hospital_service='46580001000400030002' ")>



<#macro m_visit_order_form e_index e_info e_sib>
	
	<div id="yeswx_tab_item_t_${e_index}" <#if e_index!=0>class="wxcss_base_none" </#if> >
	
		
		<div class="wxcss_height_1"></div>
		
		<#if e_info["member_code"]??>	
			<#assign a_visitlist=a_macro_wx_dbcall.queryAll("yh_visit_order_info","","-zid","","member_code",e_info["member_code"])>
			<#if (a_visitlist?size>0)>
					<#assign a_visitinfo=a_visitlist[0]>
						<div class="weui_cells_title">${e_info["member_name"]}的最近一次预约信息</div>
					<div class="weui_panel weui_panel_access">
			           
			            <div class="weui_panel_bd">
			                <div class="weui_media_box weui_media_text">
			                    <#assign a_defineinfo=a_macro_wx_dbcall.upOne("yh_define","define_code",a_visitinfo["visit_order_status"])>
			                    <p class="weui_media_desc">预约时间:${a_visitinfo["visit_time"]} 当前状态:${a_defineinfo["define_name"]}</p>
			                </div>
			                
			            </div>
			            <a href="visit_list?u_member_code=${a_visitinfo["member_code"]}" target="_blank" class="weui_panel_ft">查看更多历史信息</a>
			        </div>
				<div class="wxcss_height_1"></div>
			</#if>
		</#if>
		<div class="wxcss_page_title">出诊预约</div>
		<div class="weui_cells_title">预约信息</div>
		<div class="weui_cells weui_cells_form">
		<@m_visit_order_text p_label="预约医院" p_name="hospital_code_"+e_index  p_text=""  p_type="t_hospitalcode" p_css="weui_cell_select weui_select_after"/>
		
		<div class="weui_cell">
			<div class="weui_cell_hd">
				预约日期：
			</div>
			<div class="weui_cell_bd weui_cell_primary">
				
				<@m_wx_html_text p_id="visit_date_"+e_index  p_value=.now?date />
				<@m_wx_html_script "$(function () {$('#visit_date_${e_index}').mobiscroll().calendar({lang: 'zh',dateFormat: 'yy-mm-dd',  display: 'bottom', controls: ['calendar', 'date']});});" />
	
				
			</div>
			<div class="wxcss_form_split"></div>
		</div>
		
		
		
		<@m_visit_order_text p_label="预约时间" p_name="visit_time_"+e_index  p_text=""  p_type="t_visittime" p_css="weui_cell_select weui_select_after"/>
		
		<@m_visit_order_text p_label="预约需求" p_name="visit_note_"+e_index  p_text=""  p_type="tarea"/>
		</div>
		<div class="weui_cells_title">老人信息</div>
		<div class="weui_cells weui_cells_form">
		<@m_visit_order_text p_label="老人姓名" p_name="member_name_"+e_index  p_text=e_info["member_name"] />
		<@m_visit_order_text p_label="老人年龄" p_name="member_age_"+e_index  p_text=e_info["member_age"] />
		<@m_visit_order_text p_label="身份证号" p_name="card_code_"+e_index  p_text=e_info["card_code"] />
		<@m_visit_order_text p_label="老人性别" p_name="member_sex_"+e_index  p_text=e_info["member_sex"] p_type="t_membersex" p_css="weui_cell_select weui_select_after"/>
		
		<@m_visit_order_text p_label="老人住址" p_name="room_name_"+e_index  p_text=e_info["member_age"]  p_type="tarea" />
		<@m_visit_order_text p_label="老人电话" p_name="member_phone_"+e_index  p_text=e_info["member_phone"] />
		</div>
		<div class="weui_cells_title">家属信息</div>
		<div class="weui_cells weui_cells_form">
		<@m_visit_order_text p_label="家属姓名" p_name="sib_name_"+e_index  p_text=e_sib["sib_name"] />
		<@m_visit_order_text p_label="家属关系" p_name="relation_deep_"+e_index  p_text=e_sib["relation_deep"] p_type="t_relationdeep" p_css="weui_cell_select weui_select_after" />
		<@m_visit_order_text p_label="家属电话" p_name="sib_phone_"+e_index  p_text=e_sib["mobile_phone"] />
		</div>
		<@m_wx_html_hidden p_id="sib_code_"+e_index p_value=e_sib["sib_code"] />
		<@m_wx_html_hidden p_id="bind_token_"+e_index p_value=a_macro_wx_member_info.getBindToken() />
		
		
		<div class="wxcss_height_2"></div>
		<div class="weui_btn_area">
			<a href="javascript:yeswx.visit_order_submit(${e_index})" class="ui-btn weui_btn weui_btn_primary">立即预约</a>
		</div>
		<div class="wxcss_height_2"></div>
	</div>
</#macro>

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