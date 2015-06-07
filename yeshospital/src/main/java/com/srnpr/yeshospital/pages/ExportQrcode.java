package com.srnpr.yeshospital.pages;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
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

import com.srnpr.yeshospital.support.QrcodeSupport;
import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topdo.TopDir;
import com.srnpr.zapcom.topdo.TopUp;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.webdo.WebUp;
import com.srnpr.zapweb.webmodel.MPageData;
import com.srnpr.zapweb.webmodel.MWebPage;
import com.srnpr.zapweb.webpage.PageExec;
import com.srnpr.zapweb.webpage.RootProcess;

public class ExportQrcode extends RootProcess {
	/**
	 * 导出文件名
	 */
	private String exportName = "";

	private MPageData pageData = new MPageData();

	private HttpServletResponse httpServletResponse = null;

	public void export(String sOperateId, HttpServletRequest request,
			HttpServletResponse response) {

		exportExcel(sOperateId, request, response);

		doExport();
	}

	public void exportExcel(String sOperateId, HttpServletRequest request,
			HttpServletResponse response) {

		MWebPage mPage = WebUp.upPage(sOperateId);

		MDataMap mReqMap = convertRequest(request);

		PageExec pExec = new PageExec();

		MDataMap mOptionMap = new MDataMap("optionExport", "1");

		// exportName = mPage.getPageName() + "-"
		// + FormatHelper.upDateTime(new Date(), "yyyy-MM-dd-HH-mm-ss");
		exportName = "qrcode-"
				+ FormatHelper.upDateTime(new Date(), "yyyy-MM-dd-HH-mm-ss");
		String sAgent = request.getHeader("USER-AGENT");

		if (StringUtils.isNotEmpty(sAgent)) {
			boolean bFlagIE = request.getHeader("USER-AGENT").toLowerCase()
					.indexOf("msie") > 0 ? true : false;

			if (bFlagIE) {
				try {
					exportName = URLEncoder.encode(exportName, "UTF8");
				} catch (UnsupportedEncodingException e) {

					e.printStackTrace();
				}
			} else {

				try {
					exportName = new String(exportName.getBytes("UTF-8"),
							"ISO8859-1");
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

	public void exportExcelFile(MPageData mPageData,
			HttpServletResponse hResponse) {

		if (StringUtils.isEmpty(exportName)) {
			exportName = "export-"
					+ FormatHelper
							.upDateTime(new Date(), "yyyy-MM-dd-HH-mm-ss");
		}
		/*
		 * hResponse.setContentType("application/binary;charset=ISO8859_1"); try
		 * { exportName = new String(exportName.getBytes(), "ISO8859_1"); }
		 * catch (UnsupportedEncodingException e1) { // TODO Auto-generated
		 * catch block e1.printStackTrace(); }
		 */
		hResponse.setContentType("application/binary;charset=UTF-8");

		hResponse.setHeader("Content-disposition", "attachment; filename="
				+ exportName + ".png");// 组装附件名称和格式

		ServletOutputStream outputStream = null;
		try {
			outputStream = hResponse.getOutputStream();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// HSSFCell cell = null;

		List<String> lCode = new ArrayList<String>();

		for (List<String> lRow : mPageData.getPageData()) {

			lCode.add(lRow.get(0));

		}

		List<MDataMap> listMaps = new ArrayList<MDataMap>();

		for (MDataMap map : DbUp.upTable("yh_member_extend_geracomium")
				.queryIn("", "", "", new MDataMap(), 0, 10, "member_code",
						StringUtils.join(lCode, ","))) {

			listMaps.add(new MDataMap("url", "http://q.yxl9.cn/"
					+ map.get("post_card"), "text", "姓名："
					+ map.get("member_name") + ";卡号：" + map.get("post_card")));

		}

		String sFile = new QrcodeSupport().createImage(
				new TopDir().upTempDir("qrcode/"), listMaps);

		File file2 = new File(sFile);
		FileInputStream fis;
		try {
			fis = new FileInputStream(file2);

			byte[] buf = new byte[(int) file2.length()];
			fis.read(buf);
			fis.close();

			outputStream.write(buf);

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			// wb.write(outputStream);

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
}
