 <#include "../zapmacro/zapmacro_mobile.ftl" />
 <#include "../macro/macro_mobile.ftl" />

<@m_mobile_html_begin />
<@m_mobile_body_begin />

<header class="bar bar-nav">
  <button class="btn btn-link btn-nav pull-left" <@m_mobile_back_page/> >
    <span class="icon icon-left-nav"></span>
    返回
  </button>
 
  <h1 class="title">选择药物</h1>
</header>


<div class="zmcss_o_a zmcss_w_96">
<div class="zmcss_h_20"></div>

<input id="yesapp_ts_search" type="search" placeholder="请输入药物名" onkeyup="yesapp_tour.tour_drug_search(this)"/>
<div class="card">
  <ul class="table-view" id="yesapp_ts_table">
    
  </ul>
</div>


<div class="zmcss_h_20"></div>



 </div>


 <@m_mobile_body_end p_js=["yesapp/js/yesapp-tour.js"]/>
 
 
 <@m_mobile_html_end />






