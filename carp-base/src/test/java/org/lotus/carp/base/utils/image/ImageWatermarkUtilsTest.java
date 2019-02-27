package org.lotus.carp.base.utils.image;

import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Foy Lian
 * Date: 2/27/2019
 * Time: 3:42 PM
 */
public class ImageWatermarkUtilsTest {

    @Test
    @Ignore
    public void addTextWatermark() throws IOException {
        String source = "images/1.jpg";
        String outFile = "c:/temp/1_text.jpg";
        String waterMarkText = "Good 你好Foy Lian！";
        InputStream imageSource = ImageWatermarkUtilsTest.class.getClassLoader().getResourceAsStream(source);
        OutputStream imageTarget = Files.newOutputStream(Paths.get(outFile));
        ImageWatermarkUtils.ImageType type = ImageWatermarkUtils.ImageType.JPG;
        ImageWatermarkUtils.Position position = ImageWatermarkUtils.Position.bottom_right;
        ImageWatermarkUtils.addTextWatermark(imageSource, type, waterMarkText, position, imageTarget);
    }

    @Test
    @Ignore
    public void addImageWatermark() throws IOException {
        String source = "images/1.jpg";
        String outFile = "c:/temp/1_img.jpg";
        String waterMarkImage = "images/CodeJavaLogo.png";
        InputStream watermarkImageIn = ImageWatermarkUtilsTest.class.getClassLoader().getResourceAsStream(waterMarkImage);
        InputStream imageSource = ImageWatermarkUtilsTest.class.getClassLoader().getResourceAsStream(source);
        OutputStream imageTarget = Files.newOutputStream(Paths.get(outFile));
        ImageWatermarkUtils.ImageType type = ImageWatermarkUtils.ImageType.JPG;
        ImageWatermarkUtils.Position position = ImageWatermarkUtils.Position.bottom_right;
        ImageWatermarkUtils.addImageWatermark(imageSource, type, watermarkImageIn, position, imageTarget);
    }
}