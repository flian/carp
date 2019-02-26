package org.lotus.carp.base.crypt;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * hash工具类
 *
 * @author: Foy Lian
 * Date: 2/26/2019
 * Time: 10:36 AM
 */
@Slf4j
public class HashUtils {
    /**
     *  对给定的字符串，计算sha256散列值
     * @param plainText  字符串
     * @return sha256散列
     */
    public static String sha256String(String plainText) {
        InputStream stream = new ByteArrayInputStream(plainText.getBytes(StandardCharsets.UTF_8));
        return sha256(stream);
    }

    /**
     * 对给定输入流，计算sha256散列值.
     *
     * @param in 输入流
     * @return sha256散列
     */
    public static String sha256(InputStream in) {
        Preconditions.checkNotNull(in, "inputStream should not null");
        try (BufferedInputStream bis = new BufferedInputStream(in)) {
            byte[] buffer = new byte[8192];
            int count;
            MessageDigest digest = null;
            digest = MessageDigest.getInstance("SHA-256");
            while ((count = bis.read(buffer)) > 0) {
                digest.update(buffer, 0, count);
            }
            byte[] hash = digest.digest();
            String result = bytesToHex(hash);
            return result;
        } catch (Exception e) {
            log.error("error while sha256 inputStream:", e);
        }
        throw new RuntimeException("Failure to sha256 input stream!");
    }

    private static String bytesToHex(byte[] hashInBytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();

    }
}
