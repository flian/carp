package org.lotus.carp.base.utils.image;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 图片水印工具类
 *
 * @author: Foy Lian
 * Date: 2/27/2019
 * Time: 10:59 AM
 */
@Slf4j
public class ImageWatermarkUtils {
    public enum ImageType {
        JPEG,
        JPG,
        PNG
    }

    public enum Position {
        top_left,
        middle_center,
        bottom_right,
        bottom_center
    }

    /**
     * Embeds a textual watermark over a source image to produce
     * a watermarked one.
     *
     * @param source            The image source input stream
     * @param imageType         image type
     * @param waterMarkText     The text to be embedded as watermark.
     * @param waterMarkPosition The text position.
     * @param dest              The output image output stream
     */
    static void addTextWatermark(InputStream source, ImageType imageType, String waterMarkText, Position waterMarkPosition, OutputStream dest) {
        Preconditions.checkNotNull(source);
        Preconditions.checkNotNull(imageType);
        Preconditions.checkNotNull(waterMarkText);
        Preconditions.checkNotNull(waterMarkPosition);
        Preconditions.checkNotNull(dest);

        //String text, File sourceImageFile, File destImageFile)
        try {
            BufferedImage sourceImage = ImageIO.read(source);
            Graphics2D g2d = (Graphics2D) sourceImage.getGraphics();

            // initializes necessary graphic properties
            AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f);
            g2d.setComposite(alphaChannel);
            g2d.setColor(Color.BLUE);
            g2d.setFont(new Font("Arial", Font.BOLD, 64));
            FontMetrics fontMetrics = g2d.getFontMetrics();
            Rectangle2D rect = fontMetrics.getStringBounds(waterMarkText, g2d);

            // calculates the coordinate where the String is painted
            int x = 0;
            int y = 0;
            switch (waterMarkPosition) {
                case top_left: {
                    x = 0;
                    y = 0;
                    break;
                }
                case bottom_right: {
                    x = (sourceImage.getWidth() - (int) rect.getWidth());
                    y = sourceImage.getHeight() - (int) rect.getHeight();
                    break;
                }
                case bottom_center: {
                    x = (sourceImage.getWidth() - (int) rect.getWidth());
                    y = sourceImage.getHeight() - (int) rect.getHeight() / 2;
                    break;
                }
                case middle_center: {
                    x = (sourceImage.getWidth() - (int) rect.getWidth()) / 2;
                    y = sourceImage.getHeight() / 2;
                    break;
                }
            }
            // paints the textual watermark
            g2d.drawString(waterMarkText, x, y);
            ImageIO.write(sourceImage, imageType.name(), dest);
            g2d.dispose();

            log.info("The tex watermark is added to the image.");

        } catch (IOException ex) {
            log.error("ImageWatermarkUtils.addTextWatermark error:", ex);
        }
    }

    /**
     * Embeds an image watermark over a source image to produce
     * a watermarked one.
     *
     * @param source            The image file used as the watermark.
     * @param imageType         image type
     * @param watermarkImageIn  watermarkImage type
     * @param waterMarkPosition watermarkImage position
     * @param dest              The output image
     */
    static void addImageWatermark(InputStream source, ImageType imageType, InputStream watermarkImageIn, Position waterMarkPosition, OutputStream dest) {
        Preconditions.checkNotNull(source);
        Preconditions.checkNotNull(imageType);
        Preconditions.checkNotNull(watermarkImageIn);
        Preconditions.checkNotNull(waterMarkPosition);
        Preconditions.checkNotNull(dest);
        try {
            BufferedImage sourceImage = ImageIO.read(source);
            BufferedImage watermarkImage = ImageIO.read(watermarkImageIn);

            // initializes necessary graphic properties
            Graphics2D g2d = (Graphics2D) sourceImage.getGraphics();
            AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f);
            g2d.setComposite(alphaChannel);

            // calculates the coordinate where the image is painted
            int x = 0;
            int y = 0;
            switch (waterMarkPosition) {
                case top_left: {
                    x = 0;
                    y = 0;
                    break;
                }
                case bottom_right: {
                    x = (sourceImage.getWidth() - (int) watermarkImage.getWidth());
                    y = sourceImage.getHeight() - (int) watermarkImage.getHeight();
                    break;
                }
                case bottom_center: {
                    x = (sourceImage.getWidth() - (int) watermarkImage.getWidth());
                    y = sourceImage.getHeight() - (int) watermarkImage.getHeight() / 2;
                    break;
                }
                case middle_center: {
                    x = (sourceImage.getWidth() - (int) watermarkImage.getWidth()) / 2;
                    y = sourceImage.getHeight() / 2;
                    break;
                }
            }
            // paints the image watermark
            g2d.drawImage(watermarkImage, x, y, null);

            ImageIO.write(sourceImage, imageType.name(), dest);
            g2d.dispose();

            log.info("The image watermark is added to the image.");

        } catch (IOException ex) {
            log.error("ImageWatermarkUtils.addImageWatermark error:", ex);
        }
    }
}
