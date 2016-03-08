package com.srnpr.yeshospital.support;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import com.atomikos.util.IOHelper;
import com.srnpr.zapcom.basehelper.FormatHelper;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topdo.TopDir;
import com.srnpr.zapdata.dbdo.DbUp;
import com.srnpr.zapweb.helper.WebHelper;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class DocSupport {

	public void exportDoc(String sOperateId, HttpServletRequest request, HttpServletResponse hResponse) {

		String sDocFile = new TopDir().upServerletPath("resources/doc/") + "memberagree.doc";
		Map<String, Object> map = upMemberInfo(sOperateId);

		export(sDocFile, map, map.get("docname").toString(), request, hResponse);

	}

	private void export(String sDocFile, Map<String, Object> map, String sExportName, HttpServletRequest request,
			HttpServletResponse hResponse) {

		sExportName = sExportName + "-" + FormatHelper.upDateTime(new Date(), "yyyy-MM-dd-HH-mm-ss");

		String sAgent = request.getHeader("USER-AGENT");

		if (StringUtils.isNotEmpty(sAgent)) {
			boolean bFlagIE = (request.getHeader("USER-AGENT").toLowerCase().indexOf("msie") > 0
					|| request.getHeader("USER-AGENT").toLowerCase().indexOf("rv:11.0") > 0) ? true : false;

			if (bFlagIE) {
				try {
					sExportName = URLEncoder.encode(sExportName, "UTF8");
				} catch (UnsupportedEncodingException e) {

					e.printStackTrace();
				}
			} else {

				try {
					sExportName = new String(sExportName.getBytes("UTF-8"), "ISO8859-1");
				} catch (UnsupportedEncodingException e) {

					e.printStackTrace();
				}

			}
		}

		hResponse.setContentType("application/binary;charset=UTF-8");

		hResponse.setHeader("Content-disposition", "attachment; filename=" + sExportName + ".doc");// 组装附件名称和格式

		ServletOutputStream outputStream = null;
		try {
			outputStream = hResponse.getOutputStream();

			createWord(sDocFile, map, outputStream);

			outputStream.flush();
			outputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Map<String, Object> upMemberInfo(String sAgreeUid) {

		MDataMap mAgreeMap = DbUp.upTable("yh_member_agree").oneWhere(
				"member_code as mcode,agree_info as magree,left(now(),10) as mtoday", "", "", "uid", sAgreeUid);
		String sMemberCode = mAgreeMap.get("mcode");
		MDataMap mMemberMap = DbUp.upTable("yh_member_extend_geracomium")
				.oneWhere(
						"member_code as mcode,member_name as docname,member_name as mname,member_age as mage,geracomium_code as gcode,room_name as mroom,card_code as mcard"
								+ ",(select geracomium_name from yh_geracomium_info where yh_geracomium_info.geracomium_code=yh_member_extend_geracomium.geracomium_code) as gname"
								+ ",(select define_name from yh_define where yh_define.define_code=yh_member_extend_geracomium.account_type) as mins"
								+ ",post_card as mpost,medical_history as mmed" + "",
						"", "", "member_code", sMemberCode);

		if (DbUp.upTable("yh_report_info").count("member_code", sMemberCode) == 0) {
			DbUp.upTable("yh_report_info").insert("member_code", sMemberCode, "report_code", WebHelper.upCode("RC"));
		}

		MDataMap mReportMap = DbUp.upTable("yh_report_info")
				.oneWhere("electrocardiogram_info as dela,left(electrocardiogram_update,10) as tela"
						+ ",glucose_info as dglu,left(glucose_update,10) as tglu"
						+ ",oxygen_info as doxy,left(oxygen_update,10) as toxy"
						+ ",pressure_info as dpre,left(pressure_update,10) as tpre"
						+ ",temperature_info as dtem,left(temperature_update,10) as ttem" + ",'' as thea,'' as dhea",
				"", "", "member_code", sMemberCode);

		Map<String, Object> result = new HashMap<String, Object>();
		result.putAll(mAgreeMap);
		result.putAll(mMemberMap);
		result.putAll(mReportMap);
		return result;

	}

	public void createWord(String sTemplateName, Map<String, Object> dataMap, OutputStream outputStream) {

		try {

			/*
			 * 
			 * 
			 * Configuration configuration = null; configuration = new
			 * Configuration(); configuration.setDefaultEncoding("UTF-8");
			 * configuration.setDirectoryForTemplateLoading(new
			 * File(StringUtils.substringBeforeLast(sTemplateName, "/")));
			 * 
			 * Template t = null;
			 * 
			 * t = configuration.getTemplate(StringUtils.substringAfterLast(
			 * sTemplateName, "/")); // 文件名
			 * 
			 * Writer out = null;
			 * 
			 * out = new BufferedWriter(new OutputStreamWriter(outputStream));
			 * 
			 * t.process(dataMap, out);
			 */

			/*
			 * String templatePath = sTemplateName; InputStream is = new
			 * FileInputStream(templatePath); XWPFDocument doc = new
			 * XWPFDocument(is); // 替换段落里面的变量 this.replaceInPara(doc, dataMap);
			 * // 替换表格里面的变量 this.replaceInTable(doc, dataMap);
			 * 
			 * doc.write(outputStream);
			 * 
			 */

			InputStream is = new FileInputStream(sTemplateName);
			HWPFDocument doc = new HWPFDocument(is);
			Range range = doc.getRange();

			for (String sKey : dataMap.keySet()) {
				range.replaceText("${" + sKey + "}", dataMap.get(sKey).toString());
			}

			// 把doc输出到输出流中
			doc.write(outputStream);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 替换段落里面的变量
	 * 
	 * @param doc
	 *            要替换的文档
	 * @param params
	 *            参数
	 */
	private void replaceInPara(XWPFDocument doc, Map<String, Object> params) {
		Iterator<XWPFParagraph> iterator = doc.getParagraphsIterator();
		XWPFParagraph para;
		while (iterator.hasNext()) {
			para = iterator.next();
			this.replaceInPara(para, params);
		}
	}

	/**
	 * 替换表格里面的变量
	 * 
	 * @param doc
	 *            要替换的文档
	 * @param params
	 *            参数
	 */
	private void replaceInTable(XWPFDocument doc, Map<String, Object> params) {
		Iterator<XWPFTable> iterator = doc.getTablesIterator();
		XWPFTable table;
		List<XWPFTableRow> rows;
		List<XWPFTableCell> cells;
		List<XWPFParagraph> paras;
		while (iterator.hasNext()) {
			table = iterator.next();
			rows = table.getRows();
			for (XWPFTableRow row : rows) {
				cells = row.getTableCells();
				for (XWPFTableCell cell : cells) {
					paras = cell.getParagraphs();
					for (XWPFParagraph para : paras) {
						this.replaceInPara(para, params);
					}
				}
			}
		}
	}

	/**
	 * 替换段落里面的变量
	 * 
	 * @param para
	 *            要替换的段落
	 * @param params
	 *            参数
	 */
	private void replaceInPara(XWPFParagraph para, Map<String, Object> params) {
		List<XWPFRun> runs;
		Matcher matcher;

		if (this.matcher(para.getParagraphText()).find()) {
			runs = para.getRuns();
			for (int i = 0; i < runs.size(); i++) {
				XWPFRun run = runs.get(i);
				String runText = run.toString();
				matcher = this.matcher(runText);
				if (matcher.find()) {
					while ((matcher = this.matcher(runText)).find()) {
						runText = matcher.replaceFirst(String.valueOf(params.get(matcher.group(1))));
					}
					// 直接调用XWPFRun的setText()方法设置文本时，在底层会重新创建一个XWPFRun，把文本附加在当前文本后面，
					// 所以我们不能直接设值，需要先删除当前run,然后再自己手动插入一个新的run。
					para.removeRun(i);
					para.insertNewRun(i).setText(runText);
				}
			}
		}
	}

	/**
	 * 正则匹配字符串
	 * 
	 * @param str
	 * @return
	 */
	private Matcher matcher(String str) {
		Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(str);
		return matcher;
	}

}
