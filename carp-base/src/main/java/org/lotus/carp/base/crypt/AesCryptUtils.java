package org.lotus.carp.base.crypt;


import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;

/**
 * AES加密，解密工具类
 * 自定义加密
 *
 * @author: Foy Lian
 * Date: 12/12/2018
 * Time: 12:13 PM
 */
@Slf4j
public class AesCryptUtils {
    /**
     *  对文本进行加密操作
     * @param plainText 原生文本
     * @param encryptPassword 加密密码
     * @return 加密后的字符串
     */
    public static String encrypt(String plainText,String encryptPassword) {
        try {
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);
            //AES-256 will get `Illegal key size` exception
            // AES-128
            KeySpec spec = new PBEKeySpec(encryptPassword.toCharArray(), salt, 65536, 128);
            SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] key = f.generateSecret(spec).getEncoded();
            SecretKeySpec keySpec = new SecretKeySpec(key, "AES");

            byte[] ivBytes = new byte[16];
            random.nextBytes(ivBytes);
            IvParameterSpec iv = new IvParameterSpec(ivBytes);

            Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            c.init(Cipher.ENCRYPT_MODE, keySpec, iv);
            byte[] encValue = c.doFinal(plainText.getBytes());

            byte[] finalCipherText = new byte[encValue.length + 2 * 16];
            System.arraycopy(ivBytes, 0, finalCipherText, 0, 16);
            System.arraycopy(salt, 0, finalCipherText, 16, 16);
            System.arraycopy(encValue, 0, finalCipherText, 32, encValue.length);

            return Base64.getEncoder().encodeToString(finalCipherText);
        } catch (Exception ex) {
            log.error("AesCryptUtils encrypt error.", ex);
        }
        throw new RuntimeException("AesCryptUtils encrypt error");
    }

    /**
     *  对使用@encrypt加密后的文本进行解密
     *
     * @param encodedString 待解密的文本串
     * @param encryptPassword 解密密码
     * @return 解密后的明文
     */
    public static String decrypt(String encodedString,String encryptPassword) {
        try {
            byte[] finalCipherText = Base64.getDecoder().decode(encodedString);
            byte[] ivBytes = new byte[16];
            byte[] salt = new byte[16];
            byte[] encValue = new byte[finalCipherText.length - 2 * 16];
            System.arraycopy(finalCipherText, 0, ivBytes, 0, 16);
            System.arraycopy(finalCipherText, 16, salt, 0, 16);
            System.arraycopy(finalCipherText, 32, encValue, 0, encValue.length);
            KeySpec spec = new PBEKeySpec(encryptPassword.toCharArray(), salt, 65536, 128);
            SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] key = f.generateSecret(spec).getEncoded();
            SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
            IvParameterSpec iv = new IvParameterSpec(ivBytes);
            Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            c.init(Cipher.DECRYPT_MODE, keySpec, iv);
            byte[] originalBytes = c.doFinal(encValue);
            return new String(originalBytes);
        } catch (Exception ex) {
            log.error("AesCryptUtils decrypt error.", ex);
        }

        throw new RuntimeException("AesCryptUtils decrypt error");
    }
}
