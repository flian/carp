package org.lotus.carp.base.config;

import org.apache.logging.log4j.util.Strings;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 当前用户工具类
 *
 * @author: Foy Lian
 * Date: 2/18/2019
 * Time: 10:50 AM
 */
public class CurrentUser {
    private static final String CURRENT_USER_THEME_KEY = "_current_user_theme_string";

    /**
     * 当前请求
     *
     * @return
     */
    public static HttpServletRequest request() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        return request;
    }

    /**
     * 当前用户的页面风格
     *
     * @return
     */
    public static String theme() {
        return (String) request().getSession().getAttribute(CURRENT_USER_THEME_KEY);
    }

    /**
     * 设置当前用户的页面风格
     *
     * @param theme
     */
    public static void updateTheme(String theme) {
        if (Strings.isNotEmpty(theme)) {
            request().getSession().setAttribute(CURRENT_USER_THEME_KEY, theme);
        }
    }

    /**
     * 获取给定请求的ip
     *
     * @param request
     * @return
     */
    public static String ip(HttpServletRequest request) {
        if (request != null) {
            String remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (Strings.isNotEmpty(remoteAddr)) {
                return remoteAddr;
            }
        }
        return request.getRemoteAddr();
    }

    /**
     * 获取当前调用者的ip地址
     *
     * @return ip地址
     */
    public static String ip() {
        return ip(request());
    }

    /**
     * 获取给定请求的浏览器信息
     *
     * @param request
     * @return
     */
    public static String userAgent(HttpServletRequest request) {
        return request.getHeader("User-Agent");
    }

    /**
     * 获取当前操作人的userAgent信息
     *
     * @return userAgent信息
     */
    public static String userAgent() {
        return userAgent(request());
    }

}
