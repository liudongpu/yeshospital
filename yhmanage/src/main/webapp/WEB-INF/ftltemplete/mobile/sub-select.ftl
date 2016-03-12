 <#include "../zapmacro/zapmacro_mobile.ftl" />
 <#include "../macro/macro_mobile.ftl" />
<@m_mobile_html_begin />
<@m_mobile_body_begin />

<#assign a_mould_type=RequestParameters['u_mould_type']?default("") >
		<#assign a_app_info=b_method.upClass("com.srnpr.yeshospital.support.AppInfoSupport") />


<@m_mobile_header_begin p_title="选择" />
	<@m_mobile_button_back />
	
<@m_mobile_header_end />

<div class="zmcss_o_a zmcss_w_96">
	<div class="zmcss_h_20"></div>
	
			<div data-role="collapsibleset" data-collapsed-icon="arrow-r" data-expanded-icon="arrow-d" >
		    	<#assign a_visit_money= a_app_info.upMouldList("mould_type=:mould_type  ","mould_type",a_mould_type)  />
		    	<#list a_visit_money as ea>
		    	<#if ea["parent_code"]=="0">
			    	<div data-role="collapsible" <#if ea_index==0> data-collapsed="false" </#if> >
			        <h2>${ea["mould_content"]}</h2>
			            <ol data-role="listview" class="yb_list_view">
			            	<#list a_visit_money as eb>
								<#if eb["parent_code"]==ea["model_code"]>
									
									<li><a  href="javascript:yesapp_sub.sub_select_click('${eb["mould_content"]}')" ><p  class="yb_space">${eb["mould_content"]}</p></a></li>

								</#if>
							</#list>

			            </ol>
			        </div>
			    	</#if>
		    	</#list>
	        
		       
		       
		    </div><!-- /collapsible set -->

</div>

 <@m_mobile_body_end p_js=["yesapp/js/yesapp-sub.js"]/>
 
  <@m_mobile_html_initjs e_js="yesapp_sub.init_sub_select()" />
 <@m_mobile_html_end />