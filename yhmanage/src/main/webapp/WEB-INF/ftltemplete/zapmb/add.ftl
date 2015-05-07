<header class="bar bar-nav">
  <button class="btn btn-link btn-nav pull-left" <@m_mobile_back_page/> >
    <span class="icon icon-left-nav"></span>
    返回
  </button>
  <button class="btn btn-link btn-nav pull-right" <@m_mobile_call_page p_page="../tour/person" /> >
    保存
  </button>
  
  <@m_zapmacro_mobile_top_operate   b_page.getWebPage().getPageOperate()  "116001016" />
  
  <h1 class="title">${b_page.getWebPage().getPageName()}</h1>
</header>


<div class="zmcss_o_a zmcss_w_96">
<div class="zmcss_h_20"></div>
<@m_zapmacro_mobile_page_add b_page />


 </div>