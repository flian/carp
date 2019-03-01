package org.lotus.carp.base.utils.pdf;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.io.ByteStreams;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * PDF 文件水印工具类
 *
 * @author: Foy Lian
 * Date: 3/1/2019
 * Time: 11:26 AM
 */
@Slf4j
public class PdfWaterMarkUtils {

    /**
     * 添加pdf 文字水印
     * @param source 需要加水印的pdf
     * @param waterMarkText 需要添加的文字
     * @param dest 生成的pdf输出路径
     */
    public static void addTextWatermark(InputStream source, String waterMarkText, OutputStream dest) {
        Preconditions.checkNotNull(source);
        Preconditions.checkArgument(!Strings.isNullOrEmpty(waterMarkText));
        Preconditions.checkNotNull(dest);
        try {
            // 待加水印的文件
            PdfReader reader = new PdfReader(source);

            // 加完水印的文件
            PdfStamper stamper = new PdfStamper(reader, dest);
            int total = reader.getNumberOfPages() + 1;
            PdfContentByte content;
            // 设置字体
            BaseFont font = BaseFont.createFont();
            // 循环对每页插入水印
            for (int i = 1; i < total; i++) {
                // 水印的起始
                content = stamper.getUnderContent(i);
                // 开始
                content.beginText();
                // 设置颜色 默认为蓝色
                content.setColorFill(BaseColor.BLUE);
                // content.setColorFill(Color.GRAY);
                // 设置字体及字号
                content.setFontAndSize(font, 38);
                // 设置起始位置
                int textWidth = 300;
                int textHeight = 100;
                content.setTextMatrix(textWidth, textHeight);

                // 开始写入水印
                content.showTextAligned(Element.ALIGN_LEFT, waterMarkText, textWidth, textHeight, 45);
                content.endText();
            }
            stamper.close();
        } catch (Exception e) {
            log.error("add pdf addTextWatermark error:", e);
        }
    }

    /**
     *   为pdf文件添加图片水印
     * @param source 需要添加水印的源文件
     * @param watermarkImageIn 图片水印
     * @param dest 生成文件的输出路径
     */
    public static void addImageWatermark(InputStream source, InputStream watermarkImageIn, OutputStream dest) {
        Preconditions.checkNotNull(source);
        Preconditions.checkNotNull(watermarkImageIn);
        Preconditions.checkNotNull(dest);
        try {
            PdfReader reader = new PdfReader(source);
            PdfStamper stamp = new PdfStamper(reader, dest);

            int total = reader.getNumberOfPages() + 1;
            PdfContentByte content;
            Image img = Image.getInstance(ByteStreams.toByteArray(watermarkImageIn));
            img.setAbsolutePosition(30, 100);
            for (int i = 1; i < total; i++) {
                // 在内容上方加水印
                content = stamp.getOverContent(i);
                content.addImage(img);
            }
            stamp.close();
            reader.close();
        } catch (Exception e) {
            log.error("error add pdf addImageWatermark: ", e);
        }
    }

}
