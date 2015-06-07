package com.srnpr.yeshospital.support;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOInvalidTreeException;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.ImageOutputStream;

import org.apache.commons.lang.StringUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.basemodel.MDataMap;
import com.srnpr.zapcom.topdo.TopConst;
import com.srnpr.zapweb.helper.WebHelper;

public class QrcodeSupport extends BaseClass {

	public boolean createQrcode(String sText, int iWidth, int iHeight,
			String sTarget) {
		try {

			Map<EncodeHintType, Object> map = new HashMap<EncodeHintType, Object>();

			map.put(EncodeHintType.CHARACTER_SET, TopConst.CONST_BASE_ENCODING);

			BitMatrix bitMatrix = new MultiFormatWriter().encode(sText,
					BarcodeFormat.QR_CODE, iWidth, iHeight, map);

			Path path = new File(sTarget).toPath();
			MatrixToImageWriter.writeToPath(bitMatrix,
					StringUtils.substringAfterLast(sTarget, "."), path);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	private String filePix = ".png";

	public String createImage(String filePath, List<MDataMap> listMap) {
		String sTarget = "";

		try {

			// 分别生成对应的二维码

			for (MDataMap map : listMap) {

				String sFile = filePath + "qrcode_" + WebHelper.upUuid()
						+ filePix;
				createQrcode(map.get("url"), 600, 600, sFile);

				map.put("qs_qrcode_path", sFile);
			}

			// 开始生成加字的二维码
			for (MDataMap map : listMap) {

				String sFile = filePath + "qrtext_" + WebHelper.upUuid()
						+ filePix;

				// 1011*638
				int iItemWidth = 991;
				int iItemHeight = 608;

				BufferedImage imageResult = new BufferedImage(iItemWidth,
						iItemHeight, BufferedImage.TYPE_INT_RGB);

				Graphics2D g = imageResult.createGraphics();
				g.setBackground(Color.WHITE);

				g.fillRect(0, 0, iItemWidth, iItemHeight);// 填充整个屏幕

				BufferedImage imageFirst = ImageIO.read(new File(map
						.get("qs_qrcode_path")));
				int width = imageFirst.getWidth();// 图片宽度
				int height = imageFirst.getHeight();// 图片高度
				int[] imageArrayFirst = new int[width * height];// 从图片中读取RGB
				imageArrayFirst = imageFirst.getRGB(0, 0, width, height,
						imageArrayFirst, 0, width);

				imageResult.setRGB(0, 0, width, height, imageArrayFirst, 0,
						width);// 设置左半部分的RGB

				g.setColor(Color.BLACK);// 设置字体颜色
				Font font = new Font("msyh", Font.PLAIN, 40);// 添加字体的属性设置
				g.setFont(font);
				int x = 550;
				int y = 150;

				String[] contentArr = map.get("text").split(";");
				for (int i = 0, j = contentArr.length; i < j; i++) {
					g.drawString(contentArr[i].toString(), x, y);
					y += 40 + 20;
				}

				// 开始生成边框线
				g.drawLine(iItemWidth - 1, 0, iItemWidth - 1, iItemHeight);
				g.drawLine(0, iItemHeight - 1, iItemWidth, iItemHeight - 1);
				g.dispose();

				File outFile = new File(sFile);
				ImageIO.write(imageResult,
						StringUtils.substringAfterLast(filePix, "."), outFile);// 写图片

				map.put("qs_qrtext_path", sFile);

			}

			if (true) {

				int iA4Width = 2479;
				int iA4Height = 3508;
				BufferedImage imageResult = new BufferedImage(iA4Width,
						iA4Height, BufferedImage.TYPE_INT_RGB);

				Graphics2D g = imageResult.createGraphics();
				g.setBackground(Color.WHITE);

				g.fillRect(0, 0, iA4Width, iA4Height);// 填充整个屏幕

				g.dispose();

				int iX = 0;
				int iY = 0;
				String sFile = filePath + "img_" + WebHelper.upUuid() + filePix;
				// 开始拼接图片
				for (MDataMap map : listMap) {

					BufferedImage imageFirst = ImageIO.read(new File(map
							.get("qs_qrtext_path")));
					int width = imageFirst.getWidth();// 图片宽度
					int height = imageFirst.getHeight();// 图片高度
					int[] imageArrayFirst = new int[width * height];// 从图片中读取RGB
					imageArrayFirst = imageFirst.getRGB(0, 0, width, height,
							imageArrayFirst, 0, width);

					if (iX + width > iA4Width) {
						iX = 0;
						iY = iY + height;
					}

					imageResult.setRGB(iX, iY, width, height, imageArrayFirst,
							0, width);// 设置左半部分的RGB

					iX = iX + width;

				}

				File outFile = new File(sFile);

				/*
				 * ImageIO.write(imageResult,
				 * StringUtils.substringAfterLast(filePix, "."), outFile);// 写图片
				 */
				saveGridImage(outFile, imageResult);
				
				sTarget=sFile;

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return sTarget;

	}

	/**
	 * 300DPI保存图片
	 * 
	 * @param output
	 * @param gridImage
	 * @throws IOException
	 */
	private void saveGridImage(File output, RenderedImage gridImage)
			throws IOException {
		output.delete();

		final String formatName = "png";

		for (Iterator<ImageWriter> iw = ImageIO
				.getImageWritersByFormatName(formatName); iw.hasNext();) {
			ImageWriter writer = iw.next();
			ImageWriteParam writeParam = writer.getDefaultWriteParam();
			ImageTypeSpecifier typeSpecifier = ImageTypeSpecifier
					.createFromBufferedImageType(BufferedImage.TYPE_INT_RGB);
			IIOMetadata metadata = writer.getDefaultImageMetadata(
					typeSpecifier, writeParam);
			if (metadata.isReadOnly()
					|| !metadata.isStandardMetadataFormatSupported()) {
				continue;
			}

			setDPI(metadata);

			final ImageOutputStream stream = ImageIO
					.createImageOutputStream(output);
			try {
				writer.setOutput(stream);
				writer.write(metadata, new IIOImage(gridImage, null, metadata),
						writeParam);
			} finally {
				stream.close();
			}
			break;
		}
	}

	private void setDPI(IIOMetadata metadata) throws IIOInvalidTreeException {

		int DPI = 300;

		// for PMG, it's dots per millimeter
		double dotsPerMilli = 1.0 * DPI / 10 / 2.54;

		IIOMetadataNode horiz = new IIOMetadataNode("HorizontalPixelSize");
		horiz.setAttribute("value", Double.toString(dotsPerMilli));

		IIOMetadataNode vert = new IIOMetadataNode("VerticalPixelSize");
		vert.setAttribute("value", Double.toString(dotsPerMilli));

		IIOMetadataNode dim = new IIOMetadataNode("Dimension");
		dim.appendChild(horiz);
		dim.appendChild(vert);

		IIOMetadataNode root = new IIOMetadataNode("javax_imageio_1.0");
		root.appendChild(dim);

		metadata.mergeTree("javax_imageio_1.0", root);
	}

}
