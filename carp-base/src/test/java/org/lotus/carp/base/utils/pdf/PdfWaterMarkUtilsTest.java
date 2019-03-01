package org.lotus.carp.base.utils.pdf;

import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *  pdf水印测试
 *
 * @author: Foy Lian
 * Date: 3/1/2019
 * Time: 11:56 AM
 */
public class PdfWaterMarkUtilsTest {

    @Test
    @Ignore
    public void addTextWatermark() throws IOException {
        String source = "pdf/sample.pdf";
        String outFile = "c:/temp/sample_text.pdf";
        String waterMarkText = "Good 你好Foy Lian！";
        InputStream imageSource = PdfWaterMarkUtilsTest.class.getClassLoader().getResourceAsStream(source);
        OutputStream imageTarget = Files.newOutputStream(Paths.get(outFile));
        PdfWaterMarkUtils.addTextWatermark(imageSource, waterMarkText, imageTarget);
    }

    @Test
    @Ignore
    public void addImageWatermark() throws IOException {
        String source = "pdf/sample.pdf";
        String outFile = "c:/temp/sample_img.pdf";
        String waterMarkImage = "pdf/CodeJavaLogo.png";
        InputStream watermarkImageIn = PdfWaterMarkUtilsTest.class.getClassLoader().getResourceAsStream(waterMarkImage);
        InputStream imageSource = PdfWaterMarkUtilsTest.class.getClassLoader().getResourceAsStream(source);
        OutputStream imageTarget = Files.newOutputStream(Paths.get(outFile));
        PdfWaterMarkUtils.addImageWatermark(imageSource, watermarkImageIn, imageTarget);
    }
}