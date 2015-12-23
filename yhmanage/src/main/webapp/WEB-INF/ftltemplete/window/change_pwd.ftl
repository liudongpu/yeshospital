<#assign user_code = b_page.getReqMap()["zw_f_user_code"]>
<div class="w_p_20">
	
	<form class="form-horizontal">
		<div class="control-group">
	    	<label class="control-label" for="window_change_password_new_password">新密码：</label>
	    	<div class="controls">
	    		<input type="password" id="window_change_password_new_password" name="window_change_password_new_password"/>
	    	</div>
		</div>
		<div>
			<input type="hidden" id="user_code" name="user_code" value=${user_code}>
		</div>
	<@m_zapmacro_common_auto_operate   b_page.getWebPage().getPageOperate()  "116001016" />

	</form>

</div>