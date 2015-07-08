 <#include "../../macro/macro_wx.ftl" />

<@m_wx_html_begin p_title="绑定信息" />
<@m_wx_body_begin />
<#assign a_web_helper=b_method.upClass("com.srnpr.zapweb.helper.WebSessionHelper") />

<div class="wxcss_main_width">


	<@m_wx_html_hidden p_id="wx_bind_bind_token" p_value=a_web_helper.upRequest("bind_token") />

	<@m_wx_html_hidden p_id="wx_bind_member_uid"  />


	<div id="wx_bind_member">

		

		<div class="wxcss_height_1"></div>
		<div class="wxcss_base_note">
			请输入老人身份证：
		</div>
		<div>
			<@m_wx_html_text p_id="wx_bind_card_code"  />
		</div>
		<div class="wxcss_height_1"></div>
		<div class="wxcss_base_note">
			请输入老人姓名：
		</div>
		<div>
			<@m_wx_html_text p_id="wx_bind_user_name"  />
		</div>
		
		<a href="javascript:yeswx.wx_bind_check()" class="ui-btn">确定</a>
		
	</div>

	<div id="wx_bind_create" class="wxcss_base_none">
		<div class="wxcss_height_1"></div>
		<div class="wxcss_base_note">
			与老人关系：
		</div>
		<div>
			<select name="wx_bind_sib_relation" id="wx_bind_sib_relation">
				<option value="46580001000200050001">子女</option>
				<option value="46580001000200050002">配偶</option>
				<option value="46580001000200050003">兄弟姐妹</option>
				<option value="46580001000200050004">亲戚</option>
				<option value="46580001000200050005">朋友</option>
				<option value="46580001000200050006">其他</option>
			</select>

		</div>

		<div class="wxcss_height_1"></div>
		<div class="wxcss_base_note">
			请输入本人姓名：
		</div>
		<div>
			<@m_wx_html_text p_id="wx_bind_sib_name"  />
		</div>
		
		
		<div class="wxcss_height_1"></div>
		<div class="wxcss_base_note">
			请输入本人手机号：
		</div>
		<div>
			<@m_wx_html_text p_id="wx_bind_sib_hone" />
		</div>
		
		
		<div class="wxcss_height_1"></div>
		<div class="wxcss_base_note">
			请输入验证码：
		</div>
		<div>
			<div style="width:50%;float:left;">
				<@m_wx_html_text p_id="wx_bind_sib_number"  />
			</div>
			<div style="width:40%;float:right;">
			<div class="ui-btn ui-input-btn ui-shadow">
  发送验证码
  <input id="wx_bind_send_link" type="button" onclick="yeswx.wx_bind_verify()" data-corners="false" data-enhanced="true" value="发送验证码"></input>
</div>
				
			</div>
			<div style="clear:both;"></div>
		</div>
		<div class="wxcss_height_2"></div>
		<a href="javascript:yeswx.wx_bind_submit()" class="ui-btn">确定</a>
	</div>

	

</div>




    
<@m_wx_body_end />
 
 
<@m_wx_html_end />