 <#include "../../macro/macro_mobile.ftl" />




<@m_mobile_html_begin />
<@m_mobile_body_begin />

<header class="bar bar-nav">
  <button class="btn btn-link btn-nav pull-left" <@m_mobile_back_page/> >
    <span class="icon icon-left-nav"></span>
    返回
  </button>
  <button class="btn btn-link btn-nav pull-right" <@m_mobile_call_page p_page="../tour/person" /> >
    保存
    
  </button>
  <h1 class="title">创建一个巡视</h1>
</header>





<div class="yb_layout_box">
 <#include "../../zapmacro/zapmacro_mobile.ftl" />
<#assign a_order_address=b_method.upControlPage("page_chart_v_yh_tour_order_info","")>
<@m_zapmacro_mobile_page_add a_order_address />
 


 </div>
<div id="myModalexample" class="modal">
  <header class="bar bar-nav">
    <a class="icon icon-close pull-right" href="#myModalexample"></a>
    <h1 class="title">全部养老院</h1>
  </header>

  <div class="content">
    <div class="card">
  <ul class="table-view">
    <li class="table-view-cell">养老院一号</li>
    <li class="table-view-cell">养老院一号</li>
    <li class="table-view-cell table-view-divider">Divider</li>
    <li class="table-view-cell">Item 3</li>
    <li class="table-view-cell">Item 4</li>
  </ul>
</div>

  </div>
</div>


    
 <@m_mobile_body_end />
 
 <@m_mobile_html_end />