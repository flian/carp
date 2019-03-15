package org.lotus.carp.base.utils.sign;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 签名函数. 对对象进行签名
 *
 * @author: Foy Lian
 * Date: 12/7/2018
 * Time: 5:59 PM
 */
@Slf4j
public class SignUtils {
    /**
     * json对象工具
     */
    private static ObjectMapper mapper = new ObjectMapper();
    /**
     * 是否控制台打印日志
     */
    public static boolean printOnConsole = false;
    /**
     * BigDecimal统一成18位精度进行签名
     */
    private static int precision = 18;
    /**
     * 签名时需要忽略的字段
     */
    private static Set<String> DEFAULT_IGNORE_PROPERTIES = new HashSet() {{
        add("sign");
    }};

    /**
     * 生成签名
     * 签名方法:
     * 1. 把signObjects里的属性按字母序排序
     * 2.拼接成key=value&key=value形式 （过滤掉ignoreProperties包含的属性)
     * 3.最后拼接上&signKey
     * 4. 字符串进行MD5散列
     * 5. Base64编码散列值
     * 5.返回结果
     *
     * @param signKey          散列时使用的盐
     * @param ignoreProperties 需要忽略的字段属性
     * @param signObjects      需要参与签名的对象
     * @return 签名后的散列值
     */
    public static String sign(String signKey, Set<String> ignoreProperties, Object... signObjects) {
        Preconditions.checkArgument(null != signObjects && signObjects.length > 0, "sign objects should not be empty");
        Map<String, Object> signProperties = new HashMap<>(100);
        Arrays.stream(signObjects).forEach(o -> signProperties.putAll(mapper.convertValue(o, Map.class)));
        String originString = (signProperties.entrySet()
                .stream()
                .sorted(Comparator.comparing(e -> e.getKey()))
                .filter(e -> ignoreProperties == null || !ignoreProperties.contains(e.getKey()))
                .map(e -> {
                    Object val = e.getValue();
                    if (val instanceof Number) {
                        return e.getKey() + "=" + (new BigDecimal(val.toString())).setScale(precision).toString();
                    } else {
                        return e.getKey() + "=" + e.getValue().toString();
                    }
                })
                .collect(Collectors.joining("&")))
                + "&" + signKey;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(originString.getBytes());
            String result = Base64.getUrlEncoder().encodeToString(md5.digest());
            if (log.isDebugEnabled()) {
                log.debug(String.format("originString:%s, sign result: %s",
                        originString.substring(0, originString.lastIndexOf("&")) + "#signKey#",
                        result));
            }
            if (printOnConsole) {
                System.out.println(
                        String.format("originString:%s, sign result: %s",
                                originString.substring(0, originString.lastIndexOf("&")) + "#signKey#",
                                result));
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            log.error("error sign", e);
        }
        throw new RuntimeException("can't get sign with signKey," + signKey);
    }

    /**
     * 生成签名
     * 签名方法:
     * 1. 把signObjects里的属性按字母序排序
     * 2.拼接成key=value&key=value形式 （过滤掉ignoreProperties包含的属性)
     * 3.最后拼接上&signKey
     * 4. 字符串进行MD5散列
     * 5. Base64编码散列值
     * 5.返回结果
     *
     * @param signKey     散列时使用的盐
     * @param signObjects 需要参与签名的对象
     * @return 签名后的散列值
     */
    public static String sign(String signKey, Object... signObjects) {
        return sign(signKey, DEFAULT_IGNORE_PROPERTIES, signObjects);
    }

    /**
     * 验证对象签名后的值是否匹配
     *
     * @param expectedSign     期望得到的值
     * @param signKey          签名使用的盐
     * @param ignoreProperties 需要忽略的字段
     * @param signObjects      参与签名的对象
     * @return true：签名匹配,false:签名不匹配
     */
    public static boolean verify(String expectedSign, String signKey, Set<String> ignoreProperties, Object... signObjects) {
        return sign(signKey, ignoreProperties, signObjects).equals(expectedSign);
    }

    /**
     * 验证对象签名后的值是否匹配
     *
     * @param expectedSign 期望得到的值
     * @param signKey      签名使用的盐
     * @param signObjects  参与签名的对象
     * @return true：签名匹配,false:签名不匹配
     */
    public static boolean verify(String expectedSign, String signKey, Object... signObjects) {
        return sign(signKey, DEFAULT_IGNORE_PROPERTIES, signObjects).equals(expectedSign);
    }
}
