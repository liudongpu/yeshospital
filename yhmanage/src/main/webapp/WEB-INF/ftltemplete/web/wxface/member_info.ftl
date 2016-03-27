 <#include "../../macro/macro_wx.ftl" />
<@m_wx_weui/>
<@m_wx_html_begin p_title="个人信息" />
<@m_wx_body_begin />


<@m_wx_init_check_login />

<#if a_macro_wx_member_info.getAccessToken()!="">
	<@m_wx_init_dbcall />
	
	<#assign a_memberlist=a_macro_wx_dbcall.queryAll("yh_member_extend_geracomium","","","member_code in(select member_code from yh_sib_info where sib_code='"+a_macro_wx_member_info.getSibCode()+"') ")>
	
	
	
	<#if (a_memberlist?size>0) >
		<div data-role="tabs" id="tabs">
		  
		  
		  <@m_wx_html_tab p_data=a_memberlist p_field="member_name" />
		  
		  
			<#list a_memberlist as e>
				<div id="yeswx_tab_item_t_${e_index+1}" <#if e_index!=0>class="wxcss_base_none" </#if> >
				
					<div class="weui_cells_title">老人信息</div>
					<div class="weui_cells">
			            <div class="weui_cell">
			                <div class="weui_cell_bd weui_cell_primary">
			                    <p>老人姓名：${e["member_name"]}</p>
			                </div>
			                <div class="weui_cell_ft"><a  class="wxcss_a" href="javascript:yeswx.wx_bind_delete('${a_macro_wx_member_info.getSibCode()}','${e["member_code"]}')">解除绑定</a></div>
			            </div>
			            <div class="weui_cell">
			                <div class="weui_cell_bd weui_cell_primary">
			                    <p>老人年龄：${e["member_age"]}</p>
			                </div>
			                
			            </div>
			            <div class="weui_cell">
			                <div class="weui_cell_bd weui_cell_primary">
			                    <p>身份证号：${e["card_code"]}</p>
			                </div>
			                
			            </div>
			         </div>
			         <div class="weui_cells  weui_cells_access">
			            <a class="weui_cell" href="member_pic?u_member_code=${e["member_code"]}">
			                <div class="weui_cell_bd weui_cell_primary">
			                    <p>老人相册</p>
			                </div>
			                <div class="weui_cell_ft">
			                </div>
			            </a>
			            
			        </div>
				
				
				
					<div class="weui_cells_title">养老信息</div>
					<div class="weui_cells">
			           
			            <div class="weui_cell">
			                <div class="weui_cell_bd weui_cell_primary">
			                    <p>养老院名：${a_macro_wx_dbcall.dataGet("yh_geracomium_info","geracomium_name","","geracomium_code",e["geracomium_code"])  }</p>
			                </div>
			                
			            </div>
			            <div class="weui_cell">
			                <div class="weui_cell_bd weui_cell_primary">
			                    <p>房间号码：${e["room_name"]}</p>
			                </div>
			                
			            </div>
			            <div class="weui_cell">
			                <div class="weui_cell_bd weui_cell_primary">
			                    <p>报销类型：${a_macro_wx_dbcall.dataGet("yh_define","define_name","","define_code",e["account_type"])  }</p>
			                </div>
			                
			            </div>
			            <div class="weui_cell">
			                <div class="weui_cell_bd weui_cell_primary">
			                    <p>既往病史：${e["medical_history"]}</p>
			                </div>
			                
			            </div>
			            
			        </div>
					
					<div class="weui_cells  weui_cells_access">
			            <a class="weui_cell" href="member_warn?u_member_code=${e["member_code"]}">
			                <div class="weui_cell_bd weui_cell_primary">
			                    <p>历史报警信息</p>
			                </div>
			                <div class="weui_cell_ft">
			                </div>
			            </a>
			            
			        </div>
					
					
				
				
				
				   
				</div>
					
			</#list>
		  
		  	<div class="weui_btn_area">
		  		<a href="wx_bind?bind_token=${a_macro_wx_member_info.getBindToken()}" class="weui_btn weui_btn_primary">绑定其他老人</a>
		  	</div>
		  
		</div>
	<#else>
		<div class="weui_btn_area">
			<a href="wx_bind?bind_token=${a_macro_wx_member_info.getBindToken()}" class="weui_btn weui_btn_primary">绑定老人信息</a>
		</div>
	</#if>


</#if>


<@m_wx_body_end />
 <@m_wx_html_initjs e_js="yeswx.tab_select(1,'t')" />
 
<@m_wx_html_end />