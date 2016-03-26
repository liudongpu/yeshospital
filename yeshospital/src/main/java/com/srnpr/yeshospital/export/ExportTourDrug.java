package com.srnpr.yeshospital.export;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webdo.WebUp;
import com.srnpr.zapweb.webexport.RootExport;
import com.srnpr.zapweb.webface.IWebFuncExport;
import com.srnpr.zapweb.webmodel.MPageData;
import com.srnpr.zapweb.webmodel.MWebPage;
import com.srnpr.zapweb.webpage.PageExec;
import com.srnpr.zapweb.webpage.RootProcess;

public class ExportTourDrug extends RootProcess implements IWebFuncExport {

	/**
	 * 导出文件名
	 */
	private String exportName = "";

	private MPageData pageData = new MPageData();

	private HttpServletResponse httpServletResponse = null;

	public void exportExcel(String sOperateId, HttpServletRequest request, HttpServletResponse response) {

		MWebPage mPage = WebUp.upPage(sOperateId);

		MDataMap mReqMap = convertRequest(request);

		PageExec pExec = new PageExec();

		MDataMap mOptionMap = new MDataMap("optionExport", "1");

		exportName = mPage.getPageName() + "-" + FormatHelper.upDateTime(new Date(), "yyyy-MM-dd-HH-mm-ss");

		String sAgent = request.getHeader("USER-AGENT");

		if (StringUtils.isNotEmpty(sAgent)) {
			boolean bFlagIE = request.getHeader("USER-AGENT").toLowerCase().indexOf("msie") > 0 ? true : false;

			if (bFlagIE) {
				try {
					exportName = URLEncoder.encode(exportName, "UTF8");
				} catch (UnsupportedEncodingException e) {

					e.printStackTrace();
				}
			} else {

				try {
					exportName = new String(exportName.getBytes("UTF-8"), "ISO8859-1");
				} catch (UnsupportedEncodingException e) {

					e.printStackTrace();
				}

			}
		}

		pageData = pExec.upChartData(mPage, mReqMap, mOptionMap);

		httpServletResponse = response;
	}

	public void doExport() {

		exportExcelFile(pageData, httpServletResponse);
	}

	public void exportExcelFile(MPageData mPageData, HttpServletResponse hResponse) {

		if (StringUtils.isEmpty(exportName)) {
			exportName = "export-" + FormatHelper.upDateTime(new Date(), "yyyy-MM-dd-HH-mm-ss");
		}
		/*
		 * hResponse.setContentType("application/binary;charset=ISO8859_1"); try
		 * { exportName = new String(exportName.getBytes(), "ISO8859_1"); }
		 * catch (UnsupportedEncodingException e1) { // TODO Auto-generated
		 * catch block e1.printStackTrace(); }
		 */
		hResponse.setContentType("application/binary;charset=UTF-8");

		hResponse.setHeader("Content-disposition", "attachment; filename=" + exportName + ".xls");// 组装附件名称和格式

		ServletOutputStream outputStream = null;
		try {
			outputStream = hResponse.getOutputStream();
		} catch (Exception e) {
			e.printStackTrace();
		}

		HSSFWorkbook wb = new HSSFWorkbook();// 建立新HSSFWorkbook对象

		HSSFSheet sheet = wb.createSheet("excel");

		int iNowRow = 0;

		HSSFRow headRow = sheet.createRow(iNowRow);

		// 定义表头样式
		HSSFCellStyle hHeaderStyle = wb.createCellStyle();
		HSSFFont font = wb.createFont();
		// 加粗
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗

		hHeaderStyle.setFont(font);

		int iPageHeadSize = mPageData.getPageHead().size();

		mPageData.getPageHead().add("总金额");
		mPageData.getPageHead().add("个人承担金额");

		for (int i = 0, j = mPageData.getPageHead().size(); i < j; i++) {
			HSSFCell hCell = headRow.createCell(i);
			hCell.setCellValue(mPageData.getPageHead().get(i));
			hCell.setCellStyle(hHeaderStyle);

		}

		// HSSFCell cell = null;

		String sLastMember = "";

		String sTourCode = "";

		for (List<String> lRow : mPageData.getPageData()) {
			iNowRow++;

			if (!sLastMember.equals(lRow.get(1))) {
				sLastMember = lRow.get(1);
				HSSFRow hRow = sheet.createRow(iNowRow);

				hRow.createCell(1).setCellValue(sLastMember);

				MDataMap mDrugInfo = DbUp.upTable("yh_tour_order_drug").one("record_code", lRow.get(0));

				MDataMap mDetailMap = DbUp.upTable("yh_tour_order_detail").one("tour_code", mDrugInfo.get("tour_code"),
						"member_code", mDrugInfo.get("member_code"));

				if (StringUtils.isBlank(sTourCode)) {
					sTourCode = mDrugInfo.get("tour_code");
				}

				if (mDetailMap != null && mDetailMap.size() > 0) {
					hRow.createCell(iPageHeadSize).setCellValue(mDetailMap.get("money_all"));

					hRow.createCell(iPageHeadSize + 1).setCellValue(mDetailMap.get("money_person"));
				}

				iNowRow++;
			}

			HSSFRow hRow = sheet.createRow(iNowRow);
			for (int i = 0, j = lRow.size(); i < j; i++) {

				if (i != 1) {
					HSSFCell hCell = hRow.createCell(i);
					hCell.setCellValue(lRow.get(i));
				}
			}

		}

		if (true) {

			iNowRow++;

			if (StringUtils.isNotBlank(sTourCode)) {

				MDataMap mTourMap = DbUp.upTable("yh_tour_invoice").oneWhere(
						"sum(case money_type when '46580001000400040001' then money_all else 0 end) as a_ma,sum(case money_type when '46580001000400040001' then money_person else 0 end) as a_mp,sum(case money_type when '46580001000400040001' then 0 else money_all end) as b_ma,sum(case money_type when '46580001000400040001' then 0 else money_person end) as b_mp",
						"", "", "tour_code", sTourCode);

				HSSFRow hRow = sheet.createRow(iNowRow);

				hRow.createCell(0).setCellValue("购药总金额:" + mTourMap.get("a_ma"));
				hRow.createCell(1).setCellValue("购药自费金额:" + mTourMap.get("a_mp"));
				hRow.createCell(2).setCellValue("化验总金额:" + mTourMap.get("b_ma"));
				hRow.createCell(3).setCellValue("化验自费金额:" + mTourMap.get("b_mp"));
			}

		}

		if (true) {

			iNowRow++;

			if (StringUtils.isNotBlank(sTourCode)) {

				MDataMap mTourMap = DbUp.upTable("yh_tour_order_info").one("tour_code", sTourCode);

				HSSFRow hRow = sheet.createRow(iNowRow);

				hRow.createCell(0).setCellValue("单据总计");
				hRow.createCell(1).setCellValue("社保卡总数:" + mTourMap.get("sum_card"));
			}

		}

		try {
			wb.write(outputStream);

			outputStream.flush();
			outputStream.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getExportName() {
		return exportName;
	}

	public void setExportName(String exportName) {
		this.exportName = exportName;
	}

	public MPageData getPageData() {
		return pageData;
	}

	public void setPageData(MPageData pageData) {
		this.pageData = pageData;
	}

	public void export(String sOperateId, HttpServletRequest request, HttpServletResponse response) {
		exportExcel(sOperateId, request, response);

		doExport();

	}

}
