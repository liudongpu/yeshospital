

	<div class="c_login_box">
		<div class=" w_m_auto c_login_header c_login_width">

			<div class="w_fl w_mt_15">

				<div class="c_login_logo"></div>

			</div>
			<div class="w_fr w_mt_30">
				<ul class="w_ul">
					<li><a href="" target="_blank">官网首页</a></li>
					<li><a href="" target="_blank">北京老年医院</a></li>
				</ul>
			</div>
		</div>
		<div class="c_login_bg">
			<div class=" w_m_auto c_login_center c_login_width">
				<div class="w_h_40"></div>
				<div class="c_login_sign">
					<div class="c_login_fix w_opacity_90"></div>
					<div class="c_login_info">

						<div>
							<div class="c_login_pos">
								<div class="c_login_title"><#if a_macro_const_manage_home_title??>${a_macro_const_manage_home_title}<#else>后台登陆</#if></div>
								<div class="w_h_20"></div>

								<form>
									用户名： <input type="text" class="c_login_text"
										id="zw_f_login_name" name="zw_f_login_name"
										class="input-block-level" placeholder="请输入用户名" value="">
									<div class="w_h_20"></div>
									密&nbsp;&nbsp;&nbsp;&nbsp;码： <input type="password"
										class="c_login_text" id="zw_f_login_pass"
										name="zw_f_login_pass" class="input-block-level"
										placeholder="请输入密码" value="">
									<div class="w_h_20"></div>
									<div class="w_al_center">
										<input class="btn btn-large btn-danger" id="id_manage_login_login_submit" type="button"
											zapweb_attr_operate_id="115793e80b38485aaba8223e0ea101b6"
											onclick="zapjs.zw.login_post(this)" value="登录"/>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a>忘记密码</a>
									</div>
									<input type="hidden" id="zapjs_zw_login_sucess_target"
										name="zapjs_zw_login_sucess_target" value="../manage/home" />

								</form>
								<div class="w_h_20"></div>
								<div class="w_al_center c_login_desc">官方联系电话：400-005-5050</div>
							</div>
							<div class="c_login_version">当前系统版本：${b_method.upClass("com.srnpr.zapcom.basesupport.VersionSupport").upVersionFullName()}</div>

						</div>

					</div>

				</div>
			</div>
		</div>



		<div class=" w_m_auto c_login_footer c_login_width">

			<div class="w_fl w_mt_15">北京慕莎科技有限公司   版权所有</div>
			<div class="w_fr w_mt_15">
				<ul class="w_ul">
					<li><a href=""
						target="_blank">关于我们</a></li>
					<li><a
						href=""
						target="_blank">联系我们</a></li>
					<li><a href=""
						target="_blank">产品中心</a></li>
					<li><a
						href=""
						target="_blank">招贤纳士</a></li>
					<li><a
						href=""
						target="_blank">帮助中心</a></li>
				</ul>
			</div>
		</div>
	</div>

<script type="text/javascript">
$(function(){zapadmin.login_page()});
</script>


